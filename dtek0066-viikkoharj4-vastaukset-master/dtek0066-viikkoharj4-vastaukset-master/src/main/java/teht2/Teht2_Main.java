package teht2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Teht2_Main {

    public static void main(String[] args) {
        ArrayList<Kyltti> lista = luoArrayList();

        System.out.println("A: "+lista.get(0).equals(lista.get(1)));
        System.out.println("A: "+lista.get(0).equals(lista.get(3)));
        System.out.println();

        System.out.println("B: "+lista.get(4).equals(lista.get(5)));
        System.out.println("B: "+lista.get(4).equals(lista.get(6)));
        System.out.println();


        tulostaKyltit(lista);

        System.out.println();
        System.out.println();
        Collections.sort(lista);

        tulostaKyltit(lista);
    }

    /* Luodaan kylttejä taulukkoon
     * @.pre true
     * @.post RESULT == new ArrayList<Kyltti>()
     */
    static ArrayList<Kyltti> luoArrayList(){
        ArrayList<Kyltti> temp = new ArrayList<>();
        String mjono = "Tämä on kyltti";
            temp.add(new SimpleKyltti(mjono));
            temp.add(new PerusKyltti(mjono));
            temp.add(new CustomKyltti(mjono));
            temp.add(new Kyltti(new Dimension(5,5), new Tayte("-"), "testi - 5"));
            temp.add(new CustomKyltti("testi"));
            temp.add(new CustomKyltti("testi"));
            temp.add(new CustomKyltti(new Dimension(20,7), new Tayte("*") , "Testi - 7"));
            temp.add(new CustomKyltti(new Dimension(20,4), new Tayte("*") , "Testi - 4"));
        return temp;
    }

    /* Tulostetaan kyltit listasta
     * @.pre    kyltit != null
     * @.post   FORALL(k : kyltit: k.equals(perusKyltti)
     *                  || k.equals(customKyltti)
     *                  || k.equals(simpleKyltti))
     */
    public static void tulostaKyltit(ArrayList<Kyltti> kyltit){
        if(kyltit == null)
            return;

        for(Kyltti k : kyltit){
            k.tulosta();
            System.out.println();
        }
    }
}