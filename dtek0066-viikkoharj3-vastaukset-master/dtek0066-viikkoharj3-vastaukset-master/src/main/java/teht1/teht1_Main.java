package teht1;

import java.util.ArrayList;
import java.util.List;

public class teht1_Main {

	public static void main(String[] args) {
		Opiskelija opiskelija = new Opiskelija();
		VuokrattavaAuto ajAuto = new VuokrattavaAuto();
		VuokrattavaAuto vAuto = new VuokrattavaAuto();

		Matka ajettavaMatka = new Matka(52);

		if(opiskelija.getOmaAuto().aja(ajettavaMatka)) {
			System.out.println("Matkaan omalla autolla.");
			opiskelija.getOmaAuto().aja(ajettavaMatka);
		} else {
			System.out.println("Matkaan jonkun muun autolla. Oma auto rikki.");
			System.out.println("Tarkistetaan, onko ainejärjestön auto vapaa.");
			if(!ajAuto.getVuokrattu() && opiskelija.getHallituksenJasen()) {
				System.out.println("Auto vapaa! Lainataan sitä matkaa varten.");
				ajAuto.vuokraaAuto(opiskelija);
				if(ajAuto.aja(ajettavaMatka))
					ajAuto.palautaAuto();
			} else {
				System.out.println("Ei ole vapaa. Joudutaan turvautumaan vuokra-autoon.");
				vAuto.vuokraaAuto(opiskelija);
				if(vAuto.aja(ajettavaMatka))
					vAuto.palautaAuto();
			}
		}

		System.out.println("Matkaa suoritettu yhteensä " + ajettavaMatka + ".");
	}

}
