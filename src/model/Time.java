package model;

/**
 * Created by mhewedy on 08/05/14.
 */
public class Time {
    private int h, m;

    public Time(int h, int m) {
        this.h = h;
        this.m = m;
    }

    public int getH() {
        return h;
    }

    public int getM() {
        return m;
    }

    @Override
    public String toString() {
        return "Time{" +
                "h=" + h +
                ", m=" + m +
                '}';
    }
}
