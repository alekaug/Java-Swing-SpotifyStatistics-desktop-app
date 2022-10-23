package pl.polsl.lab.cw1;

import pl.polsl.lab.cw1.controller.MainFrameController;

/**
 *
 * @author Aleksander Augustyniak
 */
public class Component {
    protected MainFrameController controller;
    
    public <C extends MainFrameController> Component(final C controller) {
        this.controller = controller;
    }
}
