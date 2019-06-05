package it.labB.serverRdF.DB;

public class Mossa {
	private Integer idMossa;
	private String tipo;
	private int punteggio;

	public Mossa(Integer idMossa, String tipo, int punteggio) {
		super();
		this.idMossa = idMossa;
		this.tipo = tipo;
		this.punteggio = punteggio;
	}

	public Integer getIdMossa() {
		return idMossa;
	}

	public void setIdMossa(Integer idMossa) {
		this.idMossa = idMossa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	@Override
	public String toString() {
		return "Mossa [idMossa=" + idMossa + ", tipo=" + tipo + ", punteggio=" + punteggio + "]";
	}

}