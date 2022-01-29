package teht3;

import fi.utu.tech.Eläin;

public class SamaEläinPari<T extends Eläin> {

    private T eläin1;
    private T eläin2;

    public SamaEläinPari(T p1, T p2){
        this.eläin1 = p1;
        this.eläin2 = p2;
    }

    public T getEläin1() {
        return eläin1;
    }

    public T getEläin2() {
        return eläin2;
    }

    @Override
    public String toString() {
        return "SamaEläinPari: " + this.eläin1 + " ja " + this.eläin2 + " muodostavat parin!";
    }
}
