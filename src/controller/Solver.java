package controller;

import java.util.ArrayList;
import java.util.Random;

import view.Cell;
import view.PlayGround;

/**
 * L�st Sudoku-R�tsel mit dem BackTracking-Verfahren
 */
public class Solver {

	Random random;
	public static final int EMPTY = 0;

	public Solver() {
		random = new Random();
	}

	private int getBoxIndex(PlayGround playground, Cell cell) {
		return cell.getColumn() / playground.boxwidth + (cell.getRow() / playground.boxlength) * playground.boxlength;
	}

	private boolean isInBox(PlayGround playground, Cell cell, int value) {
		for (Cell localcell : playground.boxes[getBoxIndex(playground, cell)].getCells()) {
			if (localcell.getValue() == value)
				return true;
		}
		return false;
	}

	private boolean isInColumn(PlayGround playground, Cell cell, int value) {
		for (int i = 0; i < playground.getPlaysize(); i++) {
			if (playground.getMatrix()[i][cell.getColumn()].getValue() == value)
				return true;
		}
		return false;
	}

	private boolean isInRow(PlayGround playground, Cell cell, int value) {
		for (int i = 0; i < playground.getPlaysize(); i++) {
			if (playground.getMatrix()[cell.getRow()][i].getValue() == value)
				return true;
		}
		return false;
	}

	public boolean isValidValue(PlayGround playground, Cell cell, int value) {
		return !isInBox(playground, cell, value) && !isInRow(playground, cell, value)
				&& !isInColumn(playground, cell, value);
	}

	private void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	private void shuffle(int[] array) {
		for (int i = 0; i < array.length; i++) {
			swap(array, i, random.nextInt(array.length));
		}
	}

	private void permutate(int[] array, int times) {
		for (int j = 0; j < times; j++)
			for (int i = 0; i < array.length - 1; i++) {
				swap(array, i, i + 1);
			}
	}

	/**
	 * L�st ein Sudoku-R�tsel mit dem Backtracking-Verfahren. Die Reihenfolge, in
	 * der Zahlen ausprobiert werden �ndert sich bei jedem rekursiven Aufruf
	 * zuf�llig. Eignet sich gut, um zuf�llige L�sungen ,beim Generieren von
	 * R�steln, zu erstellen.
	 */
	public boolean solveRandomly(PlayGround playground) {

		// Alle freien Felder werden in eine Liste gespeichert
		ArrayList<Cell> cells = new ArrayList<>();

		for (int i = 0; i < playground.playsize; i++) {
			for (int j = 0; j < playground.playsize; j++) {
				if (!playground.getMatrix()[i][j].isFixed())
					cells.add(playground.getMatrix()[i][j]);
			}
		}

		// L�sen des Spielfeldes
		int[] order = new int[playground.getPlaysize()];
		for (int i = 0; i < playground.getPlaysize(); i++) {
			order[i] = i + 1;
		}
		shuffle(order);
		for (Cell cell : cells) {
			if (cell.getValue() == EMPTY) {
				for (int number : order) {
					if (isValidValue(playground, cell, number)) {
						cell.setValue(number);
						if (solveRandomly(playground)) {
							return true;
						} else {
							cell.setValue(EMPTY);
						}
					}
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * L�st ein Sudoku-R�tsel mit dem Backtracking-Verfahren. Die Reihenfolge, in
	 * der Zahlen ausprobiert werden, ist struktuiert und kann �ber einen Parameter
	 * modifiziert werden. �ber den Parameter wird gesteuert, welche Zahl zu erst
	 * ausprobiert wird. Wird ein R�tsel mehrmals gel�st und jede Zahl einmal als
	 * "ersten Versuch" verwendet, so hat das R�tsel meiner Ansicht nach nur eine
	 * eindeutige L�sung, falls alle L�sungen identisch sind.
	 */
	public boolean solve(PlayGround playground, int permutatetimes) {

		// Alle freien Felder werden in eine Liste gespeichert
		ArrayList<Cell> cells = new ArrayList<>();

		for (int i = 0; i < playground.playsize; i++) {
			for (int j = 0; j < playground.playsize; j++) {
				if (!playground.getMatrix()[i][j].isFixed())
					cells.add(playground.getMatrix()[i][j]);
			}
		}

		// L�sen des Spielfeldes
		int[] order = new int[playground.getPlaysize()];
		for (int i = 0; i < playground.getPlaysize(); i++) {
			order[i] = i + 1;
		}
		permutate(order, permutatetimes);
		for (Cell cell : cells) {
			if (cell.getValue() == EMPTY) {
				for (int number : order) {
					if (isValidValue(playground, cell, number)) {
						cell.setValue(number);
						if (solve(playground, permutatetimes)) {
							return true;
						} else {
							cell.setValue(EMPTY);
						}
					}
				}
				return false;
			}
		}
		return true;
	}

}
