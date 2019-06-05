package it.labB.serverRdF.DB;


public class QueryPredefinite {
	public final static String[] dropQueries = {
			"DROP TABLE IF EXISTS Effettua",
			"DROP TABLE IF EXISTS Mossa",
			"DROP TABLE IF EXISTS Manche",
			"DROP TABLE IF EXISTS Frase",
			"DROP TABLE IF EXISTS Concorrente",
			"DROP TABLE IF EXISTS Osservatore",
			"DROP TABLE IF EXISTS Partita",
			"DROP TABLE IF EXISTS Utente",
			"DROP TABLE IF EXISTS Registrazione"
	};
	
	public final static String checkDB = 
			"SELECT EXISTS (" + 
			"	SELECT 1" + 
			"	FROM   pg_tables" + 
			"	WHERE schemaname = 'public'" + 
			"   );";
	
	public final static String[] createQueries = {
			"CREATE TABLE Registrazione(" + 
			"	ID_Registrazione SERIAL PRIMARY KEY," + 
			"	Stato_Registrazione CHAR(2) DEFAULT 'KO'," + 
			"	Data_Registrazione timestamp without time zone NOT NULL," + 
			"	Codice_Verifica VARCHAR(6) NOT NULL" + 
			");", 
		
			"CREATE TABLE Utente(" + 
			"    ID_Utente SERIAL PRIMARY KEY," + 
			"    Nome VARCHAR(15) NOT NULL," + 
			"    Cognome VARCHAR(25) NOT NULL," + 
			"    Email VARCHAR(40) NOT NULL UNIQUE," + 
			"    Nickname VARCHAR(20) NOT NULL UNIQUE," + 
			"    Password VARCHAR(15)NOT NULL," + 
			"    Tipo CHAR(1) NOT NULL," + 
			"	ID_Registrazione INTEGER NOT NULL REFERENCES Registrazione(ID_Registrazione)" + 
			");",
			
			"CREATE TABLE Partita(" + 
			"    ID_Partita SERIAL PRIMARY KEY," + 
			"    Nome VARCHAR(25) NOT NULL," + 
			"    Data DATE NOT NULL," + 
			"    Ora timestamp without time zone NOT NULL" + 
			");",
			
			"CREATE TABLE Osservatore(" + 
			"    ID_Osservatore SERIAL PRIMARY KEY," + 
			"    ID_Utente INTEGER NOT NULL REFERENCES Utente(ID_Utente)," + 
			"    ID_Partita INTEGER NOT NULL REFERENCES Partita(ID_Partita)" + 
			");",
			
			"CREATE TABLE Concorrente(" + 
			"    ID_Concorrente SERIAL PRIMARY KEY," + 
			"    ID_Utente INTEGER NOT NULL REFERENCES Utente(ID_Utente)," + 
			"    ID_Partita INTEGER NOT NULL REFERENCES Partita(ID_Partita)," + 
			"    Punteggio NUMERIC(7) NOT NULL" + 
			");",
			
			"CREATE TABLE Frase(" + 
			"    ID_Frase SERIAL PRIMARY KEY," + 
			"    ID_Utente INTEGER NOT NULL REFERENCES Utente(ID_Utente)," + 
			"    Contenuto VARCHAR(60) NOT NULL UNIQUE," + 
			"    Tema VARCHAR(60) NOT NULL" + 
			");",
			
			"CREATE TABLE Manche(" + 
			"    ID_Manche SERIAL PRIMARY KEY," + 
			"    ID_Partita INTEGER NOT NULL REFERENCES Partita(ID_Partita)," + 
			"    ID_Frase INTEGER NOT NULL REFERENCES Frase(ID_Frase)," + 
			"	 ID_Vincitore INTEGER REFERENCES Concorrente(ID_Concorrente)" + 
			");",
			
			"CREATE TABLE Mossa(" + 
			"    ID_Mossa SERIAL PRIMARY KEY," + 
			"    Tipo VARCHAR(12) NOT NULL," + 
			"    Punteggio NUMERIC(7) NOT NULL" + 
			");",
			
			"CREATE TABLE Effettua(" + 
			"    ID_Mossa INTEGER REFERENCES Mossa(ID_Mossa)," + 
			"    ID_Manche INTEGER REFERENCES Manche(ID_Manche)," + 
			"    ID_Concorrente INTEGER REFERENCES Concorrente(ID_Concorrente)," + 
			"    PRIMARY KEY (ID_Mossa, ID_Manche, ID_Concorrente)" + 
			");"
	};
}
