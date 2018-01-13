package pdg5.common.protocol;

/**
 * class sent through the connection to inform the client an error occured
 */
public class ErrorMessage extends Message {

    /**
     * message of the occured error
     */
    private String error;

    /**
     * Constructor
     *
     * @param error message of the occured error
     */
    public ErrorMessage(String error) {
        this.error = error;
    }

    /**
     * return the message of the occured error
     *
     * @return message of the occured error
     */
    public String getError() {
        return error;
    }

    /**
     * override the print of this class, for easier debug in client it return a
     * string with only the message occured
     *
     * @return a string with only the result
     */
    @Override
    public String toString() {
        return "ErrorMessage{"
            + "error='" + error + '\''
            + '}';
    }
}
