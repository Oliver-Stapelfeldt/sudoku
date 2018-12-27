package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;

/**
 * Das Menü des Frames
 * 
 * @author Oliver Stapelfeldt
 */
public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JMenu gamemenu, optionsmenu, languagemenu, symmetrymenu, helpmenu, cellsizemenu;
	public JMenuItem newgameitem, solveitem, resetitem, quititem, controlitem, aboutitem;
	public JRadioButtonMenuItem englishitem, germanitem, persishitem, symmetryon, symmetryoff, smallitem, mediumitem, largeitem;
	public ArrayList<JRadioButtonMenuItem> languages, symmetries, cellsizes;
	public Color background;

	public MenuBar() {

		background = new Color(135, 206, 250);

		// Erzeugung der Menüs
		gamemenu = new JMenu("game");
		optionsmenu = new JMenu("options");
		languagemenu = new JMenu();
		symmetrymenu = new JMenu();
		cellsizemenu = new JMenu();
		helpmenu = new JMenu();

		// Erzeugung der MenüItems
		newgameitem = new JMenuItem();
		solveitem = new JMenuItem();
		resetitem = new JMenuItem();
		quititem = new JMenuItem();
		controlitem = new JMenuItem();
		aboutitem = new JMenuItem();
		englishitem = new JRadioButtonMenuItem();
		germanitem = new JRadioButtonMenuItem();
		persishitem = new JRadioButtonMenuItem();
		languages = new ArrayList<JRadioButtonMenuItem>();
		languages.add(germanitem);
		languages.add(englishitem);
		languages.add(persishitem);
		symmetryon = new JRadioButtonMenuItem();
		symmetryoff = new JRadioButtonMenuItem();
		symmetries = new ArrayList<JRadioButtonMenuItem>();
		symmetries.add(symmetryon);
		symmetries.add(symmetryoff);
		smallitem = new JRadioButtonMenuItem();
		mediumitem = new JRadioButtonMenuItem();
		largeitem = new JRadioButtonMenuItem();
		cellsizes = new ArrayList<JRadioButtonMenuItem>();
		cellsizes.add(smallitem);
		cellsizes.add(mediumitem);
		cellsizes.add(largeitem);

		// Verknüpfungen werden erzeugt
		add(gamemenu);
		add(optionsmenu);
		add(helpmenu);

		gamemenu.add(newgameitem);
		gamemenu.add(solveitem);
		gamemenu.add(resetitem);
		gamemenu.add(quititem);

		optionsmenu.add(languagemenu);
		optionsmenu.add(symmetrymenu);
		optionsmenu.add(cellsizemenu);

		for (JRadioButtonMenuItem languageitem : languages) {
			languagemenu.add(languageitem);
		}

		for (JRadioButtonMenuItem symmetryitem : symmetries) {
			symmetrymenu.add(symmetryitem);
		}

		for (JRadioButtonMenuItem sizeitem : cellsizes) {
			cellsizemenu.add(sizeitem);
		}

		helpmenu.add(controlitem);
		helpmenu.add(aboutitem);

		// sonstige Einstellungen

		setBorder(null);
		gamemenu.getPopupMenu().setBorder(null);
		optionsmenu.getPopupMenu().setBorder(null);
		languagemenu.getPopupMenu().setBorder(null);
		symmetrymenu.getPopupMenu().setBorder(null);
		cellsizemenu.getPopupMenu().setBorder(null);
		helpmenu.getPopupMenu().setBorder(null);
		languagemenu.setOpaque(true);
		symmetrymenu.setOpaque(true);
		cellsizemenu.setOpaque(true);
		setComponent(this, background);
		setComponent(gamemenu, background);
		setComponent(optionsmenu, background);
		setComponent(languagemenu, background);
		setComponent(symmetrymenu, background);
		setComponent(cellsizemenu, background);
		setComponent(newgameitem, background);
		setComponent(solveitem, background);
		setComponent(resetitem, background);
		setComponent(quititem, background);
		setComponent(englishitem, background);
		setComponent(germanitem, background);
		setComponent(persishitem, background);
		setComponent(symmetryoff, background);
		setComponent(symmetryon, background);
		setComponent(helpmenu, background);
		setComponent(controlitem, background);
		setComponent(aboutitem, background);
		setComponent(smallitem, background);
		setComponent(mediumitem, background);
		setComponent(largeitem, background);
	}

	/**
	 * Bewirkt, dass nur ein MenuItem ausgewählt werden kann
	 */
	public void selectItem(JRadioButtonMenuItem item, ArrayList<JRadioButtonMenuItem> itemlist) {
		for (JRadioButtonMenuItem localitem : itemlist)
			localitem.setSelected(false);
		item.setSelected(true);
	}

	/**
	 * Gibt die ausgewählte Symmetrie-Einstellung zurück
	 */
	public int getSelectedSymmetry() {
		for (int i = 0; i < symmetries.size(); i++) {
			if (symmetries.get(i).isSelected())
				return i;
		}
		return 0;
	}

	/**
	 * Gibt die ausgewählte Symmetrie-Einstellung zurück
	 */
	public int getSelectedCellSize() {
		for (int i = 0; i < cellsizes.size(); i++) {
			if (cellsizes.get(i).isSelected())
				return i;
		}
		return 0;
	}

	/**
	 * Setzt Hintergrundfarbe und Border der übergebenen Komponente
	 */
	private void setComponent(JComponent component, Color background) {
		component.setBackground(background);
		component.setBorder(new EmptyBorder(2, 0, 2, 0));
	}
}
