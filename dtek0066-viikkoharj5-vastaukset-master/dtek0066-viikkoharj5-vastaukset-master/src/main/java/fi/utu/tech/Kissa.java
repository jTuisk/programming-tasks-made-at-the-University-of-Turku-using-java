package fi.utu.tech;

public class Kissa extends Eläin {
    public Kissa(String nimi, int ikä) {
        super(nimi, "kissa", ikä);
    }

    public void mau() {
        ääntele("mau");
    }

    public void miu() {
        ääntele("miu");
    }

    @Override
    public void tervehdi() {
        mau();
    }
}