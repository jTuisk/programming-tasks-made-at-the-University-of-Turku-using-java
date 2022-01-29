package teht2;

import java.awt.*;

public class CustomKyltti extends Kyltti {

    public CustomKyltti(String teksti){
        super(new Dimension(30,5), new Tayte("-"), teksti);
    }

    /* Muunneltavan kyltin konstruktori */
    public CustomKyltti(Dimension koko, Tayte tayte, String teksti){
        super(koko, tayte, teksti);
    }

    /* Muutetaan olemassa olevan kyltin kokoa
     * @.pre     l != null && l > 0
     *          && k != null && k > 0
     * @.post   RESULT == new Dimension(l,k)
     */
    public void muutaKokoa(int l, int k){
        this.koko = new Dimension(l, k);
    }

    /* Muutetaan olemassa olevan kyltin täytettä
     * @.pre     t != null
     * @.post    RESULT == new Tayte(t)
     */
    public void muutaTayte(String t){
        this.tayte = new Tayte(t);
    }


    /*2 B*/
    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;

        if(!(o instanceof Kyltti))
            return false;
        Kyltti temp = (Kyltti) o;

        return  (super.koko.width == temp.koko.width)
                && (super.koko.height == temp.koko.height)
                && super.tayte.toString().equals(temp.tayte.toString())
                && super.teksti.equals(temp.teksti);
    }
}
