package GoldenThieve.Model;

/**
 * The Thieve class includes all the important data for the theive
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class Thieve {
    private int posX, posY, thieveSpeed = 8;

    /**
     * The Thieve constuctor is used to create instances of the Thievce class.
     *
     * @param x X position of the thieve
     * @param y Y position of the Thieve
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public Thieve(int x, int y) {
        posX = x;
        posY = y; }

    /**
     * @return Returns the X position of the thieve.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getPosX() {
        return posX;
    }

    /**
     * @return Returns the Y position of the thieve.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getPosY() {
        return posY;
    }

    /**
     * Sets the value of the X position
     *
     * @param posX The new X position
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Sets the value of the Y position
     *
     * @param posY The new Y position
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * @return Returns the speed of the thieve
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getThieveSpeed() {
        return thieveSpeed;
    }

    /**
     * Updates the X position of the thieve.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void updateThievePosX(int newPos) {
        posX = newPos;
    }

    /**
     * Updates the Y position of the thieve.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void updateThievePosY(int newPos) {
        posY = newPos;
    }

    /**
     * Sets the speed of the thieve.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setThieveSpeed(int newSpeed) { thieveSpeed = newSpeed; } }
