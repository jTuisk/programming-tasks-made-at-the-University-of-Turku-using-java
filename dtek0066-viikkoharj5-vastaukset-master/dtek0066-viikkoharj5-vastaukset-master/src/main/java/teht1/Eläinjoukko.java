package teht1;

import java.util.ArrayList;

public class Eläinjoukko<T>{

    private ArrayList<T> lista;
    private static int joukkoId = 0;

    public <T> Eläinjoukko(){
        lista = new ArrayList<>();
        joukkoId++;
    }

    /** Lähetetään joukko kutsujalle
     * @.pre true
     * @.post RESULT == this.lista
     */
    public ArrayList<T> getJoukko(){ return lista; }

    /** Lisätään Eläinjoukkoon eläin
     * @.pre e != null
     * @.post RESULT == ((OLD)lista.add(e))
     */
    public void lisääEläin(T e){
        if(!this.lista.contains(e))
            this.lista.add(e);
    }

    @Override
    public String toString() {
        String res = "Eläinjoukko " + joukkoId + " tässä hei - meitä on: \n";
        for(T e : this.lista) res = res + e.toString() + "\n";
        return res;
    }

}
