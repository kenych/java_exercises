package ui;

import java.util.List;

import ui.menu.Selectable;
import engine.Result;

/**
 * Console interface
 * @author azimovk
 *
 */
/**
 * @author azimovk
 */
public class Console implements UserInterface {

	/**
	 * Used to get users input
	 */
	UserInput userInput;

	/**
	 * @param userInput
	 */
	public Console(UserInput userInput) {
		this.userInput = userInput;
	}

	/**
	 * @return the userInput
	 */
	public UserInput getUserInput() {
		return userInput;
	}

	/**
	 * @param userInput the userInput to set
	 */
	public void setUserInput(UserInput userInput) {
		this.userInput = userInput;
	}

	public Selectable validateAndGet(String s, List<Selectable> commands) {

		for (Selectable selectable : commands) {
			if (selectable.getCommand().equalsIgnoreCase(s)) {
				return selectable;
			}
		}

		return null;
	}

	@Override
	public Selectable selectOption(List<Selectable> items) {

		String choice;

		String menuPrompt = createPrompt(items);

		while (true) {

			System.out.println(menuPrompt);

			choice = (String)userInput.getData();

			Selectable item = validateAndGet(choice, items);
			if (item != null) {
				return item;
			} else {
				System.out.println("Invalid command\n");

			}
		}
	}

	@Override
	public String createPrompt(List<Selectable> items) {
		StringBuilder menuPrompt = new StringBuilder("Choose item:\n");
		for (int i = 0; i < items.size(); i++) {

			Selectable selectable = items.get(i);
			menuPrompt.append(selectable.getName()).append("(").append(selectable.getCommand()).append(")");
			if (i < items.size() - 1) {
				menuPrompt.append(", ");
			}
		}

		menuPrompt.append("\n");
		return menuPrompt.toString();
	}

	@Override
	public void declareGameResult(Result result) {

		if (result.isGameOver()) {
			System.out.println("game over\n\n");
		} else if (result.isNoWinner()) {
			System.out.println("no winner\n\n");

		} else {
			System.out.println(new StringBuilder().append("winner is ").append(result.getWinner().getName())
				.append(" because ").append(result.getWinner().getName()).append("'s ")
				.append(result.getWinner().getWeapon().getName()).append(" ")
				.append(result.getWinner().getWeapon().getBeatAction()).append(" ")
				.append(result.getLooser().getName()).append("'s ").append(result.getLooser().getWeapon().getName())
				.append("\n\n"));

		}

	}

}
