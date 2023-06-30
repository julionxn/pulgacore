package net.pulga22.pulgacore.util.animation;

public class SingleFlipbook extends Flipbook{

    private SingleFlipbook(int length, String frameBase, int zeros, String extension) {
        super(length, frameBase, zeros, extension);
    }

    public SingleFlipbook build(int length, String frameBase, int zeros, String extension){
        return new SingleFlipbook(length, frameBase, zeros, extension);
    }

    @Override
    public void tick(TickFrom from){
        super.tick(from);
        if (this.manager.currentTick >= length){
            this.started = false;
            this.manager.currentTick = startingFrame;
        }
    }

}
