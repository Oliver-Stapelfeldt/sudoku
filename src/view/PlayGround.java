package view;

import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Ein JPanel, welches ein Sudoku-Spielfeld repräsentiert.
 * 
 * @author Oliver Stapelfeldt
 */
public class PlayGround extends JPanel {

	// Deklaration der Objekte
	private static final long serialVersionUID = 1L;
	private Cell matrix[][];
	public int playsize;
	public int boxwidth;
	public int boxlength;
	private Color background;
	public Box[] boxes;

	public PlayGround(int playsize) {

		// Einrichtung des Panels und Initialisierung sämtlicher Objekte
		background = new Color(255, 160, 122);
		setBackground(background);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setFocusable(true);
		requestFocusInWindow();
		this.playsize = playsize;
		matrix = new Cell[playsize][playsize];
		boxwidth = (int) Math.sqrt(playsize);
		boxlength = playsize / boxwidth;

		for (int x = 0; x < playsize; x++)
			for (int y = 0; y < playsize; y++) {

				matrix[x][y] = new Cell();
				matrix[x][y].setLine(x);
				matrix[x][y].setColumn(y);
				matrix[x][y].addMouseListener(new Selector(matrix[x][y]));
			}

		boxes = new Box[playsize];
		setLayout(new GridBagLayout());
		for (int i = 0; i < playsize; i++) {
			boxes[i] = new Box(i);
			add(boxes[i], getBoxConstraints(i % boxlength, i / boxlength));
		}

	}

	public Cell[][] getMatrix() {
		return matrix;
	}

	public Box[] getBoxes() {
		return boxes;
	}

	public int getPlaysize() {
		return playsize;
	}

	/**
	 * Wählt eine Zelle aus, alle anderen Zellen werden abgewählt
	 * 
	 * @param row
	 * @param column
	 */
	public void selectCell(int row, int column) {
		for (int x = 0; x < playsize; x++)
			for (int y = 0; y < playsize; y++)
				matrix[x][y].unselect();
		matrix[row][column].select();
	}

	public Cell getSelectedCell() {
		for (int x = 0; x < playsize; x++)
			for (int y = 0; y < playsize; y++) {
				if (matrix[x][y].isSelected()) {
					return matrix[x][y];
				}
			}
		return null;
	}

	/**
	 * Der Wert aller Zellen wird zurückgesetzt
	 */
	public void clear() {
		for (Cell[] cells : matrix)
			for (Cell cell : cells) {
				cell.setValue(0);
				cell.setText(0);
				cell.setFixed(false);
			}

	}

	/**
	 * Der Wert aller Zellen, die nicht fixiert sind, wird zurückgesetzt
	 */
	public void reset() {
		for (Cell[] cells : matrix)
			for (Cell cell : cells) {
				if (!cell.isFixed()) {
					cell.setValue(0);
					cell.setText(0);
				}
			}
	}

	// Alle Zellen geben ihren Wert aus
	public void show() {
		for (Cell[] cells : matrix)
			for (Cell cell : cells) {
				cell.show();
			}
	}

	/**
	 * Erstellt ein neues Spielfeld und übermittelt die Werte
	 * 
	 * @return PlayGround
	 */
	public PlayGround createCopy() {
		PlayGround playground = new PlayGround(playsize);

		for (int x = 0; x < playsize; x++)
			for (int y = 0; y < playsize; y++) {

				playground.matrix[x][y].setValue(matrix[x][y].getValue());
			}
		return playground;
	}

	private GridBagConstraints getBoxConstraints(int x, int y) {
		return new GridBagConstraints(x, y, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(3, 3, 3, 3), 0, 0);
	}

	/**
	 * Zwei Spielfelder sind genau dann gleich, wenn alle Werte übereinstimmen
	 * 
	 * @param playground
	 * @return boolean
	 */
	public boolean isEqual(Object playground) {

		for (int i = 0; i < playsize; i++) {
			for (int j = 0; j < playsize; j++) {
				if (((PlayGround) playground).getMatrix()[i][j].getValue() != matrix[i][j].getValue())
					return false;
			}
		}
		return true;
	}

	/**
	 * Subklasse von JPanel, repräsentiert eine Box des Spielfeldes
	 * 
	 * @author Oliver Stapelfeldt
	 */
	public class Box extends JPanel {

		private static final long serialVersionUID = 1L;

		private Cell[] cells;

		Box(int index) {
			setBorder(BorderFactory.createLoweredBevelBorder());
			setBackground(background);
			cells = new Cell[playsize];
			setLayout(new GridBagLayout());
			Cell temp;
			int i = 0;
			for (int x = 0; x < boxwidth; x++) {
				for (int y = 0; y < boxlength; y++) {
					temp = getMatrix()[y + (index / boxlength) * boxlength][x + (index * boxwidth % playsize)];
					add(temp, getcellConstraints(x, y));
					cells[i] = temp;
					i++;
				}
			}

		}

		public Cell[] getCells() {
			return cells;
		}

		private GridBagConstraints getcellConstraints(int x, int y) {
			return new GridBagConstraints(x, y, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(1, 1, 1, 1), 0, 0);
		}

	}

	/**
	 * Ein MouseListener für das Spielfeld, damit angewählte Zellen für den Spieler
	 * sichtbar werden
	 * 
	 * @author Oliver Stapelfeldt
	 */
	class Selector implements MouseListener {

		Cell cell;

		Selector(Cell cell) {
			this.cell = cell;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			if (!cell.isSelected())
				cell.setBackground(new Color(180, 220, 250));
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			if (!cell.isSelected())
				cell.setBackground(cell.getBackGround());
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			requestFocusInWindow();
			selectCell(cell.getRow(), cell.getColumn());
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

		}

	}

}
