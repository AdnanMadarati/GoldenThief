package GoldenThieve.Controller;

import GoldenThieve.Model.IModel;
import GoldenThieve.View.IView;

/**
 * AbilityCountDown is a Thread the controls the time of showing/hiding the ability on/off the screen.
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class AbilityCountDown extends Thread {
    private IModel model;
    private IView view;
    private IController controller;
    private int sleepTime = 0;
    private boolean abilityEquipped = false;

    /**
     * AbilityCountDown constructor is used to create instances of the AbilityCountDown thread.
     *
     * @param m IModel instance
     * @param v IView instnace
     * @param c IController instance
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public AbilityCountDown(IModel m, IView v, IController c) {
        model = m;
        view = v;
        controller = c;
    }

    /**
     * Sets the value of abilityEquipped.
     *
     * @param state The new boolean value
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setAbilityEquipped(boolean state) {
        abilityEquipped = state;
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
            if (abilityEquipped) {
                abilityEquipped = false;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                model.getAbility().setAbilityIsThere(!model.getAbility().getAbilityIsThere());
                if (model.getAbility().getAbilityIsThere()) {
                    model.getAbility().randomizePositions();
                    sleepTime = 5000;
                } else {
                    sleepTime = 15000;
                }

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
