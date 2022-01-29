package teht3;

import fi.utu.tech.Eläin;

public class TarkkaEläinPari<T extends Eläin, R extends Eläin>  {

    private T eläin1;
    private R eläin2;

    public TarkkaEläinPari(T p1 , R p2) {
        this.eläin1 = p1;
        this.eläin2 = p2;
    }

    public T getEläin1() {
        return eläin1;
    }

    public R getEläin2() {
        return eläin2;
    }

    @Override
    public String toString() {
        return "TarkkaEläinPari: " + this.eläin1 + " ja " + this.eläin1 + " muodostavat parin!";
    }
}
