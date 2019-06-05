package it.labB.serverRdF;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread extends Thread {
	private ServerSocket Server;

	public ServerThread() throws Exception {
		Server = new ServerSocket(4000);
		System.out.println("Il Server è in attesa sulla porta 4000.");
		this.start();
	}

	public void run() {
		while (true) {
			try {
				System.out.println("In attesa di Connessione.");
				Socket client = Server.accept();
				System.out.println("Connessione accettata da: " + client.getInetAddress());
				// Connect c = new Connect(client);

				InputStream is = client.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				HashMap<String, String> test = (HashMap<String,String>) ois.readObject();
				System.out.println("***********************");
				System.out.println("OGGETTO RICEVUTO:");
				System.out.println("EMAIL: " + test.get("EMAIL"));
				System.out.println("PASS: " + test.get("PASS"));

				System.out.println("***********************");
				if (test != null) {
					System.out.println(test.toString());
				}
				System.out.println((TestObj) ois.readObject());
				is.close();
			} catch (Exception e) {
			}
		}
	}
}
