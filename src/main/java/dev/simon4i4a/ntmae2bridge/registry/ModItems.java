package dev.simon4i4a.ntmae2bridge.registry;

import dev.simon4i4a.ntmae2bridge.NtmAe2Bridge;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NtmAe2Bridge.MOD_ID);

    public static final DeferredItem<BlockItem> ENERGY_CONVERTER = ITEMS.register(
            "energy_converter",
            () -> new BlockItem(ModBlocks.ENERGY_CONVERTER.get(), new net.minecraft.world.item.Item.Properties())
    );

    private ModItems() {
    }
}
