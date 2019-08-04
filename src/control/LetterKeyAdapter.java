package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	 * Initialize the letter with a character and a key value.
	 * 
	 * @param key The keyboard's key code.
	 * @param letter The letter associated with it.
	 */
	public LetterKeyAdapter(final int key, char letter) {
		myKeyEvent = key;
		myLetter = letter;
	}
	
	@Override
	public void keyPressed(final KeyEvent event) {
		if(event.getKeyCode() == myKeyEvent) {
			WordGameEngine.getEngine().guessLetter(myLetter);
		}
	}
}
