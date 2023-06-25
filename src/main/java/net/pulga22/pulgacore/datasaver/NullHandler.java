package net.pulga22.pulgacore.datasaver;

@FunctionalInterface
public interface NullHandler<T> {
    T run();
}
