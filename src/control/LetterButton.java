package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.WordGameEngine;

/**
 * The button class used by the word game.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Aug 4, 2019
 */
public class LetterButton extends JButton {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 80717274578833188L;
	
	/**
	 * The letter the Button represents.
	 */
	private char myLetter;
	
	public LetterButton(char letter) {
		super(letter + "");
		myLetter = letter;
		this.setEnabled(true);
		setUp();
	}

	/**
	 * Get the letter of the button.
	 * 
	 * @return The letter.
	 */
	public char getLetter() {
		return myLetter;
	}
	
	private void setUp() {
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				WordGameEngine engine = WordGameEngine.getEngine(); 
				if(engine != null && !engine.isGameOver()) {
					engine.guessLetter(myLetter);
					setEnabled(false);
				}
			}
		});
	}
}
