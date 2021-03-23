/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;

public class FXMLController {

	private Dictionary model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtChooseLanguage;

    @FXML
    private TextArea txtInsert;

    @FXML
    private Button btnCheck;

    @FXML
    private Label txtWrongWords;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label txtErrors;

    @FXML
    private Button btnClear;

    @FXML
    private Label txtTime;

    @FXML
    private ComboBox<String> boxLanguages;
    
    @FXML
    void doCheck(ActionEvent event) {
    	String testo = txtInsert.getText();
    	testo = testo.toLowerCase().replaceAll("[.,\\/#!$?%\\^&\\*;:{}=\\-_'~()\\[\\]\"]", "");
    	String[] parole = testo.split(" ");
    	//StringTokenizer parole = new StringTokenizer(testo, " ");

    	List<String> words = new ArrayList<>();
    	for(String s: parole) {
    		words.add(s);
    	}
    	long start = System.nanoTime();
    	List<RichWord> rw = this.model.spellCheckTest(words);
    	long end = System.nanoTime();
    	txtResult.setText(this.model.toString(rw));
    	txtErrors.setText("The text contains "+ this.model.getContErrori() + " errors");
    	long tempoImpiegato = end-start;
    	txtTime.setText("Spell check completed in "+ tempoImpiegato +" ns");
    }

    @FXML
    void doClear(ActionEvent event) {
    	txtInsert.clear();
    	txtResult.clear();
    }

    @FXML
    void selectLanguage(ActionEvent event) {
    	String lingua = boxLanguages.getValue();
    	this.model.loadDictionary(lingua);
    	txtInsert.setDisable(false);
    }
    
    @FXML
    void initialize() {
        assert txtChooseLanguage != null : "fx:id=\"txtChooseLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtWrongWords != null : "fx:id=\"txtWrongWords\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxLanguages != null : "fx:id=\"boxLanguages\" was not injected: check your FXML file 'Scene.fxml'.";
        boxLanguages.getItems().addAll("English", "Italian");
        
    }
    
    public void setModel(Dictionary model) {
    	this.model = model;
    	
    }
}

