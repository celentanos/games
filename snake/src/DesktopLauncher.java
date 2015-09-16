/**
 * Created by celentano on 10.09.15.
 */

import java.awt.*;

public class DesktopLauncher implements Constants {
    public static void main(String[] args) {
        int screenWidth = WORLD_WIDTH * CELL_SIZE;
        int screenHeight = WORLD_HEIGHT * CELL_SIZE;
        Dimension screenSize = new Dimension(screenWidth, screenHeight);
        Game game = DesktopGameBuilder.build(screenSize);
        game.setScene(new MainScene(game));
        game.start();
    }
}
