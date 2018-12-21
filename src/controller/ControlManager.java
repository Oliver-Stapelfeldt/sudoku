package controller;

import view.ViewManager;

/**
 * Zentrum der Kontrollumgebung
 */
public class ControlManager {

	// Deklaration der Objekte
	ViewManager viewmanager;
	Solver solver;
	Generator generator;
	ActionManager actionmanager;
	FileManager filemanager;
	LanguageManager languagemanager;
	CellSizer cellsizer;

	public ControlManager(ViewManager viewmanager) {

		// Erzeugung und Einrichtung der Objekte
		this.viewmanager = viewmanager;
		solver = new Solver();
		generator = new Generator(solver);
		languagemanager = new LanguageManager(viewmanager);
		filemanager = new FileManager(viewmanager, languagemanager);
		cellsizer = new CellSizer(viewmanager);
		filemanager.loadConfig();
		actionmanager = new ActionManager(viewmanager, filemanager, generator, solver, languagemanager, cellsizer);
		viewmanager.gamepanel.setPlayGround(filemanager.loadPlayGround());
		viewmanager.gamepanel.playground.addKeyListener(new KeyManager(viewmanager.gamepanel.playground, solver));
		viewmanager.addWindowListener(filemanager.new WindowCloser());
		languagemanager.setLanguage();
		cellsizer.setCellSizes();
		viewmanager.validate();
		viewmanager.repaint();
		viewmanager.pack();
		viewmanager.controldialog.pack();
	}

}
