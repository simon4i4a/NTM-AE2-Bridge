package dev.simon4i4a.ntmae2bridge.blockentity;

import dev.simon4i4a.ntmae2bridge.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyConverterBlockEntity extends BlockEntity {
    public EnergyConverterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENERGY_CONVERTER.get(), pos, state);
    }
}
