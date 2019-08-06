package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import model.WordGameEngine;

/**
 * The Key Adapter used by the game to take input from the keyboard.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Aug 3, 2019
 */
public class LetterKeyAdapter extends KeyAdapter {

	/**
	 * The keyboard event.
	 */
	private int myKeyEvent;
	
	/**
	 * The letter event.
	 */
	private char myLetter;
	
	/**
	 * The button associated with the letter.
	 */
	private JButton myButton;
	
	/**
	 * Initialize the letter with a character and a key value.
	 * 
	 * @param key The keyboard's key code.
	 * @param letter The letter associated with it.
	 * @param button The button associated with the letter.
	 */
	public LetterKeyAdapter(final int key, char letter, JButton button) {
		myKeyEvent = key;
		myLetter = letter;
		myButton = button;
	}
	
	@Override
	public void keyPressed(final KeyEvent event) {
		if(event.getKeyCode() == myKeyEvent && WordGameEngine.getEngine() != null
				&& !WordGameEngine.getEngine().isGameOver()) {
			WordGameEngine.getEngine().guessLetter(myLetter);
			myButton.setEnabled(false);
		}
	}
	
	/**
	 * Returns the letter associated with the keyboard.
	 * 
	 * @return The letter
	 */
	public char getLetter() {
		return myLetter;
	}
}
