package dev.simon4i4a.ntmae2bridge.registry;

import dev.simon4i4a.ntmae2bridge.NtmAe2Bridge;
import dev.simon4i4a.ntmae2bridge.blockentity.EnergyConverterBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NtmAe2Bridge.MOD_ID);

    public static final Supplier<BlockEntityType<EnergyConverterBlockEntity>> ENERGY_CONVERTER =
            BLOCK_ENTITY_TYPES.register("energy_converter",
                    () -> BlockEntityType.Builder.of(
                            EnergyConverterBlockEntity::new,
                            ModBlocks.ENERGY_CONVERTER.get()
                    ).build(null));

    private ModBlockEntities() {
    }
}
