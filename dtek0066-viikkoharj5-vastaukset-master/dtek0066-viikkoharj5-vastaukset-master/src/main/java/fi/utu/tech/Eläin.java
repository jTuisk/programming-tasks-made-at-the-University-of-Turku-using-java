package fi.utu.tech;

public abstract class Eläin {
    private final String nimi;
    private final String laji;
    private final int ikä;

    protected Eläin(String nimi, String laji, int ikä) {
        this.nimi = nimi;
        this.laji = laji;
        this.ikä = ikä;
    }

    protected final void ääntele(String ääni) {
        System.out.println(this + " sanoo " + ääni + "!");

    }

    /* 2C */
    public void perustaPerhe(Eläin toinen) {
        if(this.getClass().equals(toinen.getClass())) {
            ääntele("löysin kumppanin eläimestä " + toinen);
        } else {
            throw new Error("Ei samaa lajia");
        }
    }

    public abstract void tervehdi();

    @Override
    public String toString() {
        return nimi + "-" + laji + " (" + ikä + "v)";
    }

}