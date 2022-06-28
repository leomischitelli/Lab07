package it.polito.tdp.poweroutages.model;

import java.sql.Date;

public class Rilevamento {
	private int utenti;
	private Date dataInizio;
	private Date dataFine;
	private int differenza;
	private int anno;
	
	public Rilevamento(int utenti, Date dataInizio, Date dataFine, int differenza) {
		super();
		this.utenti = utenti;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.differenza=differenza;
		this.anno = this.dataInizio.getYear();
	}

	public int getUtenti() {
		return utenti;
	}

	public void setUtenti(int utenti) {
		this.utenti = utenti;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	

	public int getDifferenza() {
		return differenza;
	}

	public void setDifferenza(int diff) {
		this.differenza = diff;
	}
	

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	@Override
	public String toString() {
		return Integer.toString(dataInizio.toLocalDate().getYear())+" "+dataInizio.toLocalDate().toString()+" "+dataFine.toLocalDate().toString()+ " "+differenza+" "+utenti;
	}
	
	
	
	
}
