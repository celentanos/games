import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * User: celentano
 * Date: 15.09.15.
 */
public class SceneTennis extends Scene implements Constants {
    private Ball ball;
    private Racket racket1;
    private Racket racket2;
    private ScoreTennis score;

    private Delay delay;
    private int step_ball = 2;
    private int step_racket = 3;

    public SceneTennis(Game game) {
        super(game);
        ball = new Ball(100, 10, 15);
        ball.setVector(step_ball, step_ball);
        racket1 = new Racket(0, 10, 10, 60);
        racket2 = new Racket(WORLD_WIDTH * CELL_SIZE - 10, 10, 10, 60);
        score = new ScoreTennis(WORLD_WIDTH * CELL_SIZE);

        delay = new Delay(10);
    }

    @Override
    public void update(long nanosPassed) {
        processInput();

        if (delay.updateAndCheck(nanosPassed)) {
            // X ---------------------------------------------------------------
//            if (ball.getLeftBorder() < 1 + step_ball)
//                ball.setVector(step_ball, ball.getVector().getY());
//            if (ball.getRightBorder() > (WORLD_WIDTH * CELL_SIZE) - step_ball)
//                ball.setVector(-step_ball, ball.getVector().getY());

            if (ball.getLeftBorder() < racket1.getRightBorder())
                if (ball.getMiddleH() < racket1.getTopBorder() ||
                        ball.getMiddleH() > racket1.getDownBorder()) {
                    // out -----------------------------------------------------
                    if (ball.getLeftBorder() < 1 + step_ball) {
                        score.setPlayer2(score.getPlayer2() + 1);
                        ball.setPosition((WORLD_WIDTH * CELL_SIZE) / 2, (WORLD_HEIGHT * CELL_SIZE) / 2);
                    }
                } else
                    ball.setVector(step_ball, ball.getVector().getY());

            if (ball.getRightBorder() > racket2.getLeftBorder())
                if (ball.getMiddleH() < racket2.getTopBorder() ||
                        ball.getMiddleH() > racket2.getDownBorder()) {
                    // out -----------------------------------------------------
                    if (ball.getRightBorder() > (WORLD_WIDTH * CELL_SIZE) - step_ball) {
                        score.setPlayer1(score.getPlayer1() + 1);
                        ball.setPosition((WORLD_WIDTH * CELL_SIZE) / 2, (WORLD_HEIGHT * CELL_SIZE) / 2);
                    }
                } else
                    ball.setVector(-step_ball, ball.getVector().getY());


            // Y ---------------------------------------------------------------
            if (ball.getTopBorder() < 1 + step_ball)
                ball.setVector(ball.getVector().getX(), step_ball);
            if (ball.getDownBorder() > (WORLD_HEIGHT * CELL_SIZE) - step_ball)
                ball.setVector(ball.getVector().getX(), -step_ball);

            ball.update();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getScreenSize().width, game.getScreenSize().height);
        ball.draw(g);
        racket1.draw(g);
        racket2.draw(g);
        score.draw(g);
    }

    private void processInput() {
        for (KeyEvent event : game.getInput().getKeyPressedEvents()) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_W:
                    if (racket1.getTopBorder() > step_racket)
                        racket1.setY(racket1.getY() - step_racket);
                    else if (racket1.getTopBorder() > 0)
                        racket1.setY(racket1.getY() - 1);
                    break;
                case KeyEvent.VK_S:
                    if (racket1.getDownBorder() < (WORLD_HEIGHT * CELL_SIZE) - step_racket)
                        racket1.setY(racket1.getY() + step_racket);
                    else if (racket1.getDownBorder() < (WORLD_HEIGHT * CELL_SIZE))
                        racket1.setY(racket1.getY() + 1);
                    break;
                case KeyEvent.VK_UP:
                    if (racket2.getTopBorder() > step_racket)
                        racket2.setY(racket2.getY() - step_racket);
                    else if (racket2.getTopBorder() > 0)
                        racket2.setY(racket2.getY() - 1);
                    break;
                case KeyEvent.VK_DOWN:
                    if (racket2.getDownBorder() < (WORLD_HEIGHT * CELL_SIZE) - step_racket)
                        racket2.setY(racket2.getY() + step_racket);
                    else if (racket2.getDownBorder() < (WORLD_HEIGHT * CELL_SIZE))
                        racket2.setY(racket2.getY() + 1);
                    break;
            }
        }
        for (KeyEvent event : game.getInput().getKeyReleasedEvents()) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    game.setScene(new SceneStart(game));
                    break;
            }
        }
    }
}
