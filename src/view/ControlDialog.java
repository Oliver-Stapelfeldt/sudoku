package view;

import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Ein Dialog zur Anzeige der Steuerung
 * 
 * @author Oliver Stapelfeldt
 */
public class ControlDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	// Deklaration der Objekte
	public JPanel mainpanel;
	public Label navigatelabel;
	public Label deletelabel;
	public Button closebutton;

	public ControlDialog() {

		// Initialisierung der Objekte
		mainpanel = new JPanel();
		navigatelabel = new Label();
		deletelabel = new Label();
		closebutton = new Button();
		closebutton.addActionListener(e -> setVisible(false));

		// Einrichtung des Dialoges
		mainpanel.setLayout(new GridBagLayout());
		mainpanel.setBackground(new Color(255, 160, 122));
		add(mainpanel);
		mainpanel.add(navigatelabel, getLabelConstraints(0));
		mainpanel.add(deletelabel, getLabelConstraints(1));
		mainpanel.add(closebutton, getLabelConstraints(2));
	}

	private GridBagConstraints getLabelConstraints(int x) {
		return new GridBagConstraints(0, x, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0);
	}
}
