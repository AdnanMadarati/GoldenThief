package GoldenThieve.Model;

/**
 * The Officer class includes all the important data for the officer.
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class Officer {

    private int posX, posY;

    /**
     * The Officer constuctor is used to create instances of the Officer class.
     *
     * @param x X position of the officer
     * @param y Y position of the officer
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public Officer(int x, int y) {
        posX = x;
        posY = y; }

    /**
     * Updates the Y position of the officer.
     *
     * @param newPos Value to be added to the Y position
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void updateOfficerPos(int newPos) {
        posY += newPos;
    }

    /**
     * Resets the position of the officer
     *
     * @param width Width of the window
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void resetPos(int width) {
        int random = (int) (Math.random() * 2);
        if (random == 0) {
            posX = width - width/4;
            posY = -200; } else {
            posX = width/7;
            posY = -200; } }

    /**
     * @return Returns the X position of the officer.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getPosX() { return posX; }

    /**
     * @return Returns the Y Position of the officer.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getPosY() { return posY; } }
