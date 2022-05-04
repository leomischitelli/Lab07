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


	
	
	
	public Model() {
		podao = new PowerOutageDAO();
		rilevamenti = new ArrayList<>();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<Rilevamento> trovaSequenza(int maxAnni, int maxOre, Nerc nerc){
		rilevamenti = new ArrayList<>(podao.getRilevamentiNerc(nerc));
		
		
		
		
		return null;
		
	}


}
