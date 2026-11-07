package dev.simon4i4a.ntmae2bridge.registry;

import dev.simon4i4a.ntmae2bridge.NtmAe2Bridge;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public final class ModCreativeTabs {
    private ModCreativeTabs() {
    }

    public static void register(net.neoforged.bus.api.IEventBus modEventBus) {
        modEventBus.addListener(ModCreativeTabs::addToFunctionalBlocks);
    }

    private static void addToFunctionalBlocks(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModItems.ENERGY_CONVERTER);
        }
    }
}
