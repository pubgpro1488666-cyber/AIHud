package com.example.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class MusicScreen extends Screen {
    private final Screen parent;

    public MusicScreen(Screen parent) {
        super(Text.literal("Music"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Background Music: " + (ModSettings.customMusicEnabled ? "CUSTOM" : "VANILLA")), button -> {
            ModSettings.customMusicEnabled = !ModSettings.customMusicEnabled;
            button.setMessage(Text.literal("Background Music: " + (ModSettings.customMusicEnabled ? "CUSTOM" : "VANILLA")));
        }).dimensions(centerX - 100, centerY - 10, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Back"), button -> {
            this.client.setScreen(this.parent);
        }).dimensions(centerX - 50, centerY + 25, 100, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        context.fill(centerX - 120, centerY - 40, centerX + 120, centerY + 60, 0xDD0D0D0D);
        context.drawBorder(centerX - 120, centerY - 40, 240, 100, 0xFFFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, "MUSIC SYSTEM", centerX, centerY - 30, 0xFFFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
