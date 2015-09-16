import java.awt.*;

/**
 * User: celentano
 * Date: 15.09.15.
 */
public class Racket implements Border, GraphObject {

    private double x;
    private double y;
    private double w;
    private double h;

    public Racket(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public int getLeftBorder() {
        return (int)x;
    }

    @Override
    public int getRightBorder() {
        return (int)x + (int)w;
    }

    @Override
    public int getTopBorder() {
        return (int)y;
    }

    @Override
    public int getDownBorder() {
        return (int)y + (int)h;
    }

    @Override
    public int getMiddleW() {
        return (int)x + ((int)w / 2);
    }

    @Override
    public int getMiddleH() {
        return (int)y + ((int)h / 2);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }


    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.green);
        g2d.fillRect((int)x, (int)y, (int)w, (int)h);
    }
}
