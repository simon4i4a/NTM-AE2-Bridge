package dev.simon4i4a.ntmae2bridge.block;

import dev.simon4i4a.ntmae2bridge.blockentity.EnergyConverterBlockEntity;
import dev.simon4i4a.ntmae2bridge.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

public class EnergyConverterBlock extends BaseEntityBlock {
    public EnergyConverterBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EnergyConverterBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state, Level level, BlockPos pos, Player player) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof EnergyConverterBlockEntity converter) {
            Component networkStatus = Component.translatable(
                    converter.isConnectedToMeNetwork()
                            ? "message.ntm_ae2_bridge.connected"
                            : "message.ntm_ae2_bridge.disconnected"
            );
            player.displayClientMessage(
                    Component.translatable(
                            "message.ntm_ae2_bridge.converter_status",
                            String.format("%.1f", converter.getBufferedHe()),
                            String.format("%.0f", converter.getBufferCapacity()),
                            networkStatus
                    ),
                    false
            );
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(
            Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide
                ? null
                : createTickerHelper(
                        type,
                        ModBlockEntities.ENERGY_CONVERTER.get(),
                        EnergyConverterBlockEntity::serverTick
                );
    }
}
