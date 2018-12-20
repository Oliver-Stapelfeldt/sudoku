package controller;

import view.ViewManager;

public class Main {
	
	public static void main(String[] args) {
		
		ViewManager viewmanager = new ViewManager();
		new ControlManager(viewmanager);
	}
}
