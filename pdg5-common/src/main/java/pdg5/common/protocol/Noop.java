package pdg5.common.protocol;

/**
 * Message ignored by the controllers, allow to end a request-answer-request-etc
 * chain
 */
public class Noop extends Message {

    /**
     * sender can only be a CLIENT or a SERVER
     */
    public enum Sender {
        SERVER,
        CLIENT
    }

    /**
     * who sent this instance
     */
    private Sender sender;

    /**
     * Constructor
     *
     * @param sender who sent this instance
     */
    public Noop(Sender sender) {
        this.sender = sender;
    }

    /**
     * return who sent this instance
     *
     * @return who sent this instance
     */
    public Sender getSender() {
        return sender;
    }
}
