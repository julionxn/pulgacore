package net.pulga22.pulgacore.util.render2d;

public class ScreenPosition {

    private int x;
    private int y;

    public ScreenPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void add(int x, int y){
        this.x += x;
        this.y += y;
    }
}
