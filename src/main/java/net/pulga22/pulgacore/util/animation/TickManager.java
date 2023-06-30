package net.pulga22.pulgacore.util.animation;

import net.minecraft.client.MinecraftClient;

public class TickManager {

    public int currentTick;
    private final MinecraftClient client = MinecraftClient.getInstance();
    private float totalRenderTime;

    public TickManager(int startingFrame){
        this.currentTick = startingFrame;
    }

    public void changeStartingFrame(int startingFrame){
        this.currentTick = startingFrame;
    }

    public void tickFromTick(){
        this.currentTick++;
    }

    public void tickFromRender(){
        totalRenderTime += client.getLastFrameDuration();
        if (totalRenderTime >= 1.0f){
            this.tickFromTick();
            totalRenderTime = 0;
        }
    }

}
