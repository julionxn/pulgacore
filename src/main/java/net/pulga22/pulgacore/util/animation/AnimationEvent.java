package net.pulga22.pulgacore.util.animation;

@FunctionalInterface
public interface AnimationEvent {
    void run(Integer currentTick);
}
