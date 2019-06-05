package it.labB.serverRdF.DB;

public class Utente {
	private Integer idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String nickname;
	private String password;
	private String tipo;
	private Integer idRegistrazione;

	public Utente(Integer idUtente, String nome, String cognome, String email, String nickname, String password,
			String tipo, Integer idRegistrazione) {
		super();
		this.idUtente = idUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.tipo = tipo;
		this.idRegistrazione = idRegistrazione;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getIdRegistrazione() {
		return idRegistrazione;
	}

	public void setIdRegistrazione(Integer idRegistrazione) {
		this.idRegistrazione = idRegistrazione;
	}

	@Override
	public String toString() {
		return "Utente [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
				+ ", nickname=" + nickname + ", password=" + password + ", tipo=" + tipo + ", idRegistrazione="
				+ idRegistrazione + "]";
	}

}