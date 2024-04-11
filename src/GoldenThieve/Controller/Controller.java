package GoldenThieve.Controller;

import GoldenThieve.Model.IModel;
import GoldenThieve.View.IView;
import processing.event.KeyEvent;

/**
 * The Controller class is the connection between Model and View. It sends data to them via parameters.
 * Based on data recieved from Model, Controller tells View what to draw. This means that controller has access to both view and model.
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class Controller implements IController {

    private IView view;
    private IModel model;
    private GameState state = GameState.START;
    private RandomLaneChooser randomLaneChooser;
    private OfficerCountDown officerCountDown;
    private AbilityCountDown abilityCountDown;
    private int width, height;
    private boolean showTutorial = false;
    private String reasonOfGameover;

    /**
     * The Controller constructor is used to create instances of Controller.
     *
     * @param width Width of the window
     * @param height Height of the window
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public Controller(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * setView takes a view interface instance as a parameter and stores it in class variable to get access to the view methods.
     *
     * @param v IView instance
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setView(IView v){
        view = v;
    }

    /**
     * setModel takes a model interface instance as a parameter and stores it in class variable to get access to the model methods.
     * It also creates instances of the three main threads and starts them.
     *
     * @param m IModel instance
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setModel(IModel m){
        model = m;

        randomLaneChooser = new RandomLaneChooser(model, this);
        officerCountDown = new OfficerCountDown(view, model, this);
        abilityCountDown = new AbilityCountDown(model, view, this);

        randomLaneChooser.start();
        officerCountDown.start();
        abilityCountDown.start();
    }

    /**
     * nextFrame is the method that decides what should be drawn be view based on the state of the game and the data from model.
     * It also has some lambda threads to display things for x amount of time.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void nextFrame() {
        switch (state) {
            case START -> {
                if (showTutorial) {
                    view.drawTutorial();
                } else { view.drawStart(); }
            }
            case PLAYING -> {
                view.drawGame(model.getOfficer().getPosX(), model.getOfficer().getPosY(), model.getGoldCounter(), model.getGoldHeld(), model.getThieveCar().getPosX(), model.getThieveCar().getPosY(), model.officerThere());

                for (int i = 0; i < model.getCars().length; i++) {
                    view.drawCar(model.getCars()[i].getPosX(), model.getCars()[i].getPosY(), model.getCars()[i].getStartingPos(), model.getCars()[i].getCarColor(), model.getCars()[i].getIsDriving());
                }

                if (model.getAbility().getAbilityIsThere()) {
                    view.drawAbility(model.getAbility().getAbilityIsThere(), model.getAbility().getAbilityType(), model.getAbility().getPosX(), model.getAbility().getPosY());

                    if (model.checkAbilityCollision(view.getThieveWidth(), view.getThieveHeight(), view.getAbilityWidth(), view.getAbilityHeight(), view.getThieveImageFlip())) {
                        view.setShowTimer(true);
                        abilityCountDown.setAbilityEquipped(true);

                        if (model.getAbility().getAbilityType().equals("speed")) {
                            if (model.getThieve().getThieveSpeed() == 8) {
                                view.addAbilityToHUD("speed");
                                model.getThieve().setThieveSpeed(12);
                                view.playMusic("speed");

                                new Thread(() -> {
                                    try {
                                        Thread.sleep(10000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    view.removeAbilityToHUD("speed");
                                    model.getThieve().setThieveSpeed(8);
                                }).start();
                            }
                        } else if (model.getAbility().getAbilityType().equals("invisible")) {
                            if (!view.getThieveInvisible()) {
                                view.addAbilityToHUD("invisible");
                                view.setThieveInvisible(true);
                                view.playMusic("invisible");

                                new Thread(() -> {
                                    try {
                                        Thread.sleep(10000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    view.removeAbilityToHUD("invisible");
                                    view.setThieveInvisible(false);
                                }).start();
                            }
                        } else if (model.getAbility().getAbilityType().equals("traffic")) {
                            if (!model.getTrafficIsRed()) {
                                view.playMusic("traffic");
                                view.addAbilityToHUD("traffic");
                                model.setTrafficIsRed(true);

                                new Thread(() -> {
                                    while (view.getBlinkSwitcher() != 10 && model.getTrafficIsRed()) {
                                        view.increaseBlinkSwitcher();
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }).start();

                                new Thread(() -> {
                                    try {
                                        Thread.sleep(10000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    view.removeAbilityToHUD("traffic");
                                    model.setTrafficIsRed(false);
                                }).start();
                            }
                        }
                        model.getAbility().setAbilityIsThere(false);
                    }
                }

                view.drawThieve(model.getThieve().getPosX(), model.getThieve().getPosY(), model.getThieveHasGold());

                if (!model.getTrafficIsRed()) {
                    if (model.moveCars(view.getThieveWidth(), view.getThieveHeight(), view.getCarWidth(), view.getCarHeight(), view.getThieveImageFlip(), view.getThieveInvisible())) {
                        view.playMusic("crash");
                        reasonOfGameover = "You Got Flatted";
                        state = GameState.GAMEOVER;
                    }
                }

                if (model.checkBankCollision(view.getThieveHeight())) {
                    model.increaseGoldCounter(10);
                }

                if (model.getThieveHasGold() && state == GameState.PLAYING) view.playMusic("steal");

                if (model.checkTruckCollision(view.getThieveHeight(), view.getThieveCarImageWidth(), view.getThieveCarImageHeight())) model.saveStolenGold();

                if (model.officerThere()) {
                    model.getOfficer().updateOfficerPos(2);

                    if (model.getOfficer().getPosY() > height) {
                        model.getOfficer().resetPos(width);
                        model.setShowOfficer(false);
                    }
                    if (!view.getThieveInvisible()) {
                        if (model.isCaught(view.getThieveWidth())) {
                            view.playMusic("caught");
                            reasonOfGameover = "You Got Caught";
                            state = GameState.GAMEOVER;
                        }
                    }
                } else {
                    model.getOfficer().resetPos(width);
                }
                switch (model.getCarsSpeed()) {
                    case 8:
                        randomLaneChooser.changeSleepTime(1100);
                        break;
                    case 10:
                        randomLaneChooser.changeSleepTime(900);
                }
            }
            case GAMEOVER -> view.drawGameOver(reasonOfGameover);
        }
    }

    /**
     * moveThieve takes a keyEvent object as a parameter to decide what should be changed based on user's inputs
     * It is mainly used to move the player inGame
     *
     * @param event keyEvent instance
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void moveThieve(KeyEvent event) {
        if (event.getKeyCode() == 'D') {
            if (view.getThieveImageFlip() != 1) {
                view.flipThieve();
            } else {
                if (model.getThieve().getPosX() + view.getThieveHeight()/2 >= width ||
                    model.getThieve().getPosX() + view.getThieveHeight()/2 >= width - width/7 - width/66 && model.getThieve().getPosY() >= height/3 - height/30 && model.getThieve().getPosY() <= height - height/3 - height/16) {
                    model.getThieve().updateThievePosX(model.getThieve().getPosX());
                } else {
                    model.getThieve().updateThievePosX(model.getThieve().getPosX() + model.getThieve().getThieveSpeed());
                }
            }
        }
        if (event.getKeyCode() == 'A') {
            if (view.getThieveImageFlip() != -1) {
                view.flipThieve();
            } else {
                if (model.getThieve().getPosX() <= view.getThieveWidth()/2 ||
                    model.getThieve().getPosX() >= 0 && model.getThieve().getPosX() - view.getThieveHeight()/4 <= model.getThieveCar().getPosX() + view.getThieveCarImageWidth() + view.getThieveCarImageWidth()/2 && model.getThieve().getPosY() + view.getThieveHeight() >= height/2 && model.getThieve().getPosY() <= height/2 - height/30 + view.getThieveCarImageHeight()) {
                    model.getThieve().updateThievePosX(model.getThieve().getPosX());
                } else {
                    model.getThieve().updateThievePosX(model.getThieve().getPosX() - model.getThieve().getThieveSpeed());
                }
            }
        }
        if (event.getKeyCode() == 'W') {
            if (model.getThieve().getPosY() <= 0 ||
                model.getThieve().getPosX() >= 0 && model.getThieve().getPosX() <= model.getThieveCar().getPosX() + view.getThieveCarImageWidth() + view.getThieveCarImageWidth()/2 && model.getThieve().getPosY() >= height/2 && model.getThieve().getPosY() <= height/2 - height/40 + view.getThieveCarImageHeight() ||
                model.getThieve().getPosX() >= width - width/6 - width/66 && model.getThieve().getPosX() <= width && model.getThieve().getPosY() - view.getThieveHeight()/4 >= height/3 + height/24 && model.getThieve().getPosY() - view.getThieveHeight()/4 + view.getThieveHeight()/5 <= height - height/3 - height/24) {
                model.getThieve().updateThievePosY(model.getThieve().getPosY());
            } else {
                model.getThieve().updateThievePosY(model.getThieve().getPosY() - model.getThieve().getThieveSpeed());
            }
        }
        if (event.getKeyCode() == 'S') {
            if (model.getThieve().getPosY() + view.getThieveHeight() * 1.3 >= height ||
                model.getThieve().getPosX() - view.getThieveWidth()/5 >= 0 && model.getThieve().getPosX() - view.getThieveWidth()/5 <= width/128 + view.getThieveCarImageWidth() && model.getThieve().getPosY() + view.getThieveHeight() >= height/2 - height/48 ||
                model.getThieve().getPosX() >= 0 && model.getThieve().getPosX() <= model.getThieveCar().getPosX() + view.getThieveCarImageWidth() + view.getThieveCarImageWidth()/2 && model.getThieve().getPosY() >= height/2 && model.getThieve().getPosY() <= height/2 - height/40 + view.getThieveCarImageHeight()) {
                model.getThieve().updateThievePosY(model.getThieve().getPosY());
            } else {
                model.getThieve().updateThievePosY(model.getThieve().getPosY() + model.getThieve().getThieveSpeed());
            }
        }
        if (state == GameState.START) {
            if (!showTutorial) {
                if (event.getKeyCode() == ' ') {
                    model.restart();
                    view.restart();
                    randomLaneChooser.changeSleepTime(1300);
                    state = GameState.PLAYING;
                }
            }

            if (event.getKeyCode() == 'T') {
                showTutorial = !showTutorial;
            }
        }

        if (state == GameState.GAMEOVER) {
            if (event.getKeyCode() == 'R') {
                view.playMusic("background");
                model.restart();
                view.restart();
                randomLaneChooser.changeSleepTime(1300);
                state = GameState.PLAYING;
            }
            if (event.getKeyCode() == 'T') {
                view.playMusic("background");
                state = GameState.START;
            }
        }
    }
}
