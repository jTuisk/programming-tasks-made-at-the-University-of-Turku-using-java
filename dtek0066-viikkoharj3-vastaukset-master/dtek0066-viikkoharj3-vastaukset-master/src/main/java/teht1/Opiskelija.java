package teht1;

public class Opiskelija {

    private boolean hallituksenJasen = true;
    private OmaAuto omaAuto;
    private float raha;

    public Opiskelija(){
        this.omaAuto = new OmaAuto();
        this.raha = 0f;
    }
    public Opiskelija(float raha){
        this.omaAuto = new OmaAuto();
        this.raha = raha;
    }
    public Opiskelija(OmaAuto omaAuto){
        this.omaAuto = omaAuto;
        this.raha = 0f;
    }
    public Opiskelija(OmaAuto omaAuto, float raha){
        this.omaAuto = omaAuto;
        this.raha = raha;
    }

    public OmaAuto getOmaAuto(){return this.omaAuto;}
    public boolean getHallituksenJasen() {return this.hallituksenJasen;}
    public float getRaha(){return this.raha;}
    public void setRaha(float raha){this.raha = raha;}

}
