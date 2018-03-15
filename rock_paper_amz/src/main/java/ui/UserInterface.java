package ui;

import java.util.List;

import ui.menu.Selectable;
import engine.Result;

/**
 * Interface to be implemented by GUI or console interfaces
 * 
 * @author azimovk
 */
public interface UserInterface {

	/**
	 * Lets user to select menu item
	 * 
	 * @param items all items
	 * @return selected item
	 */
	Selectable selectOption(List<Selectable> items);

	/**
	 * Show message to select items
	 * 
	 * @param items
	 * @return
	 */
	String createPrompt(List<Selectable> items);

	/**
	 * show game results
	 * 
	 * @param gameResult
	 */
	void declareGameResult(Result gameResult);

	

}
