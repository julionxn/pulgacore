package net.pulga22.pulgacore.util.animation;

public class LoopFlipbook extends Flipbook{

    private LoopFlipbook(int length, String frameBase, int zeros, String extension) {
        super(length, frameBase, zeros, extension);
    }

    public LoopFlipbook build(int length, String frameBase, int zeros, String extension){
        return new LoopFlipbook(length, frameBase, zeros, extension);
    }

    @Override
    public void tick(TickFrom from){
        super.tick(from);
        if (this.manager.currentTick >= length){
            this.manager.currentTick = startingFrame;
        }
    }
}
