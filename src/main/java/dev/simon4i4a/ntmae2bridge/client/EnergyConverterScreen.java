package dev.simon4i4a.ntmae2bridge.client;

import dev.simon4i4a.ntmae2bridge.menu.EnergyConverterMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.Locale;

public class EnergyConverterScreen extends AbstractContainerScreen<EnergyConverterMenu> {
    public EnergyConverterScreen(EnergyConverterMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        imageWidth = 176;
        imageHeight = 148;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int x = leftPos;
        int y = topPos;

        graphics.fill(x, y, x + imageWidth, y + imageHeight, 0xFF111820);
        graphics.fill(x + 1, y + 1, x + imageWidth - 1, y + imageHeight - 1, 0xFF202C36);
        graphics.fill(x + 8, y + 31, x + imageWidth - 8, y + 58, 0xFF101820);

        int capacity = Math.max(1, menu.getBufferCapacity());
        int buffered = Math.max(0, menu.getBufferedHe());
        int barWidth = (int) Math.min(150.0, 150.0 * buffered / capacity);
        graphics.fill(x + 13, y + 43, x + 163, y + 51, 0xFF080D12);
        if (barWidth > 0) {
            graphics.fill(x + 13, y + 43, x + 13 + barWidth, y + 51, 0xFF55C8D8);
        }

        graphics.fill(x + 8, y + 65, x + imageWidth - 8, y + 88, 0xFF101820);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawString(font, title, 8, 8, 0xFFEAF2F5, false);
        graphics.drawString(
                font,
                Component.translatable(
                        "gui.ntm_ae2_bridge.buffer",
                        menu.getBufferedHe(),
                        menu.getBufferCapacity()
                ),
                13,
                20,
                0xFFEAF2F5,
                false
        );
        graphics.drawString(
                font,
                Component.translatable(
                        menu.isConnected()
                                ? "gui.ntm_ae2_bridge.connected"
                                : "gui.ntm_ae2_bridge.disconnected"
                ),
                13,
                73,
                menu.isConnected() ? 0xFF72D69A : 0xFFE6A36B,
                false
        );
        graphics.drawString(
                font,
                Component.translatable(
                        "gui.ntm_ae2_bridge.ratio",
                        String.format(Locale.ROOT, "%.3f", menu.getAePerHeThousandths() / 1000.0)
                ),
                13,
                96,
                0xFFEAF2F5,
                false
        );
        graphics.drawString(
                font,
                Component.translatable("gui.ntm_ae2_bridge.limit", menu.getMaxTransfer()),
                13,
                109,
                0xFFEAF2F5,
                false
        );
        graphics.drawString(
                font,
                Component.translatable(
                        "gui.ntm_ae2_bridge.loss",
                        String.format(Locale.ROOT, "%.1f", menu.getEnergyLossThousandths() / 10.0)
                ),
                13,
                122,
                0xFFEAF2F5,
                false
        );
        graphics.drawString(
                font,
                Component.translatable("gui.ntm_ae2_bridge.status_hint"),
                13,
                136,
                0xFF9AAAB4,
                false
        );
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics, mouseX, mouseY, partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
