package teht1;

import java.awt.*;

public class Kyltti {

    protected Dimension koko;
    protected Tayte tayte;
    protected String teksti;

    public Kyltti(String t){
        this.teksti = t;
    }

    public Kyltti(Dimension koko, Tayte tayte){
        this.koko = koko;
        this.tayte = tayte;
    }
    public Kyltti(Dimension koko, String teksti){
        this.koko = koko;
        this.teksti = teksti;
        this.tayte = null;
    }

    public Kyltti(Dimension koko, Tayte tayte, String teksti){
        this.koko = koko;
        this.tayte = tayte;
        this.teksti = teksti;
    }

    public void tulosta() {
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
}

