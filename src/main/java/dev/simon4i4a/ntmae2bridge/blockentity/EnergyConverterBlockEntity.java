package dev.simon4i4a.ntmae2bridge.blockentity;

import appeng.api.config.Actionable;
import appeng.api.networking.IGrid;
import appeng.api.networking.energy.IEnergyService;
import appeng.blockentity.grid.AENetworkedBlockEntity;
import com.hbm.api.energymk2.IEnergyHandlerMK2;
import dev.simon4i4a.ntmae2bridge.config.BridgeConfig;
import dev.simon4i4a.ntmae2bridge.energy.EnergyConversionMath;
import dev.simon4i4a.ntmae2bridge.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyConverterBlockEntity extends AENetworkedBlockEntity implements IEnergyHandlerMK2 {
    private static final double EPSILON = 1.0E-9;

    // Stored as a double so partial AE2 acceptance never discards fractional HE.
    private double heBuffer;

    public EnergyConverterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENERGY_CONVERTER.get(), pos, state);
    }

    public double getBufferedHe() {
        clampBufferToCapacity();
        return heBuffer;
    }

    private void clampBufferToCapacity() {
        double capacity = BridgeConfig.BUFFER_CAPACITY.get();
        if (heBuffer > capacity) {
            heBuffer = capacity;
            energyChanged();
        } else if (heBuffer < 0.0) {
            heBuffer = 0.0;
            energyChanged();
        }
    }

    public double getBufferCapacity() {
        return BridgeConfig.BUFFER_CAPACITY.get();
    }

    public boolean isConnectedToMeNetwork() {
        return getMainNode().getGrid() != null;
    }

    public static void serverTick(
            Level level, BlockPos pos, BlockState state, EnergyConverterBlockEntity blockEntity) {
        if (level.isClientSide) {
            return;
        }
        blockEntity.clampBufferToCapacity();
        if (blockEntity.heBuffer <= EPSILON) {
            return;
        }

        IGrid grid = blockEntity.getMainNode().getGrid();
        if (grid == null) {
            return;
        }

        double conversionRate = EnergyConversionMath.effectiveRate(
                BridgeConfig.AE_PER_HE.get(),
                BridgeConfig.ENERGY_LOSS.get()
        );
        if (conversionRate <= EPSILON) {
            return;
        }

        double heToOffer = Math.min(blockEntity.heBuffer, BridgeConfig.MAX_TRANSFER.get());
        double aeToOffer = EnergyConversionMath.outputForHe(heToOffer, conversionRate);
        if (aeToOffer <= EPSILON) {
            return;
        }

        IEnergyService energy = grid.getEnergyService();

        // Simulate first so a full ME network does not consume any HE.
        double simulatedRemainder = energy.injectPower(aeToOffer, Actionable.SIMULATE);
        double simulatedAccepted = Math.max(0.0, aeToOffer - simulatedRemainder);
        if (simulatedAccepted <= EPSILON) {
            return;
        }

        // Only debit the energy the ME network actually accepted.
        double actualRemainder = energy.injectPower(aeToOffer, Actionable.MODULATE);
        double actuallyAccepted = Math.max(0.0, aeToOffer - actualRemainder);
        if (actuallyAccepted > EPSILON) {
            double consumedHe = EnergyConversionMath.heForAcceptedAe(
                    actuallyAccepted, conversionRate, blockEntity.heBuffer
            );
            blockEntity.heBuffer -= consumedHe;
            if (blockEntity.heBuffer < EPSILON) {
                blockEntity.heBuffer = 0.0;
            }
            blockEntity.energyChanged();
        }
    }

    @Override
    public long getPower() {
        clampBufferToCapacity();
        return (long) Math.floor(heBuffer);
    }

    @Override
    public void setPower(long power) {
        clampBufferToCapacity();
        double capacity = BridgeConfig.BUFFER_CAPACITY.get();
        double updated = Math.max(0.0, Math.min(power, capacity));
        if (Math.abs(updated - heBuffer) > EPSILON) {
            heBuffer = updated;
            energyChanged();
        }
    }

    @Override
    public long getMaxPower() {
        return BridgeConfig.BUFFER_CAPACITY.get();
    }

    @Override
    public long getReceiverSpeed() {
        return BridgeConfig.MAX_TRANSFER.get();
    }

    @Override
    public long getProviderSpeed() {
        return 0;
    }

    @Override
    public boolean allowDirectProvision() {
        return false;
    }

    @Override
    public long transferPower(long amount, boolean simulate) {
        clampBufferToCapacity();
        if (amount <= 0) {
            return 0;
        }

        double room = Math.max(0.0, BridgeConfig.BUFFER_CAPACITY.get() - heBuffer);
        long accepted = (long) Math.floor(Math.min((double) amount, room) + EPSILON);
        if (!simulate && accepted > 0) {
            heBuffer += accepted;
            energyChanged();
        }
        // HBM expects the amount that could NOT be inserted.
        return amount - accepted;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putDouble("HeBuffer", heBuffer);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        heBuffer = Math.max(0.0, Math.min(
                tag.getDouble("HeBuffer"),
                BridgeConfig.BUFFER_CAPACITY.get()
        ));
    }
}
