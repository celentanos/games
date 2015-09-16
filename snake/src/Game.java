/**
 * Created by celentano on 10.09.15.
 */

import java.awt.*;

public interface Game {
    void start();

    void pause();

    Dimension getScreenSize();

    Input getInput();

    void setScene(Scene scene);
}

