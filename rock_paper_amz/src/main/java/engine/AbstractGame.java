package engine;

import java.util.Random;

import ui.UserInterface;
import application.GameConfig;

/**
 * 
 * Class based on which all games types created
 * @author azimovk
 *
 */
/**
 * @author azimovk
 */
public abstract class AbstractGame implements Game {

	/**
	 * used for computer players selection
	 */
	protected Random random = new Random();

	/**
	 * Menu and weapons are stored here
	 */
	protected GameConfig gameConfig;

	/**
	 * current UI
	 */
	protected UserInterface ui;

	@Override
	public void setGameConfig(GameConfig gameCng) {
		this.gameConfig = gameCng;
	}

	@Override
	public void setUI(UserInterface ui) {
		this.ui = ui;
	}

}
