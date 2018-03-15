package domain.gametypes;

import java.util.List;

import ui.menu.Selectable;
import domain.weapons.Weapon;
import engine.AbstractGame;
import engine.Fight;
import engine.Player;
import engine.Result;

/**
 * Name says..
 * 
 * @author azimovk
 */
public class ComputerVsComputerGameType extends AbstractGame {

	@Override
	public String getName() {
		return "ComputerVsComputerGameType";
	}

	@Override
	public String getCommand() {

		return "c";
	}

	@Override
	public Result play() {

		List<Selectable> weapons = gameConfig.getWeaponMenu().getAllItems();
		int size = weapons.size();

		Player player1 = new Player("computer_player1", (Weapon)weapons.get(random.nextInt(size)));
		Player player2 = new Player("computer_player2", (Weapon)weapons.get(random.nextInt(size)));

		return new Fight().of(player1).vs(player2).getResult();
	}

}
