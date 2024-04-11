package GoldenThieve.Controller;

import GoldenThieve.Model.IModel;
import GoldenThieve.View.IView;

/**
 * OfficerCountDown is a thread the controls the time of showing/hiding the officer on/off the screen.
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class OfficerCountDown extends Thread {
    private IView view;
    private IModel model;
    private IController controller;

    /**
     * OfficerCountDown constructor is used to create instances of the OfficerCountDown thread.
     *
     * @param m IModel instance
     * @param v IView instnace
     * @param c IController instance
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public OfficerCountDown(IView v, IModel m, IController c) {
        view = v;
        model = m;
        controller = c;
    }

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
            if (!model.officerThere()) model.setShowOfficer(!model.officerThere());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
