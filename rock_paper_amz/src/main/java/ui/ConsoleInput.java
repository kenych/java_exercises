package ui;

import java.util.Scanner;

/**
 * Used to get user data from command line ui
 * 
 * @author azimovk
 */
public class ConsoleInput implements UserInput {

	Scanner scanner;

	public ConsoleInput() {
		this.scanner = new Scanner(System.in);
	}

	@Override
	public Object getData() {

		return scanner.next();
	}

}
