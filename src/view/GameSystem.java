package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import control.LetterButton;
import control.LetterKeyAdapter;
import model.WordGameData;
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
	
	private JPanel topPanel;
	
	private JPanel bottomPanel;
	
	private List<JButton> letterButtons;
	
	private List<KeyListener> keyListeners;
	
	public GameSystem() {
		myFrame = new GameFrame();
		//setControls();
		//this.setUpFrame();
		//createUI();
		setUp();
	}
	
	public void setUp() {
		myFrame.setJMenuBar(new MenuBar());
		displayPanel = new JPanel(new BorderLayout());
		topPanel = new JPanel();
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
		displayPanel.add(topPanel, BorderLayout.NORTH);
		displayPanel.add(bottomPanel, BorderLayout.SOUTH);
		JLabel label = new JLabel("Top Panel");
        topPanel.add(label);
        
        myFrame.add(displayPanel);
        myFrame.setFocusable(true);
        myFrame.setVisible(true);
	}
	
	private void resetFrame() {
		for(final JButton button : letterButtons) {
			button.setEnabled(true);
		}
	}
	
	private void setUpFrame() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel top = new JPanel();
		//JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel bottom = new JPanel(new GridLayout(4, 7));
		
		List<JButton> buttons = createJButtons();
		for(final JButton button : buttons) {
			button.setVisible(true);
			bottom.add(button);
		}

		List<KeyListener> listeners = createKeyListeners(buttons);
		for(final KeyListener listener : listeners) {
			myFrame.addKeyListener(listener);
		}
		
		bottom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mainPanel.add(top, BorderLayout.NORTH);
		mainPanel.add(bottom, BorderLayout.SOUTH);
		JLabel label = new JLabel("Top Panel");
        top.add(label);
        
        myFrame.add(mainPanel);
        myFrame.setFocusable(true);
        myFrame.setVisible(true);
	}
	
	private void deleteListeners() {
		for(final KeyListener key : myFrame.getKeyListeners()) {
			myFrame.removeKeyListener(key);
		}
	}
	
	private static List<JButton> createJButtons() {
		List<JButton> buttons = new ArrayList<JButton>();
		char letter = 'A';
		while(letter <= 'Z') {
			buttons.add(new LetterButton(letter));
			letter++;
		}
		return buttons;
	}
	
	private List<KeyListener> createKeyListeners(final List<JButton> buttons) {
		List<KeyListener> listeners = new ArrayList<KeyListener>();
		
		for(int index = 0; index < buttons.size(); index++) {
			LetterButton button = (LetterButton) buttons.get(index);
			char letter = button.getLetter();
			listeners.add(new LetterKeyAdapter(letter, letter, button));
		}
		
		return listeners;
	}
	
	public static void main(String[] args) {
		char c = KeyEvent.VK_A;
		c++;
		System.out.println(c);
		System.out.println('b');
		System.out.println(c == 'B');
		GameSystem system = new GameSystem();
		//createUI();
	}
	
	private static void createUI() {
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPnl = new JPanel();
        JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //JButton btnLeft = new JButton("Left");
        //JButton btnRight = new JButton("Right");

        //btnPnl.add(btnLeft);
        //btnPnl.add(btnRight);

		List<JButton> buttons = createJButtons();
		
		for(final JButton button : buttons) {
			//button.setVisible(true);
			btnPnl.add(button);
		}
        
        btnPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        mainPanel.add(topPnl, BorderLayout.NORTH);
        mainPanel.add(btnPnl, BorderLayout.SOUTH);

        JLabel label = new JLabel("Top Panel");
        topPnl.add(label);

        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("done creating frame");
	}
	
	private class MenuBar extends JMenuBar {
		
		private MenuBar() {
			add(getJMenu());
		}
		
		private JMenu getJMenu() {
			JMenu menu = new JMenu("File");
			
			//Set the new game item.
			JMenuItem newGame = new JMenuItem(new AbstractAction("New Game") {

				@Override
				public void actionPerformed(ActionEvent e) {
					resetFrame();
					WordGameEngine.getEngine().startGame();
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
