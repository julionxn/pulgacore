package net.pulga22.pulgacore.util.animation;

import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;

import java.util.ArrayList;
import java.util.List;

public class FrameAnimation {

    private final String frameDir;
    private final int length;
    private final List<AnimationEvent> EVENTS = new ArrayList<>();
    private final TickManager TICK_MANAGER;
    private final int zeros;

    public FrameAnimation(int length, String frameDir, int zeros){
        this.length = length;
        this.frameDir = frameDir;
        this.TICK_MANAGER = new TickManager(length);
        this.zeros = zeros;
    }

    public int getLength() {
        return this.length;
    }

    public void addAnimation(AnimationEvent event){
        this.EVENTS.add(event);
    }

    public void tick(){
        this.TICK_MANAGER.tick();
        for (AnimationEvent event : EVENTS){
            event.run(this.TICK_MANAGER.tick);
        }
    }

    public Identifier getCurrentFrame(){
        return new Identifier(PulgaCore.MOD_ID, this.frameDir + String.format("%0" + this.zeros + "d", this.TICK_MANAGER.tick));
    }

}
