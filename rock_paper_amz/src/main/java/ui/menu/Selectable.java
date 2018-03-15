package ui.menu;

/**
 * Used in menu to identify abstract selectable item
 * 
 * @author azimovk
 */
public interface Selectable {

	/**
	 * Get name of selectable Object
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Get command for selectable
	 * 
	 * @return
	 */
	String getCommand();

}
