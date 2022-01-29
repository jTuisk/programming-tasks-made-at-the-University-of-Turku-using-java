package fi.utu.tech.ringersClock;

/*
 * A class for handling network related stuff
 */

import fi.utu.tech.entities.Kakku;
import fi.utu.tech.entities.WakeUpGroup;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClockClient extends Thread {

	private String host;
	private int port;
	private Gui_IO gio;
	private WakeUpGroup wug;
	private Kakku kakkuLahetys;
	private ObjectOutputStream outToServer;
	private ObjectInputStream inStream;

	public ClockClient(String host, int port, Gui_IO gio) {
		this.host = host;
		this.port = port;
		this.gio = gio;
	}

	public void run(){
		try (Socket socket = new Socket(host, port)) {
			this.outToServer = new ObjectOutputStream(socket.getOutputStream());
			this.inStream = new ObjectInputStream(socket.getInputStream());

			try{
				while(true){
					Object o = inStream.readObject();
					Kakku kakku = (o instanceof Kakku) ? (Kakku) o : null;
					smashTheCake(kakku);
				}
			} catch (IOException exe){
				inStream.close();
				outToServer.close();
			}
		} catch (Exception e) {
			throw new Error(e.getMessage());
		}
	}

	/**
	 * Lähetetään serverille kakku ObjectOutputStreaminä
	 */
	public void sendKakku(Kakku kakku) {
		try {
			System.out.println("Lähetetään kakku!");
			outToServer.writeObject(kakku);
			outToServer.flush();
		} catch (IOException e) {
			System.out.println("I/O exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Kakun purkaminen ymmärrettävään muotoon ObjectOutputStreamistä
	 */
	public void smashTheCake(Object o) {
		if(!(o instanceof Kakku))
			return;

		Kakku kakku = (Kakku) o;
		WakeUpGroup wug = kakku.getTayte() instanceof WakeUpGroup ? (WakeUpGroup) kakku.getTayte() : null;

		switch(kakku.getKuorrute()){
			case "herätäJohtaja":
				gio.alarm();
				gio.confirmAlarm(wug);
				break;
			case "herätä":
				gio.alarm();
				break;
			case "päivitäRyhmät":
				gio.fillGroups((ArrayList<WakeUpGroup>) kakku.getTayte());
				break;
			case "päivitäHerätysAika":
				gio.setAlarmTime(wug.getAlarmTime());
				break;
			case "statusMsg":
				//gio.appendToStatus(kakku.getResepti());
				break;
			case "poistaHerätys":
				gio.clearAlarmTime();
				break;
			default:
				System.out.println("Kuorutetta '"+kakku.getKuorrute()+"' ei löytynyt!");
		}
		if(kakku.getResepti() != null)
			gio.appendToStatus(kakku.getResepti());
	}

}
