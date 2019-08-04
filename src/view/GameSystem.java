package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.LetterKeyAdapter;

/**
 * System used to control the multiple components of the engine.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Aug 3, 2019
 */
public class GameSystem {

	/**
	 * The game frame.
	 */
	private JFrame myFrame;
	
	/**
	 * The panel used to display things.
	 */
	private JPanel myPanel;
	
	public GameSystem() {
		myFrame = new GameFrame();
		
		myFrame.add(myPanel);
	}
	
	public void setUp() {
		deleteListeners();
		setControls();
	}
	
	private void setControls() {
		for(final KeyListener key : this.createKeyListeners()) {
			myFrame.addKeyListener(key);
		}
	}
	
	private void deleteListeners() {
		for(final KeyListener key : myFrame.getKeyListeners()) {
			myFrame.removeKeyListener(key);
		}
	}
	
	private List<KeyListener> createKeyListeners() {
		List<KeyListener> listeners = new ArrayList<KeyListener>();
		char letter = 'a';
		int key = KeyEvent.VK_A;
		while(letter <= 'z') {
			listeners.add(new LetterKeyAdapter(key, letter));
			letter++;
			key++;
		}
		return listeners;
	}
	
	public static void main(String[] args) {
		char c = KeyEvent.VK_A;
		c++;
		System.out.println(KeyEvent.VK_B == c);
		
	}
}
