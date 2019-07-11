package core;

import java.io.IOException;

import core.context.Context;
import ui.console.LoginScreen;

public class App {

	public static void main(String[] args) {
		Context context;
		try {
			context = Context.getInstance();
		}catch(IOException e) {
			System.out.println("Something went wrong when establishing the program context! Please consult a wizzard for help.");
			return;
		}

		LoginScreen loginScreen = new LoginScreen(context);
		loginScreen.show();
	}
}
