package dev.simon4i4a.ntmae2bridge.registry;

import dev.simon4i4a.ntmae2bridge.NtmAe2Bridge;
import dev.simon4i4a.ntmae2bridge.menu.EnergyConverterMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.flag.FeatureFlags;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, NtmAe2Bridge.MOD_ID);

    public static final Supplier<MenuType<EnergyConverterMenu>> ENERGY_CONVERTER =
            MENUS.register("energy_converter", () ->
                    new MenuType<>(EnergyConverterMenu::new, FeatureFlags.DEFAULT_FLAGS));

    private ModMenus() {
    }
}
