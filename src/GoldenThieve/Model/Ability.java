package GoldenThieve.Model;

/**
 * Ability class includes all important data for the ability like it's position or type.
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class Ability {
    private int posX, posY, width, height;
    private String abilityType = "";
    private boolean abilityIsThere = true;

    /**
     * The Ability constructor is used to create insctances of the Ability class.
     *
     * @param width The width of the window
     * @param height The height of the window
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public Ability(int width, int height) {
        this.width = width;
        this.height = height;
        posX = width/5;
        posY = height/5; }

    /**
     * Randomizes the position and the type of the ability.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void randomizePositions() {
        posX += (int) (Math.random() * width/2);
        posY += (int) (Math.random() * height/2);

        int random = (int) (Math.random() * 3);
        switch (random) {
            case 0: abilityType = "speed";
                    break;
            case 1: abilityType = "invisible";
                    break;
            case 2: abilityType = "traffic";
                    break; } }

    /**
     * @return Returns the X position of the ability.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getPosX() { return posX; }

    /**
     * @return Returns the Y position of the ability
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getPosY() { return posY; }

    /**
     * @return Returns true if abilityIsThere is true otherwise false.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean getAbilityIsThere() { return abilityIsThere; }

    /**
     * @return Returns the type of the ability
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public String getAbilityType() { return abilityType; }

    /**
     * Updates the value of abilityIsThere.
     *
     * @param state The new boolean state for abilityIsThere
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setAbilityIsThere(boolean state) {
        abilityIsThere = state;
        if (!abilityIsThere) {
            posX = width/5;
            posY = height/5; } } }


