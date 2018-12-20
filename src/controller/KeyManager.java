package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.Cell;
import view.PlayGround;

/**
 * Ein KeyListener zur Einrichtung der Steuerung
 * 
 * @author Olli
 *
 */
public class KeyManager implements KeyListener {

	PlayGround playground;
	Solver solver;

	public KeyManager(PlayGround playground, Solver solver) {
		this.playground = playground;
		this.solver = solver;
	}

	private int positiveModulo(int value, int modulo) {
		if (value >= 0) {
			return value % modulo;
		} else {
			return value + modulo;
		}
	}

	/**
	 * Überprüft, ob alle Zellen die Regeln des Sudoku befolgen
	 */
	private boolean allCellsHaveValidValue() {

		Cell[][] matrix = playground.getMatrix();
		for (Cell[] vector : matrix)
			for (Cell cell : vector) {
				int value = cell.getValue();
				cell.setValue(0);
				if (!solver.isValidValue(playground, cell, value)) {
					cell.setValue(value);
					return false;
				}
				cell.setValue(value);
			}
		return true;
	}

	/**
	 * Setzt eine Zahl in eine Zelle
	 */
	private void setNumber(int number, Cell cell) {
		if (cell.isFixed() == false && number <= playground.playsize) {
			playground.getSelectedCell().setValue(number);
			playground.getSelectedCell().setText(number);
			if (allCellsHaveValidValue())
				new WinEffect(playground).start();
		}
	}

	private void moveUp(Cell cell) {
		playground.selectCell(positiveModulo(cell.getRow() - 1, playground.playsize), cell.getColumn());
	}

	private void moveDown(Cell cell) {
		playground.selectCell((cell.getRow() + 1) % playground.playsize, cell.getColumn());
	}

	private void moveLeft(Cell cell) {
		playground.selectCell(cell.getRow(), positiveModulo(cell.getColumn() - 1, playground.playsize));
	}

	private void moveRight(Cell cell) {
		playground.selectCell(cell.getRow(), (cell.getColumn() + 1) % playground.playsize);
	}

	private void deleteCell(Cell cell) {
		if (cell.isFixed() == false) {
			playground.getSelectedCell().setValue(0);
			playground.getSelectedCell().setText(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent event) {

		int keyCode = event.getKeyCode();
		Cell cell = playground.getSelectedCell();

		if (cell != null) {
			switch (keyCode) {

			case KeyEvent.VK_UP:
				moveUp(cell);
				break;
			case KeyEvent.VK_DOWN:
				moveDown(cell);
				break;
			case KeyEvent.VK_LEFT:
				moveLeft(cell);
				break;
			case KeyEvent.VK_RIGHT:
				moveRight(cell);
				break;
			case KeyEvent.VK_W:
				moveUp(cell);
				break;
			case KeyEvent.VK_S:
				moveDown(cell);
				break;
			case KeyEvent.VK_A:
				moveLeft(cell);
				break;
			case KeyEvent.VK_D:
				moveRight(cell);
				break;
			case KeyEvent.VK_DELETE:
				deleteCell(cell);
				break;
			case KeyEvent.VK_BACK_SPACE:
				deleteCell(cell);
				break;
			}

			int keyChar = event.getKeyChar();

			switch (keyChar) {
			case KeyEvent.VK_1:
				setNumber(1, cell);
				break;
			case KeyEvent.VK_2:
				setNumber(2, cell);
				break;
			case KeyEvent.VK_3:
				setNumber(3, cell);
				break;
			case KeyEvent.VK_4:
				setNumber(4, cell);
				break;
			case KeyEvent.VK_5:
				setNumber(5, cell);
				break;
			case KeyEvent.VK_6:
				setNumber(6, cell);
				break;
			case KeyEvent.VK_7:
				setNumber(7, cell);
				break;
			case KeyEvent.VK_8:
				setNumber(8, cell);
				break;
			case KeyEvent.VK_9:
				setNumber(9, cell);
				break;
			case KeyEvent.VK_0:
				deleteCell(cell);
				break;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent event) {

	}

}
