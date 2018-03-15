package domain.gametypes;

import engine.AbstractGame;
import engine.Result;

/**
 * End of game type
 * 
 * @author azimovk
 */
public class ExitGameType extends AbstractGame {

	@Override
	public String getName() {
		return "ExitGameType";
	}

	@Override
	public String getCommand() {

		return "e";
	}

	@Override
	public Result play() {

		return Result.createGameOverResult();
	}

}
