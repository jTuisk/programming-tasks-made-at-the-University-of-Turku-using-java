package fi.utu.tech.weatherInfo;

/*
 * Class presenting current weather
 * Is returned by  weather service class
 */

public class WeatherData {

	/*
	 * What kind of data is needed? What are the variable types. Define class
	 * variables to hold the data
	 */

	/*
	 * Since this class is only a container for weather data we only need to set the
	 * data in the constructor.
	 */

	private boolean belowZero;
	private boolean isRaining;

	public WeatherData(boolean degree, boolean rain) {
		this.belowZero = degree;
		this.isRaining = rain;
	}

	public boolean getBelowZero(){return this.belowZero;}
	public boolean getIsRaining(){return this.isRaining;}

}
