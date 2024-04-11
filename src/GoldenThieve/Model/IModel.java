package GoldenThieve.Model;

/**
 * IModel is the interface for the Model class that has the methods that are visible for Controler
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public interface IModel {
    void startGame();

    Officer getOfficer();
    Thieve getThieve();
    Ability getAbility();
    Car[] getCars();
    ThieveCar getThieveCar();
    int getGoldCounter();
    int getGoldHeld();
    int getCarsSpeed();
    boolean getTrafficIsRed();
    boolean getThieveHasGold();

    void increaseGoldCounter(int num);
    boolean moveCars(int thieveWidth, int thieveHeight, int carWidth, int carHeight, int flip, boolean thieveIsInvisible);
    boolean checkCrash(Car c, int thieveWidth, int thieveHeight, int carWidth, int carHeight, int flip);
    boolean checkAbilityCollision(int thieveWidth, int thieveHeight, int abilityWidth, int abilityHeight, int flip);
    void chooseLane();
    boolean officerThere();
    boolean isCaught(int thieveWidth);
    boolean checkBankCollision(int thieveHeight);
    boolean checkTruckCollision(int thieveHeight, int thieveCarWidth, int thieveCarHeight);
    void saveStolenGold();
    void restart();

    void setShowOfficer(boolean state);
    void setTrafficIsRed(boolean state);

}
