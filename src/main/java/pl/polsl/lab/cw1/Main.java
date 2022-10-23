package pl.polsl.lab.cw1;

import javax.swing.UnsupportedLookAndFeelException;
import pl.polsl.lab.cw1.controller.MainFrameController;

/**
 *
 * @author Aleksander Augustyniak
 */
public class Main {

    /**
     *
     * @param args
     * @throws UnsupportedLookAndFeelException
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        MainFrameController mainFrameController = new MainFrameController();
        mainFrameController.initialize();
        
    }
}
