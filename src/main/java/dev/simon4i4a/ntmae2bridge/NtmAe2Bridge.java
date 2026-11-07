package dev.simon4i4a.ntmae2bridge;

import dev.simon4i4a.ntmae2bridge.registry.ModBlockEntities;
import dev.simon4i4a.ntmae2bridge.registry.ModBlocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NtmAe2Bridge.MOD_ID)
public class NtmAe2Bridge {
    public static final String MOD_ID = "ntm_ae2_bridge";

    public NtmAe2Bridge(IEventBus modEventBus) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
    }
}
