package it.labB.serverRdF.DB;

public class Concorrente {
	private Integer idConcorrente;
	private Integer idUtente;
	private Integer idPartita;
	private Integer punteggio;

	public Concorrente(Integer idConcorrente, Integer idUtente, Integer idPartita, Integer punteggio) {
		super();
		this.idConcorrente = idConcorrente;
		this.idUtente = idUtente;
		this.idPartita = idPartita;
		this.punteggio = punteggio;
	}

	public Integer getIdConcorrente() {
		return idConcorrente;
	}

	public void setIdConcorrente(Integer idConcorrente) {
		this.idConcorrente = idConcorrente;
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

	public Integer getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(Integer punteggio) {
		this.punteggio = punteggio;
	}

	@Override
	public String toString() {
		return "Concorrente [idConcorrente=" + idConcorrente + ", idUtente=" + idUtente + ", idPartita=" + idPartita
				+ ", punteggio=" + punteggio + "]";
	}
}
