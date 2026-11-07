package dev.simon4i4a.ntmae2bridge.menu;

import dev.simon4i4a.ntmae2bridge.config.BridgeConfig;
import dev.simon4i4a.ntmae2bridge.registry.ModBlocks;
import dev.simon4i4a.ntmae2bridge.registry.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import dev.simon4i4a.ntmae2bridge.blockentity.EnergyConverterBlockEntity;

public class EnergyConverterMenu extends AbstractContainerMenu {
    private static final int DATA_COUNT = 6;

    private final ContainerLevelAccess access;
    private final ContainerData data;

    // Client-side constructor. Values are filled by menu data synchronization.
    public EnergyConverterMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, null, ContainerLevelAccess.NULL);
    }

    // Server-side constructor.
    public EnergyConverterMenu(
            int containerId,
            Inventory inventory,
            EnergyConverterBlockEntity converter,
            ContainerLevelAccess access) {
        super(ModMenus.ENERGY_CONVERTER.get(), containerId);
        this.access = access;
        this.data = converter == null ? new SimpleContainerData(DATA_COUNT) : new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> (int) Math.min(Integer.MAX_VALUE, Math.round(converter.getBufferedHe() * 10.0));
                    case 1 -> (int) Math.min(Integer.MAX_VALUE, converter.getBufferCapacity());
                    case 2 -> converter.isConnectedToMeNetwork() ? 1 : 0;
                    case 3 -> (int) Math.round(BridgeConfig.AE_PER_HE.get() * 1000.0);
                    case 4 -> BridgeConfig.MAX_TRANSFER.get();
                    case 5 -> (int) Math.round(BridgeConfig.ENERGY_LOSS.get() * 1000.0);
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                // The converter is read-only from this status screen.
            }

            @Override
            public int getCount() {
                return DATA_COUNT;
            }
        };
        addDataSlots(this.data);
    }

    public double getBufferedHe() {
        return data.get(0) / 10.0;
    }

    public int getBufferCapacity() {
        return data.get(1);
    }

    public boolean isConnected() {
        return data.get(2) != 0;
    }

    public int getAePerHeThousandths() {
        return data.get(3);
    }

    public int getMaxTransfer() {
        return data.get(4);
    }

    public int getEnergyLossThousandths() {
        return data.get(5);
    }

    @Override
    public boolean stillValid(Player player) {
        return AbstractContainerMenu.stillValid(access, player, ModBlocks.ENERGY_CONVERTER.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
