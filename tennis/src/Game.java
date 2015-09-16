/**
 * User: celentano
 * Date: 10.09.15.
 */

import java.awt.*;

public interface Game {

    void start();

    void pause();

    void stop();

    Dimension getScreenSize();

    Input getInput();

    void setScene(Scene scene);
}

