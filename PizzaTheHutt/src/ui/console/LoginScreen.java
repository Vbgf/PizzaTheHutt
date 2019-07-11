package ui.console;

import java.util.Arrays;

import core.context.Context;
import core.highLevel.UserAuthenticator;
import data.user.UserRoles;

public class LoginScreen extends UIBase{

	public LoginScreen(Context context) {
		super(context);
	}

	@Override
	public int show() {
		while(true) {
			System.out.println("Hello and welcome!");
			System.out.println("Please login to continue.");
			System.out.println("1. OK");
			System.out.println("9. Exit");
			
			int userInput = ConsoleReader.readMenu(Arrays.asList(1, 9));
			
			if(userInput == 1) {
				System.out.print("Username: ");
				String username = ConsoleReader.read();
				System.out.print("Password: ");
				String password = ConsoleReader.read();
				
				UserAuthenticator auth = new UserAuthenticator(context.getUserManager());
				UserRoles role =  UserRoles.UNASSIGNED;
				try {
					role = auth.authenticate(username, password);
				}catch(IllegalArgumentException e) {
					System.out.print("No such user found!");
				}
				
				switch (role) {
				case USER:
					//user UI
					break;
				case MANAGER:
					//manager UI
					break;
				case ADMINISTRATOR:
					//ADMIN UI
					break;
				case UNASSIGNED:
					//no role UI
					break;
				}
				
			}else if(userInput == 9) {

				/*
				try {
					System.out.print("Goodbye!!");
					TimeUnit.SECONDS.sleep(1);
					//context.save();
				} catch (IOException e) {
				}catch{
					
				}
				System.exit(0);
				
				*/	
			}
		
			return userInput;
		}
	}
}
