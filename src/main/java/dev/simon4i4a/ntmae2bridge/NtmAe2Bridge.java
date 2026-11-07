package dev.simon4i4a.ntmae2bridge;

import com.hbm.api.energymk2.EnergyCaps;
import dev.simon4i4a.ntmae2bridge.config.BridgeConfig;
import dev.simon4i4a.ntmae2bridge.blockentity.EnergyConverterBlockEntity;
import dev.simon4i4a.ntmae2bridge.registry.ModBlockEntities;
import dev.simon4i4a.ntmae2bridge.registry.ModBlocks;
import dev.simon4i4a.ntmae2bridge.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@Mod(NtmAe2Bridge.MOD_ID)
public class NtmAe2Bridge {
    public static final String MOD_ID = "ntm_ae2_bridge";

    public NtmAe2Bridge(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);

        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.COMMON, BridgeConfig.SPEC);
        modEventBus.addListener(this::registerCapabilities);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlock(
                EnergyCaps.RECEIVER,
                (level, pos, state, blockEntity, side) ->
                        blockEntity instanceof EnergyConverterBlockEntity converter ? converter : null,
                ModBlocks.ENERGY_CONVERTER.get()
        );
    }
}
