package com.example;

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

        // Кнопка VISUAL (соответствует макету)
        this.addDrawableChild(ButtonWidget.builder(Text.literal("VISUAL"), button -> {
            this.client.setScreen(new VisualsScreen(this));
        }).dimensions(centerX - 150, centerY - 30, 110, 20).build());

        // Кнопка MUSIC (соответствует макету)
        this.addDrawableChild(ButtonWidget.builder(Text.literal("MUSIC"), button -> {
            this.client.setScreen(new MusicScreen(this));
        }).dimensions(centerX - 150, centerY, 110, 20).build());

        // Кнопка COMING SOON... (соответствует макету)
        this.addDrawableChild(ButtonWidget.builder(Text.literal("COMING SOON..."), button -> {
            // Заглушка для будущих функций
        }).dimensions(centerX - 150, centerY + 30, 110, 20).build());
        
        // Кнопка закрытия меню
        this.addDrawableChild(ButtonWidget.builder(Text.literal("CLOSE"), button -> {
            this.close();
        }).dimensions(centerX + 60, centerY + 45, 80, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Рисуем рамку в стиле хай-тек/футуризма, как на твоем изображении
        context.fill(centerX - 170, centerY - 70, centerX + 160, centerY + 75, 0xDD0D0D0D); // Темный фон
        context.drawBorder(centerX - 170, centerY - 70, 330, 145, 0xFFFFFFFF); // Бело-серая рамка
        
        // Маленькие декоративные точки/линии по углам (стиль HUD)
        context.fill(centerX - 173, centerY - 70, centerX - 170, centerY - 60, 0xFFFFFFFF);
        context.fill(centerX + 160, centerY + 65, centerX + 163, centerY + 75, 0xFFFFFFFF);

        // Крупный заголовок "A I H u d" в левом верхнем углу рамки
        context.drawText(this.textRenderer, "A I H u d", centerX - 150, centerY - 55, 0xFFFFFFFF, false);

        super.render(context, mouseX, mouseY, delta);
    }
}
