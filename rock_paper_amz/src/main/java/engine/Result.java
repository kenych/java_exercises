package engine;

/**
 * Used to same games result
 * 
 * @author azimovk
 */
public class Result {

	/**
	 * If no one wins
	 */
	boolean noWinner;

	/**
	 * if game is over
	 */
	boolean gameOver;

	Player winner;
	Player looser;

	public Result addWinnder(Player winner) {
		this.winner = winner;
		return this;
	}

	public Result addLoser(Player looser) {
		this.looser = looser;
		return this;
	}

	private Result(boolean noWinner) {
		this.noWinner = noWinner;
	}

	private Result() {

	}

	public static Result createWinnerResult() {
		return new Result();
	}

	public static Result createNoWinnerResult() {
		Result result = new Result();
		result.noWinner = true;
		return result;
	}

	public static Result createGameOverResult() {

		Result result = new Result();
		result.gameOver = true;
		return result;

	}

	/**
	 * @return the winner
	 */
	public Player getWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(Player winner) {
		this.winner = winner;
	}

	/**
	 * @return the looser
	 */
	public Player getLooser() {
		return looser;
	}

	/**
	 * @param looser the looser to set
	 */
	public void setLooser(Player looser) {
		this.looser = looser;
	}

	/**
	 * @return the noWinner
	 */
	public boolean isNoWinner() {
		return noWinner;
	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

}
