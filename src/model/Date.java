package model;

/**
 * Created by mhewedy on 08/05/14.
 */
public class Date {
    private int y, m, d;

    public Date(int y, int m, int d) {
        this.y = y;
        this.m = m;
        this.d = d;
    }

    public int getY() {
        return y;
    }

    public int getM() {
        return m;
    }

    public int getD() {
        return d;
    }

    @Override
    public String toString() {
        return "Date{" +
                "y=" + y +
                ", m=" + m +
                ", d=" + d +
                '}';
    }
}
