package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {
	//VARIABILI
	private final int NMAX = 100;
	private final int TMAX = 7;  //2^7=128 quindi a forza di dividere dovrei trovarlo (MAX TENTATIVI)
	
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	//COSTRUTTORE
	public NumeroModel() {
		inGioco = false;
	}
	
	
	//MODELLI - METODI
	
	/***
	 * 1. Nuova Partita
	 */
	public void newGame() {
		//LOGICA DEL GIOCO presa da controller
    	this.segreto = (int)(Math.random()*NMAX)+1; //random mi da un numero compreso tra 0 e 1. Troncato ad int
    	this.tentativiFatti=0;
    	this.inGioco=true;
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
		
		this.tentativiFatti++;
		if(this.tentativiFatti==this.TMAX) {
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
			return true;
		}
	}


	//GETTER per trasferire informazioni al controller
	public int getSegreto() {
		return segreto;
	}


	public int getTentativiFatti() {
		return tentativiFatti;
	}


	public boolean isInGioco() {
		return inGioco;
	}


	public int getTMAX() {
		return TMAX;
	}
	
	
	
	
	

}
