package it.labB.serverRdF.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import it.labB.serverRdF.DB.QueryExecutor;
import it.labB.serverRdF.DB.QueryPredefinite;

/* CLASSE CHE DEFINISCE LA FINESTRA DI LOGIN AL DB CHE VIENE CREATA ALL'AVVIO DEL SERVER */
public class DBLogInPanel extends JFrame {
	private static final long serialVersionUID = 1L;

	private GUIComune gui;

	private JFrame jfrAccediAlDB;
	private GridBagLayout gridBagLayout;
	private JButton btnConnetti;
	private GridBagConstraints gbglblHost, gbglblUser, gbglblPass, gbgtxfHost, gbgtxfUser, gbgpswPass, gbgbtnConnetti,
			gbglblLogo, gbglblEccezione;
	private JTextField txfHost, txfUser;
	private JPasswordField pswPass;
	private JLabel lblHost, lblUser, lblPassword, lblEccezione;

	private QueryExecutor qe;
	private static final String TIPO_MASTER = "M";
	private static final String TIPO_AMMINISTRATORE = "A";
	private static final String TIPO_GIOCATORE = "G";

	// COSTRUTTORE CHE SETTA I LE PROPRIETA' DELLA FINESTRA
	public DBLogInPanel() {
		super();

		// 0c.Inizializzazione
		gui = new GUIComune();
		gridBagLayout = new GridBagLayout();
		gbglblLogo = new GridBagConstraints();
		gbglblHost = new GridBagConstraints();
		gbgtxfHost = new GridBagConstraints();
		gbglblUser = new GridBagConstraints();
		gbgtxfUser = new GridBagConstraints();
		gbglblPass = new GridBagConstraints();
		gbgpswPass = new GridBagConstraints();
		gbgbtnConnetti = new GridBagConstraints();
		gbglblEccezione = new GridBagConstraints();
		lblHost = new JLabel();
		lblUser = new JLabel();
		lblPassword = new JLabel();
		lblEccezione = new JLabel();
		txfHost = new JTextField();
		txfUser = new JTextField();
		pswPass = new JPasswordField();
		btnConnetti = new JButton();

		// 0b.Accedi al Database - JFrame
		jfrAccediAlDB = new JFrame("Accedi al Database");
		gui.panel(jfrAccediAlDB);

		// 0a.GridBagLayout
		gridBagLayout.columnWidths = new int[] { 300, 300, 300 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		jfrAccediAlDB.getContentPane().setLayout(gridBagLayout);

		// 0.Logo
		gbglblLogo.gridwidth = 3;
		gbglblLogo.insets = new Insets(0, 0, 5, 0);
		gbglblLogo.gridx = 0;
		gbglblLogo.gridy = 0;
		jfrAccediAlDB.getContentPane().add(gui.logo(), gbglblLogo);

		// 1.Host - Label & Textfield
		gbglblHost.anchor = GridBagConstraints.EAST;
		gbglblHost.insets = new Insets(0, 0, 5, 5);
		gbglblHost.gridx = 0;
		gbglblHost.gridy = 1;
		jfrAccediAlDB.getContentPane().add(gui.label(lblHost, "Host"), gbglblHost);

		gbgtxfHost.fill = GridBagConstraints.HORIZONTAL;
		gbgtxfHost.insets = new Insets(0, 0, 5, 5);
		gbgtxfHost.gridx = 1;
		gbgtxfHost.gridy = 1;
		jfrAccediAlDB.getContentPane().add(gui.textfield_concolore(txfHost, gui.getGiallo()), gbgtxfHost);

		// 2.Username - Label & Textfield
		gbglblUser.anchor = GridBagConstraints.EAST;
		gbglblUser.insets = new Insets(0, 0, 5, 5);
		gbglblUser.gridx = 0;
		gbglblUser.gridy = 2;
		jfrAccediAlDB.getContentPane().add(gui.label(lblUser, "Username"), gbglblUser);

		gbgtxfUser.fill = GridBagConstraints.HORIZONTAL;
		gbgtxfUser.insets = new Insets(0, 0, 5, 5);
		gbgtxfUser.gridx = 1;
		gbgtxfUser.gridy = 2;
		jfrAccediAlDB.getContentPane().add(gui.textfield_concolore(txfUser, gui.getRosa()), gbgtxfUser);

		// 3.Password - Label & Textfield
		gbglblPass.anchor = GridBagConstraints.EAST;
		gbglblPass.insets = new Insets(0, 0, 5, 5);
		gbglblPass.gridx = 0;
		gbglblPass.gridy = 3;
		jfrAccediAlDB.getContentPane().add(gui.label(lblPassword, "Password"), gbglblPass);

		gbgpswPass.fill = GridBagConstraints.HORIZONTAL;
		gbgpswPass.insets = new Insets(0, 0, 5, 5);
		gbgpswPass.gridx = 1;
		gbgpswPass.gridy = 3;
		jfrAccediAlDB.getContentPane().add(gui.passwordfield_concolore(pswPass, gui.getBlu()), gbgpswPass);

		// 4.Connetti - Button
		gbgbtnConnetti.insets = new Insets(0, 0, 5, 5);
		gbgbtnConnetti.gridx = 1;
		gbgbtnConnetti.gridy = 4;
		jfrAccediAlDB.getContentPane().add(gui.button(btnConnetti, "Connetti"), gbgbtnConnetti);

		// 5.Eccezioni - Label
		gbglblEccezione.insets = new Insets(0, 0, 0, 5);
		gbglblEccezione.gridx = 1;
		gbglblEccezione.gridy = 5;
		jfrAccediAlDB.getContentPane().add(lblEccezione, gbglblEccezione);

		// DEFINIZIONE DEL METODO DA ASSOCIARE AL BOTTONE CONNETTI PRESENTE NELLA
		// FINESTRA
		btnConnetti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String host = txfHost.getText();
				String user = txfUser.getText();
				String pass = String.valueOf(pswPass.getPassword());
				// VERIFICA L'INSERIMENTO DI TUTTI I DATI
				if (host.isEmpty() || user.isEmpty() || pass.isEmpty()) {
					gui.label_eccezione(lblEccezione, "Tutti i campi sono obbligatori");
				} else {
					// EFFETTUA CONNESSIONE AL DB UTILIZZANDO I PARAMETRI INSERITI NEI CAMPI DI
					// TESTO
					qe = new QueryExecutor("jdbc:postgresql://" + host, user, pass);

					// VERIFICA SE BISOGNA CREARE LA STRUTTURA DEL DB E IN TAL CASO LEGGE DA FILE
					// CSV UTENTI E FRASI DA INSERIRE NEL DB
					/*
					 * if (!qe.verifyExistsDB(QueryPredefinite.checkDB)) { for (int i = 0; i <
					 * QueryPredefinite.createQueries.length; i++)
					 * qe.executeQuery_DDL(QueryPredefinite.createQueries[i]); //
					 * qe.readCSVUtenti(); // qe.readCSVFrasi(); } else { for (int i = 0; i <
					 * QueryPredefinite.dropQueries.length; i++)
					 * qe.executeQuery_DDL(QueryPredefinite.dropQueries[i]); for (int i = 0; i <
					 * QueryPredefinite.createQueries.length; i++)
					 * qe.executeQuery_DDL(QueryPredefinite.createQueries[i]); }
					 * 
					 */
					svuotaDB(qe);
					creaDB(qe);

					// VERIFICA LA PRESENZA DI AMMINISTRATORI GIA' REGISTRATI ALTRIMENTI RICHIEDE
					// REGISTRAZIONE
					if (qe.checkAdmin()) {
						gui.label_eccezione(lblEccezione, "Amministratore Master presente");
						// FINESTRA CON INFORMAZIONI SUL SERVER APPENA AVVIATO
						// AL SUO INTERNO VERRA' ANCHE FATTO PARTIRE IL THREAD PER GESTIRE I CLIENT
						// ServerInfoPanel serverInfoPanel = new ServerInfoPanel();
					} else {
						gui.label_eccezione(lblEccezione,
								"Non risulta registrato l'Amministratore Master.\nEffettuare registrazione!");
						SignInPanel signInPanel = new SignInPanel(qe, TIPO_MASTER);
					}
					jfrAccediAlDB.dispose();
				}
			}
		});
	}

	private void svuotaDB(QueryExecutor qe) {
		for (int i = 0; i < QueryPredefinite.dropQueries.length; i++)
			try {
				qe.executeQuery_DDL(QueryPredefinite.dropQueries[i]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	private void creaDB(QueryExecutor qe) {
		for (int i = 0; i < QueryPredefinite.createQueries.length; i++)
			try {
				qe.executeQuery_DDL(QueryPredefinite.createQueries[i]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}