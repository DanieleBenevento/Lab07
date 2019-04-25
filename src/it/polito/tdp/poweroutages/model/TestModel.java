package it.polito.tdp.poweroutages.model;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		PowerOutageDAO  p=new PowerOutageDAO();
		//System.out.println(model.getNercList());
		//System.out.println(p.getPowerOutageList().get(6).getPersoneCoinvolte());
		//System.out.println(p.getPowerOutageList().get(6).getOraInizio());
	}
}
