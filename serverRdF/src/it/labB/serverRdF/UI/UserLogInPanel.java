package it.labB.serverRdF.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.collections.map.HashedMap;

import it.labB.serverRdF.TestObj;

/* CLASSE CHE DEFINISCE LA FINESTRA DI LOGIN AL DB CHE VIENE CREATA ALL'AVVIO DEL SERVER */
public class UserLogInPanel extends JFrame {

	private GUIComune gui;

	private JFrame jfrAccessoLobby;
	private JTextField textIPServer, textEmail;
	private JPasswordField textPass;
	private JLabel lblHost, lblUser, lblPassword, lblEccezione;
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gbglblEccezione, gbglblLogo, gbglblPassword, gbglblHost, gbgtxfIPServer, gbglblUser,
			gbgtxfEmail, gbgtxfPass, gbgbtnAccedi;
	private JButton btnAccedi;

	public UserLogInPanel() {
		super();

		// 0c.Inizializzazione
		gui = new GUIComune();
		jfrAccessoLobby = new JFrame();
		gridBagLayout = new GridBagLayout();
		gbglblPassword = new GridBagConstraints();
		gbglblHost = new GridBagConstraints();
		gbgtxfIPServer = new GridBagConstraints();
		gbglblUser = new GridBagConstraints();
		gbglblEccezione = new GridBagConstraints();
		gbgtxfEmail = new GridBagConstraints();
		gbgtxfPass = new GridBagConstraints();
		gbgbtnAccedi = new GridBagConstraints();
		textIPServer = new JTextField();
		textEmail = new JTextField();
		textPass = new JPasswordField();
		lblHost = new JLabel();
		lblUser = new JLabel();
		lblPassword = new JLabel();
		lblEccezione = new JLabel();
		btnAccedi = new JButton();
		gbglblLogo = new GridBagConstraints();

		// 0b.Accedi al Database - JFrame
		jfrAccessoLobby = new JFrame("Accedi alla Lobby di gioco");
		gui.panel(jfrAccessoLobby);

		// 0a.GridBagLayout
		gridBagLayout.columnWidths = new int[] { 300, 300, 300 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		jfrAccessoLobby.getContentPane().setLayout(gridBagLayout);

		// 0.Logo
		gbglblLogo.gridwidth = 3;
		gbglblLogo.insets = new Insets(0, 0, 5, 0);
		gbglblLogo.gridx = 0;
		gbglblLogo.gridy = 0;
		jfrAccessoLobby.getContentPane().add(gui.logo(), gbglblLogo);

		// 1.Host - Label & Textfield
		gbglblHost.insets = new Insets(0, 0, 5, 5);
		gbglblHost.gridx = 0;
		gbglblHost.gridy = 1;
		jfrAccessoLobby.getContentPane().add(gui.label(lblHost, "Host"), gbglblHost);

		gbgtxfIPServer.fill = GridBagConstraints.HORIZONTAL;
		gbgtxfIPServer.insets = new Insets(0, 0, 5, 0);
		gbgtxfIPServer.gridx = 1;
		gbgtxfIPServer.gridy = 1;
		jfrAccessoLobby.getContentPane().add(gui.textfield(textIPServer), gbgtxfIPServer);

		// 2.Email - Label & Textfield
		gbglblUser.insets = new Insets(0, 0, 5, 5);
		gbglblUser.gridx = 0;
		gbglblUser.gridy = 2;
		jfrAccessoLobby.getContentPane().add(gui.label(lblUser, "Username"), gbglblUser);

		gbgtxfEmail.fill = GridBagConstraints.HORIZONTAL;
		gbgtxfEmail.insets = new Insets(0, 0, 5, 0);
		gbgtxfEmail.gridx = 1;
		gbgtxfEmail.gridy = 2;
		jfrAccessoLobby.getContentPane().add(gui.textfield(textEmail), gbgtxfEmail);

		// 3.Password - Label & Textfield
		gbglblPassword.insets = new Insets(0, 0, 5, 5);
		gbglblPassword.gridx = 0;
		gbglblPassword.gridy = 3;
		jfrAccessoLobby.getContentPane().add(gui.label(lblPassword, "Password"), gbglblPassword);

		gbgtxfPass.fill = GridBagConstraints.HORIZONTAL;
		gbgtxfPass.insets = new Insets(0, 0, 5, 0);
		gbgtxfPass.gridx = 1;
		gbgtxfPass.gridy = 3;
		jfrAccessoLobby.getContentPane().add(gui.textfield(textPass), gbgtxfPass);

		// 4.Accedi - Button
		gbgbtnAccedi.fill = GridBagConstraints.HORIZONTAL;
		gbgbtnAccedi.gridx = 1;
		gbgbtnAccedi.gridy = 4;
		jfrAccessoLobby.getContentPane().add(gui.button(btnAccedi, "Accedi"), gbgbtnAccedi);

		// 5.Eccezioni - Label
		gbglblEccezione.insets = new Insets(0, 0, 0, 5);
		gbglblEccezione.gridx = 1;
		gbglblEccezione.gridy = 5;
		jfrAccessoLobby.getContentPane().add(lblEccezione, gbglblEccezione);

		// DEFINIZIONE DEL METODO DA ASSOCIARE AL BOTTONE CONNETTI PRESENTE NELLA
		// FINESTRA
		btnAccedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ipServer = textIPServer.getText();
				String email = textEmail.getText();
				String pass = String.valueOf(textPass.getPassword());
				// VERIFICA L'INSERIMENTO DI TUTTI I DATI
				if (ipServer.isEmpty() || email.isEmpty() || pass.isEmpty()) {
					gui.label_eccezione(lblEccezione, "Tutti i campi sono obbligatori");
				} else {

					connectToGame(ipServer, email, pass);
					jfrAccessoLobby.dispose();
				}
			}
		});
	}

	public JTextField getTextIPServer() {
		return textIPServer;
	}

	public void setTextIPServer(JTextField textIPServer) {
		this.textIPServer = textIPServer;
	}

	public JTextField getTextEmail() {
		return textEmail;
	}

	public void setTextEmail(JTextField textEmail) {
		this.textEmail = textEmail;
	}

	public JPasswordField getTextPass() {
		return textPass;
	}

	public void setTextPass(JPasswordField textPass) {
		this.textPass = textPass;
	}

	public void connectToGame(String ipServer, String email, String pass) {
		BufferedReader in = null;
		PrintStream out = null;
		Socket socket = null;
		String message;

		try {
			socket = new Socket(ipServer, 4000);
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			HashMap<String, String> userLogIn = new HashMap<String, String>();
			userLogIn.put("EMAIL", email);
			userLogIn.put("PASS", pass);
			oos.writeObject(userLogIn);
			oos.close();
			os.close();

			socket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}