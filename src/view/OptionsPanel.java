package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Ein JPanel, welches die Dropdownmenüs zur Auswahl des Schwierigkeitsgrades
 * und Größe enthält
 * 
 * @author Oliver Stapelfeldt
 */
public class OptionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public DropDownMenu choosesize;
	public DropDownMenu choosedifficulty;
	public Label sizelabel;
	public Label difficultylabel;
	public Color background;

	public OptionsPanel() {

		// Initialisierung der Objekte
		sizelabel = new Label();
		difficultylabel = new Label();
		Integer[] sizes = { 4, 6, 8, 9 };
		String[] difficulties = { "Leicht", "Mittel", "Schwer" };
		choosesize = new DropDownMenu(sizes);
		choosedifficulty = new DropDownMenu(difficulties);

		// Einrichtung des Panels
		background = new Color(100, 149, 237);
		setBackground(background);
		setBorder(new EmptyBorder(3, 3, 3, 3));
		setLayout(new GridBagLayout());
		add(sizelabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(1, 1, 1, 5), 0, 0));
		add(difficultylabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(1, 1, 1, 5), 0, 0));
		add(choosesize, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(1, 1, 1, 1), 0, 0));
		add(choosedifficulty, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
	}
}
