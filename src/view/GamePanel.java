package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * Ein Panel, welches ein Spielfeld und einen Fortschrittsbalken enthält
 * 
 * @author Oliver Stapelfeldt
 * 
 */
public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlayGround playground;
	public ProgressBar progressbar;

	public GamePanel() {
		setBackground(new Color(65, 105, 225));
		setLayout(new BorderLayout());
		progressbar = new ProgressBar(0, 100);
		progressbar.setVisible(false);
		add(progressbar, BorderLayout.SOUTH);
	}

	/**
	 * Tauscht ein Spielfeld gegen ein neues aus
	 * 
	 * @param playground
	 */
	public void setPlayGround(PlayGround playground) {
		if (this.playground != null)
			remove(this.playground);
		add(playground);
		this.playground = playground;
		validate();
		repaint();
	}

}
