package pdg5.client.process;

import pdg5.common.protocol.Message;

/**
 * GenericProcess to implement in all Process
 */
public interface GenericProcess {
    public Message execute();
}
