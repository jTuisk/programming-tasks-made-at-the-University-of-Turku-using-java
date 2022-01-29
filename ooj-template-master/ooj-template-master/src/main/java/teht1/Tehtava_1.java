package teht1;

import java.util.*;

public class Tehtava_1 {

	public static void main(String[] args) {
		
		String pelaaja1 = new Scanner(System.in).nextLine().toLowerCase();
	    String pelaaja2 = new Scanner(System.in).nextLine().toLowerCase();
	    
	    //return 1 - pelaaja1 voittaa
	    //return 2 - pelaaja2 voittaa
	    //return 0 - tasapeli
	    int voittaja = pelaaKierros(pelaaja1, pelaaja2);
	    
	    if(voittaja == 1) {
		    System.out.println("Pelin voitti pelaaja " + voittaja);
	    } else if(voittaja == 2) {
		    System.out.println("Pelin voitti pelaaja " + voittaja);
	    } else if(voittaja == 0) {
		    System.out.println("Tasapeli!");

	    }

	}

	 /**
	 * @.pre 	pelaaja1 != null && pelaaja1.length > 0
	  * 	 	&& pelaaja2 != null && pelaaja2.length > 0
	 * @.post 	RESULT == 1
	 * 			|| RESULT == 2 
	 * 			|| RESULT == 0 
	 * 			|| throws InputMismatchException
	 */
	static int pelaaKierros(String pelaaja1, String pelaaja2) throws InputMismatchException {
			//pelaaja1 != kivi/paperi/sakset/spock/lisko
			//pelaaja2 != kivi/paperi/sakset/spock/lisko

			//scissors cuts paper

			//rock crushes lizard

			//lizard poisons spock

			//spock mashes scissors

			//scissors decapitates lizard

			//paper disproves spock

			//spock vaporizes rock

			//rock crushes scissors

		return 0;
	}
}