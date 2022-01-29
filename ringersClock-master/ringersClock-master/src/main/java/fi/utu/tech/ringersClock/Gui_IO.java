package fi.utu.tech.ringersClock;

import java.time.*;
import java.util.ArrayList;

import fi.utu.tech.entities.Kakku;
import fi.utu.tech.ringersClock.UI.MainViewController;
import fi.utu.tech.entities.WakeUpGroup;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Gui_IO {

	private MainViewController cont;
	private ClockClient cc;

	public Gui_IO(MainViewController cont) {
		this.cont = cont;
	}

	public void setCC(ClockClient cc){ this.cc = cc; }

	/*
	 * Method for displaying the time of the alarm Use this method to set the alarm
	 * time in the UI The time is received from the server
	 * 
	 * DO not edit
	 */
	public void setAlarmTime(Instant time) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				cont.setAlarmTime(time);
			}
			//public void run() { cont.setAlarmTime(Instant.now()); }
		});
	}
	
	/*
	 * Method for clearing the time of the alarm. Use this method when alarm is cancelled.
	 * 
	 * DO not edit
	 */
	public void clearAlarmTime() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				cont.clearAlarmTime();
			}
		});
	}
	
	/*
	 * Method for appending text to the status display You can write status messages
	 * to UI using this method.
	 * 
	 * DO not edit
	 */
	public void appendToStatus(String text) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				cont.appendToStatus(text);
			}
		});
	}

	/*
	 * Method for filling the existing wake-up groups to Choosebox Must run every
	 * time when the existing wake-up groups are receiver from the server
	 *
	 *DO not edit
	 */
	public void fillGroups(ArrayList<WakeUpGroup> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				cont.fillGroups(list);
			}
		});
	}

	/*
	 * This method is for waking up the ringer when it is time.
	 * 
	 * DO not edit
	 */
	public void alarm() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				cont.alarm();
				cont.clearAlarmTime();
			}
		});
	}

	/*
	 * This method must only on the client is the group leader. The idea is to use
	 * this method to confirm the wake up before waking up the rest of the team
	 * 
	 * DO not edit
	 */
	public void confirmAlarm(WakeUpGroup group) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirm alarm");
				alert.setHeaderText("Do you want wake up the team?");
				alert.setContentText("The weather seems to be ok.");
				alert.showAndWait().ifPresent((btnType) -> {
					if (btnType == ButtonType.OK) {
						AlarmAll(group);
					} else {
						CancelAlarm(group);
					}
				});
			}
		});
	}

	/*
	 * This method is run if the leader accepts the wake-up Now you have to wake up
	 * the rest of the team
	 * 
	 * IMPLEMENT THIS ONE
	 */
	public void AlarmAll(WakeUpGroup group) {
		Kakku kakku = new Kakku("herätäRyhmä", group);
		cc.sendKakku(kakku);
	}
	/*
	 * This method is run if the leader cancel the wake-up The alarm is cancelled
	 * and should be removed from server
	 * 
	 * IMPLEMENT THIS ONE
	 */
	public void CancelAlarm(WakeUpGroup group) {
		Kakku kakku = new Kakku("peruutaHerätys", group);
		cc.sendKakku(kakku);
	}

	/*
	 * This method is run when user pressed the create button Now the group with
	 * wake-up time must be sent to server
	 * 
	 * IMPLEMENT THIS ONE
	 */
	public void createNewGroup(String name, Integer hour, Integer minutes, boolean noRain, boolean temp) {
		LocalDateTime wakeUpTime = LocalDate.now().atTime(hour, minutes, 0); //2020-12-20T12:00
		Instant instant = wakeUpTime.atZone(ZoneId.systemDefault()).toInstant();
		WakeUpGroup wug = new WakeUpGroup(null, name, instant, noRain, temp);
		Kakku kakku = new Kakku("luoRyhmä", wug);
		cc.sendKakku(kakku);
	}

	/*
	 * This method is run when user pressed the join button The info must be sent to
	 * server
	 * 
	 * IMPLEMENT THIS ONE
	 */
	public void joinGroup(WakeUpGroup group) {
		Kakku kakku = new Kakku("liityRyhmään", group);
		cc.sendKakku(kakku);
	}
	
	/*
	 * This method is run when user pressed the resign button The info must be sent to
	 * server
	 * 
	 * IMPLEMENT THIS ONE
	 */
	public void resignGroup() {
		Kakku kakku = new Kakku("poistuRyhmästä");
		cc.sendKakku(kakku);
	}
}
