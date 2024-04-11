package GoldenThieve.Controller;

import processing.event.KeyEvent;

/**
 * IController is the interface for the Controller class that has the methods that are visible for View
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public interface IController {
    void nextFrame();
    void moveThieve(KeyEvent event);
}
