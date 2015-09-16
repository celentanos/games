import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * User: celentano
 * Date: 16.09.15.
 */
public class ScoreTennis implements GraphObject {

    private int player1 = 0;
    private int player2 = 0;
    String score;

    private int wight;

    public ScoreTennis(int w) {
        this.wight = w;
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    @Override
    public void draw(Graphics2D g2d) {
        score = player1 + " : " + player2;

        Rectangle2D messageBounds = g2d.getFontMetrics().getStringBounds(score, g2d);
        int messageWidth = (int) (messageBounds.getWidth());
//        int messageHeight = (int) (messageBounds.getHeight());
        g2d.drawString(score, wight / 2 - messageWidth / 2, 20);

    }
}
