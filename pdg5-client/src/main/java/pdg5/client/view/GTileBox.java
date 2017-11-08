package pdg5.client.view;

public class GTileBox {
    private static GTileBox ourInstance = new GTileBox();

    public static GTileBox getInstance() {
        return ourInstance;
    }

    private GTileBox() {
    }
}
