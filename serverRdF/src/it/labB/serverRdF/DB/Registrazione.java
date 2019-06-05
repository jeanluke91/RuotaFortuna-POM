package it.labB.serverRdF.DB;

import java.util.Date;

public class Registrazione {
	private Integer idRegistrazione;
	private String statoRegistrazione;
	private Date dataRegistrazione;
	private String codiceVerifica;

	public Registrazione(Integer idRegistrazione, String statoRegistrazione, Date dataRegistrazione,
			String codiceVerifica) {
		super();
		this.idRegistrazione = idRegistrazione;
		this.statoRegistrazione = statoRegistrazione;
		this.dataRegistrazione = dataRegistrazione;
		this.codiceVerifica = codiceVerifica;
	}

	public Integer getIdRegistrazione() {
		return idRegistrazione;
	}

	public void setIdRegistrazione(Integer idRegistrazione) {
		this.idRegistrazione = idRegistrazione;
	}

	public String getStatoRegistrazione() {
		return statoRegistrazione;
	}

	public void setStatoRegistrazione(String statoRegistrazione) {
		this.statoRegistrazione = statoRegistrazione;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public String getCodiceVerifica() {
		return codiceVerifica;
	}

	public void setCodiceVerifica(String codiceVerifica) {
		this.codiceVerifica = codiceVerifica;
	}

	@Override
	public String toString() {
		return "Registrazione [idRegistrazione=" + idRegistrazione + ", statoRegistrazione=" + statoRegistrazione
				+ ", codiceVerifica=" + codiceVerifica + "]";
	}

}
