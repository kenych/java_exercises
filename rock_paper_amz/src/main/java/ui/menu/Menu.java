package ui.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Menu for user interface
 * 
 * @author azimovk
 */
public class Menu {

	/**
	 * menu items
	 */
	protected List<Selectable> items = new ArrayList<Selectable>();

	public Menu(List<Selectable> items) {
		this.items = items;
	}

	/**
	 * Find menu item by command
	 * 
	 * @param command
	 * @return item
	 */
	public Selectable getItemByCommand(String command) {

		for (Selectable item : items) {
			if (item.getCommand().equals(command)) {
				return item;
			}
		}

		return null;
	}

	/**
	 * Get all items
	 * 
	 * @return
	 */
	public List<Selectable> getAllItems() {

		return Collections.unmodifiableList(items);

	}

}
