package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.numero.model.NumeroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {
	//VARIABILI
	private NumeroModel model;
	
	
	//MODELLO
	public NumeroModel getModel() {
		return model;
	}

	public void setModel(NumeroModel model) {
		this.model = model;
	}

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
    	
    	//GESTIONE INTERFACCIA - PULIZIA PRECEDENTE
    	boxControlloPartita.setDisable(true); //disabilito possibilità iniziare nuova partita mentre gioco
    	this.boxControlloTentativo.setDisable(false); //abilito modifica con false
    	
    	this.txtMessaggi.clear();//PULISCO DA MESSAGGI PRECEDENTI
    	this.txtRimasti.setText(Integer.toString(this.model.getTMAX()));
    	
    	//INIZIO DI UNA NUOVA PARTITA
    	model.newGame();
    	
    }

    @FXML
    void handleProvaTentativo(ActionEvent event) {
    	
    	//CONTROLLO CHE INPUT SIA DI TIPO GIUSTO = IN CONTROLLER!!! (comtrollo meccanico)
    	String ts = txtTentativo.getText();
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	}catch (NumberFormatException e){
    		//Stringa inserita non è un numero valido
    		txtMessaggi.appendText("Non hai inserito un numero\n");
    		return;
    	}
    	
    	//Controllo se range è giusto (fatto in Model e qui solo richiamato)
    	if(!this.model.tentativoValido(tentativo)) {
    		txtMessaggi.appendText("Range Non Valido");
    		return;
    	}
    	
    	
    	//SVOLGO LA LOGICA IN MODELLO E LEGGO I RISULTATI
    	int risultato = this.model.tentativo(tentativo);
    	if(risultato==0) {
    		txtMessaggi.appendText("Complimenti hai indovinato il numero segreto "+this.model.getSegreto()+" in "+this.model.getTentativiFatti()+"\n");
    		this.boxControlloPartita.setDisable(false);
    		this.boxControlloTentativo.setDisable(true);
    	}else if(risultato<0) {
    		txtMessaggi.appendText("Tentativo troppo Basso!\n");
    	}
    	else {
    		txtMessaggi.appendText("Tentativo troppo alto\n");
    	}
    
    	
    	//aggiorno interfaccia con num tentativi
    	this.txtRimasti.setText(Integer.toString(this.model.getTMAX()-this.model.getTentativiFatti()));
    	
    	if(!model.isInGioco()) {
    		if(risultato!=0) {
    			txtMessaggi.appendText("Hai perso. Tentativi Esauriti\n");
    			txtMessaggi.appendText("Il numero segreto era: "+this.model.getSegreto());
    			this.boxControlloPartita.setDisable(false);
        		this.boxControlloTentativo.setDisable(true);
    		}
    	}
    	
    	

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
