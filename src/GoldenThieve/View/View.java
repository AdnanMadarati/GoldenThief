package GoldenThieve.View;

import GoldenThieve.Controller.IController;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import ddf.minim.*;
import java.util.ArrayList;

/**
 * The View class is the one responsible for drawing everything on the screen.
 * It has access to the Controller class to get the important data from it.
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class View extends PApplet implements IView {

    private IController controller;
    private Timer timer;
    private Minim minim;
    private AudioPlayer backgroundMusic, crashMusic, policeMusic, trafficMusic, stealMusic, speedMusic, invisibleMusic;
    private PImage bankImage, thieveImage, invisibleThieveImage, officerImage, thieveCarImage, sidewalkImage, roadImage, thieveGlowImage, thumbnailImage, wasdImage;
    private PImage blueCarImage, redCarImage, greenCarImage, yellowCarImage, blinkerImage;
    private PImage blueCarImageFlipped, redCarImageFlipped, greenCarImageFlipped, yellowCarImageFlipped;
    private PImage speedAbilityImage, invisibleAbilityImage, trafficAbilityImage;
    private ArrayList<PImage> abilityHUD;
    private int width, height, thieveImageFlip = 1, countDown = 10, blinkSwitcher = 0;
    private boolean thieveInvisible = false, showTimer = false;

    /**
     * The View constructor is used to create instances of the View class.
     *
     * @param width Width of the Window
     * @param height Height of the Window
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public View(int width, int height) {
        this.width = width;
        this.height = height;
        abilityHUD = new ArrayList<>();
    }

    /**
     * setController is used to store an instance of IController in a class variable to have access to Controller.
     *
     * @param c Instnace of IController
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setController(IController c) {
        controller = c;
    }

    /**
     * settings defines initial attributes of the window, mainly the width and height.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void settings() {
        size(width, height);
    }

    /**
     * setup is used to initialize alls class variables and everything that should be defined (mostly images and audios) before the drawing starts.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void setup() {
        frameRate(60);

        minim = new Minim(this);

        backgroundMusic = minim.loadFile("sounds/Itty Bitty 8 Bit.mp3");
        crashMusic = minim.loadFile("sounds/car-crash.mp3");
        policeMusic = minim.loadFile("sounds/police-called.mp3");
        trafficMusic = minim.loadFile("sounds/car-horn.mp3");
        stealMusic = minim.loadFile("sounds/cashing.mp3");
        invisibleMusic = minim.loadFile("sounds/transitionswoosh.mp3");
        speedMusic = minim.loadFile("sounds/speedup.mp3");

        speedMusic.setGain(-20);
        invisibleMusic.setGain(-20);
        stealMusic.setGain(-30);
        trafficMusic.setGain(-20);
        policeMusic.setGain(-30);
        crashMusic.setGain(-20);
        backgroundMusic.setGain(-20);

        timer = new Timer(1100);

        thumbnailImage = loadImage("images/thumbnail.jpg");
        thumbnailImage.resize(width, height);

        wasdImage = loadImage("images/wasd.png");
        wasdImage.resize(width/6, height/6);

        bankImage = loadImage("images/Bank-Icon.png");
        bankImage.resize(width/8, height/8);

        officerImage = loadImage("images/officer.png");
        officerImage.resize(width/9, height/9);

        thieveImage = loadImage("images/thieve.png");
        thieveImage.resize(width/12, height/12);

        thieveGlowImage = loadImage("images/ThieveGlow.png");
        thieveGlowImage.resize(width/10, height/10);

        invisibleThieveImage = loadImage("images/InvisibleThieve.png");
        invisibleThieveImage.resize(width/12, height/12);

        blueCarImage = loadImage("images/blue-car.png");
        blueCarImage.resize(width/16, height/8);
        blueCarImageFlipped = loadImage("images/blue-car-flipped.png");
        blueCarImageFlipped.resize(width/16, height/8);

        redCarImage = loadImage("images/red-car.png");
        redCarImage.resize(width/16, height/8);
        redCarImageFlipped = loadImage("images/red-car-flipped.png");
        redCarImageFlipped.resize(width/16, height/8);

        greenCarImage = loadImage("images/green-car.png");
        greenCarImage.resize(width/16, height/8);
        greenCarImageFlipped = loadImage("images/green-car-flipped.png");
        greenCarImageFlipped.resize(width/16, height/8);

        yellowCarImage = loadImage("images/yellow-car.png");
        yellowCarImage.resize(width/16, height/8);
        yellowCarImageFlipped = loadImage("images/yellow-car-flipped.png");
        yellowCarImageFlipped.resize(width/16, height/8);

        thieveCarImage = loadImage("images/truck.png");
        thieveCarImage.resize(width/16, height/8);

        sidewalkImage = loadImage("images/SideWalk.jpg");
        sidewalkImage.resize(width/4, height);

        roadImage = loadImage("images/Road.jpg");
        roadImage.resize(width/2, height);

        speedAbilityImage = loadImage("images/speedAbility.png");
        speedAbilityImage.resize(width/16, height/16);

        invisibleAbilityImage = loadImage("images/invisibleAbility.png");
        invisibleAbilityImage.resize(width/16, height/16);

        trafficAbilityImage = loadImage("images/trafficAbility.png");
        trafficAbilityImage.resize(width/16, height/16);

        blinkerImage = loadImage("images/blinker.png");
        blinkerImage.resize(width/16, height/8);
    }

    /**
     *  draw is where the main drawing happens, but in the MVC concept, it uses nextFrame from Controller to know, what should be drawn in the next frame.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void draw() {
        if (!backgroundMusic.isPlaying()) {
            backgroundMusic.rewind();
            backgroundMusic.play();
        }
        controller.nextFrame();
    }

    /**
     * drawStart draws the home screen of the game.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void drawStart() {
        image(thumbnailImage, 0, 0);
        textAlign(CENTER);
        fill(139,139,122);
        textSize(width/21);
        text("Golden Thief", width/4 - width/12, height/2);
        textSize(width/42);
        text("Press [Space] to Start", width/4 - width/12, height/2 + height/15);
        textSize(width/42);
        text("Press [T] for Tutorial/Home", width/4 - width/12, height/2 + height/10);
        image(wasdImage, width - width/4, height/2 - height/14);
    }

    /**
     * drawTutorial draws the tutorial of the game.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void drawTutorial() {
        image(thumbnailImage, 0, 0);
        textAlign(CENTER);
        fill(139,139,122);
        textSize(width/21);
        text("Abilities", width/4 - width/12, height/4);
        image(invisibleAbilityImage, width/4 - width/9, height/3);
        textSize(width/42);
        text("Makes you invisible for cars and officer", width/4 - width/16, height/3 + invisibleThieveImage.height + invisibleThieveImage.height/7);
        image(trafficAbilityImage, width/4 - width/9, height/2);
        textSize(width/42);
        text("Makes the cars stop", width/4 - width/12, height/2 + trafficAbilityImage.height + trafficAbilityImage.height/2);
        image(speedAbilityImage, width/4 - width/9, height - height/3);
        textSize(width/42);
        text("Increases your speed", width/4 - width/12, height - height/3 + speedAbilityImage.height + speedAbilityImage.height/2);

        textSize(width/21);
        text("Rules", width - width/4 + width/12, height/4);
        textSize(width/42);
        text("- Get gold from the bank", width - width/4 + width/12, height/3 + invisibleThieveImage.height + invisibleThieveImage.height/7);
        text("and back to the truck", width - width/4 + width/12, height/3 + invisibleThieveImage.height + invisibleThieveImage.height/2);
        textSize(width/42);
        text("- Do not let the cars hit you", width - width/4 + width/12, height/2 + trafficAbilityImage.height + trafficAbilityImage.height/2);
        textSize(width/42);
        text("- Do not let the officer see you", width - width/4 + width/12, height - height/3 + speedAbilityImage.height + speedAbilityImage.height/2);
    }

    /**
     * drawGameOver draws the gameover screen of the game.
     *
     * @param reason The reason why the player lost. Used to display as a text.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void drawGameOver(String reason) {
        fill(247, 237, 77, 80);
        rect(0, 0, width, height);
        textAlign(CENTER);
        fill(139,139,122);
        textSize(width/21);
        text(reason, width/2, height/2);
        textSize(width/42);
        text("Press [R] to restart", width/2, height/2 + height/20);
        textSize(width/42);
        text("Press [T] for Home", width/2, height/2 + height/12);
        pauseMusic();
    }

    /**
     * drawCar draws the cars.
     *
     * @param carPosX X position of the car
     * @param carPosY Y position of the car
     * @param carStartingPos Starting position of the car
     * @param carColor Color of the car
     * @param carIsDriving If the car is driving or not
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void drawCar(int carPosX, int carPosY, int carStartingPos, int carColor, boolean carIsDriving) {
        if (carIsDriving) {
            if (carStartingPos == height + 50) {
                switch (carColor) {
                    case 1:
                        image(blueCarImageFlipped, carPosX, carPosY);
                        break;
                    case 2:
                        image(redCarImageFlipped, carPosX, carPosY);
                        break;
                    case 3:
                        image(greenCarImageFlipped, carPosX, carPosY);
                        break;
                    case 4:
                        image(yellowCarImageFlipped, carPosX, carPosY);
                        break;
                }
                if (abilityHUD.contains(trafficAbilityImage)) {
                    if (blinkSwitcher % 2 == 0) {
                        image(blinkerImage, carPosX, carPosY - blueCarImage.height + blueCarImage.height/10);
                    }
                }
            } else {
                switch (carColor) {
                    case 1:
                        image(blueCarImage, carPosX, carPosY);
                        break;
                    case 2:
                        image(redCarImage, carPosX, carPosY);
                        break;
                    case 3:
                        image(greenCarImage, carPosX, carPosY);
                        break;
                    case 4:
                        image(yellowCarImage, carPosX, carPosY);
                        break;
                }
                if (abilityHUD.contains(trafficAbilityImage)) {
                    if (blinkSwitcher % 2 == 0) {
                        image(blinkerImage, carPosX, carPosY);
                    }
                }
            }
        }
    }

    /**
     * drawAbility draws the abilites.
     *
     * @param abilityIsThere If there is an ability or not
     * @param abilityType Type of the ability
     * @param abilityPosX X position of the ability
     * @param abilityPosY Y position of the ability
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void drawAbility(boolean abilityIsThere, String abilityType, int abilityPosX, int abilityPosY){
        if (abilityIsThere && !abilityType.isEmpty()) {
            switch (abilityType) {
                case "speed":
                    image(speedAbilityImage, abilityPosX, abilityPosY);
                    break;
                case "invisible":
                    image(invisibleAbilityImage, abilityPosX, abilityPosY);
                    break;
                case "traffic":
                    image(trafficAbilityImage, abilityPosX, abilityPosY);
                    break;
            }
        }
    }

    /**
     * drawThieve draws the thieve.
     *
     * @param thievePosX X position of the thieve
     * @param thievePosY Y position of the thieve
     * @param thieveHasGold If the thieve is holding gold or not
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void drawThieve(int thievePosX, int thievePosY, boolean thieveHasGold) {
        scale(thieveImageFlip, 1);
        if (thieveHasGold) image(thieveGlowImage, thieveImageFlip * thievePosX - thieveImage.width/2, thievePosY - thieveImage.height/6);
        if (!thieveInvisible) {
            image(thieveImage, thieveImageFlip  * thievePosX - thieveImage.width/2, thievePosY);
        } else {
            image(invisibleThieveImage, thieveImageFlip  * thievePosX - invisibleThieveImage.width/2, thievePosY);
        }
    }

    /**
     * drawGame draws the gameplay screen.
     *
     * @param officerPosX X position of the officer
     * @param officerPoxY Y position of the officer
     * @param goldCounter How much gold the thieve collected
     * @param goldHeld How muchn gold the thieve is holding
     * @param thieveCarPosX X position of the thieve
     * @param thieveCarPosY Y position of the thieve
     * @param showOfficer If the officer is there or not
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void drawGame(int officerPosX, int officerPoxY, int goldCounter, int goldHeld, int thieveCarPosX, int thieveCarPosY, boolean showOfficer) {
        background(200);

        image(sidewalkImage, 0, 0);
        image(sidewalkImage, width - width/4, 0);
        image(roadImage, width/4, 0);

        strokeWeight(2);
        fill(139,115,85);
        rect(width/90, height/50, width/10, height/15, 10);
        fill(255);
        textSize(width/55);
        text("Holding: " + goldHeld, width/18, height/14);
        textSize(width/42);
        text("Gold: " + goldCounter, width/18, height/20);

        fill(0);
        stroke(0);
        strokeWeight(2);

        fill(139,115,85);
        circle(width/14, height - height/8, 60);
        rect(width/37, height - height/8 + height/80, width/11, width/11, 10);

        if (showOfficer) {
            image(officerImage, officerPosX, officerPoxY);
            if (officerPosX == width - width/4) {
                stroke(255, 0, 0);
                line(width - width/4, officerPoxY + width/3, width, officerPoxY + width/3 );
            } else {
                stroke(255, 0, 0);
                line(0, officerPoxY + width/3, width/4, officerPoxY + width/3 );
            }
        }

        fill(244,164,96);
        stroke(0);
        strokeWeight(2);
        rect(width - (bankImage.width + bankImage.width/4), height/2 - bankImage.height, (bankImage.width + bankImage.width/4), height/2 - bankImage.height*2, 5);

        image(bankImage, width - bankImage.width, height/2 - bankImage.height/2);
        image(thieveCarImage, thieveCarPosX, thieveCarPosY);

        for (int i = 0; i < abilityHUD.size(); i++) {
            image(abilityHUD.get(i), width/24, height - height/11 - height/100);
        }

        if (showTimer) {
            if (countDown == 0) {
                showTimer = false;
                countDown = 10;
            }
            if (timer.complete()) {
                countDown--;
                timer.start();
            }
            fill(255);
            textSize(width/23);
            text(countDown, width/14, height - height/11 - height/50 - height/130);
        }
    }

    /**
     * @return Returns the width of the car.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public int getCarWidth() {
        return blueCarImage.width;
    }

    /**
     * @return Returns the height of the car.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public int getCarHeight() {
        return blueCarImage.height;
    }

    /**
     * @return Returns the blinkSwitcher current value.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public int getBlinkSwitcher() {
        return blinkSwitcher;
    }

    /**
     * @return Returns the width of the ability.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public int getAbilityWidth() {
        return speedAbilityImage.width;
    }

    /**
     * @return Returns the height of the ability.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public int getAbilityHeight() {
        return speedAbilityImage.height;
    }

    /**
     * @return Returns the flip of the thieve image (facing left or right).
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public int getThieveImageFlip() {
        return thieveImageFlip;
    }

    /**
     * @return Returns the height of the thieve.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public int getThieveHeight() {
        return thieveImage.height;
    }

    /**
     * @return Returns the width of the thieve.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public int getThieveWidth() {
        return thieveImage.width;
    }

    /**
     * @return Returns the width of the thieve's car.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */


    @Override
    public int getThieveCarImageWidth() {
        return thieveCarImage.width;
    }

    /**
     * @return Returns the height of the thieve's car.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public int getThieveCarImageHeight() {
        return thieveCarImage.height;
    }

    /**
     * @return Returns true if the thieve is invisible otherwise false.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public boolean getThieveInvisible() {
        return thieveInvisible;
    }

    /**
     * setShowTimer updates the state of showTimer.
     *
     * @param state The new boolean state for showTimer
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void setShowTimer(boolean state) {
        showTimer = state;
    }

    /**
     * setThieveInvisible updates the state of thieveInvisible.
     *
     * @param state The new boolean state for thieveInvisible
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void setThieveInvisible(boolean state) {
        thieveInvisible = state;
    }

    /**
     * increaseBlinkSwitcher increases blinkSwitcher by one until 10 and then resets it.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void increaseBlinkSwitcher() {
        blinkSwitcher++;
        if (blinkSwitcher == 10) blinkSwitcher = 0;
    }

    /**
     * pauseMusic pauses the music.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void pauseMusic() {
        backgroundMusic.pause();
        stealMusic.pause();
    }

    /**
     * playMusic plays the wanted music.
     *
     * @param type Which music should be played.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void playMusic(String type) {
        switch (type) {
            case "steal":
                stealMusic.rewind();
                stealMusic.play();
                break;
            case "background":
                backgroundMusic.rewind();
                backgroundMusic.play();
                break;
            case "crash":
                crashMusic.rewind();
                crashMusic.play();
                break;
            case "caught":
                policeMusic.rewind();
                policeMusic.play();
                break;
            case "traffic":
                trafficMusic.rewind();
                trafficMusic.play();
                break;
            case "invisible":
                invisibleMusic.rewind();
                invisibleMusic.play();
                break;
            case "speed":
                speedMusic.rewind();
                speedMusic.play();
                break;
        }
    }

    /**
     * flipThieve flips the image of the thieve.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void flipThieve() {
        thieveImageFlip *= -1;
    }

    /**
     * addAbilityToHUD adds the equipped ability icon in the ability HUD.
     *
     * @param abilityType Type of the equipped ability
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void addAbilityToHUD(String abilityType) {
        switch (abilityType) {
            case "speed":
                abilityHUD.add(speedAbilityImage);
                break;
            case "invisible":
                abilityHUD.add(invisibleAbilityImage);
                break;
            case "traffic":
                abilityHUD.add(trafficAbilityImage);
                break;
        }
    }

    /**
     * removeAbilityToHUD removes the ability icon from the ability HUD.
     *
     * @param abilityType Type of the ability
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void removeAbilityToHUD(String abilityType) {
        switch (abilityType) {
            case "speed":
                abilityHUD.remove(speedAbilityImage);
                break;
            case "invisible":
                abilityHUD.remove(invisibleAbilityImage);
                break;
            case "traffic":
                abilityHUD.remove(trafficAbilityImage);
                break;
        }
    }

    /**
     * restarts all stats and counters to their initial values.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void restart() {
        showTimer = false;
        thieveInvisible = false;
        if (thieveImageFlip == -1) thieveImageFlip = 1;
        blinkSwitcher = 0;
        countDown = 10;
        abilityHUD.clear();
    }

    /**
     * keyPressed passes the user input to the controller to handle it.
     *
     * @param event key pressed by the user
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    @Override
    public void keyPressed(KeyEvent event) {
        controller.moveThieve(event);
    }
}
