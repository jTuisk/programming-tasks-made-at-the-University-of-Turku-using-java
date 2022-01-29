package fi.utu.tech;

public class Koira extends Eläin {
    public Koira(String nimi, int ikä) {
        super(nimi, "koira", ikä);
    }

    public void nouda(Esine esine) {
        System.out.println(this + " noutaa esineen " + esine + ".");
    }

    public void hau() {
        ääntele("hau");
    }

    @Override
    public void tervehdi() {
        hau();
    }
}