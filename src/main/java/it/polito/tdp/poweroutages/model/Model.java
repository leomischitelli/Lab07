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
	private List<PowerOutage> listPO;
	private int maxMinuti;
	private int maxDemandLoss;
	private List<PowerOutage> listaMigliore;
	
	
	
	public Model() {
		podao = new PowerOutageDAO();
		listPO = new ArrayList<PowerOutage>();
		maxMinuti = 0;
		maxDemandLoss = 0;
		listaMigliore = new ArrayList<PowerOutage>();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<PowerOutage> trovaSequenza(int maxAnni, int maxOre, Nerc nerc){

		maxMinuti = maxOre * 60;
		maxDemandLoss = 0;
		listaMigliore.clear();
		int i = 0;
		while( (OLDEST_YEAR + i + maxAnni - 1) <= NEWEST_YEAR ) {
			listPO.clear();
			listPO = podao.getPowerOutagesNercGap((OLDEST_YEAR + i), (OLDEST_YEAR + i + maxAnni - 1), nerc);
			Set<PowerOutage> setAttuale = new HashSet<PowerOutage>();
			sequenza_ricorsiva(setAttuale, 0); //set, oreAttuali, maxMinuti
			i++;
		}
		
		
//		listPO.clear();
//		listPO = podao.getPowerOutagesNerc(nerc);
//		Set<PowerOutage> setAttuale = new HashSet<PowerOutage>();
//		sequenza_ricorsiva(setAttuale, 0, maxAnni, maxOre, 0, 0); //setAttuale, oreAttuali, maxAnni, maxOre, annoMin, annoMax
		
		
		
		return listaMigliore;
		
	}

	private boolean sequenza_ricorsiva(Set<PowerOutage> setAttuale, int minutiAttuali) {
		if(minutiAttuali == maxMinuti) {
			
			return calcolaDemandLoss(setAttuale);
		}
		
		for(PowerOutage p : listPO) {
			//sono gia sicuro di essere nel gap corretto di anni
			if(!setAttuale.contains(p)) {
				//1.calcolo la durata in minuti
				int ore = Math.abs(p.getDateEventFinished().getHour() - p.getDateEventBegan().getHour());
				int minuti = Math.abs(p.getDateEventFinished().getMinute() - p.getDateEventBegan().getMinute());
				int durata = ore * 60 + minuti;
				if((minutiAttuali + durata) <= maxMinuti) { //sono nel limite di ore
					setAttuale.add(p);
					minutiAttuali+=durata;
					sequenza_ricorsiva(setAttuale, minutiAttuali); //ricorsione
					setAttuale.remove(p); //backtracking
				}
			}	
			
		}
		
		//se ho finito il set vuol dire che non raggiungero il max di ore con questo range, calcolo e proseguo
		
		return calcolaDemandLoss(setAttuale);
		
		
		
	}

	
	private boolean calcolaDemandLoss(Set<PowerOutage> setAttuale) {
		int demand = 0;
		for(PowerOutage p : setAttuale) {
			demand+=p.getDemandLoss();
		}
		if(demand > maxDemandLoss) {
			maxDemandLoss = demand;
			listaMigliore.clear();
			listaMigliore.addAll(setAttuale);
			return true;
		}
		else
			return false;
	}	

	private void sequenza_ricorsiva2(Set<PowerOutage> setAttuale, int oreAttuali, int maxAnni, int maxOre, int annoMin, int annoMax) {
		
		if(oreAttuali == maxOre) {
			//calcola demandLoss
			return;
		}
		
		for(PowerOutage p : listPO) {
			
			
			
			
		}
		
		//calcola demandLoss
		return;
		
	}
}
