import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by celentano on 10.09.15.
 */
public class SceneStart extends Scene implements Constants {

    public SceneStart(Game game) {
        super(game);
    }

    @Override
    public void update(long nanosPassed) {
        for (KeyEvent event : game.getInput().getKeyPressedEvents()) {
            if (event.getKeyCode() == KeyEvent.VK_1) {
                game.setScene(new SceneSnake(game));
                break;
            }
            if (event.getKeyCode() == KeyEvent.VK_2) {
                game.setScene(new SceneTennis(game));
                break;
            }
        }
        for (KeyEvent event : game.getInput().getKeyReleasedEvents()) {
            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                game.stop();
                break;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getScreenSize().width, game.getScreenSize().height);

        g.setFont(new Font("Default", Font.BOLD, 16));
        g.setColor(Color.white);

        String message = "Press <1> to start <snake>";
        Rectangle2D messageBounds = g.getFontMetrics().getStringBounds(message, g);
        int messageWidth = (int) (messageBounds.getWidth());
        int messageHeight = (int) (messageBounds.getHeight());
        g.drawString(message,
                game.getScreenSize().width / 2 - messageWidth / 2,
                game.getScreenSize().height / 2 - messageHeight / 2
        );

        message = "Press <2> to start <tennis>";
        messageBounds = g.getFontMetrics().getStringBounds(message, g);
        messageWidth = (int) (messageBounds.getWidth());
        messageHeight = (int) (messageBounds.getHeight());
        g.drawString(message,
                game.getScreenSize().width / 2 - messageWidth / 2,
                game.getScreenSize().height / 2 - messageHeight / 2 + 20
        );
    }
}
