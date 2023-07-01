package net.pulga22.pulgacore.util.animation;

public class LoopFlipbook extends Flipbook{

    protected LoopFlipbook(int length, String frameBase, int zeros, String extension) {
        super(length, frameBase, zeros, extension);
    }

    /**
     * @param from Tick or render thread.
     */
    @Override
    public void tick(TickFrom from){
        super.tick(from);
        if (this.manager.currentTick >= length){
            this.manager.currentTick = startingFrame;
        }
    }
}
