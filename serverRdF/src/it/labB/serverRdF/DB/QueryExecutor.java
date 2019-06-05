package it.labB.serverRdF.DB;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.opencsv.CSVReader;

public class QueryExecutor {

	private Connection conn;
	private static int x;
	

	public QueryExecutor(String url, String usr, String pwd) {
		try {
			conn = openConnection(url, usr, pwd);
			JOptionPane.showMessageDialog(null, "Connesso al db " + conn.getCatalog(), null, JOptionPane.PLAIN_MESSAGE);
			x = 1;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Qualcosa è andato storto", null, JOptionPane.PLAIN_MESSAGE);
			x = 0;
		}

	}

	private Connection openConnection(String host, String username, String password) throws SQLException {
		Properties prop = new Properties();
		prop.setProperty("user", username);
		prop.setProperty("password", password);
		Connection c = DriverManager.getConnection(host, prop);
		return c;
	}

	public boolean checkAdmin() {
		String qry = "SELECT COUNT(*) FROM UTENTE WHERE TIPO = 'M';";
		int count = 0;
		Statement stmt = null;
		try {
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		if (count > 0)
			return true;
		return false;
	}

	public boolean verifyExistsDB(String qry) {
		Statement stmt = null;
		boolean exists = false;
		try {
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			rs.next();
			exists = rs.getBoolean(1);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return exists;
	}

	public void executeQuery_DDL(String sqlQuery) throws SQLException {
		Statement stmt = null;
		stmt = this.conn.createStatement();

		try {
			stmt.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			System.err.println("Message: " + e.getMessage());
			System.err.println("Error: " + e.getErrorCode());
			System.err.println("SQL State: " + e.getSQLState());
		}

		stmt.close();
	}

/*	public void readCSVUtenti() {
		try {
			CSVReader reader = new CSVReader(new FileReader("utenti.csv"));
			String[] nextLine = new String[7];
			List<Utente> utenti = new ArrayList<Utente>();
			while ((nextLine = reader.readNext()) != null) {
				Integer idUtente = Integer.parseInt(nextLine[0]);
				String nome = nextLine[1];
				String cognome = nextLine[2];
				String email = nextLine[3];
				String nickname = nextLine[4];
				String password = nextLine[5];
				String tipo = nextLine[6];
				Utente utente = new Utente(idUtente, nome, cognome, email, nickname, password, tipo, null);
				utenti.add(utente);
			}
			populateUtente(utenti);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readCSVFrasi() {
		try {
			CSVReader reader = new CSVReader(new FileReader("frasi.csv"));
			String[] nextLine = new String[4];
			List<Frase> frasi = new ArrayList<Frase>();
			while ((nextLine = reader.readNext()) != null) {
				Frase frase = new Frase(Integer.parseInt(nextLine[0]), Integer.parseInt(nextLine[1]), nextLine[2],
						nextLine[3]);
				frasi.add(frase);
			}
			populateFrase(frasi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/
	private void populateUtente(List<Utente> users) {
		PreparedStatement stmt = null;
		for (Utente u : users) {
			try {
				stmt = conn.prepareStatement(
						"INSERT INTO utente(id_utente, nome, cognome, email, nickname, password, tipo) "
								+ "VALUES (?,?,?,?,?,?,?)");
				stmt.setInt(1, u.getIdUtente());
				stmt.setString(2, u.getNome());
				stmt.setString(3, u.getCognome());
				stmt.setString(4, u.getEmail());
				stmt.setString(5, u.getNickname());
				stmt.setString(6, u.getPassword());
				stmt.setString(7, u.getTipo());

				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void populateFrase(List<Frase> frasi) {
		PreparedStatement stmt = null;
		for (Frase f : frasi) {
			try {
				stmt = conn.prepareStatement(
						"INSERT INTO frase(id_frase, id_utente, contenuto, tema)" + "VALUES (?,?,?,?)");
				stmt.setInt(1, f.getIdFrase());
				stmt.setInt(2, f.getIdUtente());
				stmt.setString(3, f.getContenuto());
				stmt.setString(4, f.getTema());
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean checkUserMail(String nickname, String email) {
		String qry = "SELECT EXISTS(SELECT * FROM UTENTE WHERE NICKNAME = '" + nickname + "' OR EMAIL = '" + email
				+ "');";
		Statement stmt = null;
		boolean exists = false;
		try {
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			rs.next();
			exists = rs.getBoolean(1);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return exists;
	}

	public void registerInfo(Registrazione registrazione) throws SQLException {
		PreparedStatement stmt = null;
			stmt = conn.prepareStatement(
					"INSERT INTO registrazione(id_registrazione, stato_registrazione, data_registrazione, codice_verifica) "
							+ "VALUES (?,?,?,?)");
			stmt.setInt(1, registrazione.getIdRegistrazione());
			stmt.setString(2, registrazione.getStatoRegistrazione());
			stmt.setTimestamp(3, new java.sql.Timestamp(registrazione.getDataRegistrazione().getTime()));
			stmt.setString(4, registrazione.getCodiceVerifica());
			stmt.executeUpdate();
	}

	public void registerUser(Utente utente) throws SQLException {
		PreparedStatement stmt = null;
			stmt = conn.prepareStatement(
					"INSERT INTO utente(nome, cognome, email, nickname, password, tipo, id_registrazione) "
							+ "VALUES (?,?,?,?,?,?,?)");
			stmt.setString(1, utente.getNome());
			stmt.setString(2, utente.getCognome());
			stmt.setString(3, utente.getEmail());
			stmt.setString(4, utente.getNickname());
			stmt.setString(5, utente.getPassword());
			stmt.setString(6, utente.getTipo());
			stmt.setInt(7, 1);
			stmt.executeUpdate();
	}
}
