package teht3;

import fi.utu.tech.Eläin;

public class EläinPari{

    private Eläin pari1;
    private Eläin pari2;

    public EläinPari(Eläin p1, Eläin p2){
        this.pari1 = p1;
        this.pari2 = p2;
    }

    @Override
    public String toString() {
        return "Eläinpari: " + this.pari1 + " ja " + this.pari2 + " muodostavat parin!";
    }
}
