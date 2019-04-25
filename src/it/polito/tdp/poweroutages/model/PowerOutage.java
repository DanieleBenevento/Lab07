package it.polito.tdp.poweroutages.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PowerOutage {
	
	private int id;
	private LocalDateTime dataInizio;
	private LocalDateTime dataFine;
	private int personeCoinvolte;
	private Nerc  nerc;
	private String tipoEvento;
	private String responsabile;
	private String area;
	private String tag;
	
	public PowerOutage(int id, LocalDateTime dataInizio, LocalDateTime dataFine,int personeCoinvolte,
			Nerc nerc, String tipoEvento, String responsabile, String area,String tag) {
		
		this.id = id;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.personeCoinvolte = personeCoinvolte;
		this.nerc = nerc;
		this.tipoEvento = tipoEvento;
		this.responsabile = responsabile;
		this.area = area;
		this.tag=tag;
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public int getPersoneCoinvolte() {
		return personeCoinvolte;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public String getResponsabile() {
		return responsabile;
	}

	public String getArea() {
		return area;
	}
	
	public String getTag() {
		return tag;
	}

}
