package ui.console;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ui.Console;
import ui.ConsoleInput;
import ui.UserInput;
import ui.menu.Selectable;
import application.GameConfig;
import domain.weapons.AbstractWeapon;
import domain.weapons.ConcreteWeapon;
import domain.weapons.Weapon;

public class ConsoleTest {

	Console console;
	GameConfig gameConfig;

	@Before
	public void setup() {
		console = new Console(new ConsoleInput());
		gameConfig = new GameConfig();
	}

	@Test
	// extension point 4, update actial message
	public void testPromptForWeapon() {
		console.setUserInput(new ConsoleInput());
		assertEquals((console).createPrompt(gameConfig.getWeaponMenu().getAllItems()),
			"Choose item:\nPaper(p), Scissors(sc), Stone(st), KalashnikovMachineGun(k)\n");
	}

	@Test
	public void testPromptForGames() {
		console.setUserInput(new ConsoleInput());
		assertEquals((console).createPrompt(gameConfig.getGameMenu().getAllItems()),
			"Choose item:\nComputerVsComputerGameType(c), ComputerVsHumanGameType(h), ExitGameType(e)\n");
	}

	@Test
	public void testSelectOptionForPaper() {
		final AbstractWeapon paper = new ConcreteWeapon();
		paper.setName("Paper");
		paper.setCommand("p");
		paper.setBeatAction("Covers");

		console.setUserInput(new UserInput() {
			@Override
			public Object getData() {
				return paper.getCommand();
			}
		});
		Selectable selectable = console.selectOption(gameConfig.getWeaponMenu().getAllItems());
		assertEquals(paper, (Weapon)selectable);

	}

	@Test
	public void testValidateAndGet() {
		AbstractWeapon paper = new ConcreteWeapon();
		paper.setName("Paper");
		paper.setCommand("p");
		paper.setBeatAction("Covers");

		AbstractWeapon kalashnikov = new ConcreteWeapon();
		kalashnikov.setName("KalashnikovMachineGun");
		kalashnikov.setCommand("k");
		kalashnikov.setBeatAction("BloodyCrashes");

		assertEquals(kalashnikov, console.validateAndGet("k", gameConfig.getWeaponMenu().getAllItems()));
		assertEquals(paper, console.validateAndGet("p", gameConfig.getWeaponMenu().getAllItems()));
		// etc

	}

}
