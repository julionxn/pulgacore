package net.pulga22.pulgacore.util.animation;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;
import net.pulga22.pulgacore.util.Render2D;
import net.pulga22.pulgacore.util.render2d.PivotedRender;

public class Flipbook {

    protected final int length;
    protected final String frameBase;
    protected final String extension;
    protected final int zeros;
    protected boolean started = false;
    protected int startingFrame = 0;
    protected final TickManager manager = new TickManager(startingFrame);
    protected Identifier currentFrame;
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

    public Flipbook setStartingFrame(int startingFrame){
        this.startingFrame = startingFrame - 1;
        this.manager.changeStartingFrame(this.startingFrame);
        return this;
    }

    public Flipbook setFadeIn() {
        return this.setFadeIn(calculateFadeTimes());
    }

    public Flipbook setFadeIn(int fadeIn){
        this.fadeInStop = fadeIn;
        this.fadeIn = true;
        return this;
    }

    public Flipbook setFadeOut(){
        return this.setFadeOut(calculateFadeTimes());
    }

    public Flipbook setFadeOut(int fadeOut){
        this.fadeOutStart = this.length - fadeOut;
        this.fadeOutLength = fadeOut;
        this.fadeOut = true;
        return this;
    }

    public void start(){
        this.started = true;
    }

    public void stop(){
        this.started = false;
    }

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

    public void render(PivotedRender render, int width, int height){
        if (render.getDrawContext() == null) return;
        render.getDrawContext().setShaderColor(1f, 1f, 1f, transparency);
        render.renderTexture(getFrame(), width, height);
    }

    public void render(DrawContext drawContext, int x, int y, int width, int height, boolean centered){
        drawContext.setShaderColor(1f, 1f, 1f, transparency);
        Render2D.renderTexture(drawContext, getFrame(), x, y, width, height, centered);
    }

    public Identifier getFrame(){
        return currentFrame;
    }

    private int calculateFadeTimes(){
        return Math.min(this.length / 3, 30);
    }

}
