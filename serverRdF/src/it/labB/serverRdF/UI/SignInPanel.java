package it.labB.serverRdF.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import it.labB.serverRdF.ServerThread;
import it.labB.serverRdF.DB.QueryExecutor;
import it.labB.serverRdF.DB.Registrazione;
import it.labB.serverRdF.DB.Utente;

/* CLASSE CHE DEFINISCE LA FINESTRA DI LOGIN AL DB CHE VIENE CREATA ALL'AVVIO DEL SERVER */
public class SignInPanel extends JFrame {
	private static final long serialVersionUID = 1L;

	private GUIComune gui;
	private JTextField txfNome, txfCognome, txfNickname, txfEmail;
	private JPasswordField textPassword;
	private JFrame jfrRegistrazione;
	private JLabel lblNome, lblCognome, lblNickname, lblEmail, lblPassword, lblEccezione;
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gbglbllogo, gbglblNome, gbglblCognome, gbglblNickname, gbglblEmail, gbglblPassword,
			gbglblEccezione, gbgtxfNome, gbgtxfCognome, gbgtxfNickname, gbgtxfEmail, gbgtxfPassword, gbgbtnRegistrati;
	private JButton btnRegistrati;

	private QueryExecutor qe;

	// COSTRUTTORE CHE SETTA I LE PROPRIETA' DELLA FINESTRA
	public SignInPanel(final QueryExecutor qe, final String tipoUtente) {
		super();
		this.qe = qe;

		// 0c.Inizializzazione
		gui = new GUIComune();
		jfrRegistrazione = new JFrame();
		gridBagLayout = new GridBagLayout();
		gbglbllogo = new GridBagConstraints();
		gbglblNome = new GridBagConstraints();
		gbglblCognome = new GridBagConstraints();
		gbglblNickname = new GridBagConstraints();
		gbglblEmail = new GridBagConstraints();
		gbglblPassword = new GridBagConstraints();
		gbgtxfNome = new GridBagConstraints();
		gbgtxfCognome = new GridBagConstraints();
		gbgtxfNickname = new GridBagConstraints();
		gbgtxfEmail = new GridBagConstraints();
		gbgtxfPassword = new GridBagConstraints();
		gbgbtnRegistrati = new GridBagConstraints();
		gbglblEccezione = new GridBagConstraints();
		lblNome = new JLabel();
		lblCognome = new JLabel();
		lblNickname = new JLabel();
		lblEmail = new JLabel();
		lblPassword = new JLabel();
		lblEccezione = new JLabel();
		txfNome = new JTextField();
		txfCognome = new JTextField();
		txfNickname = new JTextField();
		txfEmail = new JTextField();
		textPassword = new JPasswordField();
		btnRegistrati = new JButton();

		// 0b.Registrazione - JFrame
		jfrRegistrazione = new JFrame("Effettua Registrazione");
		gui.panel(jfrRegistrazione);

		// 0a.GridBagLayout
		gridBagLayout.columnWidths = new int[] { 300, 300, 300 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		jfrRegistrazione.getContentPane().setLayout(gridBagLayout);

		// 0.Logo
		gbglbllogo.gridwidth = 3;
		gbglbllogo.insets = new Insets(0, 0, 5, 0);
		gbglbllogo.gridx = 0;
		gbglbllogo.gridy = 0;
		jfrRegistrazione.getContentPane().add(gui.logo(), gbglbllogo);

		// 1.Nome - Label & Textfield
		gbglblNome.anchor = GridBagConstraints.EAST;
		gbglblNome.insets = new Insets(0, 0, 5, 5);
		gbglblNome.gridx = 0;
		gbglblNome.gridy = 1;
		jfrRegistrazione.getContentPane().add(gui.label(lblNome, "Nome"), gbglblNome);

		gbgtxfNome.fill = GridBagConstraints.HORIZONTAL;
		gbgtxfNome.insets = new Insets(0, 0, 5, 5);
		gbgtxfNome.gridx = 1;
		gbgtxfNome.gridy = 1;
		jfrRegistrazione.getContentPane().add(gui.textfield(txfNome), gbgtxfNome);

		// 2.Cognome - Label & Textfield
		gbglblCognome.anchor = GridBagConstraints.EAST;
		gbglblCognome.insets = new Insets(0, 0, 5, 5);
		gbglblCognome.gridx = 0;
		gbglblCognome.gridy = 2;
		jfrRegistrazione.getContentPane().add(gui.label(lblCognome, "Cognome"), gbglblCognome);

		gbgtxfCognome.fill = GridBagConstraints.HORIZONTAL;
		gbgtxfCognome.insets = new Insets(0, 0, 5, 5);
		gbgtxfCognome.gridx = 1;
		gbgtxfCognome.gridy = 2;
		jfrRegistrazione.getContentPane().add(gui.textfield(txfCognome), gbgtxfCognome);

		// 3.Email - Label & Textfield
		gbglblEmail.anchor = GridBagConstraints.EAST;
		gbglblEmail.insets = new Insets(0, 0, 5, 5);
		gbglblEmail.gridx = 0;
		gbglblEmail.gridy = 3;
		jfrRegistrazione.getContentPane().add(gui.label(lblEmail, "Email"), gbglblEmail);

		gbgtxfEmail.fill = GridBagConstraints.HORIZONTAL;
		gbgtxfEmail.insets = new Insets(0, 0, 5, 5);
		gbgtxfEmail.gridx = 1;
		gbgtxfEmail.gridy = 3;
		jfrRegistrazione.getContentPane().add(gui.textfield(txfEmail), gbgtxfEmail);

		// 4.Nickname - Label & Textfield
		gbglblNickname.anchor = GridBagConstraints.EAST;
		gbglblNickname.insets = new Insets(0, 0, 5, 5);
		gbglblNickname.gridx = 0;
		gbglblNickname.gridy = 4;
		jfrRegistrazione.getContentPane().add(gui.label(lblNickname, "Nickname"), gbglblNickname);

		gbgtxfNickname.fill = GridBagConstraints.HORIZONTAL;
		gbgtxfNickname.insets = new Insets(0, 0, 5, 5);
		gbgtxfNickname.gridx = 1;
		gbgtxfNickname.gridy = 4;
		jfrRegistrazione.getContentPane().add(gui.textfield(txfNickname), gbgtxfNickname);

		// 5.Password - Label e Textfield
		gbglblPassword.anchor = GridBagConstraints.EAST;
		gbglblPassword.insets = new Insets(0, 0, 5, 5);
		gbglblPassword.gridx = 0;
		gbglblPassword.gridy = 5;
		jfrRegistrazione.getContentPane().add(gui.label(lblPassword, "Password"), gbglblPassword);

		gbgtxfPassword.fill = GridBagConstraints.HORIZONTAL;
		gbgtxfPassword.insets = new Insets(0, 0, 5, 5);
		gbgtxfPassword.gridx = 1;
		gbgtxfPassword.gridy = 5;
		jfrRegistrazione.getContentPane().add(gui.passwordfield(textPassword), gbgtxfPassword);

		// 6.Registrati - Button
		gbgbtnRegistrati.insets = new Insets(0, 0, 5, 5);
		gbgbtnRegistrati.gridx = 1;
		gbgbtnRegistrati.gridy = 6;
		jfrRegistrazione.getContentPane().add(gui.button(btnRegistrati, "Registrati"), gbgbtnRegistrati);

		// 7.Eccezioni - Label
		gbglblEccezione.insets = new Insets(0, 0, 0, 5);
		gbglblEccezione.gridx = 0;
		gbglblEccezione.gridy = 7;
		jfrRegistrazione.getContentPane().add(gui.label_eccezione(lblEccezione, "Registrati"), gbglblEccezione);

		// DEFINIZIONE DEL METODO DA ASSOCIARE AL BOTTONE CONNETTI PRESENTE NELLA
		// FINESTRA
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = txfNome.getText();
				String cognome = txfCognome.getText();
				String password = String.valueOf(textPassword.getPassword());
				String nickname = txfNickname.getText();
				String email = txfEmail.getText();
				// VERIFICA L'INSERIMENTO DI TUTTI I DATI
				if (nome.isEmpty() || cognome.isEmpty() || password.isEmpty() || nickname.isEmpty()
						|| email.isEmpty()) {
					gui.label_eccezione(lblEccezione, "Tutti i campi sono obbligatori");
				} else {
					// VERIFICA CHE NON VENGANO INSERITI MAIL/NICKNAME GIA' UTILIZZATI
					if (qe.checkUserMail(nickname, email))
						gui.label_eccezione(lblEccezione, "L'utente risulta già registrato");
					else {
						try {
							// REGISTRA L'UTENTE E SALVA I DATI DI REGISTRAZIONE
							/*
							 * METODO PER LA CREAZIONE DEL CODICE DI VERIFICA
							 */
							String code = randomAlphaNumeric(6);
							Registrazione registrazione = new Registrazione(1, "OK", new Date(), code);
							Utente utente = new Utente(null, nome, cognome, email, nickname, password, tipoUtente,
									registrazione.getIdRegistrazione());
							qe.registerInfo(registrazione);
							qe.registerUser(utente);
							send_uninsubria_email(email, code, utente);
							gui.label_eccezione(lblEccezione,
									"Registrazione avvenuta con successo!\nA breve riceverai una mail con il riepilogo dati e istruzioni per il primo accesso.");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (AddressException e) {
							e.printStackTrace();
						} catch (MessagingException e) {
							e.printStackTrace();
						}
					}
				}
				// frmSignIn.dispose();

			}
		});

	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public void send_uninsubria_email(String to, String code, Utente utente)
			throws AddressException, MessagingException {
		String subject = "Registrazione Ruota della Fortuna";
		String body = "Gentile utente,\nabbiamo ricevuto la tua richiesta di registrazione.\nDi seguito i dati da te inseriti in fase di registrazione:\nNome: "
				+ utente.getNome() + "\nCognome: " + utente.getCognome() + "\nNickname: " + utente.getNickname()
				+ "\nEmail: " + utente.getEmail()
				+ "\n\n\nPer accedere dovrai utilizzare il tuo indirizzo mail e la password da te inserita.\n\nAl primo accesso ti verrà chiesto di inserire il seguente codice di verifica "
				+ code
				+ ".\nTale codice ha una validità di 10 minuti, dopo i quali l'account verrà disabilitato e dovrai richiedere la generazione di un nuovo codice.\n\n\nGrazie di averci scelto\nBuon divertimento!";
		System.setProperty("java.net.useSystemProxies", "true");

		String password = "progettoLab!";
		String username = "laboratorioprogettob@gmail.com";

		String host = "smtp.gmail.com";
		String from = username;

		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", 587);

		Session session = Session.getInstance(props);

		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(from));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
		msg.setSubject(subject);
		msg.setText(body);

		Transport.send(msg, username, password);
		System.out.println("\nMail inviata correttamente.");

		try {
			new ServerThread();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jfrRegistrazione.dispose();
	}
}
