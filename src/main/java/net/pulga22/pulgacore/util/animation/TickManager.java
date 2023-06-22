package net.pulga22.pulgacore.util.animation;

import net.minecraft.client.MinecraftClient;

public class TickManager {

    public int tick = 1;
    private final int length;
    private float totalRenderTime;

    /**
     * @param length The total length of the tracker.
     */
    public TickManager(int length){
        this.length = length;
    }

    /**
     * Call this method ALWAYS inside the tick method.
     */
    public void tick(){
        if (length >= tick) this.tick = 0;
        this.tick++;
    }

    /**
     * Call this method ALWAYS inside the render method.
     * @param client The minecraft client.
     */
    public boolean renderTick(MinecraftClient client){
        totalRenderTime += client.getLastFrameDuration();
        if (totalRenderTime >= 1.0f){
            this.tick();
            totalRenderTime = 0;
            return true;
        }
        return false;
    }

}
