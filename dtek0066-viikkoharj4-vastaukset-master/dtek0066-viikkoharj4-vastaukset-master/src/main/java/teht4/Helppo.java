package teht4;

/*
TEHTÄVÄN RATKAISUN IDEA:

Tehtävä todella on mahdollinen Javalla luokkien periytymisen vuoksi.

Lähdin ensityökseni havainnoimaan, mikä luokka perii minkäkin ja luokkien periminen menee seuraavasti:
A -> A:n perii D -> D:n perii B -> B:n perii C -> C:n perii E

Pyöräytin idean ylösalaisin, koska Helppo-luokka luo E-luokan olion
E -> C -> B -> D -> A

Sitten vain aloin rakentamaan lausetta takaperin (osaa + sen + kun + poa + help).

----------

E-luokan x()-metodin idea lausetta rakentaessa käyden jokainen lause kutsuineen läpi
(suluissa metodien edessä luokka, johon kutsu menee)

1) saadaan osuus "helppoa":
super.x() >> (C).super.x() >> (B).super.x() + a()=="poa" >> (D).super.x() >> (A).a()=="help"

2) saadaan osuus " kun sen":
y() >> super.y() >> (C).a()==" kun" + (C).super.y() >> (B).super.y() >> (D).a()==" sen"

3) saadaan osuus " osaa":
a()==" osaa"

*/

public class Helppo {
    public static void main(String[] args) {
        E e = new E();
        e.x(); // "helppoa kun sen osaa"
    }
}

abstract class A {
    private void a() {
        System.out.print("help");
    }
    void x() { a(); } // "help"
    void y() { }
}

abstract class B extends D {
    private void a() {
        System.out.print("poa");
    }
    void x() { super.x(); a(); } // "help" + "poa"
    void y() { super.y();}       // " sen"
}

abstract class C extends B {
    private void a() {
        System.out.print(" kun");
    }
    void x() { super.x(); }      // "help" + "poa"
    void y() { a(); super.y(); } // " kun" + " sen"
}

abstract class D extends A {
    private void a() {
        System.out.print(" sen");
    }
    void x() { super.x(); } // "help"
    void y() { a(); }       // " sen"
}

final class E extends C {
    private void a() {
        System.out.print(" osaa");
    }
    void x() { super.x(); y(); a(); } // ("help" + "poa") + (" kun" + "sen") + " osaa"
    void y() { super.y(); }           // " kun" + " sen"
}

