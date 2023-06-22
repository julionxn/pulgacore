package net.pulga22.pulgacore.util.render2d;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.util.Render2DUtils;

public class PivotedRender {

    private final MinecraftClient client;
    private final DrawContext drawContext;
    private final Pivot pivot;
    private int offsetX = 0;
    private int offsetY = 0;
    private boolean centered;


    public PivotedRender(MinecraftClient client, DrawContext drawContext, Pivot pivot){
        this.client = client;
        this.drawContext = drawContext;
        this.pivot = pivot;
    }

    public PivotedRender offsetX(int offsetX){
        this.offsetX = offsetX;
        return this;
    }

    public PivotedRender offsetY(int offsetY){
        this.offsetY = offsetY;
        return this;
    }

    public PivotedRender offset(int offsetX, int offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        return this;
    }

    public PivotedRender centered(){
        this.centered = true;
        return this;
    }

    public void renderTexture(Identifier identifier, int width, int height){
        ScreenPosition screenPosition = getScreenPosition();
        Render2DUtils.renderTexture(this.drawContext, identifier, screenPosition.getX(), screenPosition.getY(), width, height, this.centered);
    }

    public void renderTextureWithColor(Identifier identifier, int width, int height, int color){
        ScreenPosition screenPosition = getScreenPosition();
        Render2DUtils.renderTextureWithColor(this.drawContext, identifier, screenPosition.getX(), screenPosition.getY(), width, height, color, this.centered);
    }

    public void renderTextureWithTransparencies(Identifier identifier, int width, int height){
        ScreenPosition screenPosition = getScreenPosition();
        Render2DUtils.renderTextureWithTransparencies(this.drawContext, identifier, screenPosition.getX(), screenPosition.getY(), width, height, this.centered);
    }

    public void renderTextureWithTransparenciesAndColor(Identifier identifier, int width, int height, int color){
        ScreenPosition screenPosition = getScreenPosition();
        Render2DUtils.renderTextureWithTransparenciesAndColor(this.drawContext, identifier, screenPosition.getX(), screenPosition.getY(), width, height, color, this.centered);
    }

    public void renderText(String text, int color, boolean shadow){
        ScreenPosition screenPosition = getScreenPosition();
        Render2DUtils.renderText(this.client, this.drawContext, text, screenPosition.getX(), screenPosition.getY(), color, shadow, this.centered);
    }

    public void renderTextWithoutShadows(String text, int color){
        this.renderText(text, color, false);
    }

    public void renderTextWithShadows(String text, int color){
        this.renderText(text, color, true);
    }

    private ScreenPosition getScreenPosition(){
        int screenWidth = this.drawContext.getScaledWindowWidth();
        int screenHeight = this.drawContext.getScaledWindowHeight();
        ScreenPosition screenPosition = getScreenPositionFromPivot(screenWidth, screenHeight, this.pivot);
        screenPosition.add(this.offsetX, this.offsetY);
        return screenPosition;
    }

    private ScreenPosition getScreenPositionFromPivot(int screenWidth, int screenHeight, Pivot pivot){
        switch (pivot){
            case TOP -> {
                return new ScreenPosition(screenWidth/2, 0);
            }
            case TOP_RIGHT -> {
                return new ScreenPosition(screenWidth, 0);
            }
            case CENTER_LEFT -> {
                return new ScreenPosition(0, screenHeight/2);
            }
            case CENTER -> {
                return new ScreenPosition(screenWidth/2, screenHeight/2);
            }
            case CENTER_RIGHT -> {
                return new ScreenPosition(screenWidth, screenHeight/2);
            }
            case BOT_LEFT -> {
                return new ScreenPosition(0, screenHeight);
            }
            case BOT -> {
                return new ScreenPosition(screenWidth/2, screenHeight);
            }
            case BOT_RIGHT -> {
                return new ScreenPosition(screenWidth, screenHeight);
            }
        }
        return new ScreenPosition(0, 0);
    }

}
