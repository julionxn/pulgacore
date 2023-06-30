package net.pulga22.pulgacore.util;

import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Contract;

import java.util.Random;

public abstract class MathHelper {

    public static final Random random = new Random();

    /**
     * This method helps to clamp a rotation that goes 0 -> 180 -> -180 > 0
     * @param value Current rotation.
     * @param min Min rotation.
     * @param max Max rotation.
     * @return Clamped rotation.
     */
    @Contract(pure = true)
    public static double rotationClamp(double value, double min, double max) {
        double result = 0;
        if ((-180 <= value && value <= min) || (max <= value && value <= 180)) {
            result = value;
        } else if (min < value && value <= 0) {
            result = min;
        } else if (0 <= value && value < max) {
            result = max;
        }
        return result;
    }

    /**
     * @param radians Radians to convert.
     * @return Radians to degrees.
     */
    @Contract(pure = true)
    public static float toDegrees(float radians){
        return radians * 57.29578f;
    }

    /**
     * @param direction Direction property.
     * @return Rotation in degrees according to the direction.
     */
    @Contract(pure = true)
    public static float getAngleRotation(Direction direction){
        switch (direction){
            case NORTH -> {return 180f;}
            case SOUTH -> {return 0f;}
            case WEST -> {return 90f;}
            case EAST -> {return 270f;}
        }
        return 0f;
    }

}
