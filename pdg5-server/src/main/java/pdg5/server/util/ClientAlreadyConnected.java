/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.server.util;

/**
 *
 * @author Miguel-Portable
 */
public class ClientAlreadyConnected extends Exception {

    /**
     * Creates a new instance of <code>ClientAlreadyConnected</code> without detail
     * message.
     */
    public ClientAlreadyConnected() {
    }

    /**
     * Constructs an instance of <code>ClientAlreadyConnected</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ClientAlreadyConnected(String msg) {
        super(msg);
    }
}
