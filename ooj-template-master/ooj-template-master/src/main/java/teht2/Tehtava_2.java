package teht2;
import java.util.*;

public class Tehtava_2 {

	public static void main(String[] args) {
    	Scanner lukija = new Scanner(System.in);
		Kortti[] käsi = new Kortti[5];
		 
		for(int i=0; i<5; i++) {
			System.out.print("Syötä kortin maa: ");
			String maa = lukija.nextLine();
		    System.out.print("Syötä kortin arvo: ");
		    int arvo = lukija.nextInt();

		    Kortti kortti = määritäKortti(maa, arvo);
		    käsi[i] = kortti;
		}

		lukija.close();
		 
		 boolean käsiSisältääParin = sisältääParin(käsi);
		 System.out.println("Käsi " +
		            (käsiSisältääParin ? "sisältää parin" : "ei sisällä paria"));
		 
	}
	/*
	 * @.pre käsi != null && käsi.length == 5
	 * @.post FORALL(i; 0 <= i < käsi.length;
	 * 				EXISTS(j; 0 <= j < käsi.length;
	 * 						i != j
	 * 						&& käsi[i].getArvo() == käsi[j].getArvo()))
	 */
	public static boolean sisältääParin(Kortti[] käsi) {
		for(int i = 0; i < käsi.length; i++) {
			for(int j = 0; j < käsi.length; j++) {
				if(i != j && käsi[i].getArvo() == käsi[j].getArvo()) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * @.pre 	maa != null && maa.length > 0
	 * 			int > 0 && int <= 13
	 * @.post 	RESULT == Kortti(maa,arvo) || throws IllegalArgumentException
	 */
	public static Kortti määritäKortti(String maa, int arvo) throws InputMismatchException {
		final List<String> maat = new ArrayList<String>(List.of("hertta", "risti", "ruutu", "pata"));

		if(arvo < 0 || arvo > 13)
			throw new InputMismatchException("Vääränlainen arvo");

		if(!maat.equals(maa.toLowerCase().trim()))
			throw new InputMismatchException("Vääränlainen maa");
		
		Kortti uusiKortti = new Kortti(maa, arvo);
		return uusiKortti;
		
	}

}
