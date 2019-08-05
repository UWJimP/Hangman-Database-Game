package view;

import javax.swing.JFrame;

/**
 * The game frame.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Aug 3, 2019
 */
public class GameFrame extends JFrame {

	/**
	 * The serial ID.
	 */
	private static final long serialVersionUID = -6399886456682347905L;

	/**
	 * The constructor.
	 */
	public GameFrame() {
		super("Word Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
		//setVisible(true);
	}
}
