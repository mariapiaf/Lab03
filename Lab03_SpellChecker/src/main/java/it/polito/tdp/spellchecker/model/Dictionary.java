package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {
	
	private Set<String> dizionario;
	private int contErrori;
	
	public Dictionary() {
		dizionario = new HashSet<>();
		contErrori = 0;
	}
	
	public void loadDictionary(String language) {
		if(language.equals("English")) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine()) != null) {
					dizionario.add(word);
				}
				br.close();
				
			} catch(IOException e) {
				System.out.println("Errore nella lettura del file!");
			}
		}
		else if(language.equals("Italian")) {
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine()) != null) {
					dizionario.add(word);
				}
				br.close();
			}catch(IOException e) {
				System.out.println("Errore nella lettura del file!");
			}
		}
		
	}
	
	public List<RichWord> spellCheckTest(List<String> inputText){
		List<RichWord> parole = new ArrayList<RichWord>();
		for(String s: inputText) {
		//	if(dizionario.contains(s)) {
				RichWord r = new RichWord(s);
				parole.add(r);
				if(dizionario.contains(s)) {
					r.isCorrect();
				}
			//}
		}
		return parole;
	}
	
	public String toString(List<RichWord> parole) {
		String ritorno = "";
		for(RichWord r: parole) {
			if(r.isCorretta()==false) {
				ritorno+= r.getParola() +"\n";
				contErrori++;
			}
		}
		if(ritorno.equals(""))
			return "Le parole sono tutte corrette!";
		else
			return ritorno.substring(0, ritorno.length()-1);
	}

	public int getContErrori() {
		return contErrori;
	}
	
	
}
