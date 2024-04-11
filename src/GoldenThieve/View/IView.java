package GoldenThieve.View;

/**
 * IView is the interface for the View class that has the methods that are visible for Controller
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public interface IView {
    void drawStart();
    void drawTutorial();
    void drawGameOver(String reason);
    void drawGame(int officerPosX, int officerPoxY, int goldCounter, int goldHeld, int thieveCarPosX, int thieveCarPosY, boolean showOfficer);
    void drawThieve(int thievePosX, int thievePosY, boolean thieveHasGold);
    void drawCar(int posX, int posY, int startingPos, int carColor, boolean isDriving);
    void drawAbility(boolean abilityIsThere, String abilityType, int abilityPosX, int abilityPosY);

    int getThieveWidth();
    int getThieveHeight();
    int getThieveCarImageWidth();
    int getThieveCarImageHeight();
    int getThieveImageFlip();
    int getCarWidth();
    int getCarHeight();
    int getBlinkSwitcher();
    int getAbilityWidth();
    int getAbilityHeight();
    boolean getThieveInvisible();

    void setThieveInvisible(boolean state);
    void setShowTimer(boolean state);

    void addAbilityToHUD(String abilityType);
    void removeAbilityToHUD(String abilityType);
    void restart();
    void playMusic(String type);
    void pauseMusic();
    void flipThieve();
    void increaseBlinkSwitcher();
}
