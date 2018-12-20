package view;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Eine Subklasse des JButtons
 * 
 * @author Oliver Stapelfeldt
 *
 */
public class Button extends JButton {

	private static final long serialVersionUID = 1L;

	public Color background;

	public Button() {
		background = this.getBackground();
		this.setFocusable(false);
	}

}
