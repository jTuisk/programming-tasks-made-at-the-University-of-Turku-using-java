package teht3;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class teht3_Main{

	public static void main(String[] args){

		var lista = List.of(1,2,3);
		System.out.println(lista);
		
		//tietovirraksi
		var streamedList_A = lista.stream();
		
		//takaisin listaksi:
		// var takaisinListaksi = tietovirta.collect(Collectors.toList());
		
		/* A */
		var streamedList_B = lista.stream();
		var concatLists = Stream.concat(streamedList_A, streamedList_B).collect(Collectors.toList());
		System.out.println("Yhdistetty lista: " + concatLists);
		
		/* B */
		var doubledList = concatLists.stream().map(number -> number*2).collect(Collectors.toList());
		System.out.println("Tuplattu lista: " + doubledList);
		var withoutFours = doubledList.stream().filter(number -> number != 4).collect(Collectors.toList());
		System.out.println("Poistettu neloset: " + withoutFours);
		
		/* C */
		var largestInteger = lista.stream().max((a,b) -> {
			return Integer.compare(a, b);
		});
		System.out.println("Suurin luku Integer.comparella: " + largestInteger.get());
		
		var largestInteger2 = lista.stream().max(Integer::compare).get();
		System.out.println("Suurin luku ::-notaatiolla: " + largestInteger2);
		
		/* D */
		System.out.println("Tulostetaan luvut:");
		concatLists.forEach(System.out::println);

		/* E */

		// 1) oma luokka
		Nelioija nelioija_1 = new Nelioija();
		System.out.println("10 * 10 = " + nelioija_1.apply(10));

		/* 2) nimetön luokka
		 * f) yritetään heittää poikkeus Exception nimettömän luokan metodissa,
		 * mutta tämä ei onnistu (siksi laitettu tässä yhteydessä kommenteiksi
		 * jotta main ei heitä erroria)
		 */
		Function<Integer, Integer> nelioija_2 = new Function<Integer, Integer>() {
			@Override
			public Integer apply(Integer t) /*throws Exception*/ {
				return (int) Math.pow(t, 2);
				//throw new Exception("test");
			}
		};

		try {
			System.out.println("4 * 4 = " + nelioija_2.apply(4));
		} catch (Exception err){
			System.out.println(err.getMessage());
		}

		// 3) funktioliteraaliolio
		Function<Integer, Integer> nelioija_3 = (Integer x) -> (int) Math.pow(x, 2);
		System.out.println("15 * 15 = " + nelioija_3.apply(15));

		/* F
		/* Luodaan uusi rajapinta F_Rajapinta ja määritetään
		 * metodi laskeNelio() heittämään poikkeus Exception
		 * ja luodaan overriden metodi nimettömään luokkaan.
		 * Jotta saadaan käsiteltyä poikkeuksia, rajataan neliöiden
		 * laskeminen positiivisiin lukuihin (joskin neg. lukujen
		 * neliöiden laskemiset tietysti onnistuvat)
		 */
		try {
			F_Rajapinta fun = new F_Rajapinta() {
				@Override
				/*Lasketaan positiivisen luvun neliö
				 *@.pre t != null && t > 0
				 *@.post RESULT == t*2 || throws Exception("Liian pieni arvo")
				 */
				public Object laskePositiivistenNelio(Object t) throws Exception {
					int kasiteltava = (int) t;
					if(kasiteltava > 0) return Math.pow(kasiteltava, 2);
					throw new Exception("Liian pieni arvo");
				}
			};

			System.out.println("7 * 7 = " + fun.laskePositiivistenNelio(0) );
		} catch (Exception e){
			System.out.println(e.getMessage());
		}

		/* Nyt poikkeuksen heittäminen onnistuu!
		 * Erona edelliseen, E-kohdassa heitettyyn poikkeukseen
		 * se, että F_Rajapinnan funktion määrittelyssä heitetään poikkeus,
		 * Function-rajapinnan apply()-funktion määrittelyssä tätä ei ole tehty.
		 */
	}
}