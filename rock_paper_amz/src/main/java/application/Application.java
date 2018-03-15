package application;

import ui.AdvertisingDecaratorConsole;
import ui.Console;
import ui.ConsoleInput;
import ui.UserInterface;
import ui.menu.Selectable;
import engine.Game;
import engine.Result;

/**
 * Entry point
 * 
 * @author azimovk
 */
public class Application {

	UserInterface ui;
	GameConfig gameConfig;

	public static void main(String[] args) {

		// try advertising campaign
		UserInterface applicationUI = new AdvertisingDecaratorConsole(new Console(new ConsoleInput()));

		
		//play simple game
		// UserInterface applicationUI = new Console(new ConsoleInput());
		GameConfig gameConfig = new GameConfig();

		Application application = new Application(applicationUI, gameConfig);

		while (!application.run().isGameOver()) {
			continue;
		}

	}

	public Application(UserInterface ui, GameConfig gameConfig) {

		this.ui = ui;
		this.gameConfig = gameConfig;

	}

	public Result run() {

		Selectable gameType = ui.selectOption(gameConfig.getGameMenu().getAllItems());

		Game game = (Game)gameType;
		game.setGameConfig(gameConfig);
		game.setUI(ui);

		Result result = game.play();

		ui.declareGameResult(result);

		return result;

	}
}
