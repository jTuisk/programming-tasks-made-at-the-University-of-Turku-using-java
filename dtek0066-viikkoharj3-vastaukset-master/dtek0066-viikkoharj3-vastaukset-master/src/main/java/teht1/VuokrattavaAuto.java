package teht1;

public class VuokrattavaAuto extends Auto {

    private boolean vuokrattu;
    private Opiskelija vuokraaja;
    private float vuokraHinta;

    public VuokrattavaAuto(){
        super();
        this.vuokrattu &= new java.util.Random().nextBoolean();
        this.vuokraHinta = 0f;
        this.vuokraaja = null;
    }

    public boolean getVuokrattu(){return this.vuokrattu;}
    public void setVuokrattu(boolean vuokrattu){this.vuokrattu = vuokrattu;}
    public void setVuokraaja(Opiskelija vuokraaja){this.vuokraaja = vuokraaja;}

    /**Palautetaan vuokrattu auto ja korjataan oma auto
     *@.pre true
     *@.post omaAuto.korjaa()
     *       && OLD(vuokraaja) == null
     *       && OLD(vuokrattu) == false
     */
    public void palautaAuto(){
        this.vuokraaja.getOmaAuto().korjaa();
        setVuokrattu(false);
        setVuokraaja(null);
        System.out.println("Laina-auto palautettu!");
    }

    /**Vuokrataan auto
     *@.pre vuokraaja != null
     *@.post OLD(vuokraaja) = vuokraaja
     *       && OLD(vuokrattu) = true
     */
    public void vuokraaAuto(Opiskelija vuokraaja){
        setVuokraaja(vuokraaja);
        setVuokrattu(true);
    }

    /**Ajetaan määrätty matka ja palautetaan auto
     *@.pre matka != null && matka > 0
     *@.post RESULT == true || RESULT == false
     */
    @Override
    public boolean aja(Matka matka) {
        if(vuokraaja == null || matka == null || !(matka.getMatkaKM() > 0f))
            return false;

        return true;
    }
}
