package ui.console;

import java.util.Arrays;

import core.context.Context;
import data.order.Order;
import data.user.User;

public class WorkerMainScreen extends BaseUI {

	private static final int GET_ACTIVE_ORDERS = 1;
	private static final int MARK_FINISHED = 2;
	private static final int REQUEST_CANCEL = 3;
	
	public WorkerMainScreen(Context context) {
		super(context);
	}
	
	public void showMenu() {
		System.out.println();
		System.out.println("Please choose one of the following:");
		System.out.println(GET_ACTIVE_ORDERS + ". Get active orders");
		System.out.println(MARK_FINISHED + ". Mark order as finished");
		System.out.println(REQUEST_CANCEL + ". Cancel order");
		System.out.println(BACK + ". Okay, i'm done, please log me off");	
	}
	
	@Override
	public void show() {
		System.out.println();
		System.out.println("Welcome " + context.getCurrentUser().getUsername().toString() + "!");
		System.out.println("You are currently logged in as a " + context.getCurrentUser().getRole().toString().toLowerCase());
		
		while(true) {
			showMenu();
			int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, GET_ACTIVE_ORDERS, MARK_FINISHED, REQUEST_CANCEL));
			
			switch(userInput) {
			case GET_ACTIVE_ORDERS:
				System.out.println("TODO: Expect soon!");
				break;
				
			case MARK_FINISHED:
				System.out.println("TODO: Expect soon!");
				break;
				
			case REQUEST_CANCEL:
				System.out.println("TODO: Expect soon!");
				break;
				
			case BACK:
				context.setCurrentUser(new User());
				context.setCurrentOrder(new Order());
				return;
			}
		}
	}
}
