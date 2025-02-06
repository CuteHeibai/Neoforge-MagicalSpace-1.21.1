package net.magicalspace.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class VillagerStarlightDialogScreen extends Screen {
    private Component title;
    private Component content;

    public VillagerStarlightDialogScreen() {
        super(Component.translatable("magicalspace.dialog.title")); // 使用本地化键作为标题
        this.title = Component.translatable("magicalspace.dialog.title");
        this.content = Component.translatable("magicalspace.dialog.content");
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft == null) {
            throw new IllegalStateException("Minecraft instance is null");
        }

        int windowWidth = minecraft.getWindow().getGuiScaledWidth();
        int windowHeight = minecraft.getWindow().getGuiScaledHeight();

        int dialogueHeight = windowHeight / 2;
        int dialogueWidth = windowWidth;

        int x = 0;
        int y = windowHeight - dialogueHeight;

        // 绘制半透明背景
        guiGraphics.fill(x, y, x + dialogueWidth, y + dialogueHeight, 0x80FFFFFF);

        // 绘制边框
        guiGraphics.fill(x, y, x + dialogueWidth, y + 2, 0xFF000000); // 上边框
        guiGraphics.fill(x, y, x + 2, y + dialogueHeight, 0xFF000000); // 左边框
        guiGraphics.fill(x + dialogueWidth - 2, y, x + dialogueWidth, y + dialogueHeight, 0xFF000000); // 右边框
        guiGraphics.fill(x, y + dialogueHeight - 2, x + dialogueWidth, y + dialogueHeight, 0xFF000000); // 下边框

        // 绘制文本
        int titleX = (windowWidth - minecraft.font.width(title)) / 2;
        int titleY = y + 20;
        guiGraphics.drawString(minecraft.font, title.getString(), titleX, titleY, 0xFFFFFF, true);

        int contentX = (windowWidth - minecraft.font.width(content)) / 2;
        int contentY = y + 50;
        guiGraphics.drawString(minecraft.font, content.getString(), contentX, contentY, 0xFFFFFF, true);
    }

    @Override
    public boolean isPauseScreen() {
        return false; // 不暂停游戏
    }

    public static void open(ServerPlayer player) {
        Minecraft.getInstance().execute(() -> {
            VillagerStarlightDialogScreen screen = new VillagerStarlightDialogScreen();
            Minecraft.getInstance().setScreen(screen);
        });
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true; // 当按下 ESC 键时关闭对话框
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) { // 256 是 ESC 键的键码
            Minecraft.getInstance().setScreen(null); // 关闭对话框
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}