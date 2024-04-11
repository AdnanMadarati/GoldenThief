package GoldenThieve.Model;

/**
 * The Model class has all necessary data for the logic of the game like positions, speeds, instances of main game components, ...
 * The Model class has no access to any other class outside his package.
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class Model implements IModel {

    private int width, height, selectedCar = 0, goldHeld = 0, goldCounter = 0, carsSpeed = 6;
    private boolean thieveHasGold = false, trafficIsRed = false, showOfficer = false;
    private Officer officer;
    private Thieve thieve;
    private Car[] cars;
    private Ability ability;
    private ThieveCar thieveCar;

    /**
     * The Model constructor is used to create instances of Model.
     * It also creates an array of Cars and starts the game.
     *
     * @param width Width of the window
     * @param height Height of the window
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public Model(int width, int height) {
        this.width = width;
        this.height = height;

        cars = new Car[4];

        startGame();
    }

    /**
     * startGame creates and initializes all important instances and components of the game like the officer, cars, ...
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void startGame() {
        officer = new Officer(width - width/4, -200);

        thieve = new Thieve(width/8, height/2);

        ability = new Ability(width, height);

        cars[0] = new Car();
        cars[0].setCarColor(1);
        cars[1] = new Car();
        cars[1].setCarColor(2);
        cars[2] = new Car();
        cars[2].setCarColor(3);
        cars[3] = new Car();
        cars[3].setCarColor(4);

        thieveCar = new ThieveCar(width/128, height/2 - height/25);
    }

    /**
     * moveCars is the method that moves the four cars in the array of Cars and controls their speeds.
     *
     * @param thieveWidth Width of the thieve image
     * @param thieveHeight Height of thieve image
     * @param carWidth Width of the car image
     * @param carHeight Height of the car image
     * @param flip Thieve image flip (which way the image is facing)
     * @param isThieveInvisible Checks if the thieve is visible
     *
     * @return Returns true if the checkCrash method returns true otherwise false.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean moveCars(int thieveWidth, int thieveHeight, int carWidth, int carHeight, int flip, boolean isThieveInvisible) {
        int chance = (int) (Math.random() * 10);

        if (chance == 9) {
            for (Car c : cars) {
                if (!c.getIsDriving()) {
                    c.setSpeedMultiplier(true);
                    break; } } }

        for (int i = 0; i < cars.length; i++) {
            if (cars[i].getStartingPos() == -150) cars[i].updateCarPosPositive(carsSpeed, height + 50);
            if (cars[i].getStartingPos() == height + 50) cars[i].updateCarPosNegative(-1 * carsSpeed, -150);
            if (!isThieveInvisible) {
                if (checkCrash(cars[i], thieveWidth, thieveHeight, carWidth, carHeight, flip)) return true;
            }
        }
        return false;
    }

    /**
     * isCought checks the thieve's and the officer's positions to see if the thieve is in the officer's vision.
     *
     * @param thieveWidth Width of the thieve image
     *
     * @return Returns true if the thieve is in vision of the officer otherwise false.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean isCaught(int thieveWidth) {
        if (thieve.getPosX() + thieveWidth/2 - 10 >= width - width/4 && thieve.getPosY() >= officer.getPosY() && thieve.getPosY() <= officer.getPosY() + width/3 && officer.getPosX() == width - width/4|| thieve.getPosX() <= width/4 + width/28 && thieve.getPosY() >= officer.getPosY() && thieve.getPosY() <= officer.getPosY() + width/3 && officer.getPosX() == width/7) return true;
        return false;
    }

    /**
     * checkCrash checks if the thieve has been hit by a car.
     *
     * @param c Car instance
     * @param thieveWidth Width of the thieve image
     * @param thieveHeight Height of thieve image
     * @param carWidth Width of the car image
     * @param carHeight Height of the car image
     * @param flip Thieve image flip (which way the image is facing)
     *
     * @return Returns true if the car hits the thieve.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean checkCrash(Car c, int thieveWidth, int thieveHeight, int carWidth, int carHeight, int flip) {
        if (c.getIsDriving()) {
            if (flip == 1) {
                if (thieve.getPosX() - thieveWidth/5 >= c.getPosX() && thieve.getPosX() - thieveWidth/5 <= c.getPosX() + carWidth && thieve.getPosY() >= c.getPosY() && thieve.getPosY() <= c.getPosY() + carHeight - carHeight/10 || thieve.getPosX() + thieveWidth/2 - thieveWidth/12 >= c.getPosX() && thieve.getPosX() + thieveWidth/2 - thieveWidth/12 <= c.getPosX() + carWidth &&  thieve.getPosY() >= c.getPosY() && thieve.getPosY() <= c.getPosY() + carHeight - carHeight/10 || thieve.getPosX() - thieveWidth/5 >= c.getPosX() && thieve.getPosX() - thieveWidth/5 <= c.getPosX() + carWidth && thieve.getPosY() + thieveHeight - thieveHeight/10 >= c.getPosY() && thieve.getPosY() + thieveHeight - thieveHeight/10 <= c.getPosY() + carHeight - carHeight/10 || thieve.getPosX() + thieveWidth/2 - thieveWidth/12 >= c.getPosX() && thieve.getPosX() + thieveWidth/2 - thieveWidth/12 <= c.getPosX() + carWidth && thieve.getPosY() + thieveHeight - thieveHeight/10 >= c.getPosY() && thieve.getPosY() + thieveHeight - thieveHeight/10 <= c.getPosY() + carHeight - carHeight/10) return true;
            } else {
                if (thieve.getPosX() - thieveWidth/5 - thieveWidth/6 >= c.getPosX() && thieve.getPosX() - thieveWidth/5 - thieveWidth/6 <= c.getPosX() + carWidth && thieve.getPosY() >= c.getPosY() && thieve.getPosY() <= c.getPosY() + carHeight - carHeight/10 || thieve.getPosX() + thieveWidth/2 - thieveWidth/12 - thieveWidth/6 >= c.getPosX() && thieve.getPosX() + thieveWidth/2 - thieveWidth/12 - thieveWidth/6 <= c.getPosX() + carWidth &&  thieve.getPosY() >= c.getPosY() && thieve.getPosY() <= c.getPosY() + carHeight - carHeight/10 || thieve.getPosX() - thieveWidth/5 - thieveWidth/6 >= c.getPosX() && thieve.getPosX() - thieveWidth/5 - thieveWidth/6 <= c.getPosX() + carWidth && thieve.getPosY() + thieveHeight - thieveHeight/10 >= c.getPosY() && thieve.getPosY() + thieveHeight - thieveHeight/10 <= c.getPosY() + carHeight - carHeight/10 || thieve.getPosX() + thieveWidth/2 - thieveWidth/12 - thieveWidth/6 >= c.getPosX() && thieve.getPosX() + thieveWidth/2 - thieveWidth/12 - thieveWidth/6 <= c.getPosX() + carWidth && thieve.getPosY() + thieveHeight - thieveHeight/10 >= c.getPosY() && thieve.getPosY() + thieveHeight - thieveHeight/10 <= c.getPosY() + carHeight - carHeight/10) return true;
            }
        }
        return false;
    }

    /**
     * chooseLane takes a car from the array of Cars and gives it a random lane from 0-3 and a starting position.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void chooseLane() {
        if (!cars[selectedCar].getIsDriving()) {
            cars[selectedCar].setIsDriving(true);
            cars[selectedCar].setLane((int) (Math.random() * 4));
            cars[selectedCar].setPositions(width, height);
            selectedCar++;
            if (selectedCar > 3) selectedCar = 0;
        } else {
            selectedCar++;
            if (selectedCar > 3) selectedCar = 0;
        }
    }

    /**
     * checkBankCollision checks if the thieve comes in contact with the bank.
     *
     * @param thieveHeight Height of the thieve image
     *
     * @return Returns true if the thieve comes in contact with the bank otherwise false.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean checkBankCollision(int thieveHeight) {
        if (thieve.getPosX() + thieveHeight/2 >= width - width/7 - width/66 && thieve.getPosY() >= height/3 - height/30 && thieve.getPosY() <= height - height/3 - height/16 || thieve.getPosX() >= width - width/6 - width/66 && thieve.getPosX() <= width && thieve.getPosY() - thieveHeight/4 >= height/3 + height/24 && thieve.getPosY() - thieveHeight/4 + thieveHeight/5 <= height - height/3 - height/24 || thieve.getPosX() >= width - width/6 - width/66 && thieve.getPosX() <= width && thieve.getPosY() + thieveHeight >= height/3 + height/24 && thieve.getPosY() + thieveHeight <= height - height/3 - height/24) return true;
        return false;
    }

    /**
     * checkTruckCollsion checks if the thieve comes in contact with his truck.
     *
     * @param thieveHeight Height of the thieve image
     * @param thieveCarHeight Height of the thieveCar image
     * @param thieveCarWidth Width of the thieveCar image
     *
     * @return Returns true if the thieve comes in contact with his truck otherwise false.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean checkTruckCollision(int thieveHeight, int thieveCarWidth, int thieveCarHeight) {
        if (thieve.getPosX() >= 0 && thieve.getPosX() - thieveHeight/4 <= thieveCar.getPosX() + thieveCarWidth + thieveCarWidth/2 && thieve.getPosY() + thieveHeight >= height/2 - height/48 && thieve.getPosY() <= height/2 - height/48 + thieveCarHeight) return true;
        return false;
    }

    /**
     * checkAbilityCollision checks if the thieve touched an ability.
     *
     * @param thieveWidth Width of the thieve image
     * @param thieveHeight Height of thieve image
     * @param abilityHeight Height of the ability image
     * @param abilityWidth Width of the ability image
     * @param flip Thieve image flip (which way the image is facing)
     *
     * @return Returns true if the thieve touches the ability otherwise false.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean checkAbilityCollision(int thieveWidth, int thieveHeight, int abilityWidth, int abilityHeight, int flip) {
        if (ability.getAbilityIsThere()) {
            if (flip == 1) {
                if (thieve.getPosX() - thieveWidth/5 >= ability.getPosX() && thieve.getPosX() - thieveWidth/5 <= ability.getPosX() + abilityWidth && thieve.getPosY() >= ability.getPosY() - 10 && thieve.getPosY() <= ability.getPosY() + abilityHeight + 10 || thieve.getPosX() + thieveWidth/2 - thieveWidth/12 >= ability.getPosX() && thieve.getPosX() + thieveWidth/2 - thieveWidth/12 <= ability.getPosX() + abilityWidth &&  thieve.getPosY() >= ability.getPosY() - 10 && thieve.getPosY() <= ability.getPosY() + abilityHeight + 10 || thieve.getPosX() - thieveWidth/5 >= ability.getPosX() && thieve.getPosX() - thieveWidth/5 <= ability.getPosX() + abilityWidth && thieve.getPosY() + thieveHeight - thieveHeight/10 >= ability.getPosY() - 10 && thieve.getPosY() + thieveHeight - thieveHeight/10 <= ability.getPosY() + abilityHeight + 10 || thieve.getPosX() + thieveWidth/2 - thieveWidth/12 >= ability.getPosX() && thieve.getPosX() + thieveWidth/2 - thieveWidth/12 <= ability.getPosX() + abilityWidth && thieve.getPosY() + thieveHeight - thieveHeight/10 >= ability.getPosY() - 10 && thieve.getPosY() + thieveHeight - thieveHeight/10 <= ability.getPosY() + abilityHeight + 10) return true;
            } else {
                if (thieve.getPosX() - thieveWidth/5 - thieveWidth/6 >= ability.getPosX() && thieve.getPosX() - thieveWidth/5 - thieveWidth/6 <= ability.getPosX() + abilityWidth && thieve.getPosY() >= ability.getPosY() - 10 && thieve.getPosY() <= ability.getPosY() + abilityHeight + 10 || thieve.getPosX() + thieveWidth/2 - thieveWidth/12 - thieveWidth/6 >= ability.getPosX() && thieve.getPosX() + thieveWidth/2 - thieveWidth/12 - thieveWidth/6 <= ability.getPosX() + abilityWidth &&  thieve.getPosY() >= ability.getPosY() - 10 && thieve.getPosY() <= ability.getPosY() + abilityHeight + 10 || thieve.getPosX() - thieveWidth/5 - thieveWidth/6 >= ability.getPosX() && thieve.getPosX() - thieveWidth/5 - thieveWidth/6 <= ability.getPosX() + abilityWidth && thieve.getPosY() + thieveHeight - thieveHeight/10 >= ability.getPosY() - 10 && thieve.getPosY() + thieveHeight - thieveHeight/10 <= ability.getPosY() + abilityHeight + 10 || thieve.getPosX() + thieveWidth/2 - thieveWidth/12 - thieveWidth/6 >= ability.getPosX() && thieve.getPosX() + thieveWidth/2 - thieveWidth/12 - thieveWidth/6 <= ability.getPosX() + abilityWidth && thieve.getPosY() + thieveHeight - thieveHeight/10 >= ability.getPosY() - 10 && thieve.getPosY() + thieveHeight - thieveHeight/10 <= ability.getPosY() + abilityHeight + 10) return true;
            }
        }
        return false;
    }

    /**
     * Restarts the game by resetting all data to their initial values.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void restart() {
        thieve.setPosX(width/8);
        thieve.setPosY(height/2);
        showOfficer = false;
        thieveHasGold = false;
        trafficIsRed = false;
        goldHeld = 0;
        goldCounter = 0;
        carsSpeed = 6;
        selectedCar = 0;
        ability.setAbilityIsThere(false);
        thieve.setThieveSpeed(8);
        for (Car c : cars) {
            c.setIsDriving(false);
            c.setSpeedMultiplier(false);
        }
    }

    /**
     * increaseGoldCounter increases the gold counter.
     *
     * @param num Number to be added to the goldHeld
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void increaseGoldCounter(int num) {
        if (!thieveHasGold) {
            thieveHasGold = true;
            goldHeld += num;
        }
    }

    /**
     * saveStolenGold adds the stolen gold to the gold counter.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void saveStolenGold() {
        if (thieveHasGold) {
            goldCounter += 10;
            goldHeld = 0;
            thieveHasGold = false;
        }
        if (goldCounter >= 50 && goldCounter < 100) {
            setCarsSpeed(8); } else if (goldCounter >= 100) {
            setCarsSpeed(10); }
    }

    /**
     * @return Returns true if showOfficer is true otherwise false.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean officerThere() { return showOfficer; }

    /**
     * setShowOfficer updates the value of showOfficer.
     *
     * @param state Boolean value to be stored in showOfficer
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setShowOfficer(boolean state) { showOfficer = state; }

    /**
     * @return Returns the instance of Officer.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public Officer getOfficer() { return officer; }

    /**
     * Sets the speed of cars.
     *
     * @param newSpeed New int value for carsSpeed
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setCarsSpeed(int newSpeed) { carsSpeed = newSpeed; }

    /**
     * @return Returns true of trafficIsRed is true otherwise false.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean getTrafficIsRed() { return trafficIsRed; }

    /**
     * setTrafficIsRed updates the value of trafficIsRed.
     *
     * @param state Boolean value to be stored in trafficIsRed
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public void setTrafficIsRed(boolean state) { trafficIsRed = state; }

    /**
     * @return Returns true if thieveHasGold is true otherwise false.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public boolean getThieveHasGold() { return thieveHasGold; }

    /**
     * @return Return the instance of Ability.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public Ability getAbility() { return  ability; }

    /**
     * @return Return the current value of goldCounter.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getGoldCounter() { return goldCounter; }

    /**
     * @return Return the current value of goldHeld.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getGoldHeld() { return goldHeld; }

    /**
     * @return Returns the instance of Thieve.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public Thieve getThieve() { return thieve; }

    /**
     * @return Returns the instance of the ThieveCar.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public ThieveCar getThieveCar() { return thieveCar; }

    /**
     * @return Returns the instance of Car array.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public Car[] getCars() { return cars; }

    /**
     * @return Returns the current value of carsSpeed.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public int getCarsSpeed() { return carsSpeed; }
}

