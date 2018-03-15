package domain.weapons;

import java.util.List;

import ui.menu.Selectable;

/**
 * Main class for weapons
 * 
 * @author azimovk
 */
public interface Weapon extends Selectable {

	public void setBeatables(List<Weapon> beatables);

	/**
	 * Identifies if this weapons is beating the other
	 * 
	 * @param otherWeapon
	 * @return
	 */
	public boolean isBeating(Weapon otherWeapon);

	/**
	 * Identifies if weapons are eq
	 * 
	 * @param that
	 * @return
	 */
	public boolean equals(Object that);

	/**
	 * How come weapon beats
	 * 
	 * @return
	 */
	public String getBeatAction();

	/**
	 * What weapons beats
	 * 
	 * @return
	 */
	public List<Weapon> getBeatables();

}
