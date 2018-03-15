package domain.weapons;

import java.util.List;

/**
 * Class for creation of diff weapons
 * 
 * @author azimovk
 */

public abstract class AbstractWeapon implements Weapon {

	/**
	 * What other weapons Weapon can beat
	 */
	protected List<Weapon> beatables;

	/**
	 * 'Weapon name
	 */
	protected String name;

	/**
	 * command to get weapon from menu
	 */
	protected String command;

	/**
	 * How come weapons beats
	 */
	protected String beatAction;

	public void setBeatables(List<Weapon> beatables) {
		this.beatables = beatables;
	}

	public List<Weapon> getBeatables() {
		return beatables;
	}

	/**
	 * Used to make sure weapon is fighting same weaponn
	 */
	@Override
	public boolean equals(Object that) {

		if (that == null) {
			return false;
		} else if (this.getName().equals(((AbstractWeapon)that).getName())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBeating(Weapon otherWeapon) {
		return this.getBeatables().contains(otherWeapon);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * @return the beatAction
	 */
	public String getBeatAction() {
		return beatAction;
	}

	/**
	 * @param beatAction the beatAction to set
	 */
	public void setBeatAction(String beatAction) {
		this.beatAction = beatAction;
	}

}
