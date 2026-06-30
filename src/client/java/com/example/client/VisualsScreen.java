package com.example.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class VisualsScreen extends Screen {
    private final Screen parent;

    public VisualsScreen(Screen parent) {
        super(Text.literal("Visuals"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Main HUD: " + (ModSettings.hudEnabled ? "ON" : "OFF")), button -> {
            ModSettings.hudEnabled = !ModSettings.hudEnabled;
            button.setMessage(Text.literal("Main HUD: " + (ModSettings.hudEnabled ? "ON" : "OFF")));
        }).dimensions(centerX - 100, centerY - 40, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Armor HUD: " + (ModSettings.armorHudEnabled ? "ON" : "OFF")), button -> {
            ModSettings.armorHudEnabled = !ModSettings.armorHudEnabled;
            button.setMessage(Text.literal("Armor HUD: " + (ModSettings.armorHudEnabled ? "ON" : "OFF")));
        }).dimensions(centerX - 100, centerY - 15, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("KeyStrokes: " + (ModSettings.keyStrokesEnabled ? "ON" : "OFF")), button -> {
            ModSettings.keyStrokesEnabled = !ModSettings.keyStrokesEnabled;
            button.setMessage(Text.literal("KeyStrokes: " + (ModSettings.keyStrokesEnabled ? "ON" : "OFF")));
        }).dimensions(centerX - 100, centerY + 10, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Back"), button -> {
            this.client.setScreen(this.parent);
        }).dimensions(centerX - 50, centerY + 40, 100, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        context.fill(centerX - 120, centerY - 65, centerX + 120, centerY + 75, 0xDD0D0D0D);
        context.drawBorder(centerX - 120, centerY - 65, 240, 140, 0xFFFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, "VISUAL CONFIG", centerX, centerY - 55, 0xFFFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
