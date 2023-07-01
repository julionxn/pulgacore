package net.pulga22.pulgacore.util.animation;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;
import net.pulga22.pulgacore.util.Render2D;
import net.pulga22.pulgacore.util.render2d.PivotedRender;
import org.jetbrains.annotations.Nullable;

public class Flipbook {

    protected final int length;
    protected final String frameBase;
    protected final String extension;
    protected final int zeros;
    protected boolean started = false;
    protected int startingFrame = 0;
    protected final TickManager manager = new TickManager(startingFrame);
    protected Identifier currentFrame = null;
    protected boolean fadeIn = false;
    protected int fadeInStop;
    protected int fadeOutStart;
    protected int fadeOutLength;
    protected float transparency = 1f;
    protected boolean fadeOut = false;


    protected Flipbook(int length, String frameBase, int zeros, String extension){
        this.length = length;
        this.frameBase = frameBase;
        this.zeros = zeros;
        this.extension = extension;
    }

    /**
     * @param startingFrame Integer of the first frame name.
     * @return The same flipbook.
     */
    public Flipbook setStartingFrame(int startingFrame){
        this.startingFrame = startingFrame - 1;
        this.manager.changeStartingFrame(this.startingFrame);
        return this;
    }

    /**
     * Establish that the flipbook is going to have a fadeIn. Self-calculates the time.
     * @return The same flipbook.
     */
    public Flipbook setFadeIn() {
        return this.setFadeIn(calculateFadeTimes());
    }

    /**
     * @param fadeIn The ticks of the fadeIn.
     * @return The same flipbook.
     */
    public Flipbook setFadeIn(int fadeIn){
        this.fadeInStop = fadeIn;
        this.fadeIn = true;
        return this;
    }

    /**
     * Establish that the flipbook is going to have a fadeOut. Self-calculates the time.
     * @return The same flipbook.
     */
    public Flipbook setFadeOut(){
        return this.setFadeOut(calculateFadeTimes());
    }

    /**
     * @param fadeOut The ticks of the fadeOut.
     * @return The same flipbook.
     */
    public Flipbook setFadeOut(int fadeOut){
        this.fadeOutStart = this.length - fadeOut;
        this.fadeOutLength = fadeOut;
        this.fadeOut = true;
        return this;
    }

    /**
     * Start the flipbook.
     */
    public void start(){
        this.started = true;
    }

    /**
     * Stop the flipbook.
     */
    public void stop(){
        this.started = false;
    }

    /**
     * @param from Tick or render thread.
     */
    public void tick(TickFrom from){
        if (!started) return;
        switch (from){
            case TICK -> {
                this.manager.tickFromTick();
            }
            case RENDER -> {
                this.manager.tickFromRender();
            }
        }
        this.transparency = 1f;
        if (fadeIn && this.manager.currentTick <= this.fadeInStop){
            this.transparency = (float) this.manager.currentTick / fadeInStop;
        }
        if (fadeOut && this.manager.currentTick > this.fadeOutStart){
            this.transparency = (float) (this.length - this.manager.currentTick) / this.fadeOutLength;
        }
        this.frameUpdate();
    }


    protected void frameUpdate(){
        int currentTick = this.manager.currentTick;
        this.currentFrame = new Identifier(PulgaCore.MOD_ID, this.frameBase + String.format("%0" + this.zeros + "d", currentTick) + extension);
    }

    /**
     * @param render The pivoted render.
     * @param width The width of the flipbook.
     * @param height The height of the flipbook.
     */
    public void render(PivotedRender render, int width, int height){
        if (render.getDrawContext() == null) return;
        render.getDrawContext().setShaderColor(1f, 1f, 1f, transparency);
        render.renderTexture(getFrame(), width, height);
    }

    /**
     * @param drawContext Draw context of the method.
     * @param x X position.
     * @param y Y position.
     * @param width The width of the flipbook.
     * @param height The height of the flipbook.
     * @param centered If the x and y coordinates are taken from the center of the flipbook.
     */
    public void render(DrawContext drawContext, int x, int y, int width, int height, boolean centered){
        drawContext.setShaderColor(1f, 1f, 1f, transparency);
        Render2D.renderTexture(drawContext, getFrame(), x, y, width, height, centered);
    }

    /**
     * @return Get the current frame displayed.
     */
    @Nullable
    public Identifier getFrame(){
        return currentFrame;
    }

    private int calculateFadeTimes(){
        return Math.min(this.length / 3, 30);
    }

}
