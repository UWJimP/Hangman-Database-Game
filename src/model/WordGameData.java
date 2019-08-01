package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Holds the data for the word guessing game.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Jul 22, 2019
 */
public class WordGameData {

	/**
	 * The ASCII values of the characters from A to Z and a to z.
	 */
	private static final int ASCII_A = 65;
	private static final int ASCII_Z = 90;
	private static final int ASCII_a = 97;
	private static final int ASCII_z = 122;
	
	/**
	 * The max amount of incorrect guesses a person can have.
	 */
	private static final int MAX_WRONG = 5;
	
	/**
	 * The word being guessed.
	 */
	private String myWord;
	
	/**
	 * The letters that were guessed.
	 */
	private Set<Character> myGuessedLetter;
	
	/**
	 * The current revealed letters.
	 */
	private Word myCurrentRevealedLetters;
	
	/**
	 * The current amount of wrong guesses.
	 */
	private int myWrong; 
	
	/**
	 * Tells if the game is over.
	 */
	private boolean myGameOver;
	
	public WordGameData(String word) {
		startNewGame(word);
	}
	
	/**
	 * Starts a new game.
	 * 
	 * @param word The word for the new game.
	 */
	public void startNewGame(String word) {
		myWord = word;
		myGuessedLetter = new HashSet<Character>();
		myWrong = 0;
		myGameOver = false;
		myCurrentRevealedLetters = new Word(myWord);
	}
	
	/**
	 * Tells if the game is over.
	 * 
	 * @return If the game is over.
	 */
	public boolean isGameOver() {
		return myGameOver;
	}
	
	/**
	 * Guess a letter in the word.
	 * 
	 * @param letter The letter being guessed.
	 * @return If a suitable letter was guessed.
	 */
	public boolean guessLetter(char letter) {
		if(!myGuessedLetter.contains(letter) && correctCharacter(letter)) {
			myGuessedLetter.add(letter);
			boolean found = myCurrentRevealedLetters.revealLetter(letter);
			if(!found) {
				myWrong++;
			}
			if(myWrong >= MAX_WRONG || myCurrentRevealedLetters.isCompletelyRevealed()) {
				myGameOver = true;
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns a set of current guessed letters.
	 * 
	 * @return A set of current guessed letters.
	 */
	public Set<Character> getGuessedLetters() {
		Set<Character> letters = new HashSet<Character>();
		for(Character letter : letters) {
			letters.add(letter);
		}
		return letters;
	}
	
	/**
	 * Returns a character array of the current revealed letters.
	 * 
	 * @return The array of current revealed letters.
	 */
	public char[] currentRevealedLetters() {
		return new String(myCurrentRevealedLetters.currentRevealedLetters()).toCharArray();
	}
	
	/**
	 * Returns if the word is complete.
	 * 
	 * @return If the word is complete.
	 */
	public boolean isCompleted() {
		return myCurrentRevealedLetters.isCompletelyRevealed();
	}
	
	private boolean correctCharacter(char letter) {
		if((letter >= ASCII_A && letter <= ASCII_Z) || (letter >= ASCII_a && letter <= ASCII_z)) {
			return true;
		}
		return false;
	}
	
	private class Letter {
		
		private char letter;
		
		private boolean isRevealed;
		
		private Letter(char theLetter) {
			this.letter = theLetter;
			this.isRevealed = false;
		}
	}
	
	private class Word {
		
		/**
		 * The letters in the word.
		 */
		private Letter[] letters;
		
		private Word(String word) {
			letters = new Letter[word.length()];
			for(int index = 0; index < letters.length; index++) {
				letters[index] = new Letter(word.charAt(index));
			}
		}
		
		/**
		 * Checks and reveals any letters in the word.
		 * 
		 * @param theLetter The letter that has to be checked.
		 * @return If a letter was revealed or not.
		 */
		private boolean revealLetter(char theLetter) {
			boolean revealed = false;
			
			for(Letter check : letters) {
				if(check.letter == theLetter) {
					check.isRevealed = true;
					revealed = true;
				}
			}
			
			return revealed;
		}
		
		/**
		 * Returns an array of the current revealed letters.
		 * 
		 * @return The array of current revealed letters.
		 */
		private char[] currentRevealedLetters() {
			char[] current = new char[letters.length];
			
			for(int index = 0; index < current.length; index++) {
				if(letters[index].isRevealed) {
					current[index] = letters[index].letter;
				} else {
					current[index] = '-';
				}
			}
			
			return current;
		}
		
		private boolean isCompletelyRevealed() {
			return !(new String(currentRevealedLetters()).contains("-"));
		}
	}
}
