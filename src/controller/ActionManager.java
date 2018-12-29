package controller;

import javax.swing.JRadioButtonMenuItem;

import view.PlayGround;
import view.ViewManager;

/**
 * Sämtliche Zuweisungen von ActionListenern finden hier statt.
 * 
 * @author Oliver Stapelfeldt
 */
public class ActionManager {

	public ActionManager(ViewManager viewmanager, FileManager filemanager, Generator generator, Solver solver,
			LanguageManager languagemanager, CellSizer cellsizer) {

		// Zuweisung der Buttons

		viewmanager.buttonpanel.quitbutton.addActionListener(e -> {
			filemanager.quit(viewmanager);
		});

		viewmanager.buttonpanel.newgamebutton.addActionListener(e -> {
			viewmanager.gamepanel.playground.clear();
			new GenerateThread(viewmanager, generator).start();
		});

		viewmanager.buttonpanel.solvebutton.addActionListener(e -> {

			viewmanager.gamepanel.playground.reset();
			solver.solveRandomly(viewmanager.gamepanel.playground);
			viewmanager.gamepanel.playground.show();
		});

		viewmanager.buttonpanel.resetbutton.addActionListener(e -> viewmanager.gamepanel.playground.reset());

		// Zuweisung des Dropdownmenüs

		viewmanager.optionspanel.choosesize.addActionListener(e -> {
			viewmanager.gamepanel
					.setPlayGround(new PlayGround((Integer) viewmanager.optionspanel.choosesize.getSelectedItem()));
			viewmanager.gamepanel.playground.addKeyListener(new KeyManager(viewmanager.gamepanel.playground, solver));
			cellsizer.setCellSizes();
		});

		// Zuweisung der Menüitems

		for (JRadioButtonMenuItem languageitem : viewmanager.menubar.languages)
			languageitem.addActionListener(e -> {
				viewmanager.menubar.selectItem(languageitem, viewmanager.menubar.languages);
				languagemanager.setLanguage();
				viewmanager.pack();
				viewmanager.controldialog.pack();
			});

		for (JRadioButtonMenuItem symmetryitem : viewmanager.menubar.symmetries)
			symmetryitem.addActionListener(e -> {
				viewmanager.menubar.selectItem(symmetryitem, viewmanager.menubar.symmetries);
			});
		
		for (JRadioButtonMenuItem cellsizeitem : viewmanager.menubar.cellsizes)
			cellsizeitem.addActionListener(e -> {
				viewmanager.menubar.selectItem(cellsizeitem, viewmanager.menubar.cellsizes);
				cellsizer.setCellSizes();});

		viewmanager.menubar.quititem.addActionListener(e -> {
			filemanager.quit(viewmanager);
		});

		viewmanager.menubar.newgameitem.addActionListener(e -> {
			viewmanager.gamepanel.playground.clear();
			new GenerateThread(viewmanager, generator).start();
		});

		viewmanager.menubar.solveitem.addActionListener(e -> {

			viewmanager.gamepanel.playground.reset();
			solver.solveRandomly(viewmanager.gamepanel.playground);
			viewmanager.gamepanel.playground.show();
		});

		viewmanager.menubar.resetitem.addActionListener(e -> viewmanager.gamepanel.playground.reset());

		viewmanager.menubar.controlitem.addActionListener(e -> viewmanager.controldialog.setVisible(true));
		
		viewmanager.menubar.aboutitem.addActionListener(e -> viewmanager.aboutdialog.setVisible(true));
	}
}