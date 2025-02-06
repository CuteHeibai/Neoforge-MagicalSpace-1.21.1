package net.magicalspace.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class VillagerStarlightDialogScreen extends Screen {
    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath("magicalspace", "textures/gui/villager_starlight_dialog.png");
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

        // 绘制背景图片
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        guiGraphics.blit(BACKGROUND_TEXTURE, 0, 0, 0, 0, windowWidth, windowHeight, windowWidth, windowHeight);
//        RenderSystem.disableBlend();

        // 绘制文本
        int titleX = (windowWidth - minecraft.font.width(title)) / 2;
        int titleY = 20;
        guiGraphics.drawString(minecraft.font, title.getString(), titleX, titleY, 0xFFFFFF, true);

        int contentX = (windowWidth - minecraft.font.width(content)) / 2;
        int contentY = 50;
        guiGraphics.drawString(minecraft.font, content.getString(), contentX, contentY, 0xFFFFFF, true);

        // 其他自定义渲染逻辑
        // ...
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