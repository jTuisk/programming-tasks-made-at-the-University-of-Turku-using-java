package teht3;

import java.util.Objects;
import java.util.Random;

public class teht3_Main {

    public static void main(String[] args) {

        int[] hashTaulu = new int[20];
        Henkilo[] henkilot = new Henkilo[500];
        Henkilo test = new Henkilo("Janek", 27);
        Henkilo2 test2 = new Henkilo2("Janek", 27);
        Henkilo3 test3 = new Henkilo3("Janek", 27);

        /* A */
        System.out.println("A) "+test.hashCode());
        /* B */
        System.out.println("B) "+test2.hashCode());
        /* C */
        System.out.println("C) "+test3.hashCode());

        System.out.println();

        for(int i = 0; i < henkilot.length; i++){
            Random rand = new Random();
            Henkilo henk[] = new Henkilo[] {    new Henkilo(randomName(), randomIka()),
                    new Henkilo2(randomName(), randomIka()),
                    new Henkilo3(randomName(), randomIka())};
            henkilot[i] = henk[rand.nextInt(henk.length)];
        }

        for(int i = 0; i < hashTaulu.length; i++){
            for(int j = 0; j < henkilot.length; j++){
                if(j % (henkilot.length/hashTaulu.length) == 0)
                    hashTaulu[i] = henkilot[i].hashCode();
            }
        }

        for(int i : hashTaulu)
            System.out.println(i);
    }

    static String randomName(){
        byte[] array = new byte[50];
        new Random().nextBytes(array);
        for(int i = 0; i < array.length; i++) { array[i] &= 127; array[i] %= 94; array[i] += 32; }
        return new String(array, java.nio.charset.Charset.forName("UTF-8"));
    }

    static int randomIka(){
        return (int) ( Math.random()*100)+1;
    }
}

//Määritellään luokka Henkilo:
class Henkilo{
    String nimi;
    Integer ika;

    public Henkilo(String nimi, int ika){
        this.nimi = nimi;
        this.ika = ika;
    }

}
class Henkilo2 extends Henkilo{

    public Henkilo2(String nimi, int ika){
        super(nimi, ika);
    }

    @Override
    public int hashCode(){
        return this.nimi.length()*17 % 46 * this.nimi.length()*231*ika*3;
    }
}
class Henkilo3 extends Henkilo{

    public Henkilo3(String nimi, int ika){
        super(nimi, ika);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nimi, ika);
    }
}
/* TEHTÄVÄNANTO:

Tehtävä: toteuta Henkilöstä kolme variaatiota,
a) yksi luokan oletushajautuksella,
b) yksi itse keksimälläsi hajautusarvon laskennalla ja
c) kolmas käyttämällä Objects.hash-metodia.

Laadi 500 eri satunnaista henkilöä ja demonstroi 20-paikkaista taulua näyttäen,
miten oliot hajautuvat tauluun hajautusarvollaan (modulo (%) taulukon koko).
Käytännössä jokainen generoitu olio siis kasvattaa paikkansa laskuria.
Mikä hajautus näistä on mielestäsi teoriassa paras, entä käytännössä?

 */
