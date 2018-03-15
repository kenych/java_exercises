package application;

import org.junit.Before;
import org.junit.Test;

import ui.AdvertisingDecaratorConsole;
import ui.Console;
import ui.ConsoleInput;
import ui.UserInput;

public class ApplicationTest {

	Console console;
	GameConfig gameConfig;

	@Before
	public void setup() {
		console = new Console(new ConsoleInput());
		gameConfig = new GameConfig();
	}

	@Test
	public void testSimulateComputerVsCompGame() {

		GameConfig gameConfig = new GameConfig();

		Application application = new Application(new AdvertisingDecaratorConsole(console), gameConfig);

		console.setUserInput(new UserInput() {
			@Override
			public Object getData() {

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}

				// comp vs comp game
				return "c";
			}
		});

		application.run();
		application.run();
		application.run();
		application.run();
		application.run();
		application.run();

	}

	@Test()
	public void testSimulateComputerVsHumanGane() {

		GameConfig gameConfig = new GameConfig();

		Application application = new Application(console, gameConfig);

		class SimulatorData {
			int timeout;
			int inputTimes = 0;
		}

		final SimulatorData simdata = new SimulatorData();
		simdata.timeout = 500;

		// select comp vs comp game
		console.setUserInput(new UserInput() {
			@Override
			public Object getData() {
				try {
					Thread.sleep(simdata.timeout);
					simdata.inputTimes++;

					if (simdata.inputTimes == 1) {
						// select human game incorrectly
						return "hh";
					} else if (simdata.inputTimes == 2) {
						// select human game
						return "h";
					} else if (simdata.inputTimes == 3) {
						// select paper
						return "p";
					} else if (simdata.inputTimes == 4) {
						// game over
						return "e";
					}

				} catch (InterruptedException e) {
				}
				return null;
			}
		});

		application.run();

	}

}
