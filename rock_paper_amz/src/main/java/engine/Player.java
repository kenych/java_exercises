package engine;

import domain.weapons.Weapon;

public class Player {

	String name;

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

	Weapon weapon;

	/**
	 * @return the weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * @param weapon the weapon to set
	 */
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * @param name
	 * @param weapon
	 */
	public Player(String userName, Weapon weapon) {
		this.name = userName;
		this.weapon = weapon;
	}

}
