package teht1;

import fi.utu.tech.Eläin;
import fi.utu.tech.Koira;
import fi.utu.tech.Kissa;

public class Teht1_main {
    public static void main(String[] args) {

        /* A */
        Eläinjoukko<Eläin> eJoukko = new Eläinjoukko();
        Eläin misse = new Kissa("Misse", 5);
        Eläin mirri = new Kissa("Mirri", 10);
        Eläin musti = new Koira("Musti", 1);

        eJoukko.lisääEläin(misse);
        eJoukko.lisääEläin(mirri);
        eJoukko.lisääEläin(musti);
        System.out.println(eJoukko);

        // luodaan toinen Eläinjoukko, id == 2
        // ei voida lisätä kaksi saman identiteetin kissaa
        Eläinjoukko toinen = new Eläinjoukko();
        Eläin cat = new Kissa("Miuku", 5);
        Eläin cat2 = cat;
        Eläin cat3 = new Kissa("Miuku", 5);

        toinen.lisääEläin(cat);
        toinen.lisääEläin(cat2); // ei onnistu
        toinen.lisääEläin(cat3);
        System.out.println(toinen);

        //Erikoistettu joukko
        Eläinjoukko<Kissa> kissaJoukko = new Eläinjoukko<>();
        Kissa kis = new Kissa("Moi", 5);
        Kissa kis2 = new Kissa("Hei", 1);
        Koira koira = new Koira ("Ben", 10);

        kissaJoukko.lisääEläin(kis);
        kissaJoukko.lisääEläin(kis2);
        // kissaJoukko.lisääEläin(koira); ei toimi
        System.out.println(kissaJoukko);

        /* B */
        //luodaan järjestetty joukko ja tulostetaan se olio kerrallaan
        JärjestettyEläinjoukko jJoukko = new JärjestettyEläinjoukko();
        jJoukko.lisääEläin(new Kissa("Örmy", 1));
        jJoukko.lisääEläin(cat);
        jJoukko.lisääEläin(kis2);

        System.out.println("Järjestetty lista tulostettuna:");
        for(var e : jJoukko.getJoukko())
            System.out.println(e);
        
        }
}

