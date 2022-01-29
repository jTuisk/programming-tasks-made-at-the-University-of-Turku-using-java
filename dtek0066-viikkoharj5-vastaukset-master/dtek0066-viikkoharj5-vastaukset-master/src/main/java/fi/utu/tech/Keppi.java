package fi.utu.tech;

public class Keppi extends Esine {
    private static int id = 1;

    private int keppiId;

    public Keppi() {
        keppiId = id++;
    }

    @Override
    public String toString() {
        return "keppi-" + keppiId;
    }
}
