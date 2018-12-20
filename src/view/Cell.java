package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ein Panel, welches eine Zelle eines Spielfelder repräsentiert
 * 
 * @author Oliver Stapelfeldt
 */
public class Cell extends JPanel {

	private static final long serialVersionUID = 1L;

	// Deklaration der Objekte
	private int value;
	private boolean selected;
	private JLabel text;
	private Color background;
	private int row;
	private int column;
	private boolean fixed;
	private Font fixedfont;
	private Font writefont;

	public Cell() {

		// Erzeugung einiger Objekte und Einrichtung des Panels
		setLayout(new GridBagLayout());
		writefont = new Font(getWriteFont(), Font.BOLD, 20);
		fixedfont = new Font("Arial", Font.BOLD, 20);
		setPreferredSize(new Dimension(33, 33));
		setMinimumSize(new Dimension(33, 33));
		background = new Color(240, 240, 240);
		setBackground(background);
		setBorder(BorderFactory.createRaisedBevelBorder());
		text = new JLabel();
		text.setFont(writefont);
		add(text);
		fixed = false;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getBackGround() {
		return background;
	}

	public int getText() {
		return Integer.parseInt(text.getText());
	}

	public void setText(int text) {
		if (text == 0) {
			this.text.setText("");
		} else {
			this.text.setText(text + "");
		}
	}

	public int getRow() {
		return row;
	}

	public void setLine(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
		if (fixed)
			text.setFont(fixedfont);
		else
			text.setFont(writefont);
	}

	public void select() {
		setBackground(new Color(135, 206, 250));
		setSelected(true);
	}

	public void unselect() {
		setBackground(background);
		setSelected(false);
	}

	public void show() {
		setText(getValue());
	}

	@Override
	public String toString() {
		return getValue() + "";
	}

	private String getWriteFont() {
		String[] favFonts = { "Ink Free", "Indie Flower", "Bradley Hand ITC", "Brush Script MT", "Segoe Script" };
		String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

		for (String font : favFonts) {
			if (Arrays.asList(fontList).contains(font))
				return font;
		}
		return null;
	}

}
