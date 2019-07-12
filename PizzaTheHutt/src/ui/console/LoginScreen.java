package ui.console;

import java.util.Arrays;

import core.browsers.UserBrowser;
import core.context.Context;

public class LoginScreen extends BaseUI{

	private static final int LOGIN = 1;
	private static final int REGISTER = 2;
	
	public LoginScreen(Context context) {
		super(context);
	}
	
	public void showMenu() {
		System.out.println();
		System.out.println("Please login to continue shopping");
		System.out.println(LOGIN + ". Sure, let me put in my credentials");
		System.out.println(REGISTER + ". I don't have an account");
		System.out.println(BACK + ". No, thanks. Goodbye!");
	}

	@Override
	public void show() {
		System.out.println("Hello and welcome!");
		
		while(true) {
			showMenu();
			int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, LOGIN, REGISTER));
			
			switch (userInput) {
			case LOGIN:
				System.out.print("Username: ");
				String username = ConsoleReader.read();
				System.out.print("Password: ");
				String password = ConsoleReader.read();
				
				UserBrowser auth = new UserBrowser(context.getUserManager());
				try {
					context.setCurrentUser( auth.authenticate(username, password) );
				}catch(IllegalArgumentException e) {
					System.out.println("No such user found!");
				}
				
				switch (context.getCurrentUser().getRole()) {
				case USER:
					new UserMainScreen(context).show();
					break;
					
				case WORKER:
					new WorkerMainScreen(context).show();
					break;
					
				case MANAGER:
					new ManagerMainScreen(context).show();
					break;
					
				case UNASSIGNED:
					break;
				}
				break;
				
			case REGISTER:
				new UserRegisterScreen(context).show();
				break;
				
			case BACK:
				System.out.println();
				System.out.println("Thank you for stopping by. Goodbye!");
				System.exit(0);
			}
		}
	}
}
