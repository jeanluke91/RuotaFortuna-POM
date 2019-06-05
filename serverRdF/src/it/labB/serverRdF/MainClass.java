package it.labB.serverRdF;

import java.util.*;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.*;
public class MainClass extends Thread {
	private ServerSocket server;
	/*
	 * PERMETTE L'ESECUZIONE DEL CLIENT ADMIN, COME PRIMA OPERAZIONE ISTANZIA LA
	 * FINESTRA DI LOGIN ALLA LOBBY
	 */
	public static void main(String[] args) throws Exception {
		new MainClass();

	/*	DBLogInPanel dbLoginPanel = new DBLogInPanel();
		InetAddress ia = null; 

		// OTTENGO IP DELLA MACCHINA COSì DA POTER DIRE AI CLIENT COME CONNETTERSI AL
		// SERVER
		try {
			ia = InetAddress.getLocalHost();
			System.out.println(ia.toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ia.getHostAddress();

		}
*/
	}
	
	public MainClass() throws Exception {
		server = new ServerSocket(4000);
		System.out.println("Il Server è in attesa sulla porta 4000.");
		this.start();
	}
	
	public void run() {
		while (true) {
			try {
				System.out.println("In attesa di Connessione.");
				Socket client = server.accept();
				System.out.println("Connessione accettata da: " + client.getInetAddress());
				// Connect c = new Connect(client);

				InputStream is = client.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				List<String> test = (List<String>) ois.readObject();
				if (test != null) {
					System.out.println(test.toString());
				}
				System.out.println((String) ois.readObject());
				is.close();
			} catch (Exception e) {
			}
		}
	}
	

}
