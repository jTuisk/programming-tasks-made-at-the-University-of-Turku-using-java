package fi.utu.tech;

import net.jqwik.api.*;

import java.util.List;

public class EläinTests {

    @Property
    boolean testailu(@ForAll int i) {

        var a = new Kissa("Misse", 5);
        var b = new Kissa("Mirri", 10);
        var c = new Koira("Musti", 1);

        a.tervehdi();
        b.miu();
        c.tervehdi();
        c.nouda(new Keppi());

        for (var k : List.of(a, b)) k.miu();
        for (var e : List.of(a, b, c)) e.tervehdi();

        return true;
    }
}