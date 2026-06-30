package com.example;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

public class HudRenderer {
    public static void render(DrawContext context, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        int screenHeight = context.getScaledWindowHeight();
        int screenWidth = context.getScaledWindowWidth();

        // 1. Левый верхний инфо-блок (FPS и Координаты)
        if (ModSettings.hudEnabled) {
            int fps = client.getCurrentFps();
            String xyz = String.format("XYZ: %.0f, %.0f, %.0f", client.player.getX(), client.player.getY(), client.player.getZ());

            context.fill(5, 5, 120, 35, 0x66000000);
            context.drawBorder(5, 5, 115, 30, 0xFFFFFFFF);
            context.drawText(client.textRenderer, "FPS: " + fps, 10, 10, 0xFFFFFF00, true);
            context.drawText(client.textRenderer, xyz, 10, 22, 0xFFFFFFFF, true);
        }

        // 2. Armor HUD (Броня слева на экране)
        if (ModSettings.armorHudEnabled) {
            int startX = 10;
            int startY = screenHeight / 2 - 40;
            for (int i = 3; i >= 0; i--) {
                ItemStack armorStack = client.player.getInventory().armor.get(i);
                if (!armorStack.isEmpty()) {
                    context.drawItem(armorStack, startX, startY);
                    context.drawItemInSlot(client.textRenderer, armorStack, startX, startY);
                    startY += 18;
                }
            }
        }

        // 3. KeyStrokes (Клавиши WASD справа вверху)
        if (ModSettings.keyStrokesEnabled) {
            int rightX = screenWidth - 75;
            int rightY = 10;

            drawKey(context, rightX + 22, rightY, 20, 20, "W", client.options.forwardKey.isPressed());
            drawKey(context, rightX, rightY + 22, 20, 20, "A", client.options.leftKey.isPressed());
            drawKey(context, rightX + 22, rightY + 22, 20, 20, "S", client.options.backKey.isPressed());
            drawKey(context, rightX + 44, rightY + 22, 20, 20, "D", client.options.rightKey.isPressed());
        }
    }

    private static void drawKey(DrawContext context, int x, int y, int w, int h, String key, boolean isPressed) {
        int bgColor = isPressed ? 0xFFFFFFFF : 0x66000000;
        int textColor = isPressed ? 0xFF000000 : 0xFFFFFFFF;
        context.fill(x, y, x + w, y + h, bgColor);
        context.drawBorder(x, y, w, h, 0xFFFFFFFF);
        MinecraftClient client = MinecraftClient.getInstance();
        int textW = client.textRenderer.getWidth(key);
        context.drawText(client.textRenderer, key, x + (w - textW) / 2, y + (h - 8) / 2, textColor, false);
    }
}
