package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ui.menu.Menu;
import ui.menu.Selectable;
import domain.gametypes.ComputerVsComputerGameType;
import domain.gametypes.ComputerVsHumanGameType;
import domain.gametypes.ExitGameType;
import domain.weapons.AbstractWeapon;
import domain.weapons.ConcreteWeapon;
import domain.weapons.Weapon;

public class GameConfig {

	Menu gameMenu;

	/**
	 * @param gameMenu the gameMenu to set
	 */
	public void setGameMenu(Menu gameMenu) {
		this.gameMenu = gameMenu;
	}

	/**
	 * @param weaponMenu the weaponMenu to set
	 */
	public void setWeaponMenu(Menu weaponMenu) {
		this.weaponMenu = weaponMenu;
	}

	Menu weaponMenu;

	/**
	 * @return the gameMenu
	 */
	public Menu getGameMenu() {
		return gameMenu;
	}

	/**
	 * @return the weaponMenu
	 */
	public Menu getWeaponMenu() {
		return weaponMenu;
	}

	/**
	 * ideally config could be externalized, through spring beans or property file, for the sake of example we just put
	 * them here. There are extension points 1-4 where new weapond initialized and tested.
	 */
	public GameConfig() {

		configureGamesMenu();
		configureWeaponsMenu();
	}

	void configureWeaponsMenu() {

		// create weapons
		AbstractWeapon scissors = createNewWeapon("Scissors", "sc", "Cuts");
		AbstractWeapon paper = createNewWeapon("Paper", "p", "Covers");
		AbstractWeapon stone = createNewWeapon("Stone", "st", "Destroys");

		// extension point1, new weapon
		AbstractWeapon kalashnikov = createNewWeapon("KalashnikovMachineGun", "k", "BloodyCrashes");

		// set weapon beating rules
		scissors.setBeatables(new ArrayList<Weapon>(Arrays.asList(paper)));
		stone.setBeatables(new ArrayList<Weapon>(Arrays.asList(scissors)));
		paper.setBeatables(new ArrayList<Weapon>(Arrays.asList(stone)));

		// extension point2
		kalashnikov.setBeatables(new ArrayList<Weapon>(Arrays.asList(stone, scissors, paper)));

		// add weapons to menu
		List<Selectable> weapons = new ArrayList<Selectable>();
		weapons.add(paper);
		weapons.add(scissors);
		weapons.add(stone);

		// extension point3
		weapons.add(kalashnikov);

		weaponMenu = new Menu(weapons);
	}

	private AbstractWeapon createNewWeapon(String name, String command, String action) {
		AbstractWeapon weapon = new ConcreteWeapon();
		weapon.setName(name);
		weapon.setCommand(command);
		weapon.setBeatAction(action);
		return weapon;
	}

	void configureGamesMenu() {
		// create games
		Selectable compVsComp = new ComputerVsComputerGameType();
		Selectable compVsHuman = new ComputerVsHumanGameType();
		Selectable exitGame = new ExitGameType();

		// add games to menu
		ArrayList<Selectable> games = new ArrayList<Selectable>();
		games.add(compVsComp);
		games.add(compVsHuman);
		games.add(exitGame);
		gameMenu = new Menu(games);
	}

}
