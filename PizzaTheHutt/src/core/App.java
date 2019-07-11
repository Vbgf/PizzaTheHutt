package core;

import java.io.IOException;

import core.context.Context;
import ui.console.LoginScreen;

public class App {

	public static void main(String[] args) {
		try {
			Context context = Context.getInstance();
			LoginScreen landingpage = new LoginScreen(context);
			landingpage.show();
		}catch(IOException e) {
			System.out.println("Internal error! Problems with the StackTrace");
		}
	}
}
