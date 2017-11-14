package pdg5.common.protocol;


/**
 * Created on 31.10.17 by Bykow
 */
public class ErrorMessage extends Message {
    private String error;

    public ErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "error='" + error + '\'' +
                '}';
    }
}
