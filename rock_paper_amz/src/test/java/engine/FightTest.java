package engine;

import static org.junit.Assert.assertEquals;

import java.util.Random;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import ui.Console;
import ui.ConsoleInput;
import application.GameConfig;
import domain.weapons.Weapon;

public class FightTest {

	Scanner scanner;
	Console console;
	GameConfig gameConfig;
	protected Random random = new Random();

	@Before
	public void setup() {

		console = new Console(new ConsoleInput());
		gameConfig = new GameConfig();
	}

	@Test
	public void testFightOfKalashnikov() {

		Player player1 = new Player("computer_player1", (Weapon)gameConfig.getWeaponMenu().getItemByCommand("k"));
		Player player2 = new Player("computer_player2", (Weapon)gameConfig.getWeaponMenu().getItemByCommand("p"));

		Result result = new Fight().of(player1).vs(player2).getResult();

		assertEquals(player1, result.getWinner());
		assertEquals(player2, result.getLooser());
	}

	// similarly might have tests of all kinda fights

}
