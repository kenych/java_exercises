package engine;

import ui.UserInterface;
import ui.menu.Selectable;
import application.GameConfig;

public interface Game extends Selectable {

	void setGameConfig(GameConfig gameCng);

	public void setUI(UserInterface ui);

	/**
	 * Logic of game is here
	 * 
	 * @return
	 */
	Result play();

}
