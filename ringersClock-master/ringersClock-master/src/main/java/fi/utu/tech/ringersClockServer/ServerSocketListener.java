package fi.utu.tech.ringersClockServer;

import fi.utu.tech.entities.WakeUpGroup;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerSocketListener extends Thread {

	public static HashMap<WakeUpGroup, ArrayList<KakkuHandler>> groups = new HashMap<>();
	public static ArrayList<KakkuHandler> clients = new ArrayList<>();
	public static Integer index = 1;

	private String host;
	private int port;
	private WakeUpService wup;

	public ServerSocketListener(String host, int port, WakeUpService wup) {
		this.host = host;
		this.port = port;
		this.wup = wup;
	}

	/**
	 * Kuuntelee asiakaspuolen yhteydenottopyyntöjä tiettyyn porttiin
	 * Jos yhteydenottopyyntö tulee, luo KakkuHandlerin siitä vastaamaan
	 * Starttaa KakkuHandlerin toiminnan
	 */
	public void run(){
		try (ServerSocket serverSocket = new ServerSocket(this.port)) {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				new KakkuHandler(clientSocket).start();
			}
		}catch (IOException e) {
			System.out.println("Server exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
