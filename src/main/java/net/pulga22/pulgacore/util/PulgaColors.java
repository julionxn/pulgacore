package net.pulga22.pulgacore.util;

import java.util.HashMap;
import java.util.Map;

public enum PulgaColors {
    /**
     * <p><font color=#372f3a>◼◼◼◼◼</font></p>
     */
    DARK_DARK(55, 47, 58),
    /**
     * <p><font color=#464459>◼◼◼◼◼</font></p>
     */
    DARK_MID(70, 68, 89),
    /**
     * <p><font color=#545e72>◼◼◼◼◼</font></p>
     */
    DARK_LIGHT(84, 94, 114),
    /**
     * <p><font color=#5d7680>◼◼◼◼◼</font></p>
     */
    MID_DARK(93, 118, 128),
    /**
     * <p><font color=#6a9395>◼◼◼◼◼</font></p>
     */
    MID_MID(106, 147, 149),
    /**
     * <p><font color=#7bad9f>◼◼◼◼◼</font></p>
     */
    MID_LIGHT(123, 173, 159),
    /**
     * <p><font color=#8eb29a>◼◼◼◼◼</font></p>
     */
    LIGHT_DARK(142, 178, 154),
    /**
     * <p><font color=#b3c6b4>◼◼◼◼◼</font></p>
     */
    LIGHT_MID(179, 198, 180),
    /**
     * <p><font color=#c5d2ce>◼◼◼◼◼</font></p>
     */
    LIGHT_LIGHT(197, 210, 206),
    /**
     * <p><font color=#d3d8d9>◼◼◼◼◼</font></p>
     */
    WHITE(211, 216, 217);

    private final int r;
    private final int g;
    private final int b;

    PulgaColors(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Map<Color, Integer> getRgb(){
        Map<Color, Integer> map = new HashMap<>();
        map.put(Color.R, this.r);
        map.put(Color.G, this.g);
        map.put(Color.B, this.b);
        return map;
    }

    public Map<Color, Float> getFloat(){
        Map<Color, Float> map = new HashMap<>();
        map.put(Color.R, this.r / 255f);
        map.put(Color.G, this.g / 255f);
        map.put(Color.B, this.b / 255f);
        return map;
    }

    public int getInt(){
        return (this.r<<16)|(this.g<<8)|(this.b);
    }

    public enum Color {
        R,
        G,
        B
    }
}



