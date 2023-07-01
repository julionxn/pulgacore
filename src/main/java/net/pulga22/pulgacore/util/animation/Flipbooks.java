package net.pulga22.pulgacore.util.animation;

public class Flipbooks {

    /**
     * @param length The length of the flipbook.
     * @param frameBase The basename of the frame textures. Ex. "flipbooks/test/frame"
     * @param zeros The amount of digits after the basename. Ex. zeros = 5 -> "flipbooks/test/frame00001"
     * @param extension The extension of the frame textures. Ex. extension = ".png" -> "flipbooks/test/frame00001.png"
     * @return The SingleFlipbook.
     */
    public static SingleFlipbook single(int length, String frameBase, int zeros, String extension){
        return new SingleFlipbook(length, frameBase, zeros, extension);
    }

    /**
     * @param length The length of the flipbook.
     * @param frameBase The basename of the frame textures. Ex. "flipbooks/test/frame"
     * @param zeros The amount of digits after the basename. Ex. zeros = 5 -> "flipbooks/test/frame00001"
     * @param extension The extension of the frame textures. Ex. extension = ".png" -> "flipbooks/test/frame00001.png"
     * @return The LoopFlipbook.
     */
    public static LoopFlipbook build(int length, String frameBase, int zeros, String extension){
        return new LoopFlipbook(length, frameBase, zeros, extension);
    }

}
