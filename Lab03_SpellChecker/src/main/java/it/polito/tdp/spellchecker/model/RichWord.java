package it.polito.tdp.spellchecker.model;

public class RichWord {

	private String parola;
	private boolean corretta;
	
	public RichWord(String parola) {
		this.parola = parola;
		this.corretta = false;
	}
	
	public void isCorrect() {
		this.corretta = true;
	}

	public String getParola() {
		return parola;
	}

	public boolean isCorretta() {
		return corretta;
	}
	
	
}
