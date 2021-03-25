package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Dictionary {
	
	private List<String> dizionario;
	private int contErrori;
	
	public Dictionary() {
		dizionario = new LinkedList<>();
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
		//List<RichWord> parole = new ArrayList<RichWord>();
		List<RichWord> parole = new LinkedList<RichWord>();
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
	
	public List<RichWord> spellCheckTestLinear(List<String> inputText) {
		//List<RichWord> parole = new ArrayList<RichWord>();
		List<RichWord> paroleLinked = new LinkedList<RichWord>();
		//Iterator i = dizionario.iterator();
		//while(i.hasNext()) {
			for(String si: inputText) {
				for(String sd: dizionario) {
					if(si.equals(sd)) {
						RichWord r = new RichWord(si);
						r.isCorrect();
						//parole.add(r);
							paroleLinked.add(r);
					}
				}
			}
	//	}
		//return parole;
		return paroleLinked;
	}
	
	public List<RichWord> spellCheckTestDichotomic(List<String> inputText) {
		List<RichWord> parole = new ArrayList<RichWord>();
		List<RichWord> paroleLinked = new LinkedList<RichWord>();
		int indice = dizionario.size()/2;
		int max = dizionario.size();
		int min = 0;
		boolean inDizionario = false;
		
		while(min<=max) {
			for(String s:inputText) {
				if(s.compareTo(dizionario.get(indice))==0) {
					inDizionario = true;
				}
				else if (s.compareTo(dizionario.get(indice))>0){
					min = indice;
					indice = (min+max)/2;
				}
				else {
					max = indice;
					indice = (min+max)/2;
				}
				if(inDizionario == true) {
					RichWord r = new RichWord(s);
					r.isCorrect();
					parole.add(r);
					paroleLinked.add(r);
				}
			}
			
			
		}
		/*for(String s: inputText) {
			while(min<=max) {
				if(s.compareTo(dizionario.get(indice))==0) {
					inDizionario = true;
				}
				else if (s.compareTo(dizionario.get(indice))>0){
					min = indice;
					indice = (min+max)/2;
				}
				else {
					max = indice;
					indice = (min+max)/2;
				}
			}
			if(inDizionario == true) {
				RichWord r = new RichWord(s);
				r.isCorrect();
				parole.add(r);
				paroleLinked.add(r);
			}
		}*/
		
		//return parole;
		return paroleLinked;
	}
	
}
