package pl.polsl.lab.cw1;

import pl.polsl.lab.cw1.controller.MainFrameController;

/**
 *
 * @author Aleksander Augustyniak
 */
public class Container extends Component {
    
    public <C extends MainFrameController> Container(final C controller) {
        super(controller);
    }
    
}
