package it.labB.serverRdF.DB;

public class Frase {
	private Integer idFrase;
	private Integer idUtente;
	private String contenuto;
	private String tema;

	public Frase(Integer idFrase, Integer idUtente, String contenuto, String tema) {
		super();
		this.idFrase = idFrase;
		this.idUtente = idUtente;
		this.contenuto = contenuto;
		this.tema = tema;
	}

	public Integer getIdFrase() {
		return idFrase;
	}

	public void setIdFrase(Integer idFrase) {
		this.idFrase = idFrase;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	@Override
	public String toString() {
		return "Frase [idFrase=" + idFrase + ", idUtente=" + idUtente + ", contenuto=" + contenuto + ", tema=" + tema
				+ "]";
	}

}