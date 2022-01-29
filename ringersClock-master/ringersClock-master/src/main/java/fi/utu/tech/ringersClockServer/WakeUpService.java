package fi.utu.tech.ringersClockServer;


import fi.utu.tech.entities.Kakku;
import fi.utu.tech.entities.WakeUpGroup;
import fi.utu.tech.weatherInfo.FMIWeatherService;
import fi.utu.tech.weatherInfo.WeatherData;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.*;
import java.util.Timer;
import java.util.TimerTask;

public class WakeUpService extends Thread {

	public WakeUpService(){}

	public void run() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				LocalDateTime wakeUpTime = LocalDate.now().atTime(LocalTime.now().getHour() , LocalTime.now().getMinute(), LocalTime.now().getSecond()); //2020-12-20T12:00
				Instant instant = wakeUpTime.atZone(ZoneId.systemDefault()).toInstant();
				for(WakeUpGroup wug : ServerSocketListener.groups.keySet()){
					if(instant.equals(wug.getAlarmTime())){
						if(!wug.getIsRaining() && !wug.getBelowZero()){
							ServerSocketListener.groups.get(wug).get(0).sendKakku(new Kakku("herätäJohtaja"));
						}else{
							try {
								WeatherData wd = FMIWeatherService.getWeather();
								boolean wakeLeaderUp = true;

								if(wug.getIsRaining() && wd.getIsRaining())
									wakeLeaderUp = false;

								if(wug.getBelowZero() && wd.getBelowZero())
									wakeLeaderUp = false;

								if(wakeLeaderUp){
									ServerSocketListener.groups.get(wug).get(0).sendKakku(new Kakku("herätäJohtaja"));
								}else{
									System.out.println("Sataa: wug: "+wug.getIsRaining()+" wd: "+wd.getIsRaining());
									System.out.println("Pakkanen: wug: "+wug.getBelowZero()+" wd: "+wd.getBelowZero());
									ServerSocketListener.groups.get(wug).get(0).removeGroup(wug, "Ryhmä \""+wug.getName()+"\" poistettu!" +
																											"\nSää vaatimukset eivät täsmänneet");
								}
							} catch (IOException | ParserConfigurationException | SAXException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}, 0, 1000);
	}
}
