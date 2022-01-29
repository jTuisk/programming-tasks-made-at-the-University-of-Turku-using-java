package teht2;

import java.awt.*;

public class Kyltti {
    Dimension koko;
    Tayte tayte;
    String teksti;

    public Kyltti(Dimension koko, Tayte tayte){
        this.koko = koko;
        this.tayte = tayte;
    }

    public void setTeksti(String teksti){
        this.teksti = teksti;
    }
    public void setTayte(Tayte t){this.tayte = t;}

    /** Muutetaan olemassa olevan kyltin kokoa
     * @.pre     l != null && l > 0
     *          && k != null && k > 0
     * @.post   RESULT new Dimension(l,k)
     */
    public void muutaKokoa(int l, int k){
        this.koko = new Dimension(l, k);
    }

    /** Muutetaan olemassa olevan kyltin täytettä
     * @.pre     t != null
     * @.post    RESULT new Tayte(t)
     */
    public void muutaTayte(String t){
        this.tayte = new Tayte(t);
    }

    public void tulostaKyltti() {
        final int korkeus2 = (koko.height - 1) / 2;
        final int leveys2 = (koko.width - this.teksti.length() - 2) / 2;

        final String merkki = tayte.toString();
        String kyltinRivi = "";

        for (int i = 0; i < koko.width; i++)
            kyltinRivi += merkki;

        for(int i = 0; i < korkeus2; i++)
            System.out.println(kyltinRivi);

        for(int i = 0; i < leveys2; i++)
            System.out.print(merkki);

        System.out.print(" " + this.teksti + " ");

        for(int i=0;i < koko.width-leveys2-this.teksti.length()-2;i++)
            System.out.print(merkki);

        System.out.println();

        for(int i=0;i < koko.height -korkeus2-1;i++)
            System.out.println(kyltinRivi);
    }

    @Override
    public String toString(){
        return "leveys="+koko.width+", korkeus="+koko.height+", täyte="+this.tayte+" teksti=\""+this.teksti+"\"";
    }
}
