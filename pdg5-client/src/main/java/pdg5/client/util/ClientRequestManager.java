package pdg5.client.util;


import pdg5.client.controller.MainController;
import pdg5.client.process.ProcessErrorMessage;
import pdg5.client.process.ProcessGame;
import pdg5.client.process.ProcessLoad;
import pdg5.client.process.ProcessNoop;
import pdg5.common.protocol.*;

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
        System.out.println(o.toString());
        if (o instanceof ErrorMessage) {
            return new ProcessErrorMessage((ErrorMessage) o).execute();
        } else if (o instanceof Noop) {
            return new ProcessNoop((Noop) o).execute();
        } else if (o instanceof Load) {
            return new ProcessLoad((Load) o, mainController).execute();
        } else if (o instanceof Game) {
            return new ProcessGame((Game) o, mainController).execute();
        }

        return new ErrorMessage("Unable to check Message Type");
    }

}
