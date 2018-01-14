/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.server.util;

/**
 * Exception that happen when a client try to sign in multiple times
 */
public class ClientAlreadyConnected extends Exception {

    /**
     * Constructor
     * 
     * Creates a new instance of <code>ClientAlreadyConnected</code> without detail
     * message.
     */
    public ClientAlreadyConnected() {
    }

    /**
     * Constructor
     * 
     * Constructs an instance of <code>ClientAlreadyConnected</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ClientAlreadyConnected(String msg) {
        super(msg);
    }
}
