package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Ein Dialog zur Anzeige des Entwicklers
 * 
 * @author Oliver Stapelfeldt
 */
public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public JPanel mainpanel;
	public JTextArea area;
	public Button closebutton;

	public AboutDialog() {

		// Initialisierung der Objekte
		area = new JTextArea();
		closebutton = new Button();

		// Einrichtung der Objekte
		area.setEditable(false);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setBackground(new Color(255, 160, 122));
		closebutton.addActionListener(e -> setVisible(false));

		// Einrichtung des Dialoges
		setBounds(400, 450, 270, 150);
		this.add(area);
		this.add(closebutton, BorderLayout.SOUTH);
	}

}
