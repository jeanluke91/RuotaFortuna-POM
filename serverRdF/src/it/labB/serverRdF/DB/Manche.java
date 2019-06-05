package it.labB.serverRdF.DB;

public class Manche {
	private Integer idManche;
	private Integer idPartita;
	private Integer idFrase;

	public Manche(Integer idManche, Integer idPartita, Integer idFrase) {
		super();
		this.idManche = idManche;
		this.idPartita = idPartita;
		this.idFrase = idFrase;
	}

	public Integer getIdManche() {
		return idManche;
	}

	public void setIdManche(Integer idManche) {
		this.idManche = idManche;
	}

	public Integer getIdPartita() {
		return idPartita;
	}

	public void setIdPartita(Integer idPartita) {
		this.idPartita = idPartita;
	}

	public Integer getIdFrase() {
		return idFrase;
	}

	public void setIdFrase(Integer idFrase) {
		this.idFrase = idFrase;
	}

	@Override
	public String toString() {
		return "Manche [idManche=" + idManche + ", idPartita=" + idPartita + ", idFrase=" + idFrase + "]";
	}

}