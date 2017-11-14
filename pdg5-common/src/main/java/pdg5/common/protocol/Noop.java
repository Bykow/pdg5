package pdg5.common.protocol;

public class Noop extends Message {

    private Sender sender;

    public Noop(Sender sender) {
        this.sender = sender;
    }

    public Sender getSender() {
        return sender;
    }

    public enum Sender {
        SERVER,
        CLIENT
    }
}
