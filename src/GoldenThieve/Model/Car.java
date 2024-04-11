package GoldenThieve.Model;

/**
 * The Car class includes all the important data for each car like their position or colors.
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class Car {
    private int posX, posY, lane, carColor, startingPos, speedMultiplier = 0;
    private boolean isDriving = false;
    private static boolean speedMultiplierIsOn = false;

    /**
     * increases the speed of a car if speedMultiplierIsOn is true.
     *
     * @param state The new boolean value for speedMultiplierIsOn
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setSpeedMultiplier(boolean state) {
        speedMultiplierIsOn = state;
        if (speedMultiplierIsOn) {
            speedMultiplier = 1;
        } else { speedMultiplier = 0; } }

    /**
     * Sets the positions of each car based on their lane.
     *
     * @param width Width of the window
     * @param height Height of the window
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setPositions(int width, int height) {
        switch (getLane()) {
            case 0: this.posX = width/2 - width/4 + width/30;
                    this.posY = -150;
                    this.startingPos = -150;
                    break;
            case 1: this.posX = width/2 - width/10;
                    this.posY = -150;
                    this.startingPos = -150;
                    break;
            case 2: this.posX = width/2 + width/32;
                    this.posY = height + 50;
                    this.startingPos = height + 50;
                    break;
            case 3: this.posX = width/2 + width/7;
                    this.posY = height + 50;
                    this.startingPos = height + 50;
                    break; } }

    /**
     * Updates the position of the cars that are moving down.
     *
     * @param newPos Value to be added to the current Y position
     * @param limit When the car should stop
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void updateCarPosPositive(int newPos, int limit) {
        if (isDriving) {
            if (posY < limit) posY += newPos + speedMultiplier;
            else {
                isDriving = false;
                posY = 0;
                speedMultiplier = 0;
                speedMultiplierIsOn = false; } } }

    /**
     * Updates the position of the cars that are moving up.
     *
     * @param newPos Value to be added to the current Y position
     * @param limit When the car should stop
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void updateCarPosNegative(int newPos, int limit) {
        if (isDriving) {
            if (posY > limit) posY += newPos - speedMultiplier;
            else {
                isDriving = false;
                posY = 0;
                speedMultiplier = 0;
                speedMultiplierIsOn = false; } } }

    /**
     * Sets the color of the car.
     *
     * @param color Color code of the car (1 = blue, 2 = red, ...)
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setCarColor(int color) { carColor = color; }

    /**
     * @return Returns the lane of the car.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getLane() { return lane; }

    /**
     * @return Returns the color of the car.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getCarColor() { return carColor; }

    /**
     * @return Returns the starting position of the car.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getStartingPos() { return startingPos; }

    /**
     * @return Returns the X position of the car.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getPosX() {
        return posX;
    }

    /**
     * @return Returns the Y position of the car.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getPosY() {
        return posY;
    }

    /**
     * @return Returns true if isDriving is true otherwise false.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean getIsDriving() { return isDriving; }

    /**
     * Updates the value of isDriving.
     *
     * @param state The new boolean state for isDriving
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setIsDriving(boolean state) { isDriving = state; }

    /**
     * Sets the lane for the car
     *
     * @param lane Lane index (0 = first lane, 1 = second lane, ...)
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setLane(int lane) { this.lane = lane; } }
