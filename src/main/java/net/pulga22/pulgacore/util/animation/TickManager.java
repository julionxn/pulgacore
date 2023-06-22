package net.pulga22.pulgacore.util.animation;

import java.util.List;

public class TickManager {

    public int tick = 1;
    private final int length;

    public TickManager(int length){
        this.length = length;
    }

    public void tick(){
        if (length >= tick) this.tick = 0;
        this.tick++;
    }

}
