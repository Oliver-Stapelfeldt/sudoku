package controller;



import view.ViewManager;

/**
 * Erzeugt das Suduko-Puzzle.
 * 
 * @author Oliver Stapelfeldt
 *
 */
public class GenerateThread extends Thread {

	// Attribute	
	public static boolean generating;
	private Generator generator;
	private ViewManager viewmanager;
	
	// Konstruktor
	GenerateThread(ViewManager viewmanager,Generator generator) {
		this.viewmanager = viewmanager;
		this.generator = generator;
	}

	/**
	 *  Erzeugt das Puzzle.
	 */
	public void run() {
		viewmanager.gamepanel.progressbar.setVisible(true);
		disableButtons();
		generating = true;
		if (viewmanager.menubar.symmetries.get(1).isSelected())
		generator.generatePuzzle(viewmanager.gamepanel.playground, difficultyIndextoValue(), viewmanager.gamepanel.progressbar);
		else generator.generateSymmetricPuzzle(viewmanager.gamepanel.playground, difficultyIndextoValue(), viewmanager.gamepanel.progressbar);
		generating = false;
		enableButtons();
		viewmanager.gamepanel.progressbar.setVisible(false);
	}
	
	private void disableButtons() {
		viewmanager.buttonpanel.newgamebutton.setEnabled(false);
		viewmanager.buttonpanel.resetbutton.setEnabled(false);
		viewmanager.buttonpanel.solvebutton.setEnabled(false);
		viewmanager.optionspanel.choosedifficulty.setEnabled(false);
		viewmanager.optionspanel.choosesize.setEnabled(false);
		viewmanager.menubar.gamemenu.setEnabled(false);
	}
	
	private void enableButtons() {
		viewmanager.buttonpanel.newgamebutton.setEnabled(true);
		viewmanager.buttonpanel.resetbutton.setEnabled(true);
		viewmanager.buttonpanel.solvebutton.setEnabled(true);
		viewmanager.optionspanel.choosedifficulty.setEnabled(true);
		viewmanager.optionspanel.choosesize.setEnabled(true);
		viewmanager.menubar.gamemenu.setEnabled(true);
	}
	
	private int difficultyIndextoValue() {
		int index = viewmanager.optionspanel.choosedifficulty.getSelectedIndex();
		int difficulty=0;
		switch(index) {
		case(0): difficulty = Generator.EASY; break; 
		case(1): difficulty = Generator.MEDIUM; break;
		case(2): difficulty = Generator.HARD;
		}
		return difficulty;
	}
}
