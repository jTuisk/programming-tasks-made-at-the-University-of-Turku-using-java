package fi.utu.tech.weatherInfo;

import fi.utu.tech.ringersClockServer.ServerSocketListener;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FMIWeatherService {

	private static float belowZeroAt = 1f; //koska Clientissä lukee > 0 vaadittava lämpö
	private static float rainingAt = 99f;

	/*
	 * In this method your are required to fetch weather data from The Finnish
	 * Meteorological Institute. The data is received in XML-format.
	 */

	public static WeatherData getWeather() throws IOException, ParserConfigurationException, SAXException {
		URL dataURL = new URL(getFinalUrl());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(dataURL.openStream());
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("gml:doubleOrNilReasonTupleList");
		String[] fixedData = fixedData = nodeList.item(0).getTextContent().trim().split(" ");

		float	temperature = Float.parseFloat(fixedData[0]),
				humidity = Float.parseFloat(fixedData[1]);

		boolean	belowZero = temperature < belowZeroAt,
				isRaining = humidity > rainingAt;

		WeatherData weatherData = new WeatherData(belowZero, isRaining);

		return weatherData;
	}


	/**
	 * Funktio palauttaa ilmatieteenlaitoksen api urlin, josta hakemaan lämpötilan ja ilmankosteuden.
	 * Funktio käyttää getStartAndEndTimeAsUrlString() saadakseen vain viimeisemmät tiedot
	 * @return
	 */
	private static String getFinalUrl(){
		return "http://opendata.fmi.fi/wfs?" +
				"service=WFS&version=2.0.0&" +
				"request=getFeature&" +
				"storedquery_id=fmi::observations::weather::multipointcoverage&" +
				"place=turku&" +
				"parameters=temperature,Humidity&"+getStartAndEndTimeForFinalUrl();
	}

	/**
	 * Funktio luo ilmatieteenlaitoksen api urlille loppuosan johon laitetaan startTime ja endTime.
	 * startTime on localTime -2 tuntia ja -9 minuuttia, koska ilmatieteenlaitos päivittä dataa joka 10 minuutti.
	 * endTime on localTime -2 tuntia.
	 * -2 Tuntia johtuu siitä koska asumme gmt+2 aikavyöhykkeessä ja ilmatieteenlaitos käyttää gmt0 aikavyöhykettä.
	 * @return
	 */
	private static String getStartAndEndTimeForFinalUrl(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:00'Z'");
		LocalDateTime dateTimeBefore = LocalDateTime.now().minusHours(2).minusMinutes(9);
		LocalDateTime dateNow = LocalDateTime.now().minusHours(2);

		return "&starttime="+formatter.format(dateTimeBefore) + "&endtime=" + formatter.format(dateNow)+"&";
	}
}
