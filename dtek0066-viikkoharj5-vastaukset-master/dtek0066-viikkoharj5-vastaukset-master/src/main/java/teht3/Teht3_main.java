package teht3;

import fi.utu.tech.*;

public class Teht3_main {

    public static void main(String[] args) {

        /* A */
        Eläin koira_Musti = new Koira("Musti", 4);
        Eläin kissa_Mirri = new Kissa("Mirri", 2);
        EläinPari eläinPari = new EläinPari(koira_Musti, kissa_Mirri);
        System.out.println(eläinPari);

        /* B */
        Koira koira_Sepe = new Koira("Sepe", 8);
        Kissa kissa_Bob = new Kissa("Bob", 3);
        //TarkkaEläinPari<Koira, Kissa> tarkkaPari1 = new TarkkaEläinPari<Koira, Kissa>(kissa_Bob, koira_Sepe); // ei toimi koska parametrit väärinpäin
        TarkkaEläinPari<Kissa, Koira> tarkkaPari2= new TarkkaEläinPari<>(kissa_Bob, koira_Sepe);

        System.out.println(tarkkaPari2);
        tarkkaPari2.getEläin1().miu();
        tarkkaPari2.getEläin2().nouda(new Keppi());

        /* C */
        //SamaEläinPari<Kissa> samaPari1 = new SamaEläinPari<Kissa>(kissa_Bob, koira_Musti); // ei toimi koska molemmat ei Kissoja
        //SamaEläinPari<Koira> samaPari2 = new SamaEläinPari<Koira>(koira_Sepe, kissa_Bob); // ei toimi koska molemmat ei Koiria
        SamaEläinPari<Koira> samaPari3 = new SamaEläinPari<>(koira_Sepe, new Koira("Rekku", 3));
        System.out.println(samaPari3);
        samaPari3.getEläin1().hau();
        samaPari3.getEläin2().nouda(new Keppi());

        SamaEläinPari samaPari4 = new SamaEläinPari(kissa_Bob, koira_Sepe);
        System.out.println(samaPari4);
        /* Jos tyypitystä ei määritetä tarkemmin jo oliota luodessa (samaPari4),
         * tarkempien eläinlajien luonti ja niiden vertailu ovatko samat
         * ei ole mahdollista generoidulla luokalla ilman 'impliments Comparable<T>' ja '@Override compareTo()'
         * tai if-lauseita käyttäen SamaEläinPari-luokassa.
         *
         *  Voitaisiin rajata konstuktorissa esim.
         *  if(eläin1.getClass() != eläin2.getClass()) throw new Error("Eri lajia!");
         */

        /* D */
        /* Oletetaan, että SamaEläinPari <: TarkkaEläinPari TAI TarkkaEläinPari <: SamaEläinPari
         *
         * class SamaEläinPari<T> extends TarkkaEläinPari<T,R>{}
         * class TarkkaEläinPari<T,R> extends SamaEläinPari<T>{}
         *
         * Homma toimii, koska molempien tyyppiparametrit extendaavat Eläin-olion.
         * Jotta saadaan kutsuttua tarkemmin Kissa- ja Koira-olioiden
         * omia metodeja, tulee tarkempi määritys tehdä itse SamaEläinPari-
         * tai TarkkaEläinPari-olioita luodessa. Muuten käytössä vain Eläin-luokan oliot.
         *
         * Esim.
         * TarkkaEläinPari<Kissa, Koira> tarkkaPari2 = new TarkkaEläinPari<Kissa, Koira>(kissa_Bob, koira_Sepe);
         * SamaEläinPari<Koira> samaPari3 = new SamaEläinPari<>(koira_Sepe, new Koira("Rekku", 3));
         */

        /* E */
        /*
         * Oletetaan TarkkaEläinPari <: EläinPari ja SamaEläinPari <: TarkkaEläinPari
         *
         * class EläinPari{}
         * class TarkkaEläinPari<T,R> extends EläinPari{}
         * class SamaEläinPari<T> extends TarkkaEläinPari<T,R>{}
         *
         * Alityypitys toimii, sillä jokainen luokka käyttää Eläin-olioita (tyyppi)parametreinaan
         * (tarkempi määrittely olioita kutsuttaessa, kuten D-kohdassa).
         *
         * Tulee kuitenkin ottaa huomioon, että koska EläinPari-luokka ei ole geneerinen eli
         * se käyttää aina parametreinaan Eläin-olioita, se ei siis pystytä käyttämään
         * Kissa- tai Koira-luokkien omia metodeja.
         */
    }
}
