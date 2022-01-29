package fi.utu.tech.entities;

import java.io.Serializable;

public class Kakku implements Serializable {

    private String kuorrute;
    private Object tayte;
    private String resepti;

    public Kakku(String kuorrute){
        this.kuorrute = kuorrute;
        this.tayte = null;
        this.resepti = null;
    }

    public Kakku(String kuorrute, Object tayte){
        this.kuorrute = kuorrute;
        this.tayte = tayte;
        this.resepti = null;
    }

    public Kakku(String kuorrute, String resepti){
        this.kuorrute = kuorrute;
        this.tayte = null;
        this.resepti = resepti;
    }

    public Kakku(String kuorrute, Object tayte, String resepti){
        this.kuorrute = kuorrute;
        this.tayte = tayte;
        this.resepti = resepti;
    }

    public String getKuorrute() { return this.kuorrute; }
    public Object getTayte() { return this.tayte; }
    public String getResepti() { return this.resepti; }
}
