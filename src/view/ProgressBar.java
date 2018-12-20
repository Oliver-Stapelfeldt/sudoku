package view;

import java.awt.Color;

import javax.swing.JProgressBar;

/**
 * Eine SubKlasse der JProgressBar
 * 
 * @author Oliver Stapelfeldt
 */
public class ProgressBar extends JProgressBar {

	private static final long serialVersionUID = 1L;

	public ProgressBar(int start, int end) {
		super(start, end);
		this.setStringPainted(true);
		this.setForeground(new Color(250, 128, 114));
	}

}
