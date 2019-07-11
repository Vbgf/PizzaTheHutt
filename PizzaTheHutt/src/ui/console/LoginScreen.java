package ui.console;

import java.util.Arrays;

import core.context.Context;
import core.highLevel.UserAuthenticator;

public class LoginScreen extends BaseUI{

	public LoginScreen(Context context) {
		super(context);
	}

	@Override
	public int show() {
		System.out.println("Hello and welcome!");
		while(true) {
			System.out.println();
			System.out.println("Please login to continue.");
			System.out.println("1. Sure, let me put in my credentials");
			System.out.println("9. No, thanks");
			
			int userInput = ConsoleReader.readMenu(Arrays.asList(1, 9));
			
			if(userInput == 1) {
				System.out.print("Username: ");
				String username = ConsoleReader.read();
				System.out.print("Password: ");
				String password = ConsoleReader.read();
				
				UserAuthenticator auth = new UserAuthenticator(context.getUserManager());
				try {
					context.setCurrentUser( auth.authenticate(username, password) );
				}catch(IllegalArgumentException e) {
					System.out.println("No such user found!");
				}
				
				switch (context.getCurrentUser().getRole()) {
				case USER:
					MainUserScreen mainUserScreen = new MainUserScreen(context);
					mainUserScreen.show();
					break;
				case MANAGER:
					System.out.println("Handling MANAGER: Not implemented yet");
					break;
				case ADMINISTRATOR:
					System.out.println("Handling ADMINISTRATOR: Not implemented yet");
					break;
				case UNASSIGNED:
					System.out.println("Handling UNASSIGNED: Not implemented yet");
					break;
				}
				
			}else if(userInput == 9) {
				System.out.println("Thank you for using our pizzeria. Goodbye!");
				System.exit(0);
			}
		}
	}
}
