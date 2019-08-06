package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import control.LetterButton;
import control.LetterKeyAdapter;
import model.WordGameEngine;

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
	private JPanel displayPanel;
	
	/**
	 * The top panel of the frame.
	 */
	private JPanel topPanel;
	
	/**
	 * The button and bottom panel of the frame.
	 */
	private JPanel bottomPanel;
	
	/**
	 * The letter buttons.
	 */
	private List<JButton> letterButtons;
	
	/**
	 * The keyboard listeners.
	 */
	private List<KeyListener> keyListeners;
	
	/**
	 * The constructor and starts the program.
	 */
	public GameSystem() {
		myFrame = new GameFrame();
		setUp();
	}
	
	/**
	 * Sets up the frame and buttons.
	 */
	private void setUp() {
		myFrame.setJMenuBar(new MenuBar());
		displayPanel = new JPanel(new BorderLayout());
		//topPanel = new JPanel();
		topPanel = new GamePanel();
		bottomPanel = new JPanel(new GridLayout(4, 7));
		
		letterButtons = createJButtons();
		for(final JButton button : letterButtons) {
			button.setVisible(true);
			bottomPanel.add(button);
		}
		
		keyListeners = createKeyListeners(letterButtons);
		for(final KeyListener listener : keyListeners) {
			myFrame.addKeyListener(listener);
		}
		
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		displayPanel.add(topPanel, BorderLayout.CENTER);
		displayPanel.add(bottomPanel, BorderLayout.SOUTH);	
        
        myFrame.add(displayPanel);
        myFrame.setFocusable(true);
        myFrame.setVisible(true);
	}
	
	/**
	 * Resets the game.
	 */
	private void resetGame() {
		if(WordGameEngine.getEngine() == null) {
			WordGameEngine.createEngine();
			WordGameEngine.getEngine().addObserver((Observer) topPanel);
		}
		for(final JButton button : letterButtons) {
			button.setEnabled(true);
		}
		WordGameEngine.getEngine().startGame();
	}
	
	/**
	 * Creates a list of JButtons.
	 * 
	 * @return The list of JButtons.
	 */
	private static List<JButton> createJButtons() {
		List<JButton> buttons = new ArrayList<JButton>();
		char letter = 'A';
		while(letter <= 'Z') {
			buttons.add(new LetterButton(letter));
			letter++;
		}
		return buttons;
	}
	
	/**
	 * Create a list of key listener of the keyboard.
	 * 
	 * @param buttons The buttons from the Frame. 
	 * @return A list of these key listeners.
	 */
	private List<KeyListener> createKeyListeners(final List<JButton> buttons) {
		List<KeyListener> listeners = new ArrayList<KeyListener>();
		
		for(int index = 0; index < buttons.size(); index++) {
			LetterButton button = (LetterButton) buttons.get(index);
			char letter = button.getLetter();
			listeners.add(new LetterKeyAdapter(letter, letter, button));
		}
		
		return listeners;
	}
	
	/**
	 * The menu item.
	 *
	 * @author Jim Phan phanjim2@hotmail.com
	 * @version Aug 6, 2019
	 */
	private class MenuBar extends JMenuBar {
		
		/**
		 * Serial ID.
		 */
		private static final long serialVersionUID = 7161628216109737319L;

		private MenuBar() {
			add(getJMenu());
		}
		
		private JMenu getJMenu() {
			JMenu menu = new JMenu("File");
			
			//Set the new game item.
			JMenuItem newGame = new JMenuItem(new AbstractAction("New Game") {

				/**
				 * Serial id of the action.
				 */
				private static final long serialVersionUID = 7784755064785587773L;

				@Override
				public void actionPerformed(ActionEvent e) {
					if(WordGameEngine.getEngine() == null ||
							WordGameEngine.getEngine().isGameOver()) {
						resetGame();
					}
				}
			});
			
			//Set the quit menu item.
			JMenuItem quit = new JMenuItem(new AbstractAction("Quit") {
				/**
				 * Serial id.
				 */
				private static final long serialVersionUID = 7784755064785587773L;

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}				
			});
			
			menu.add(newGame);
			menu.add(quit);
			return menu;
		}
	}
}
