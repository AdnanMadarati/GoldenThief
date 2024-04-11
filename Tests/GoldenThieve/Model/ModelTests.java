package GoldenThieve.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTests {
    private Model model;

    @BeforeEach
    public void setup() {
        model = new Model(1000, 1000);
    }

    @Test
    public void testStartGame() {
        assertNotNull(model.getOfficer());
        assertNotNull(model.getThieve());
        assertNotNull(model.getAbility());
        assertNotNull(model.getCars());
        assertNotNull(model.getThieveCar());
    }

    @Test
    public void testMoveCars() {
            model.chooseLane();

            assertTrue(model.getCars()[0].getPosY() == -150 || model.getCars()[0].getPosY() == 1050);

            model.moveCars(1000/12, 1000/12, 1000/16, 1000/8, 1, false);

            assertTrue(model.getCars()[0].getPosY() == -144 || model.getCars()[0].getPosY() == -143 || model.getCars()[0].getPosY() == 1044 || model.getCars()[0].getPosY() == 1043);
    }

    @Test
    public void testIsCaught() {
        assertFalse(model.isCaught(1000/12));

        model.setShowOfficer(true);
        model.getOfficer().updateOfficerPos(400);
        model.getThieve().setPosX(750);

        assertTrue(model.isCaught(1000/12));
    }

    @Test
    public void testCheckCrash() {
        assertFalse(model.getCars()[0].getIsDriving());

        model.getCars()[0].setIsDriving(true);

        assertTrue(model.getCars()[0].getIsDriving());

        model.getCars()[0].setLane(0);
        model.getCars()[0].setPositions(1000, 1000);
        model.getCars()[0].setSpeedMultiplier(false);

        assertFalse(model.checkCrash(model.getCars()[0], 1000/12, 1000/12, 1000/16, 1000/8, -1));

        model.getCars()[0].updateCarPosPositive(650, 1000);
        model.getThieve().updateThievePosX(280);
        model.getThieve().updateThievePosY(500);

        assertTrue(model.checkCrash(model.getCars()[0], 1000/12, 1000/12, 1000/16, 1000/8, -1));

        model.getCars()[1].setIsDriving(true);
        model.getCars()[1].setLane(2);
        model.getCars()[1].setPositions(1000, 1000);
        model.getCars()[1].setSpeedMultiplier(false);

        assertFalse(model.checkCrash(model.getCars()[1], 1000/12, 1000/12, 1000/16, 1000/8, 1));

        model.getCars()[1].updateCarPosNegative(-550, 1000);
        model.getThieve().updateThievePosX(1000/2 + 1000/32);
        model.getThieve().updateThievePosY(500);

        assertTrue(model.checkCrash(model.getCars()[1], 1000/12, 1000/12, 1000/16, 1000/8, 1));

    }

    @Test
    public void testCheckBankCollision() {
        assertFalse(model.checkBankCollision(1000/12));

        model.getThieve().updateThievePosX(810);

        assertTrue(model.checkBankCollision(1000/12));
    }

    @Test
    public void testCheckTruckCollision() {
        assertFalse(model.checkTruckCollision(1000/12, 1000/16, 1000/8));

        model.getThieve().updateThievePosX(1000/12);

        assertTrue(model.checkTruckCollision(1000/12, 1000/16, 1000/8));
    }

    @Test
    public void testCheckAbilityCollision() {
        assertTrue(model.getAbility().getAbilityIsThere());
        assertFalse(model.checkAbilityCollision(1000/12, 1000/12, 1000/16, 1000/16, 1));

        model.getThieve().updateThievePosX(1000/5);
        model.getThieve().updateThievePosY(1000/5);

        assertTrue(model.checkAbilityCollision(1000/12, 1000/12, 1000/16, 1000/16, 1));
        assertTrue(model.checkAbilityCollision(1000/12, 1000/12, 1000/16, 1000/16, -1));

    }

    @Test
    public void testRestart() {
        model.startGame();
        for (int i = 0; i < 3; i++) model.moveCars(1000/12, 1000/12, 1000/16, 1000/8, 1, false);

        model.restart();

        assertEquals(1000/8, model.getThieve().getPosX());
        assertEquals(1000/2, model.getThieve().getPosY());
        assertEquals(0, model.getGoldHeld());
        assertEquals(0, model.getGoldCounter());
        assertEquals(6, model.getCarsSpeed());
        assertEquals(8, model.getThieve().getThieveSpeed());

        assertFalse(model.officerThere());
        assertFalse(model.getThieveHasGold());
        assertFalse(model.getTrafficIsRed());
        assertFalse(model.getAbility().getAbilityIsThere());

        for (Car c : model.getCars()) assertFalse(c.getIsDriving());

    }

    @Test
    public void testChooseLanes() {
        for (Car c : model.getCars()) {
            assertFalse(c.getIsDriving());
            assertTrue(c.getLane() == 0);
            assertTrue(c.getStartingPos() == 0);
            assertTrue(c.getPosX() == 0 && c.getPosY() == 0);

            model.chooseLane();

            assertTrue(c.getIsDriving());
            assertTrue(c.getLane() >= 0 && c.getLane() < 4);
            assertTrue(c.getStartingPos() == -150 || c.getStartingPos() == 1050);
            assertTrue(c.getPosX() != 0 && c.getPosY() != 0);
            assertTrue(c.getCarColor() > 0 && c.getCarColor() <= 4);
        }
    }

    @Test
    public void testIncreaseGoldCounter() {
        assertEquals(0, model.getGoldHeld());
        assertFalse(model.getThieveHasGold());

        model.increaseGoldCounter(10);

        assertEquals(10, model.getGoldHeld());
        assertTrue(model.getThieveHasGold());
    }

    @Test
    public void testSaveStolenGold() {
        assertEquals(0, model.getGoldCounter());

        model.increaseGoldCounter(10);
        model.saveStolenGold();

        assertEquals(10, model.getGoldCounter());
        assertEquals(0, model.getGoldHeld());
        assertFalse(model.getThieveHasGold());
    }

    @Test
    public void testOfficerThere() {
        assertFalse(model.officerThere());

        model.setShowOfficer(true);

        assertTrue(model.officerThere());
    }

    @Test
    public void testGetTrafficIsRed() {
        assertFalse(model.getTrafficIsRed());

        model.setTrafficIsRed(true);

        assertTrue(model.getTrafficIsRed());
    }

    @Test
    public void testGetCarsSpeed() {
        assertEquals(6, model.getCarsSpeed());

        model.setCarsSpeed(8);

        assertEquals(8, model.getCarsSpeed());

        model.setCarsSpeed(10);

        assertEquals(10, model.getCarsSpeed());
    }

    @Test
    public void testAbilityRandomizePositions() {
        assertEquals("", model.getAbility().getAbilityType());

        model.getAbility().randomizePositions();

        assertTrue(model.getAbility().getAbilityType() == "speed" || model.getAbility().getAbilityType() == "invisible" || model.getAbility().getAbilityType() == "traffic");
    }

    @Test
    public void testOfficerResetPos() {
        assertTrue(model.getOfficer().getPosX() == 750 && model.getOfficer().getPosY() == -200);

        model.getOfficer().resetPos(1000);

        assertTrue(model.getOfficer().getPosX() == 750 && model.getOfficer().getPosY() == -200 || model.getOfficer().getPosX() == 1000/7 && model.getOfficer().getPosY() == -200);
    }

    @Test
    public void testThieveCarGetPosY() {
        assertEquals(460, model.getThieveCar().getPosY());
    }
}
