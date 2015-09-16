/**
 * Created by celentano on 10.09.15.
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class MainScene extends Scene implements Constants {
    private Snake snake;
    private Apple apple;
    private Delay delay;

    public MainScene(Game game) {
        super(game);
        snake = new Snake(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, Direction.RIGHT);
        placeApple();
        delay = new Delay(200);
    }

    @Override
    public void update(long nanosPassed) {
        if (isGameOver()) {
            game.setScene(new GameOverScene(game));
            return;
        }

        processInput();

        if (delay.updateAndCheck(nanosPassed)) {
            snake.move();

            BodyPart head = snake.head();

            if (head.getX() < 1)
                head.setX(WORLD_WIDTH);

            if (head.getX() > WORLD_WIDTH)
                head.setX(1);

            if (head.getY() < 1)
                head.setY(WORLD_HEIGHT);

            if (head.getY() > WORLD_HEIGHT)
                head.setY(1);

            if (head.getX() == apple.getX() && head.getY() == apple.getY()) {
                List<BodyPart> body = snake.getBody();
                BodyPart lastPart = body.get(body.size() - 1);
                body.add(new BodyPart(lastPart.getX(), lastPart.getY()));

                if (isGameOver())
                    game.setScene(new GameOverScene(game));
                else
                    placeApple();
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getScreenSize().width, game.getScreenSize().height);
        drawSnake(g);
        drawApple(g);
    }

    private void drawSnake(Graphics2D g) {
        g.setColor(Color.pink);
        for (BodyPart bodyPart : snake.getBody()) {
            g.fillRect(
                    bodyPart.getX() * CELL_SIZE - CELL_SIZE,
                    game.getScreenSize().height - (bodyPart.getY() * CELL_SIZE),
                    CELL_SIZE,
                    CELL_SIZE
            );
        }
    }

    private void drawApple(Graphics2D g) {
        g.setColor(Color.green);
        g.fillRect(
                apple.getX() * CELL_SIZE - CELL_SIZE,
                game.getScreenSize().height - (apple.getY() * CELL_SIZE),
                CELL_SIZE,
                CELL_SIZE
        );
    }

    private void placeApple() {
        int x = 1 + (int) (Math.random() * WORLD_WIDTH);
        int y = 1 + (int) (Math.random() * WORLD_HEIGHT);
        while (!isCellEmpty(x, y)) {
            if (x < WORLD_WIDTH) {
                x++;
            } else {
                if (y < WORLD_HEIGHT) {
                    x = 1;
                    y++;
                } else {
                    x = 1;
                    y = 1;
                }
            }
        }
        apple = new Apple(x, y);
    }

    private boolean isCellEmpty(int x, int y) {
        for (BodyPart bodyPart : snake.getBody()) {
            if (bodyPart.getX() == x && bodyPart.getY() == y)
                return false;
        }
        return true;
    }

    private void processInput() {
        for (KeyEvent event : game.getInput().getKeyPressedEvents()) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP:
                    snake.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_DOWN:
                    snake.setDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    snake.setDirection(Direction.LEFT);
                    break;
            }
        }
    }

    private boolean isGameOver() {
        if (snake.getBody().size() == WORLD_WIDTH * WORLD_HEIGHT) {
            return true;
        }
        for (BodyPart bodyPart : snake.getBody()) {
            if (bodyPart != snake.head()
                    && snake.head().getX() == bodyPart.getX()
                    && snake.head().getY() == bodyPart.getY()) {
                return true;
            }
        }
        return false;
    }
}


