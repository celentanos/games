import java.awt.*;

/**
 * User: celentano
 * Date: 15.09.15.
 */
public class Ball implements Border, GraphObject {
    private int x;
    private int y;
    private int d;
    private Vector vector;

    public Ball(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
        vector = new Vector(1, 1, 0);
    }

    public void update() {
        x += vector.getX();
        y += vector.getY();
    }

    public void setVector(int x, int y) {
        vector.setVector(x, y, 0);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector getVector() {
        return vector;
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

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    @Override
    public int getLeftBorder() {
        return x;
    }

    @Override
    public int getRightBorder() {
        return x + d - 1;
    }

    @Override
    public int getTopBorder() {
        return y;
    }

    @Override
    public int getDownBorder() {
        return y + d - 1;
    }

    @Override
    public int getMiddleW() {
        return x + (d / 2);
    }

    @Override
    public int getMiddleH() {
        return y + (d / 2);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.gray);
        g2d.fillOval(x, y, d, d);
    }
}
