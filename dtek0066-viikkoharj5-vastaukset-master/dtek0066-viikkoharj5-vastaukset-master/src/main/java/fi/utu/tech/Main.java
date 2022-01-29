package fi.utu.tech;

public class Main {
    public static void main(String[] args) {
        var a = new Kissa("Misse", 5);
        var b = new Kissa("Mirri", 10);
        var c = new Koira("Musti", 1);

        a.tervehdi();
        b.miu();
        c.tervehdi();
        c.nouda(new Keppi());
    }
}