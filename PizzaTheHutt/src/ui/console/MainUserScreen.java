package ui.console;

import java.util.Arrays;

import core.context.Context;

public class MainUserScreen extends BaseUI{

	public MainUserScreen(Context context) {
		super(context);
	}

	@Override
	public int show() {
		System.out.println();
		System.out.println("Welcome " + context.getCurrentUser().getUsername().toString() + "!");
		System.out.println("You are currently logged in as a " + context.getCurrentUser().getRole().toString().toLowerCase());
		while(true) {
			
			System.out.println();
			System.out.println("Please choose one of the following:");
			System.out.println("1. Show me what you've got!");
			System.out.println("2. I want to order something");
			System.out.println("3. Show me my order");
			System.out.println("4. Place my order");
			System.out.println("5. Show my my past orders");
			System.out.println("6. I want to reorder a past order");
			System.out.println("9. Okay, i'm done, please log me off");
			
			int userInput = ConsoleReader.readMenu(Arrays.asList(1, 2, 3, 4, 5, 6, 9));
			
			switch(userInput) {
			case 1:
				System.out.println("Handling 1");
				break;
			case 2:
				System.out.println("Handling 2");
				break;
			case 3:
				System.out.println("Handling 3");
				break;
			case 4:
				System.out.println("Handling 4");
				break;
			case 5:
				System.out.println("Handling 5");
				break;
			case 6:
				System.out.println("Handling 6");
				break;
			case 9:
				System.out.println("Handling 9");
				break;
			}
		}
	}
	
	
}
