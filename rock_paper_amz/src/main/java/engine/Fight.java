package engine;

public class Fight {

	Player player1;
	Player player2;

	public Fight of(Player player1) {
		this.player1 = player1;

		return this;
	}

	public Fight vs(Player player2) {
		this.player2 = player2;

		return this;

	}

	/**
	 * Game winning rule
	 * 
	 * @return
	 */
	public Result getResult() {

		Result result = null;

		if (player1.getWeapon().equals(player2.getWeapon())) {
			result = Result.createNoWinnerResult();
		} else {
			if (player1.getWeapon().isBeating(player2.getWeapon())) {
				result = Result.createWinnerResult().addWinnder(player1).addLoser(player2);
			} else if (player2.getWeapon().isBeating(player1.getWeapon())) {
				result = Result.createWinnerResult().addWinnder(player2).addLoser(player1);
			} else {
				result = Result.createNoWinnerResult();
			}

		}

		return result;

	}

}
