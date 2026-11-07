package dev.simon4i4a.ntmae2bridge.client;

import dev.simon4i4a.ntmae2bridge.NtmAe2Bridge;
import dev.simon4i4a.ntmae2bridge.registry.ModMenus;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = NtmAe2Bridge.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.ENERGY_CONVERTER.get(), EnergyConverterScreen::new);
    }
}
