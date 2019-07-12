package ui.console;

import java.io.IOException;
import java.util.Arrays;

import core.context.Context;
import data.user.User;
import data.user.UserRoles;

public class UserRegisterScreen extends BaseUI{

	private static final int REGISTER = 1;
	
	public UserRegisterScreen(Context context) {
		super(context);
	}

	public void showMenu() {
		System.out.println();
		System.out.println("Would you like to register?");
		System.out.println(REGISTER + ". Okay!");
		System.out.println(BACK + ". No, thanks. Please bring me back to the login screen");
	}

	@Override
	public void show() {
		System.out.println();
		System.out.println("No problem! I can create a new account for you");
		
		while(true) {
			showMenu();
			int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, REGISTER));
			
			switch(userInput){
			case REGISTER:
				System.out.print("Please enter a username: ");
				String username = ConsoleReader.read();
				
				boolean userExists = false;
				for (User user : context.getUserManager().getAll()) {
					if(user.getUsername().equals(username)) {
						userExists = true;
						break;
					}
				}

				if(userExists) {
					System.out.println("Username already exist! Please choose another one");
					continue;
				}
				
				
				System.out.print("Now add a strong password: ");
				String password = ConsoleReader.read();
				
				if(password.length() < 8) {
					System.out.println("Not the strongest i've seen, but it will do!");
					System.out.print("Okay, now type your password one more time just in case: ");
					
				}else if(password.length() >= 8 && password.length() <= 16) {
					System.out.println("Hey, that's plenty strong!");
					System.out.print("Okay, now type your password one more time just in case: ");
					
				}else if(password.length() >= 16 && password.length() <= 32){
					System.out.println("No one is breaking in your pizzeria account any time soon!");
					System.out.print("Okay, now type your password one more time just in case: ");
					
				} if(password.length() > 32){
					System.out.println("That's total overkill dude, good luck typing it every time though!");
					System.out.print("Okay smartass, now type it again: ");
				}

				String secondPassword = ConsoleReader.read();
				
				if(!password.equals(secondPassword)) {
					System.out.println("Passwords do not match! Please try again");
					continue;
				}else {
					User user = new User(context.getUserManager().reserveId(), username, password, UserRoles.USER);
					
					try {
						context.getUserManager().add(user);
					}catch(IllegalArgumentException e) {
						System.out.println("Something went wrong, please try again");
						continue;
					}
					
					try {
						context.getUserManager().save();
					}catch(IOException e) {
						context.getUserManager().delete(user.getId());
						System.out.println("Something went wrong, please try again");
						continue;
					}
					
					System.out.println("Your account is ready!");
					return;
				}				
			case BACK:
				return;
			}
		}
	}
}
