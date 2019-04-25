package it.polito.tdp.poweroutages;


import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Nerc> btnChoice;

    @FXML
    private TextField txtMaxYears;

    @FXML
    private TextField txtMaxHours;

    @FXML
    private Button btnWorstCaseAnalysis;

    @FXML
    private TextArea txtResult;
    
    private Model model= new Model();

    @FXML
    void doSearch(ActionEvent event) {
    	txtResult.clear();
   
    	List<PowerOutage> l=new ArrayList();
    	model.generaRicorsione(btnChoice.getValue(),l,Integer.parseInt(txtMaxYears.getText()),
    			Integer.parseInt(txtMaxHours.getText()));
    	int sommaPersone=0;
    	long sommaTempo=0;
    	for(int j=0;j<model.getSoluzione().size();j++) {
    		sommaPersone+=model.getSoluzione().get(j).getPersoneCoinvolte();
    		sommaTempo= sommaTempo+(model.getSoluzione().get(j).getDataInizio().until(model.getSoluzione().get(j).getDataFine(),ChronoUnit.HOURS));
    	}
    	txtResult.appendText("Tot people affected: "+sommaPersone+"\n");
    	txtResult.appendText("Tot hours of outage: "+sommaTempo+"\n");
    	for(int i=0;i<model.getSoluzione().size();i++) {
    		txtResult.appendText(model.getSoluzione().get(i).getDataInizio().getYear()+" "+model.getSoluzione().get(i).getDataInizio()+" "+model.getSoluzione().get(i).getDataFine()+
    				" "+model.getSoluzione().get(i).getPersoneCoinvolte()+"\n");
    		
    	}

    }

    @FXML
    void initialize() {
        assert btnChoice != null : "fx:id=\"btnChoice\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtMaxYears != null : "fx:id=\"txtMaxYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtMaxHours != null : "fx:id=\"txtMaxHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnWorstCaseAnalysis != null : "fx:id=\"btnWorstCaseAnalysis\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    	this.btnChoice.getItems().addAll(model.getNercList());
    	
    }
}

