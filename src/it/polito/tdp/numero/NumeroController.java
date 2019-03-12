package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {
	//VARIABILI
	private final int NMAX = 100;
	private final int TMAX = 7;  //2^7=128 quindi a forza di dividere dovrei trovarlo (MAX TENTATIVI)
	
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	

	//CONTROLLER
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox boxControlloPartita;

    @FXML
    private TextField txtRimasti;
    //numero tentativi rimasti da provare

    @FXML
    private HBox boxControlloTentativo;

    @FXML
    private TextField txtTentativo;
    //tentativo inserito da utente

    @FXML
    private TextArea txtMessaggi;

    
    
    //FUNZIONI DI EVENTI
    @FXML
    void handleNuovaPartita(ActionEvent event) {
    	//Gestisce l'inizio di una nuova partita
    	
    	//LOGICA DEL GIOCO
    	this.segreto = (int)(Math.random()*NMAX)+1; //random mi da un numero compreso tra 0 e 1. Troncato ad int
    	this.tentativiFatti=0;
    	this.inGioco=true;
    	
    	
    	//GESTIONE INTERFACCIA
    	boxControlloPartita.setDisable(true); //disabilito possibilità iniziare nuova partita mentre gioco
    	this.boxControlloTentativo.setDisable(false); //abilito modifica con false
    	
    	this.txtMessaggi.clear();//PULISCO DA MESSAGGI PRECEDENTI
    	this.txtRimasti.setText(Integer.toString(TMAX));
    	
    	
    	
    }

    @FXML
    void handleProvaTentativo(ActionEvent event) {
    	//leggi il valore del tentativo
    	String ts = txtTentativo.getText();
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	}catch (NumberFormatException e){
    		//Stringa inserita non è un numero valido
    		
    		txtMessaggi.appendText("Non hai inserito un numero\n");
    		return;
    	}
    	
    	this.tentativiFatti++;
    	
    	//controlla se ha indovinato
    	//-> fine partita
    	if(tentativo==segreto) {
    		txtMessaggi.appendText("Complimenti hai indovinato il numero segreto "+this.segreto+" in "+this.tentativiFatti+"\n");
    		
    		this.boxControlloPartita.setDisable(false);
    		this.boxControlloTentativo.setDisable(true);
    		this.inGioco=false;
    		return;
    	}
    	
    	
    	//Verifica se hai esaurito tentativi
    	//-> fine partita
    	if(this.tentativiFatti==this.TMAX) {
    		txtMessaggi.appendText("HAI PERSO! Il numero segreto era "+this.segreto+"\n");
    		this.txtRimasti.setText(Integer.toString(TMAX-this.tentativiFatti));
    		
    		this.boxControlloPartita.setDisable(false);
    		this.boxControlloTentativo.setDisable(true);
    		this.inGioco=false;
    		return;
    	}
    	
    	
    	//Informa se troppo alto/troppo basso
    	//Stampo messaggio
    	if(tentativo<segreto) {
    		txtMessaggi.appendText("Tentativo troppo Basso!\n");
    	}
    	else {
    		txtMessaggi.appendText("Tentativo troppo alto\n");
    	}
    	
    	
    	//aggiorno interfaccia con num tentativi
    	this.txtRimasti.setText(Integer.toString(TMAX-this.tentativiFatti));
    	
    	

    }

    @FXML
    void initialize() {
        assert boxControlloPartita != null : "fx:id=\"boxControlloPartita\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Numero.fxml'.";
        assert boxControlloTentativo != null : "fx:id=\"boxControlloTentativo\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'Numero.fxml'.";

    }
}
