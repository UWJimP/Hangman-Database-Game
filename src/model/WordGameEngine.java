package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import org.json.simple.JSONObject;

/**
 * The engine that runs the game. Using Singleton pattern to prevent multiple engines.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Aug 1, 2019
 */
public class WordGameEngine extends Observable {

	private static WordGameData myData;
	
	private static WordGameEngine myEngine;
	
	private static JSONObject myDataBaseWord;
	
	private static Set<String> myCurrentWords;
	
	private WordGameEngine() {
		myCurrentWords = new HashSet<String>(); 
	}
	
	public static void createEngine() {
		if(myEngine == null) {
			myEngine = new WordGameEngine();
		}
	}
	
	public static WordGameEngine getEngine() {
		return myEngine;
	}
	
	public void startGame() {
		myDataBaseWord = WordGameDatabase.getMethod();
		String word = (String) myDataBaseWord.get("word");
		int id = (int)((long) myDataBaseWord.get("id"));
		if(myCurrentWords.contains(word)) {
			myDataBaseWord = null;
			startGame();
		} else {
			myData = new WordGameData(word, id);	
		}
		setChanged();
		notifyObservers(myData);//Might need to change
		
	}
	
	public boolean guessLetter(char letter) {
		boolean suitableGuess = false;
		if(myData != null && !myData.isGameOver()) {
			suitableGuess = myData.guessLetter(letter);
		}
		if(suitableGuess) {
			setChanged();
			notifyObservers(myData);//Might need to change
			if(myData.isGameOver()) {
				Map<String, Object> values = new HashMap<>();
				String success = "false";
				if(myData.isCompleted()) {
					success = "true";
				}
				values.put("success", success);
				values.put("id", myData.getID());
				JSONObject json = new JSONObject(values);
				WordGameDatabase.postMethod("word", json);
			}
		}
		return suitableGuess;
	}

}
