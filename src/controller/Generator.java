package controller;

import java.util.ArrayList;
import java.util.Random;

import view.Cell;
import view.PlayGround;
import view.ProgressBar;

/**
 * Generiert Sukoku-Rätsel.
 * 
 * @author Oliver Stapelfeldt
 */
public class Generator {

	private Random random;
	private Solver solver;
	public static final int EMPTY = 0;
	public static final int HARD = 0;
	public static final int MEDIUM = 1;
	public static final int EASY = 2;

	public Generator(Solver solver) {
		this.solver = solver;
		random = new Random();
	}

	/**
	 * Überprüft, ob ein Sudoku-Rätsel eine eindeutige Lösung besitzt. Es werden
	 * Kopien erzeugt, gelöst und dann miteinander verglichen. Bei jeder Kopie
	 * ändert sich die Reihenfolge, in der Zahlen beim Lösen durchprobiert werden.
	 * Weitere Kopien werden nur dann gelöst, falls bereits gelöste Kopien
	 * identische Lösungen besitzen, um die Laufzeit zu entlasten.
	 */
	public boolean hasUniqueSolution(PlayGround playground) {

		int attempts = playground.playsize;
		PlayGround[] playgrounds = new PlayGround[attempts];
		for (int i = 0; i < attempts; i++) {
			playgrounds[i] = playground.createCopy();
		}

		solver.solve(playgrounds[0], 0);

		boolean bool = true;
		int index = 1;
		while (bool == true && index < attempts) {
			solver.solve(playgrounds[index], index);
			if (playgrounds[0].isEqual(playgrounds[index]))
				index++;
			else {
				bool = false;
			}
		}
		return bool;
	}

	private void swap(ArrayList<Cell> cells, int index1, int index2) {

		Cell temp = cells.get(index1);

		cells.add(index1, cells.get(index2));
		cells.remove(cells.get(index1 + 1));
		cells.add(index2, temp);
		cells.remove(cells.get(index2 + 1));
	}

	private void shuffle(ArrayList<Cell> cells) {
		for (int i = 0; i < cells.size(); i++) {
			swap(cells, i, random.nextInt(cells.size()));
		}
	}

	/**
	 * Generiert ein Sudoku-Rätsel
	 */
	public void generatePuzzle(PlayGround playground, int difficulty, ProgressBar progressbar) {

		// Spielfeld wird gefüllt
		solver.solveRandomly(playground);

		// Alle Eingabefelder werden in eine Liste gespeichert und die Reihenfolge
		// geändert.
		ArrayList<Cell> cells = new ArrayList<Cell>();

		for (Cell[] vector : playground.getMatrix()) {
			for (Cell cell : vector) {
				cells.add(cell);

			}
		}
		shuffle(cells);

		// Die generierte Lösung wird in einer Matrix ziwschengespeichert. Damit können
		// soeben gellerte Zellen wieder gefüllt werden.

		int valuematrix[][] = new int[playground.playsize][playground.playsize];
		for (int i = 0; i < playground.playsize; i++)
			for (int j = 0; j < playground.playsize; j++)
				valuematrix[i][j] = playground.getMatrix()[i][j].getValue();

		// Jede Zelle wird geleert. Falls das neu entstandene Rätsel keine eindeutige
		// Lösung mehr besitzt, wird die Zahl wieder zurückgesetzt.
		int progressvalue = 0;
		ArrayList<Cell> emptycells = new ArrayList<Cell>();

		for (Cell cell : cells) {
			cell.setValue(EMPTY);
			if (!hasUniqueSolution(playground)) {
				cell.setValue(valuematrix[cell.getRow()][cell.getColumn()]);
				cell.setFixed(true);
				cell.show();
			} else {
				emptycells.add(cell);
			}

			// Update des Fortschrittsbalkens nach jeder bearbeiteten Zelle
			progressvalue++;
			progressbar.setValue(progressvalue * 100 / cells.size());
		}

		// Je nach Auswahl des Schwierigkeitsgrades werden geleerte Zellen wieder
		// belegt.
		for (int i = emptycells.size() - difficulty * playground.playsize / 4; i < emptycells.size(); i++) {
			Cell cell = emptycells.get(i);
			cell.setValue(valuematrix[cell.getRow()][cell.getColumn()]);
			cell.show();
			cell.setFixed(true);
		}
		progressbar.setValue(0);
	}

	/**
	 * Generiert ein symmetrisches Sudoku-Rätsel
	 */
	public void generateSymmetricPuzzle(PlayGround playground, int difficulty, ProgressBar progressbar) {

		// Spielfeld wird gefüllt
		solver.solveRandomly(playground);

		// Die Hälfe der Eingabefelder werden in eine Liste gespeichert und die
		// Reihenfolge
		// geändert.
		ArrayList<Cell> cells = new ArrayList<Cell>();
		Cell[][] matrix = playground.getMatrix();

		for (int i = 0; i < playground.playsize; i++)
			for (int j = 0; j < playground.playsize / 2; j++)
				cells.add(matrix[j][i]);

		if (playground.playsize == 9)
			for (int i = 0; i <= 4; i++)
				cells.add(matrix[4][i]);

		shuffle(cells);

		// Die generierte Lösung wird in einer Matrix ziwschengespeichert. Damit können
		// soeben gellerte Zellen wieder gefüllt werden.
		int valuematrix[][] = new int[playground.playsize][playground.playsize];
		for (int i = 0; i < playground.playsize; i++)
			for (int j = 0; j < playground.playsize; j++)
				valuematrix[i][j] = matrix[i][j].getValue();

		// Jede Zelle der Spielfeldhälfte wird geleert. Falls das neu entstandene Rätsel
		// keine eindeutige
		// Lösung mehr besitzt, wird die Zahl wieder zurückgesetzt, auch die
		// gegenüberliegende Zelle wird sichtbar gemacht.
		int progressvalue = 0;
		ArrayList<Cell> emptycells = new ArrayList<Cell>();

		for (Cell cell : cells) {
			Cell cellacross = matrix[playground.playsize - cell.getRow() - 1][playground.playsize - cell.getColumn()
					- 1];
			cell.setValue(EMPTY);
			cellacross.setValue(EMPTY);
			if (!hasUniqueSolution(playground)) {
				cell.setValue(valuematrix[cell.getRow()][cell.getColumn()]);
				cellacross.setValue(valuematrix[cellacross.getRow()][cellacross.getColumn()]);
				cell.setFixed(true);
				cellacross.setFixed(true);
				cell.show();
				cellacross.show();
			} else {
				emptycells.add(cell);
			}

			// Update des Fortschrittsbalkens nach jedem bearbeiteten Zellenpaar
			progressvalue++;
			progressbar.setValue(progressvalue * 100 / cells.size());
		}

		// Je nach Auswahl des Schwierigkeitsgrades werden geleerte Zellen wieder
		// belegt.
		for (int i = emptycells.size() - difficulty * playground.playsize / 8; i < emptycells.size(); i++) {
			Cell cell = emptycells.get(i);
			Cell cellacross = matrix[playground.playsize - cell.getRow() - 1][playground.playsize - cell.getColumn()
					- 1];
			cell.setValue(valuematrix[cell.getRow()][cell.getColumn()]);
			cell.show();
			cell.setFixed(true);
			cellacross.setValue(valuematrix[cellacross.getRow()][cellacross.getColumn()]);
			cellacross.show();
			cellacross.setFixed(true);
		}
		progressbar.setValue(0);
	}

}
