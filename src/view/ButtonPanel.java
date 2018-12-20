package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Ein Panel, welches die Buttons beinhaltet
 * 
 * @author Oliver Stapelfeldt
 */
public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// Deklaration der Objekte
	public Button newgamebutton;
	public Button quitbutton;
	public Button solvebutton;
	public Button resetbutton;
	public Color background;

	public ButtonPanel() {

		// Erzeugung der Objekte
		newgamebutton = new Button();
		solvebutton = new Button();
		resetbutton = new Button();
		quitbutton = new Button();
		background = new Color(100, 149, 237);

		// Einrichtung des Panels
		setBackground(background);
		setBorder(new EmptyBorder(3, 3, 3, 3));
		setLayout(new GridBagLayout());

		add(newgamebutton, getButtonConstraints(0));
		add(solvebutton, getButtonConstraints(1));
		add(resetbutton, getButtonConstraints(2));
		add(quitbutton, getButtonConstraints(3));
	}

	private GridBagConstraints getButtonConstraints(int x) {
		return new GridBagConstraints(x, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5), 0, 0);
	}

}
