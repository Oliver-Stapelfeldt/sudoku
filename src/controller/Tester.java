package controller;

import java.util.ArrayList;

import view.PlayGround;
import view.ProgressBar;

/**
 * Eine Testklasse, um zu überprüfen, ob erzeugte Rätsel eine eindeutige Lösung liefern.
 * Im Programm selbst hat diese Klasse keine Funktion.
 * 
 * @author Oliver Stapelfeldt
 */
public class Tester {
	Solver solver = new Solver();
	Generator generator = new Generator(solver);
	ArrayList<Boolean> results = new ArrayList<Boolean>();
	
	public void generateSamples(int attempts) {
		for (int i = 0; i < attempts; i++ ) {
			PlayGround playground = new PlayGround(9);
			generator.generatePuzzle(playground,0, new ProgressBar(0,100));
			results.add(generator.hasUniqueSolution(playground));
			System.out.println(countFalses());
			
		}
		System.out.println("done");
	}
	
	public int countFalses() {
		int counter = 0;
		for(boolean bool : results) {
			if(bool == false)
				counter++;
		}
		return counter;
	}

}
