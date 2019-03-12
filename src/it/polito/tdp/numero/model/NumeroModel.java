package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.*;

public class NumeroModel {
	//LISTA PER NON INSERIRE DUE VOLTE STESSO NUMERO
	private List<Integer>tentativi;
	
	
	//VARIABILI
	private final int NMAX = 100;
	private final int TMAX = 8;  //2^7=128 quindi a forza di dividere dovrei trovarlo (MAX TENTATIVI)
	private int segreto;
	private boolean inGioco;
	
	
	//private int tentativiFatti;
	private IntegerProperty tentativiFatti; //PROPERTY PER LEGARE VARIAZIONE A VISTA DIRETTAMENTE
	
	
	
	
	//COSTRUTTORE
	public NumeroModel() {
		inGioco = false;
		
		tentativiFatti = new SimpleIntegerProperty(); //Integer Property è un classe astratta e quindi bisogna creare un oggetto con la Simple
	
		tentativi = new LinkedList<Integer>();
	}
	
	
	//MODELLI - METODI
	
	/***
	 * 1. Nuova Partita
	 */
	public void newGame() {
		//LOGICA DEL GIOCO presa da controller
    	this.segreto = (int)(Math.random()*NMAX)+1; //random mi da un numero compreso tra 0 e 1. Troncato ad int
    	this.tentativiFatti.set(0);
    	this.inGioco=true;
    	this.tentativi.clear(); //pulisco la lista
	}
	
	
	/***
	 * 2.Tentativo in partita
	 * @param tentativo
	 * @return 1 se tentativo troppo alto, 0 se giusto, -1 se troppo basso
	 */
	public int tentativo(int tentativo) {
		
		//controllo se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è terminata"); //ECCEZIONE PER UNO STATO ILLEGALE
		}
		
		//controllo se l'input è nel range (logico)
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException(String.format("Devi inserire un numero "
					+ "tra %d e %d", 1,NMAX));
		}
		
		
		//GESTIONE TENTATIVO
		
		this.tentativiFatti.set(this.tentativiFatti.get()+1);
		this.tentativi.add(tentativo);
		
		
		if(this.tentativiFatti.get()==this.TMAX) {
			this.inGioco=false;
		}
		
		if(tentativo==this.segreto) {
			this.inGioco=false;
			return 0;
		}
		
		if(tentativo>this.segreto) {
			return 1;
		}
		
		return -1;
		
			
	}
	
	/***
	 * controllo input corretto dal punto di vista logico e valido sia per model che richiamabile da controller
	 */
	public boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo>NMAX) {
			return false;
		}else {
			if(this.tentativi.contains(tentativo)) {
				return false;
			}
			else
				return true;
		}
		
		
	}


	//GETTER per trasferire informazioni al controller
	public int getSegreto() {
		return segreto;
	}

	public boolean isInGioco() {
		return inGioco;
	}


	public int getTMAX() {
		return TMAX;
	}


	public int getNMAX() {
		return NMAX;
	}

	
	//GETTER&SETTER PER PROPERTY - SOURCE(JAVAFX)?
	public final IntegerProperty tentativiFattiProperty() {
		return this.tentativiFatti;
	}
	

	public final int getTentativiFatti() {
		return this.tentativiFattiProperty().get();
	}
	

	public final void setTentativiFatti(final int tentativiFatti) {
		this.tentativiFattiProperty().set(tentativiFatti);
	}
	
	
	
	
	
	
	

}
