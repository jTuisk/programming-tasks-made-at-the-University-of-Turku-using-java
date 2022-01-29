package teht2;

import java.util.InputMismatchException;

public class Tayte {
    String tayte;

    public Tayte(String tayte){
        this.tayte = checkTayte(tayte);
    }

    /* Tarkistetaan, onko tayte halutunlainen
     * @.pre    tayte != null
     * @.post   RESULT  tayte == "asteriski" ? "*" :
     *                  tayte == "risuaita" ? "#" :
     *                  tayte == "vaakaviiva" ? "-" :
     *                  "*"
     */
    private String checkTayte(String tayte) throws InputMismatchException {
        switch(tayte.toLowerCase().trim()){
            case "asteriski":
            case "*":
                return "*";
            case "risuaita":
            case "#":
                return "#";
            case "vaakaviiva":
            case "-":
                return "-";
            default:
                throw new InputMismatchException("Virheellinen syöte");
        }
    }

    /* Muutetaan täyte
     * @.pre t != null
     * @.post RESULT checkTayte(t)
     */
    public void muutaTayte(String t){ this.tayte = checkTayte(t); }

    @Override
    public String toString(){
        return tayte;
    }

}
