import java.awt.*;

/**
 * User: celentano
 * Date: 15.09.15.
 */
public class Racket implements Border, GraphObject {

    private int x;
    private int y;
    private int w;
    private int h;

    public Racket(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public int getLeftBorder() {
        return x;
    }

    @Override
    public int getRightBorder() {
        return x + w;
    }

    @Override
    public int getTopBorder() {
        return y;
    }

    @Override
    public int getDownBorder() {
        return y + h;
    }

    @Override
    public int getMiddleW() {
        return x + (w / 2);
    }

    @Override
    public int getMiddleH() {
        return y + (h / 2);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }


    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.green);
        g2d.fillRect(x, y, w, h);
    }
}
