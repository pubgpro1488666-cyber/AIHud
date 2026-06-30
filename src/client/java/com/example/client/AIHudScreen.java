package com.example.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class AIHudScreen extends Screen {
    public AIHudScreen() {
        super(Text.literal("AIHud"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("VISUAL"), button -> {
            this.client.setScreen(new VisualsScreen(this));
        }).dimensions(centerX - 150, centerY - 30, 110, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("MUSIC"), button -> {
            this.client.setScreen(new MusicScreen(this));
        }).dimensions(centerX - 150, centerY, 110, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("BACK"), button -> {
            this.client.setScreen(null);
        }).dimensions(centerX - 150, centerY + 30, 110, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        context.fill(centerX - 170, centerY - 50, centerX + 170, centerY + 70, 0x88000000);
        context.drawCenteredTextWithShadow(this.textRenderer, "AI-HUD MOD MENU", centerX, centerY - 45, 0xFFFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
