package it.polito.tdp.poweroutages.model;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List<PowerOutage> disponibili;
	int best;
	List<PowerOutage> soluzione;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<PowerOutage> getSoluzione(){
		return soluzione;
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutage> getPowerOutagesList() {
		return podao.getPowerOutageList();
	}
	
	public List<PowerOutage> getPowerOutagesListByN(Nerc n) {
		return podao.getPowerOutageListByNerc(n);
	}
	
	public void generaRicorsione(Nerc n,List<PowerOutage> parziale,int year,int hour) {
		soluzione=new ArrayList<PowerOutage>();
		disponibili=new ArrayList<PowerOutage>();
		disponibili.addAll(this.getPowerOutagesListByN(n));
		parziale.clear();
		best=Integer.MIN_VALUE;
		this.Ricorsione(0,parziale,year,hour);
	}
	
	public void Ricorsione(int level,List<PowerOutage> parziale,int year, int hour) {
		
		if(parziale.isEmpty()==false && this.getTotalAffectedPeople(parziale)>best) {
		
			best=this.getTotalAffectedPeople(parziale);
			
			soluzione.clear();
			
			soluzione.addAll(parziale);
		}
		
		for(int i=0;i<disponibili.size();i++) {
		
			parziale.add(disponibili.get(i));
			
		if(this.getMaxYearsRange(parziale)<=year && this.getTotalOutageHour(parziale)<=hour 
				&& this.controllaParziale(parziale)==true ) {	
			
			this.Ricorsione(level+1, parziale, year, hour);	
		
		}
		parziale.remove(level);
			}
			
		
		
		return;
	}
	
	public boolean controllaParziale(List<PowerOutage> parziale) {
		for(int i=0;i<parziale.size();i++) {
			for(int j=0;j<parziale.size() && j!=i;j++) {
				if(parziale.get(j).getId()==parziale.get(i).getId()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int getMaxYearsRange(List<PowerOutage> parziale) {
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		for(PowerOutage p: parziale) {
			if(p.getDataInizio().getYear()<min) {
				min=p.getDataInizio().getYear();
			}
			if(p.getDataFine().getYear()>max) {
				max=p.getDataFine().getYear();
			}
		}
		return (max-min);
	}
	
	public int getTotalOutageHour(List<PowerOutage> parziale) {
		long sommaTempo=0;
    	for(int j=0;j<parziale.size();j++) {
    		sommaTempo= sommaTempo+(parziale.get(j).getDataInizio().until(parziale.get(j).getDataFine(),ChronoUnit.HOURS));
    	}
		return (int)sommaTempo;
	}
	
	public int getTotalAffectedPeople(List<PowerOutage> parziale) {
		int sommaPersone=0;
		for(int j=0;j<parziale.size();j++) {
    		sommaPersone= sommaPersone+parziale.get(j).getPersoneCoinvolte();
    	}
		return sommaPersone;
	}

}
