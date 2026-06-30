package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ExampleMod implements ClientModInitializer {
    private static KeyBinding menuKey;

    @Override
    public void onInitializeClient() {
        // Регистрация кнопки открытия меню (Правый Shift)
        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.aihud.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.aihud"
        ));

        // Отслеживание нажатия кнопки
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && menuKey.wasPressed()) {
                client.setScreen(new AIHudScreen());
            }
        });

        // Регистрация отрисовщика HUD
        HudRenderCallback.EVENT.register(HudRenderer::render);
    }
}
