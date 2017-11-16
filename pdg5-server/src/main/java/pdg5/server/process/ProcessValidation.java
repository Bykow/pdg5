/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.server.process;

import pdg5.common.game.Composition;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.Validation;
import pdg5.server.model.GameController;

/**
 *
 * @author Jimmy Verdasca
 */
public class ProcessValidation implements GenericProcess {

   private GameController gameController;
   private Validation validation;
   public ProcessValidation(Validation validation, GameController gm) {
      this.validation = validation;
      this.gameController = gm;
   }
   
   @Override
   public Message execute() {
      return gameController.validateComposition(validation.getComposition());
   }
   
}
