package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.WordGameData;

/**
 * The panel that displays the game's information to the player.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Aug 4, 2019
 */
public class GamePanel extends JPanel implements Observer {

	/**
	 * The serial id.
	 */
	private static final long serialVersionUID = 7064910535493102897L;

	/**
	 * The number of wrong guesses.
	 */
	private int myWrong;
	
	/**
	 * The current letters revealed.
	 */
	private String myCurrentLetters;
	
	/**
	 * The word when revealed.
	 */
	private String myRevealedWord;
	
	/**
	 * The constructor.
	 */
	public GamePanel() {
		this.setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		Graphics2D g2d = (Graphics2D) theGraphics;
		g2d.setColor(Color.BLACK);
		Font font = new Font("Times New Roman", Font.BOLD, 20);
		g2d.setFont(font);
		g2d.drawString("Wrong: " + myWrong, 100, 100);
		
		font = new Font("Times New Roman", Font.BOLD, 30);
		g2d.setFont(font);
		
		if(myCurrentLetters != null) {
			g2d.drawString("Word: " + myCurrentLetters, 100, 200);
		} else {
			g2d.drawString("Word: ", 100, 200);
		}
		
		if(myWrong >= 5) {
			g2d.drawString("You lose, the word was: " + myRevealedWord, 100, 300);
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof WordGameData) {
			WordGameData data = (WordGameData) arg1;
			myWrong = data.getWrong();
			myCurrentLetters = data.toString();
			myRevealedWord = data.getWord();
			repaint();
		}
	}
}
