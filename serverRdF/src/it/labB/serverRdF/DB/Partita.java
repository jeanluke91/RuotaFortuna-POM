package it.labB.serverRdF.DB;

import java.util.Date;

public class Partita {
	private Integer idPartita;
	private String nome;
	private Date dataOra;

	public Partita(Integer idPartita, String nome, Date dataOra) {
		super();
		this.idPartita = idPartita;
		this.nome = nome;
		this.dataOra = dataOra;
	}

	public Integer getIdPartita() {
		return idPartita;
	}

	public void setIdPartita(Integer idPartita) {
		this.idPartita = idPartita;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataOra() {
		return dataOra;
	}

	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}

	@Override
	public String toString() {
		return "Partita [idPartita=" + idPartita + ", nome=" + nome + ", dataOra=" + dataOra + "]";
	}

}