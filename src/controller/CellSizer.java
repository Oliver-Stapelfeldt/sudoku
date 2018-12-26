package controller;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

import view.Cell;
import view.ViewManager;

/**
 * Richtet die Zellengrößen ein
 * 
 * @author Oliver Stapelfeldt
 *
 */
public class CellSizer {

	ViewManager viewmanager;

	public CellSizer(ViewManager viewmanager) {
		this.viewmanager = viewmanager;
	}

	/**
	 * richtet Zellengrößen ein
	 */
	public void setCellSizes() {
		Font writefont = new Font(getWriteFont(), Font.BOLD, getFontSize());
		Font fixedfont = new Font("Arial", Font.BOLD, getFontSize());
		Cell[][] matrix = viewmanager.gamepanel.playground.getMatrix();
		for (Cell[] vector : matrix)
			for (Cell cell : vector) {
				cell.writefont = writefont;
				cell.fixedfont = fixedfont;
				cell.setMinimumSize(new Dimension(getCellSize(), getCellSize()));
				cell.setPreferredSize(new Dimension(getCellSize(), getCellSize()));
				if (cell.isFixed())
					cell.setFixed(true);
				else
					cell.setFixed(false);
			}
		viewmanager.pack();
	}

	private int getFontSize() {
		int size = 0;
		int index = viewmanager.menubar.getSelectedCellSize();
		switch (index) {
		case (0):
			size = 20;
			break;
		case (1):
			size = 25;
			break;
		case (2):
			size = 30;
		}
		return size;
	}

	private int getCellSize() {
		int size = 0;
		int index = viewmanager.menubar.getSelectedCellSize();
		switch (index) {
		case (0):
			size = 33;
			break;
		case (1):
			size = 41;
			break;
		case (2):
			size = 49;
		}
		return size;
	}

	private String getWriteFont() {
		String[] favFonts = { "Ink Free", "Indie Flower", "Bradley Hand", "Bradley Hand ITC", "Brush Script MT",
				"Segoe Script" };
		String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

		for (String font : favFonts) {
			if (Arrays.asList(fontList).contains(font))
				return font;
		}
		return null;
	}
}
