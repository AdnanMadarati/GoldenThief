package GoldenThieve.Controller;

import GoldenThieve.Model.IModel;

/**
 * RandomLaneChooser is a thread that calls the chooseLane method in Model every x seconds to randomize the lanes of the cars.
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class RandomLaneChooser extends Thread {
    private IModel model;
    private IController controller;
    private int sleepTime = 1300;

    /**
     * RandomLaneChooser constructor is used to create instances of the RandomLaneChooser thread.
     *
     * @param m IModel instance
     * @param c IController instance
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public RandomLaneChooser(IModel m, IController c) {
        model = m;
        controller = c;
    }

    /**
     * Changes the time of sleep.
     *
     * @param newTime New Value for sleepTime
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void changeSleepTime(int newTime) { sleepTime = newTime; }

    /**
     * Gets called once the thread starts and excutes the code inside.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void run() {
        while (true) {
            if (!model.getTrafficIsRed()) model.chooseLane();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
