package ui.console;

import java.util.List;
import java.util.Scanner;
/**
 * Be very careful! This class has System.in a Scanner object.
 * This means that using System.in in other places is highly inadvisable.
 * If you close System.in somewhere in the program, the class will fail and vice versa.
 * */
public class ConsoleReader {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public int readMenu(List<Integer> availableCommands) {
		String userInput;
		int res = 0;
		
		while(true) {
			try {
				if(scanner.hasNextLine()) {
					userInput = scanner.nextLine();
					res = Integer.parseInt(userInput);
					
					if(availableCommands.contains(res)) {
						return res;
					}else {
						throw new NumberFormatException();
					}
				}else {
					scanner.next();
				}
				
			}catch(NumberFormatException e) {
				System.out.print("No such command! Please try again: ");
			}
		}
	}
	
	public String read() {
		String res = new String();
		if(scanner.hasNextLine()) {
			res = scanner.nextLine();
		}else {
			scanner.next();
		}
		return res;
	}
}
