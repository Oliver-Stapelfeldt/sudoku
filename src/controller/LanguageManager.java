package controller;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JRadioButtonMenuItem;

import view.ViewManager;

/**
 * Richtet die Spracheinstellung ein
 * 
 * @author Oliver Stapelfeldt
 */
public class LanguageManager {
	ResourceBundle bundle;
	ViewManager viewmanager;
	Locale loc;

	public LanguageManager(ViewManager viewmanager) {
		this.viewmanager = viewmanager;
	}

	public void selectLanguage(Locale loc) {
		int index = 0;
		if (loc.toString().equals("de"))
			index = 0;
		if (loc.toString().equals("en"))
			index = 1;

		for (JRadioButtonMenuItem languageitem : viewmanager.menubar.languages) {
			languageitem.setSelected(false);
		}
		viewmanager.menubar.languages.get(index).setSelected(true);
	}

	public Locale getSelectedLanguage() {
		int index = 0;
		for (int i = 0; i < viewmanager.menubar.languages.size(); i++) {
			if (viewmanager.menubar.languages.get(i).isSelected())
				index = i;
		}
		switch (index) {
		case (0):
			loc = Locale.GERMAN;
			break;
		case (1):
			loc = Locale.ENGLISH;
		}
		return loc;
	}

	public void setLanguage() {
		bundle = ResourceBundle.getBundle("model/LabelsBundle", getSelectedLanguage());

		// Richtet Buttons ein
		viewmanager.buttonpanel.newgamebutton.setText(bundle.getString("newgamebutton"));
		viewmanager.buttonpanel.resetbutton.setText(bundle.getString("resetbutton"));
		viewmanager.buttonpanel.solvebutton.setText(bundle.getString("solvebutton"));
		viewmanager.buttonpanel.quitbutton.setText(bundle.getString("quitbutton"));
		viewmanager.buttonpanel.newgamebutton.setToolTipText(bundle.getString("newgamebuttontooltip"));
		viewmanager.buttonpanel.resetbutton.setToolTipText(bundle.getString("resetbuttontooltip"));
		viewmanager.buttonpanel.solvebutton.setToolTipText(bundle.getString("solvebuttontooltip"));
		viewmanager.buttonpanel.quitbutton.setToolTipText(bundle.getString("quitbuttontooltip"));

		// Richtet Labels ein
		viewmanager.optionspanel.difficultylabel.setText(bundle.getString("difficultylabel"));
		viewmanager.optionspanel.sizelabel.setText(bundle.getString("sizelabel"));

		// Richtet das Menü ein
		viewmanager.menubar.languagemenu.setText(bundle.getString("languagemenu"));
		viewmanager.menubar.optionsmenu.setText(bundle.getString("optionsmenu"));
		viewmanager.menubar.gamemenu.setText(bundle.getString("gamemenu"));
		viewmanager.menubar.englishitem.setText(bundle.getString("englishitem"));
		viewmanager.menubar.germanitem.setText(bundle.getString("germanitem"));
		viewmanager.menubar.newgameitem.setText(bundle.getString("newgamebutton"));
		viewmanager.menubar.resetitem.setText(bundle.getString("resetbutton"));
		viewmanager.menubar.solveitem.setText(bundle.getString("solvebutton"));
		viewmanager.menubar.quititem.setText(bundle.getString("quitbutton"));
		viewmanager.menubar.symmetrymenu.setText(bundle.getString("symmetrymenu"));
		viewmanager.menubar.symmetryon.setText(bundle.getString("symmetryon"));
		viewmanager.menubar.symmetryoff.setText(bundle.getString("symmetryoff"));
		viewmanager.menubar.helpmenu.setText(bundle.getString("helpmenu"));
		viewmanager.menubar.controlitem.setText(bundle.getString("controlitem"));
		viewmanager.menubar.aboutitem.setText(bundle.getString("aboutitem"));
		viewmanager.menubar.cellsizemenu.setText(bundle.getString("cellsizemenu"));
		viewmanager.menubar.smallitem.setText(bundle.getString("smallitem"));
		viewmanager.menubar.mediumitem.setText(bundle.getString("mediumitem"));
		viewmanager.menubar.largeitem.setText(bundle.getString("largeitem"));
		
		// Richtet Dialoge ein
		viewmanager.controldialog.setTitle(bundle.getString("controlitem"));
		viewmanager.controldialog.navigatelabel.setText(bundle.getString("navigatedialog"));
		viewmanager.controldialog.deletelabel.setText(bundle.getString("deletedialog"));
		viewmanager.controldialog.closebutton.setText(bundle.getString("closebutton"));
		viewmanager.aboutdialog.setTitle(bundle.getString("aboutitem"));
		viewmanager.aboutdialog.closebutton.setText(bundle.getString("closebutton"));
		viewmanager.aboutdialog.area
				.setText(bundle.getString("developer") + "\n\n https://github.com/Oliver-Stapelfeldt/sudoku");

		// Richtet ein DropDownMenü ein
		String entries = bundle.getString("choosedifficulty");
		String[] boxentries = entries.split(",");
		int selected = viewmanager.optionspanel.choosedifficulty.getSelectedIndex();
		viewmanager.optionspanel.choosedifficulty.removeAllItems();
		for (String boxentry : boxentries)
			viewmanager.optionspanel.choosedifficulty.addItem(boxentry);
		viewmanager.optionspanel.choosedifficulty.setSelectedIndex(selected);
	}
}