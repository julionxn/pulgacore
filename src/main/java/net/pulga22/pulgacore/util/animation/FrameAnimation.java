package net.pulga22.pulgacore.util.animation;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;

import java.util.ArrayList;
import java.util.List;

public class FrameAnimation {

    private final String frameBase;
    private final String extension;
    private final int length;
    private final List<AnimationEvent> EVENTS = new ArrayList<>();
    private final TickManager TICK_MANAGER;
    private final int zeros;
    /**
     * The frame texture according to the current tick.
     */
    public Identifier frameTexture;

    private FrameAnimation(int length, String frameBase, String extension, int zeros){
        this.length = length;
        this.frameBase = frameBase;
        this.extension = extension;
        this.TICK_MANAGER = new TickManager(length);
        this.zeros = zeros;
    }

    /**
     * @param length The total length of the animation.
     * @param frameBase The base path of the frame source. Ex: textures/animations/frame
     * @param extension The file extension of the frame. Ex: .png
     * @param zeros The total digits of the frame image name. Ex: zeros = 5 -> frame00000, frame00001
     * @return The FrameAnimation object.
     */
    public static FrameAnimation createFrameAnimation(int length, String frameBase, String extension, int zeros) {
        return new FrameAnimation(length, frameBase, extension, zeros);
    }

    /**
     * Add an event in the animation.
     * @param event The event happening in certain tick.
     */
    public void registerEvent(AnimationEvent event){
        this.EVENTS.add(event);
    }

    /**
     * @return The length of the animation.
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @return The frame texture according to the current tick.
     */
    public Identifier getCurrentFrame(){
        return this.frameTexture;
    }

    /**
     * Call this method ALWAYS inside the tick method.
     */
    public void tick(){
        this.TICK_MANAGER.tick();
        this.tickCommon();
    }

    /**
     * Call this method ALWAYS inside the render method.
     * @param client The minecraft client.
     */
    public void renderTick(MinecraftClient client){
        if(this.TICK_MANAGER.renderTick(client)){
            this.tickCommon();
        }
    }

    private void tickCommon(){
        this.frameTexture = new Identifier(PulgaCore.MOD_ID, this.frameBase + String.format("%0" + this.zeros + "d", this.TICK_MANAGER.tick) + extension);
        for (AnimationEvent event : EVENTS){
            event.run(this.TICK_MANAGER.tick);
        }
    }

}
