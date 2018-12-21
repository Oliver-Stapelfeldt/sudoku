package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Erstellt alle Komponenten und richtet den Frame ein
 * 
 * @author Oliver Stapelfeldt
 */
public class ViewManager extends JFrame {

	private static final long serialVersionUID = 1L;

	// Deklaration der Objekte
	public OptionsPanel optionspanel;
	public ButtonPanel buttonpanel;
	public GamePanel gamepanel;
	public MenuBar menubar;
	public ControlDialog controldialog;
	public AboutDialog aboutdialog;

	public ViewManager() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400, 350);
		setTitle("Sudoku");

		// Initialisierung der Objekte
		optionspanel = new OptionsPanel();
		buttonpanel = new ButtonPanel();
		gamepanel = new GamePanel();
		menubar = new MenuBar();
		controldialog = new ControlDialog();
		aboutdialog = new AboutDialog();

		// Einrichtung des Frames
		add(optionspanel, BorderLayout.NORTH);
		add(gamepanel);
		add(buttonpanel, BorderLayout.SOUTH);
		setJMenuBar(menubar);

		setVisible(true);
	}
}
