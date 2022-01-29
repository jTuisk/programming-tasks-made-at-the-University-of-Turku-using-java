package teht2;

import fi.utu.tech.Eläin;
import fi.utu.tech.Keppi;
import fi.utu.tech.Kissa;
import fi.utu.tech.Koira;
import teht1.Eläinjoukko;

public class Teht2_main {
    public static void main(String[] args) {

        /* A */
        //Eläin-oliot tervehtii
        Eläinjoukko<Eläin> eJoukko = new Eläinjoukko();
        Eläin misse = new Kissa("Misse", 5);
        Eläin mirri = new Kissa("Mirri", 10);
        Eläin musti = new Koira("Musti", 1);
        eJoukko.lisääEläin(misse);
        eJoukko.lisääEläin(mirri);
        eJoukko.lisääEläin(musti);

        tervehdi(eJoukko);
        System.out.println("");

        /* B */
        //Koira-oliot noutavat saman kepin ja tervehtii
        Keppi keppi = new Keppi();
        Eläinjoukko<Koira> koiraJoukko = new Eläinjoukko<>();
        Koira koi1 = new Koira("Aapo", 5);
        Koira koi2 = new Koira("Bensku", 12);
        Koira koi3 = new Koira("Rekku", 4);
        koiraJoukko.lisääEläin(koi1);
        koiraJoukko.lisääEläin(koi2);
        koiraJoukko.lisääEläin(koi3);

        nouda(koiraJoukko, keppi);
        System.out.println();
        tervehdi(koiraJoukko);
        System.out.println();

        // Kissa-oliot tervehtii
        Eläinjoukko<Kissa> kissaJoukko = new Eläinjoukko<>();
        Kissa kis = new Kissa("Moi", 5);
        Kissa kis2 = new Kissa("Hei", 1);

        kissaJoukko.lisääEläin(kis);
        kissaJoukko.lisääEläin(kis2);
        tervehdi(kissaJoukko);
        System.out.println();

        /* C */
        // perustetaan kisuperhe
        // HUOM > Eläin-luokassa muutokset!!
        kis.perustaPerhe(kis2);
        // kis.perustaPerhe(koi3); throws "Ei samaa lajia"

    }

    /* A */
    public static <T extends Eläin> void tervehdi(Eläinjoukko<T> eläinJoukko){
        for(Eläin el : eläinJoukko.getJoukko()){
            el.tervehdi();
        }
    }

    /* B */
    public static void nouda(Eläinjoukko<Koira> eläinJoukko, Keppi keppi){
        for(Koira el : eläinJoukko.getJoukko()){
            el.nouda(keppi);
        }
    }
}
