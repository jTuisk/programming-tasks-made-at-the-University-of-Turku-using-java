package teht1;
import java.awt.Dimension;

public class CustomKyltti extends Kyltti {

    public CustomKyltti(String teksti){
        super(new Dimension(30,5), new Tayte("-"), teksti);
    }

    /* Muunneltavan kyltin konstruktori */
    public CustomKyltti(Dimension koko, Tayte tayte){
        super(koko, tayte);
        super.koko = koko;
        super.tayte = tayte;
    }

    /* Muutetaan olemassa olevan kyltin kokoa
     * @.pre     l != null && l > 0
     *          && k != null && k > 0
     * @.post   RESULT == new Dimension(l,k)
     */
    public void muutaKokoa(int l, int k){
        super.koko = new Dimension(l, k);
    }

    /* Muutetaan olemassa olevan kyltin täytettä
     * @.pre     t != null
     * @.post    RESULT == new Tayte(t)
     */
    public void muutaTayte(String t){
        super.tayte = new Tayte(t);
    }

}
