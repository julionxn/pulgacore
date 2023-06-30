package net.pulga22.pulgacore.util.render2d;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.util.Render2D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PivotedRender {

    private final MinecraftClient client;
    @Nullable private DrawContext drawContext;
    private final Pivot pivot;
    private int offsetX = 0;
    private int offsetY = 0;
    private boolean centered;


    public PivotedRender(MinecraftClient client, @Nullable DrawContext drawContext, Pivot pivot){
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

    public void setDrawContext(@Nullable DrawContext drawContext){
        this.drawContext = drawContext;
    }

    public @Nullable DrawContext getDrawContext(){
        return this.drawContext;
    }

    public void renderTexture(Identifier identifier, int width, int height){
        if (this.drawContext == null) return;
        ScreenPosition screenPosition = getScreenPosition(this.drawContext);
        Render2D.renderTexture(this.drawContext, identifier, screenPosition.getX(), screenPosition.getY(), width, height, this.centered);
    }

    public void renderTextureWithColor(Identifier identifier, int width, int height, int color){
        if (this.drawContext == null) return;
        ScreenPosition screenPosition = getScreenPosition(this.drawContext);
        Render2D.renderTextureWithColor(this.drawContext, identifier, screenPosition.getX(), screenPosition.getY(), width, height, color, this.centered);
    }

    public void renderTextureWithTransparencies(Identifier identifier, int width, int height){
        if (this.drawContext == null) return;
        ScreenPosition screenPosition = getScreenPosition(this.drawContext);
        Render2D.renderTextureWithTransparencies(this.drawContext, identifier, screenPosition.getX(), screenPosition.getY(), width, height, this.centered);
    }

    public void renderTextureWithTransparenciesAndColor(Identifier identifier, int width, int height, int color){
        if (this.drawContext == null) return;
        ScreenPosition screenPosition = getScreenPosition(this.drawContext);
        Render2D.renderTextureWithTransparenciesAndColor(this.drawContext, identifier, screenPosition.getX(), screenPosition.getY(), width, height, color, this.centered);
    }

    public void renderText(String text, int color, boolean shadow){
        if (this.drawContext == null) return;
        ScreenPosition screenPosition = getScreenPosition(this.drawContext);
        Render2D.renderText(this.client, this.drawContext, text, screenPosition.getX(), screenPosition.getY(), color, shadow, this.centered);
    }

    public void renderTextWithoutShadows(String text, int color){
        this.renderText(text, color, false);
    }

    public void renderTextWithShadows(String text, int color){
        this.renderText(text, color, true);
    }

    private ScreenPosition getScreenPosition(DrawContext drawContext){
        int screenWidth = drawContext.getScaledWindowWidth();
        int screenHeight = drawContext.getScaledWindowHeight();
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
