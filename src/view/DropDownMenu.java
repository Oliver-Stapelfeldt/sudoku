package view;

import javax.swing.JComboBox;

/**
 * Eine Dropdownmenü
 * 
 * @author Oliver Stapelfeldt
 */
public class DropDownMenu extends JComboBox<Object> {

	private static final long serialVersionUID = 1L;

	public DropDownMenu(Object[] obj) {
		super(obj);
		this.setFocusable(false);
	}
}
