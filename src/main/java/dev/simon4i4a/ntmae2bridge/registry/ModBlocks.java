package dev.simon4i4a.ntmae2bridge.registry;

import dev.simon4i4a.ntmae2bridge.NtmAe2Bridge;
import dev.simon4i4a.ntmae2bridge.block.EnergyConverterBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(NtmAe2Bridge.MOD_ID);

    public static final DeferredBlock<Block> ENERGY_CONVERTER = BLOCKS.register(
            "energy_converter",
            () -> new EnergyConverterBlock(BlockBehaviour.Properties.of().strength(3.5F).requiresCorrectToolForDrops())
    );

    private ModBlocks() {
    }
}
