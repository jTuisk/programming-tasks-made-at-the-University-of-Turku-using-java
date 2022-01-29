package teht1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class JärjestettyEläinjoukko<T> extends Eläinjoukko<T>{

    public JärjestettyEläinjoukko(){
        super();
    }

    /** Lisätään eläin listaan ja sortataan heti
     * @.pre e != null
     * @.post RESULT == ((OLD)super.lista.add(e))
     *                     && (super.lista.sort())
     */
    @Override
    public void lisääEläin(T e){
        if(!super.getJoukko().contains(e))
            super.getJoukko().add(e);

        super.getJoukko().sort(Comparator.comparing(Object::toString));
    }

}
