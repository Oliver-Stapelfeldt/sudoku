package controller;

import java.awt.Color;

import view.Cell;
import view.PlayGround;

/**
 * Ein Thread zur Ausführung eines Effektes, der ausgeführt wird, sobald ein
 * Rätsel erfolgreich gelöst wurde.
 * 
 * @author Oliver Stapelfeldt
 */
public class WinEffect extends Thread {

	// Deklaration der Objekte
	PlayGround playground;
	Color white;
	Color yellow;
	Color orange;
	Color red;
	Color lightpurple;
	Color darkpurple;
	Color blue;
	Color green;

	public WinEffect(PlayGround playground) {

		// Initialisierung der Objekte
		this.playground = playground;
		white = playground.getMatrix()[0][0].getBackGround();
		yellow = new Color(255, 255, 0);
		orange = new Color(255, 127, 0);
		red = new Color(255, 0, 0);
		lightpurple = new Color(143, 0, 255);
		darkpurple = new Color(75, 0, 130);
		blue = new Color(0, 0, 255);
		green = new Color(0, 255, 0);
	}

	public void run() {
		Color[] colororder = { white, yellow, orange, red, lightpurple, darkpurple, blue, green, yellow, orange, red,
				lightpurple, darkpurple, blue, green, yellow, orange, red, lightpurple, darkpurple, blue, green };
		runEffect(75, colororder, getCellGroups());
	}

	/**
	 * Für den Effekt aus, bei dem sich eine Farbenfolge durch die Zellgruppen
	 * bewegt
	 */
	public void runEffect(int millis, Color[] colors, Cell[]... cellgroups) {

		for (int startindex = -colors.length; startindex < cellgroups.length; startindex++) {
			int colorindex = 0;
			for (int i = startindex; i < startindex + colors.length; i++) {

				setColor(colors[colorindex], cellgroups, i);
				colorindex++;

			}
			pause(millis);
		}
	}

	/**
	 * Pausiert für die angegebenen Millisekunden
	 */
	private void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setColor(Color color, Cell[][] cellgroups, int cellgroupindex) {
		if (cellgroupindex >= 0 && cellgroupindex < cellgroups.length)
			for (Cell cell : cellgroups[cellgroupindex])
				cell.setBackground(color);
	}

	/**
	 * Für verschiedene Spielfeldgrößen werden verschiedene Zellgruppen definiert.
	 */
	private Cell[][] getCellGroups() {
		Cell[][] matrix = playground.getMatrix();
		Cell[][] cellgroups = null;
		if (playground.playsize == 9) {
			cellgroups = new Cell[5][];
			Cell[] cellgroup1 = { matrix[4][4] };
			Cell[] cellgroup2 = { matrix[3][3], matrix[3][4], matrix[3][5], matrix[4][3], matrix[4][5], matrix[5][3],
					matrix[5][4], matrix[5][5] };
			Cell[] cellgroup3 = { matrix[2][2], matrix[2][3], matrix[2][4], matrix[2][5], matrix[2][6], matrix[3][2],
					matrix[3][6], matrix[4][2], matrix[4][6], matrix[5][2], matrix[5][6], matrix[6][2], matrix[6][3],
					matrix[6][4], matrix[6][5], matrix[6][6] };
			Cell[] cellgroup4 = { matrix[1][1], matrix[1][2], matrix[1][3], matrix[1][4], matrix[1][5], matrix[1][6],
					matrix[1][7], matrix[2][1], matrix[2][7], matrix[3][1], matrix[3][7], matrix[4][1], matrix[4][7],
					matrix[5][1], matrix[5][7], matrix[6][1], matrix[6][7], matrix[7][1], matrix[7][2], matrix[7][3],
					matrix[7][4], matrix[7][5], matrix[7][6], matrix[7][7] };
			Cell[] cellgroup5 = { matrix[0][0], matrix[0][1], matrix[0][2], matrix[0][3], matrix[0][4], matrix[0][5],
					matrix[0][6], matrix[0][7], matrix[0][8], matrix[1][0], matrix[1][8], matrix[2][0], matrix[2][8],
					matrix[3][0], matrix[3][8], matrix[4][0], matrix[4][8], matrix[5][0], matrix[5][8], matrix[6][0],
					matrix[6][8], matrix[7][0], matrix[7][8], matrix[8][0], matrix[8][1], matrix[8][2], matrix[8][3],
					matrix[8][4], matrix[8][5], matrix[8][6], matrix[8][7], matrix[8][8] };
			cellgroups[0] = cellgroup1;
			cellgroups[1] = cellgroup2;
			cellgroups[2] = cellgroup3;
			cellgroups[3] = cellgroup4;
			cellgroups[4] = cellgroup5;
		}
		if (playground.playsize == 8) {
			cellgroups = new Cell[4][];
			Cell[] cellgroup1 = { matrix[3][3], matrix[3][4], matrix[4][3], matrix[4][4] };
			Cell[] cellgroup2 = { matrix[2][2], matrix[2][3], matrix[2][4], matrix[2][5], matrix[3][2], matrix[3][5],
					matrix[4][2], matrix[4][5], matrix[5][2], matrix[5][3], matrix[5][4], matrix[5][5] };
			Cell[] cellgroup3 = { matrix[1][1], matrix[1][2], matrix[1][3], matrix[1][4], matrix[1][5], matrix[1][6],
					matrix[2][1], matrix[2][6], matrix[3][1], matrix[3][6], matrix[4][1], matrix[4][6], matrix[5][1],
					matrix[5][6], matrix[6][1], matrix[6][2], matrix[6][3], matrix[6][4], matrix[6][5], matrix[6][6] };
			Cell[] cellgroup4 = { matrix[0][0], matrix[0][1], matrix[0][2], matrix[0][3], matrix[0][4], matrix[0][5],
					matrix[0][6], matrix[0][7], matrix[1][0], matrix[1][7], matrix[2][0], matrix[2][7], matrix[3][0],
					matrix[3][7], matrix[4][0], matrix[4][7], matrix[5][0], matrix[5][7], matrix[6][0], matrix[6][7],
					matrix[7][0], matrix[7][1], matrix[7][2], matrix[7][3], matrix[7][4], matrix[7][5], matrix[7][6],
					matrix[7][7] };
			cellgroups[0] = cellgroup1;
			cellgroups[1] = cellgroup2;
			cellgroups[2] = cellgroup3;
			cellgroups[3] = cellgroup4;
		}
		if (playground.playsize == 6) {
			cellgroups = new Cell[3][];
			Cell[] cellgroup1 = { matrix[2][2], matrix[2][3], matrix[3][2], matrix[3][3] };
			Cell[] cellgroup2 = { matrix[1][1], matrix[1][2], matrix[1][3], matrix[1][4], matrix[2][1], matrix[2][4],
					matrix[3][1], matrix[3][4], matrix[4][1], matrix[4][2], matrix[4][3], matrix[4][4] };
			Cell[] cellgroup3 = { matrix[0][0], matrix[0][1], matrix[0][2], matrix[0][3], matrix[0][4], matrix[0][5],
					matrix[1][0], matrix[1][5], matrix[2][0], matrix[2][5], matrix[3][0], matrix[3][5], matrix[4][0],
					matrix[4][5], matrix[5][0], matrix[5][1], matrix[5][2], matrix[5][3], matrix[5][4], matrix[5][5] };
			cellgroups[0] = cellgroup1;
			cellgroups[1] = cellgroup2;
			cellgroups[2] = cellgroup3;
		}
		if (playground.playsize == 4) {
			cellgroups = new Cell[2][];
			Cell[] cellgroup1 = { matrix[1][1], matrix[1][2], matrix[2][1], matrix[2][2] };
			Cell[] cellgroup2 = { matrix[0][0], matrix[0][1], matrix[0][2], matrix[0][3], matrix[1][0], matrix[1][3],
					matrix[2][0], matrix[2][3], matrix[3][0], matrix[3][1], matrix[3][2], matrix[3][3] };
			cellgroups[0] = cellgroup1;
			cellgroups[1] = cellgroup2;
		}

		return cellgroups;
	}

}
