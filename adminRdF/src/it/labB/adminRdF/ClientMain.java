package it.labB.adminRdF;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import it.labB.serverRdF.TestObj;
import it.labB.serverRdF.UI.UserLogInPanel;

public class ClientMain {
	public static void main(String argv[]) {
		
		new UserLogInPanel();
		
	}
}
