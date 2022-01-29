package teht2;

import java.awt.Dimension;
import java.util.ArrayList;

public class teht2_Main {
    static Dimension speksit;
    static Tayte tayte1, tayte2;
    static ArrayList<Kyltti> kyltit = new ArrayList<>();


    public static void main(String[] args) {
        //Määritetään leveys, korkeus, tayte1 ja tayte2
        speksit = new Dimension(24,3);
        tayte1 = new Tayte("asteriski");
        tayte2 = new Tayte("risuaita");

        //luodaan kylttiA, kylttiB ja kylttiC
        //kylttiA ja kylttiB tayte1:n pohjalta, kylttiC tayte2:n pohjalta
        Kyltti kylttiA = luoKyltti("AAAA");
        Kyltti kylttiB = luoKyltti("BBBB");
        Kyltti kylttiC = luoKyltti("CCCC");
        kylttiC.setTayte(tayte2);

        /* Tulostetaan kylttien tiedot ja niiden graafinen muoto */
        System.out.println("----- Kyltit 1. kerralla-----");
        for (Kyltti k : kyltit)
            System.out.println("id: " + (kyltit.indexOf(k) + 1) + " - " + k.toString());

        System.out.println("");
        for (Kyltti k : kyltit){
            k.tulostaKyltti();
            System.out.println("");
        }

        //muutetaan kylttiA:n tayte asteriskiksi, linkitys katkeaa
        //muutetaan yleinen täyte "-" -> kylttiB:n tayte
        kylttiA.muutaTayte("asteriski");
        tayte1.muutaTayte("-");

        /* Tulostetaan kylttien tiedot ja niiden graafinen muoto */
        System.out.println("----- Kyltit 2. kerralla -----");
        for (Kyltti k : kyltit)
            System.out.println("id: " + (kyltit.indexOf(k) + 1) + " - " + k.toString());

        System.out.println("");
        for (Kyltti k : kyltit){
            k.tulostaKyltti();
            System.out.println("");
        }

        //muutetaan kylttiC:n kokoa, linkitys katkeaa
        //muutetaan yleistä kokoa -> kylttiA ja kylttiB koko muuttuu
        kylttiC.muutaKokoa(20, 1);
        speksit.setSize(30,5);

        /* Tulostetaan kylttien tiedot ja niiden graafinen muoto */
        System.out.println("----- Kyltit 3. kerralla -----");
        for (Kyltti k : kyltit)
            System.out.println("id: " + (kyltit.indexOf(k) + 1) + " - " + k.toString());

        System.out.println("");
        for (Kyltti k : kyltit){
            k.tulostaKyltti();
            System.out.println("");
        }

    }

    /** Luodaan uusi kyltti
     * @.pre teksti.length >= 0
     * @.post RESULT new Kyltti(koko, tayte1, "teksti")
     *          && kyltit.add(new Kyltti)
     */
    public static Kyltti luoKyltti(String teksti){
        Kyltti uusiKyltti = new Kyltti(speksit,tayte1);
        uusiKyltti.setTeksti(teksti);
        kyltit.add(uusiKyltti);
        return uusiKyltti;
    }

}