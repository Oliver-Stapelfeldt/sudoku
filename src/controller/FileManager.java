package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;

import view.Cell;
import view.PlayGround;
import view.ViewManager;

/**
 * Speichert sämtliche Einstellungen, sowie den Status der Spielfeldes.
 * 
 * @author Oliver Stapelfeldt
 */
public class FileManager {

	// Deklaration der Objekte
	Path datapath;
	WindowCloser windowcloser;
	ViewManager viewmanager;
	LanguageManager languagemanager;

	public FileManager(ViewManager viewmanager, LanguageManager languagemanager) {

		// Initialisierung der Objekte
		this.viewmanager = viewmanager;
		this.languagemanager = languagemanager;
		datapath = Paths.get(System.getProperty("user.home")).toAbsolutePath().resolve("Sudoku data").normalize();
	}

	/**
	 * Speichert den Status des Spielfeldes in die playground.txt
	 */
	public void savePlayGround(PlayGround playground) {
		try {
			if (!Files.isDirectory(datapath))
				Files.createDirectory(datapath);
			Path playgroundfile = datapath.resolve("playground.txt");
			if (!Files.exists(playgroundfile))
				Files.createFile(playgroundfile);
			FileWriter writer = new FileWriter(playgroundfile.toString());
			PrintWriter printer = new PrintWriter(writer);

			printer.println(playground.playsize);
			for (Cell[] cells : playground.getMatrix())
				for (Cell cell : cells) {
					printer.println("" + cell.getValue() + cell.isFixed());
				}
			printer.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Lädt den Status des Spielfeldes aus der playground.txt
	 */
	public PlayGround loadPlayGround() {
		try {
			if (!Files.isDirectory(datapath))
				Files.createDirectory(datapath);
			Path playgroundfile = datapath.resolve("playground.txt");
			if (!Files.exists(playgroundfile)) {
				Files.createFile(playgroundfile);
			}
			FileReader fr = new FileReader(playgroundfile.toString());
			BufferedReader br = new BufferedReader(fr);
			PlayGround playground = new PlayGround(Integer.parseInt(br.readLine()));
			String temp = "";
			for (Cell[] cells : playground.getMatrix())
				for (Cell cell : cells) {
					temp = br.readLine();
					cell.setValue(Integer.parseInt(temp.substring(0, 1)));
					cell.setFixed(Boolean.parseBoolean(temp.substring(1, temp.length())));
					cell.show();
				}
			br.close();
			return playground;

		} catch (Exception e) {
			System.out.println("playground.txt konnte nicht geladen werden.");
			return new PlayGround((Integer) viewmanager.optionspanel.choosesize.getSelectedItem());
		}
	}

	/**
	 * Löscht die playground.txt
	 */
	public void deletePlayGround() {
		try {
			if (!Files.isDirectory(datapath))
				Files.createDirectory(datapath);
			Path playgroundfile = datapath.resolve("playground.txt");
			if (Files.exists(playgroundfile)) {
				Files.delete(playgroundfile);
			}

		} catch (Exception e) {
			System.out.println("Löschen von playground.txt fehlgeschlagen");
		}
	}

	/**
	 * Speichert sämtliche ausgewählte Einstellungen in die config.txt.
	 */
	public void setConfig() {
		try {
			if (!Files.isDirectory(datapath))
				Files.createDirectory(datapath);
			Path config = datapath.resolve("config.txt");
			if (!Files.exists(config))
				Files.createFile(config);
			FileWriter writer = new FileWriter(config.toString());
			PrintWriter printer = new PrintWriter(writer);
			printer.println("size=" + viewmanager.optionspanel.choosesize.getSelectedIndex());
			printer.println("difficulty=" + viewmanager.optionspanel.choosedifficulty.getSelectedIndex());
			printer.println("language=" + languagemanager.getSelectedLanguage());
			printer.println("symmetry=" + viewmanager.menubar.getSelectedSymmetry());
			printer.println("cellsize=" + viewmanager.menubar.getSelectedCellSize());
			printer.close();
		} catch (Exception e) {
			System.out.println("config.txt konnte nicht gespeichert werden.");
		}
	}

	/**
	 * Lädt ausgewählte Einstellungen aus der config.txt und setzt Diese.
	 */
	public void loadConfig() {
		try {
			if (!Files.isDirectory(datapath))
				Files.createDirectory(datapath);
			Path config = datapath.resolve("config.txt");
			if (!Files.exists(config)) {
				Files.createFile(config);
				FileWriter writer = new FileWriter(config.toString());
				PrintWriter printer = new PrintWriter(writer);
				printer.println("size=3");
				printer.println("difficulty=2");
				printer.println("language=de");
				printer.println("symmetry=1");
				printer.println("cellsize=0");
				printer.close();
			}
			FileReader fr = new FileReader(config.toString());
			BufferedReader br = new BufferedReader(fr);
			String[] temparr;
			String temp;
			Properties prop = new Properties();
			while ((temp = br.readLine()) != null) {
				temparr = temp.split("=");
				prop.setProperty(temparr[0], temparr[1]);
			}
			br.close();
			viewmanager.optionspanel.choosesize.setSelectedIndex(Integer.parseInt(prop.getProperty("size")));
			viewmanager.optionspanel.choosedifficulty
					.setSelectedIndex(Integer.parseInt(prop.getProperty("difficulty")));
			languagemanager.selectLanguage(new Locale(prop.getProperty("language")));
			viewmanager.menubar.symmetries.get(Integer.parseInt(prop.getProperty("symmetry"))).setSelected(true);
			viewmanager.menubar.cellsizes.get(Integer.parseInt(prop.getProperty("cellsize"))).setSelected(true);
		} catch (Exception e) {
			System.out.println("config.txt konnte nicht geladen werden.");
		}
	}

	public int languageToIndex(String lang) {
		int index = 0;
		switch (lang) {
		case ("de"):
			index = 0;
			break;
		case ("en"):
			index = 1;
			break;
		}
		return index;
	}

	/**
	 * Beim Beenden des Programmes, wird diese Methode ausgeführt. Alle Einstellungen werden gespeichert.
	 */
	public void quit(ViewManager viewmanager) {
		savePlayGround(viewmanager.gamepanel.playground);
		setConfig();
		if (GenerateThread.generating == true)
			deletePlayGround();
		System.exit(0);
	}

	/**
	 * WindowListener zum speichern aller Einstellungen
	 */
	class WindowCloser extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent arg0) {
			quit(viewmanager);
		}
	}

}
