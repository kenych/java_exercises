package domain.gametypes;

import java.util.List;

import ui.menu.Selectable;
import domain.weapons.Weapon;
import engine.AbstractGame;
import engine.Fight;
import engine.Player;
import engine.Result;

/**
 * Name says
 * 
 * @author azimovk
 */
public class ComputerVsHumanGameType extends AbstractGame {

	@Override
	public String getName() {
		return "ComputerVsHumanGameType";
	}

	@Override
	public String getCommand() {

		return "h";
	}

	@Override
	public Result play() {

		List<Selectable> weapons = gameConfig.getWeaponMenu().getAllItems();
		int size = weapons.size();

		Player player1 = new Player("user", (Weapon)ui.selectOption(weapons));
		Player player2 = new Player("computer_player", (Weapon)weapons.get(random.nextInt(size)));

		return new Fight().of(player1).vs(player2).getResult();
	}

}
