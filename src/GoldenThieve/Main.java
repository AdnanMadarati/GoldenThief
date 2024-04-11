package GoldenThieve;

import GoldenThieve.Controller.Controller;
import GoldenThieve.Model.Model;
import GoldenThieve.View.View;
import processing.core.PApplet;

/**
 * The GoldenThieve.Main class is used to connect the MVC together and define the width and height of the window.
 *
 * @since 1.0
 * @author Adnan Madarati
 * @version 1.0
 * */

public class Main {

    /**
     * The main method that connects the MVC and start the sketch.
     *
     * @since 1.0
     * @author Adnan Madarati
     * @version 1.0
     * */

    public static void main(String[] args) {

        /**
         * Width of the window
         * */

        final int WIDTH = 1000;

        /**
         * Height of the window
         * */

        final int HEIGHT = 1000;

        /**
         * New Controller instance
         * */

        var controller = new Controller(WIDTH, HEIGHT);

        /**
         * New Model instance
         * */

        var model = new Model(WIDTH, HEIGHT);

        /**
         * New View instance
         * */

        var view = new View(WIDTH, HEIGHT);

        /**
         * The three methods down below connect the View to Controller, Controller to View and Controller to Model
         * */

        controller.setView(view);
        controller.setModel(model);
        view.setController(controller);

        /**
         * Runs the sketch from view
         * */

        PApplet.runSketch(new String[]{"Golden Thief"}, view);
    }
}