package it.polito.tdp.poweroutages.model;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;



public class Model {
	
	static final int OLDEST_YEAR = 2000;
	static final int NEWEST_YEAR = 2014;
	
	private PowerOutageDAO podao;
	private List<Rilevamento> rilevamenti;
	private int maxAnni;
	private int maxOre;
	private int maxClienti;
	private List<Rilevamento> sequenzaMigliore;


	
	
	
	public Model() {
		podao = new PowerOutageDAO();
		rilevamenti = new ArrayList<>();
		this.maxAnni = 0;
		this.maxOre = 0;
		this.maxClienti = 0;
		sequenzaMigliore = new ArrayList<>();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<Rilevamento> trovaSequenza(int maxAnni, int maxOre, Nerc nerc){
		rilevamenti = new ArrayList<>(podao.getRilevamentiNerc(nerc));
		this.maxAnni = maxAnni;
		this.maxOre = maxOre;
		this.maxClienti = 0;
		cercaSequenza(new ArrayList<Rilevamento>()); //sequenzaAttuale, oreAttuali
		return sequenzaMigliore;
		
	}

	private void cercaSequenza(ArrayList<Rilevamento> sequenzaAttuale) {
		
		verificaSoluzione(sequenzaAttuale); //verifico soluzioni sempre valide
		
		for(Rilevamento r : this.rilevamenti) {
			if(!sequenzaAttuale.contains(r)) {
				
				sequenzaAttuale.add(r); //aggiungo a prescindere perche la soluzione è valida
				//verifico dopo
				if(controllaAnni(sequenzaAttuale, r) && controllaOre(sequenzaAttuale, r)) { //se supera i controlli esploro
					cercaSequenza(sequenzaAttuale); //nuovo livello
				}
				sequenzaAttuale.remove(sequenzaAttuale.size() - 1); //backtracking
				}
			}
		
	}

	private void verificaSoluzione(ArrayList<Rilevamento> sequenzaAttuale) {
		int sommaAttuale = 0;
		for(Rilevamento r : sequenzaAttuale) {
			sommaAttuale+=r.getUtenti();
		}
		if(sommaAttuale > this.maxClienti) {
			sequenzaMigliore = new ArrayList<>(sequenzaAttuale);
			this.maxClienti = sommaAttuale;
		}
	}

	private boolean controllaOre(ArrayList<Rilevamento> sequenzaAttuale, Rilevamento rilevamento) {
		int oreAttuali = rilevamento.getDifferenza();
		for(Rilevamento r : sequenzaAttuale)
			oreAttuali+=r.getDifferenza();
		if(oreAttuali > maxOre)
			return false;
		return true;
	}

	private boolean controllaAnni(ArrayList<Rilevamento> sequenzaAttuale, Rilevamento rilevamento) {
		// eventi ordinati dal primo all'ultimo, prendo solo loro
		if(sequenzaAttuale.size() > 2) {
			int primo = sequenzaAttuale.get(0).getAnno();
			int ultimo = rilevamento.getAnno();
			if((ultimo - primo) > maxAnni)
				return false; //ho superato il limite
		}
		return true;
	}


}
