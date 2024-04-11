package GoldenThieve.View;

import processing.core.PApplet;

/**
 * This Timer class is used as a countdown timer in view.
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class Timer extends PApplet {
    private int startTime, interval;

    /**
     * The Timer constructor is used to create instances of the Timer class.
     *
     * @param time Time between each countdown (1000 = countdown every one second)
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public Timer(int time) {
        interval = time;
    }

    /**
     * Saves the time that the program has been running for.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void start() {
        startTime = millis();
    }

    /**
     * checks if time since the start method is called is greater than the time to countdown.
     *
     * @return Returns true if it is time to countdown otherwise false
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean complete() {
        int elapsedTime = millis() - startTime;
        if (elapsedTime > interval) {
            return true;
        }
        return false;
    }

}
