package ui.console;

import java.util.Arrays;

import core.context.Context;

public class UserMainScreen extends BaseUI{

	private static final int SHOW_PRODUCTS = 1;
	private static final int ORDER_ITEM = 2;
	private static final int SHOW_ORDER = 3;
	private static final int REMOVE_FROM_ORDER = 4;
	private static final int PLACE_ORDER = 5;
	private static final int PAST_ORDERS = 6;
	private static final int PLACE_PAST_ORDER = 7;
	
	public UserMainScreen(Context context) {
		super(context);
	}
	
	public void showMenu() {
		System.out.println();
		System.out.println("Please choose one of the following:");
		System.out.println(SHOW_PRODUCTS + ". Show me what you've got!");
		System.out.println(ORDER_ITEM + ". I want to order something");
		System.out.println(SHOW_ORDER + ". Show me my order");
		System.out.println(REMOVE_FROM_ORDER + ". Remove an item from my order");
		System.out.println(PLACE_ORDER + ". Place my order");
		System.out.println(PAST_ORDERS + ". Show me my past orders");
		System.out.println(PLACE_PAST_ORDER + ". I want to reorder a previous purchase");
		System.out.println(BACK + ". Okay, i'm done, please log me off");		
	}
	
	@Override
	public void show() {
		System.out.println();
		System.out.println("Welcome " + context.getCurrentUser().getUsername().toString() + "!");
		System.out.println("You are currently logged in as a " + context.getCurrentUser().getRole().toString().toLowerCase());
		
		while(true) {
			showMenu();
			int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, SHOW_PRODUCTS, ORDER_ITEM,
					SHOW_ORDER, REMOVE_FROM_ORDER, PLACE_ORDER, PAST_ORDERS, PLACE_PAST_ORDER));
			
			switch(userInput) {
			case SHOW_PRODUCTS:
				new GetActiveProductsScreen(context).show();
				break;
				
			case ORDER_ITEM:
				new OrderItemScreen(context).show();
				break;
				
			case SHOW_ORDER:
				new GetCurrentOrderScreen(context).show();
				break;
				
			case REMOVE_FROM_ORDER:
				new DeleteFromOrderScreen(context).show();
				break;
				
			case PLACE_ORDER:
				new PlaceOrderScreen(context).show();
				break;
				
			case PAST_ORDERS:
				new GetPreviousOrdersScreen(context).show();
				break;
				
			case PLACE_PAST_ORDER:
				new ReorderScreen(context).show();
				break;
				
			case BACK:
				new LogoutScreen(context).show();
				return;
			}
		}
	}
}
