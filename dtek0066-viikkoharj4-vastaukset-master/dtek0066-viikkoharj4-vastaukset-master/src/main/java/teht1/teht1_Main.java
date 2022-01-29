package teht1;

public class teht1_Main {
    public static void main(String[] args) {
        tulostaKyltit(luoKyltit());
    }

    /* Luodaan kylttejä taulukkoon
     * @.pre true
     * @.post RESULT == new Kyltti[3]
     */
    public static Kyltti[] luoKyltit(){
        Kyltti[]taulukko = new Kyltti[3];
        String mjono = "Tämä on kyltti";

        /* 1) yksinkertaistettu kyltti */
        Kyltti simpleKyltti = new SimpleKyltti(mjono);
        taulukko[0] = simpleKyltti;

        /* 2) peruskyltti tietyllä täytteellä ja spekseillä */
        Kyltti perusKyltti = new PerusKyltti(mjono);
        taulukko[1] = perusKyltti;

        /* 3) kustomoitava kyltti */
        Kyltti customKyltti = new CustomKyltti(mjono);
        taulukko[2] = customKyltti;

        return taulukko;
    }

    /* Tulostetaan kyltit listasta
     * @.pre true
     * @.post FORALL(k : kyltit: k.equals(perusKyltti)
     *                  || k.equals(customKyltti)
     *                  || k.equals(simpleKyltti))
     */
    public static void tulostaKyltit(Kyltti[] kyltit){
        for(Kyltti k : kyltit){
            k.tulosta();
            System.out.println();
        }
    }
}