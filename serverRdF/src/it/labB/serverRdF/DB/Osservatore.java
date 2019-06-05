package it.labB.serverRdF.DB;

public class Osservatore {
	private Integer idOsservatore;
	private Integer idUtente;
	private Integer idPartita;

	public Osservatore(Integer idOsservatore, Integer idUtente, Integer idPartita) {
		super();
		this.idOsservatore = idOsservatore;
		this.idUtente = idUtente;
		this.idPartita = idPartita;
	}

	public Integer getIdOsservatore() {
		return idOsservatore;
	}

	public void setIdOsservatore(Integer idOsservatore) {
		this.idOsservatore = idOsservatore;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public Integer getIdPartita() {
		return idPartita;
	}

	public void setIdPartita(Integer idPartita) {
		this.idPartita = idPartita;
	}

	@Override
	public String toString() {
		return "Osservatore [idOsservatore=" + idOsservatore + ", idUtente=" + idUtente + ", idPartita=" + idPartita
				+ "]";
	}

}