package net.pulga22.pulgacore.util;

import java.util.HashMap;
import java.util.Map;

public enum PulgaColors {
    DARK_DARK(55, 47, 58),
    DARK_MID(70, 68, 89),
    DARK_LIGHT(84, 94, 114),
    MID_DARK(93, 118, 128),
    MID_MID(106, 147, 149),
    MID_LIGHT(123, 173, 159),
    LIGHT_DARK(142, 178, 154),
    LIGHT_MID(179, 198, 180),
    LIGHT_LIGHT(197, 210, 206),
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



