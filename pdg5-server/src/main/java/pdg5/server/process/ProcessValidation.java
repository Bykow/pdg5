package pdg5.server.process;

import pdg5.common.protocol.Message;
import pdg5.common.protocol.Validation;
import pdg5.server.model.GameController;

/**
 * Class checking if a Composition contain a valid, existing word
 */
public class ProcessValidation implements GenericProcess {

    /**
     * the original Validation request
     */
    private final Validation validation;

    /**
     * gameController that will do the check of the Composition
     */
    private final GameController gameController;


    /**
     * Constructor
     *
     * @param validation Item containing the Composition we want to validate by the
     *                   server
     * @param gm         GameController containing the dictionnary instance where we cancheck
     *                   if a Composition is valid
     */
    public ProcessValidation(Validation validation, GameController gm) {
        this.validation = validation;
        this.gameController = gm;
    }

    /**
     * return a protocol.ValidationWord with a boolean response if the word contained
     * in the given Composition is valid
     *
     * @return a protocol.ValidationWord with a boolean response if the word
     * contained in the given Composition is valid
     */
    @Override
    public Message execute() {
        return gameController.validateComposition(validation.getComposition());
    }

}
