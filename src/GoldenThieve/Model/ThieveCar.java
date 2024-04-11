package GoldenThieve.Model;

/**
 * The ThieveCar class includes data for the thieve's car
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class ThieveCar {
    private int posX, posY;

    /**
     * The ThieveCar constuctor is used to create instacnes of the TheiveCar class.
     *
     * @param posX X position of the car
     * @param posY Y position of the car
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public ThieveCar(int posX, int posY) {
        this.posX = posX;
        this.posY = posY; }

    /**
     * @return Returns the Y position.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getPosY() { return posY; }

    /**
     * @return Returns the X position.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getPosX() { return posX; } }
