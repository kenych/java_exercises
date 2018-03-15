package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ui.menu.Selectable;
import engine.Result;

public class AdvertisingDecaratorConsole implements UserInterface {

	class Runner extends Thread {

		@Override
		public void run() {
			while (!gameResult.isGameOver()) {
				try {
					currentAdvert = allAdvert.get(random.nextInt(allAdvert.size()));
					sleep(random.nextInt(10) * 1000);

				} catch (InterruptedException e) {
				}
			}
		}
	}

	UserInterface decorableConsole;

	Random random = new Random();

	Runner runner;

	Result gameResult = Result.createWinnerResult();

	enum Subject {
		SELECT_OPTION, VALIDATE_INPUT, WINNER, NO_WINNER;
	}

	Map<Subject, String> currentAdvert = new HashMap<Subject, String>();
	List<Map<Subject, String>> allAdvert = new ArrayList<Map<Subject, String>>();

	public AdvertisingDecaratorConsole(UserInterface decorableConsole) {
		Map<Subject, String> temp = new HashMap<Subject, String>();
		temp.put(Subject.SELECT_OPTION, "............selec blood smth..........");
		temp.put(Subject.WINNER, "......yeeeah!.............");
		temp.put(Subject.VALIDATE_INPUT, ".............is it really right choice?...");
		temp.put(Subject.NO_WINNER, ".............wasn't it just a waste of time?...");
		allAdvert.add(temp);

		temp = new HashMap<Subject, String>();
		temp.put(Subject.SELECT_OPTION, "............selec blood smth2222222222......");
		temp.put(Subject.WINNER, "......yeeeah!..222222222222.....");
		temp.put(Subject.VALIDATE_INPUT, ".............is it really right choice?.222222222222222.");
		temp.put(Subject.NO_WINNER, ".............wasn't it just a waste of time?222222222222222222...");
		allAdvert.add(temp);

		temp = new HashMap<Subject, String>();
		temp.put(Subject.SELECT_OPTION, "............selec blood smth333333333333333......");
		temp.put(Subject.WINNER, "......yeeeah!..3333333333333333.....");
		temp.put(Subject.VALIDATE_INPUT, ".............is it really right choice?.3333333333.");
		temp.put(Subject.NO_WINNER, ".............wasn't it just a waste of time?333333333333333333333...");
		allAdvert.add(temp);

		currentAdvert = allAdvert.get(random.nextInt(allAdvert.size()));

		this.decorableConsole = decorableConsole;

		runner = new Runner();

		System.out.println("This game has an advertising!");

		runner.start();
	}

	@Override
	public Selectable selectOption(List<Selectable> items) {

		showAdvertisement(Subject.SELECT_OPTION);

		return decorableConsole.selectOption(items);
	}

	@Override
	public String createPrompt(List<Selectable> items) {
		return decorableConsole.createPrompt(items);
	}

	@Override
	public void declareGameResult(Result gameResult) {
		this.gameResult = gameResult;

		if (!gameResult.isGameOver()) {

			if (gameResult.isNoWinner()) {

				showAdvertisement(Subject.NO_WINNER);
			} else {

				showAdvertisement(Subject.WINNER);
			}

		}

		decorableConsole.declareGameResult(gameResult);

	}

	

	void showAdvertisement(Subject subject) {
		if (currentAdvert != null) {

			System.out.println("\n\nadvert:\n " + currentAdvert.get(subject) + "\n\n");
			currentAdvert = null;
		}
	}

}
