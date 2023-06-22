package net.pulga22.pulgacore.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.util.render2d.Pivot;
import net.pulga22.pulgacore.util.render2d.PivotedRender;

public abstract class Render2D {

    /**
     * @param drawContext DrawContext of the render method.
     * @param color Color to fill.
     */
    public static void fillFullScreen(DrawContext drawContext, int color){
        drawContext.fill(0, 0, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), color);
    }

    /**
     * Renders a texture fullscreen.
     * @param drawContext DrawContext of the render method.
     * @param identifier The identifier of the texture.
     */
    public static void renderFullScreen(DrawContext drawContext, Identifier identifier){
        setDefaultColor(drawContext);
        renderTexture(drawContext, identifier, 0, 0, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), false);
    }


    /**
     * @param drawContext DrawContext of the render method.
     * @param identifier The identifier of the texture.
     * @param x X position.
     * @param y Y position.
     * @param width Width of the render.
     * @param height Height of the render.
     * @param centered If the position should be in the center of the render.
     */
    public static void renderTexture(DrawContext drawContext, Identifier identifier, int x, int y, int width, int height, boolean centered){
        setDefaultColor(drawContext);
        if (centered){
            int offsetX = width / 2;
            int offsetY = height / 2;
            drawContext.drawTexture(identifier, x - offsetX, y - offsetY, 0, 0, width, height);
        } else {
            drawContext.drawTexture(identifier, x, y, 0, 0, width, height);
        }

    }

    /**
     * Render a texture with a color overlay.
     * @param drawContext DrawContext of the render method.
     * @param identifier The identifier of the texture.
     * @param x X position.
     * @param y Y position.
     * @param width Width of the render.
     * @param height Height of the render.
     * @param color Color of the overlay.
     * @param centered If the position should be in the center of the render.
     */
    public static void renderTextureWithColor(DrawContext drawContext, Identifier identifier, int x, int y, int width, int height, int color, boolean centered){
        float a = ((color>>24)&0xFF) / 255f;
        float r = ((color>>16)&0xFF) / 255f;
        float g = ((color>>8)&0xFF) / 255f;
        float b = ((color)&0xFF) / 255f;
        drawContext.setShaderColor(r, g, b, a);
        renderTexture(drawContext, identifier, x, y, width, height, centered);
    }

    /**
     * Renders correctly a texture which contains transparencies.
     * @param drawContext DrawContext of the render method.
     * @param identifier The identifier of the texture.
     * @param x X position.
     * @param y Y position.
     * @param width Width of the render.
     * @param height Height of the render.
     * @param centered If the position should be in the center of the render.
     */
    public static void renderTextureWithTransparencies(DrawContext drawContext, Identifier identifier, int x, int y, int width, int height, boolean centered){
        RenderSystem.enableBlend();
        setDefaultColor(drawContext);
        renderTexture(drawContext, identifier, x, y, width, height, centered);
    }

    /**
     * Renders correctly a texture which contains transparencies with a color overlay.
     * @param drawContext DrawContext of the render method.
     * @param identifier The identifier of the texture.
     * @param x X position.
     * @param y Y position.
     * @param width Width of the render.
     * @param height Height of the render.
     * @param color Color of the overlay.
     * @param centered If the position should be in the center of the render.
     */
    public static void renderTextureWithTransparenciesAndColor(DrawContext drawContext, Identifier identifier, int x, int y, int width, int height, int color, boolean centered){
        RenderSystem.enableBlend();
        float a = ((color>>24)&0xFF) / 255f;
        float r = ((color>>16)&0xFF) / 255f;
        float g = ((color>>8)&0xFF) / 255f;
        float b = ((color)&0xFF) / 255f;
        drawContext.setShaderColor(r, g, b, a);
        renderTexture(drawContext, identifier, x, y, width, height, centered);
    }


    /**
     * @param client The minecraft client instance.
     * @param drawContext DrawContext of the render method.
     * @param text The text to display.
     * @param x X position.
     * @param y Y Position.
     * @param color Color of the text.
     * @param centered If the position should be in the center of the render.
     * @param shadow Indicates if the text should render with a shadow.
     */
    public static void renderText(MinecraftClient client, DrawContext drawContext, String text, int x, int y, int color, boolean centered, boolean shadow){
        if (centered){
            drawContext.drawText(client.textRenderer, text, x, y, color, shadow);
        } else {
            int offset = client.textRenderer.getWidth(text);
            drawContext.drawText(client.textRenderer, text, x - offset, y, color, shadow);
        }

    }

    /**
     * @param client The minecraft client instance.
     * @param drawContext DrawContext of the render method.
     * @param text The text to display.
     * @param x X position.
     * @param y Y Position.
     * @param color Color of the text.
     * @param centered If the position should be in the center of the render.
     */
    public static void renderTextWithoutShadows(MinecraftClient client, DrawContext drawContext, String text, int x, int y, int color, boolean centered){
        renderText(client, drawContext, text, x, y, color, centered, false);
    }

    /**
     * @param client The minecraft client instance.
     * @param drawContext DrawContext of the render method.
     * @param text The text to display.
     * @param x X position.
     * @param y Y Position.
     * @param color Color of the text.
     * @param centered If the position should be in the center of the render.
     */
    public static void renderTextWithShadows(MinecraftClient client, DrawContext drawContext, String text, int x, int y, int color, boolean centered){
        renderText(client, drawContext, text, x, y, color, centered, true);
    }

    /**
     * @param client The minecraft client instance.
     * @param context DrawContext of the render method.
     * @param pivot The pivot.
     * @return A PivotedRender object.
     */
    public static PivotedRender PIVOTED(MinecraftClient client, DrawContext context, Pivot pivot){
        return new PivotedRender(client, context, pivot);
    }

    private static void setDefaultColor(DrawContext drawContext){
        drawContext.setShaderColor(1, 1, 1, 1);
    }

}
