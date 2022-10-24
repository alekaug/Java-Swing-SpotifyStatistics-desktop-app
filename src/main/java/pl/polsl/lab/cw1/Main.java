package pl.polsl.lab.cw1;

import pl.polsl.lab.cw1.controller.MainFrameController;

/**
 * The application's entry class. Firstly, the Controller object is being
 * created. The controller is responsible for View and Model objects creation.
 *
 * @author Aleksander Augustyniak
 * @version 1.0
 */
public class Main {

    /**
     * An entry method for the application.
     * @param args Program arguments.
     */
    public static void main(String[] args) {
        MainFrameController mainFrameController = new MainFrameController();
        if (mainFrameController.getReadyState()) {
            mainFrameController.initialize();
        }
    }
}
