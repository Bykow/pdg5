package pdg5.client.util;


import pdg5.client.process.*;
import pdg5.common.protocol.*;
import pdg5.client.controller.MainController;

/**
 * @author Maxime Guillod
 */
public class ClientRequestManager {

    private MainController mainController;

    public ClientRequestManager(MainController mainController) {
        this.mainController = mainController;

    }

    public Message execute(Message o) {
       
       System.out.println("Message received : " + o);
       
        if (o instanceof Game) {
            return new ProcessGame((Game) o).execute();

        } else if (o instanceof Load) {
            return new ProcessLoad((Load) o).execute();

        } else if (o instanceof NewGame) {
            return new ProcessNewGame((NewGame) o).execute();

        } else if (o instanceof ErrorMessage) {
            return new ProcessErrorMessage((ErrorMessage) o).execute();

        } else if (o instanceof Noop) {
            return new ProcessNoop((Noop) o).execute();

        } else if (o instanceof Load) {
            return new ProcessLoad((Load) o, mainController).execute();
        }

        return new ErrorMessage("Unable to check Message Type");
    }

}
