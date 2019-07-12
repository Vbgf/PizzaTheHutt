package ui.console;

import java.util.Arrays;

import core.context.Context;
import data.order.Order;
import data.user.User;

public class ManagerMainScreen extends BaseUI {

	private static final int GET_ALL_PRODUCTS = 1;
	private static final int GET_ACTIVE_PRODUCTS = 2;
	private static final int GET_INACTIVE_PRODUCTS = 3;
	private static final int ADD_PRODUCT = 4;
	private static final int MODIFY_PRODUCT = 5;
	private static final int REMOVE_PRODUCT = 6;
	private static final int ACTIVATE_PRODUCT = 7;
	private static final int DEACTIVATE_PRODUCT = 8;
	private static final int MODIFY_USER = 9;
	private static final int MODIFY_ORDER = 10;
	private static final int GET_ALL_ORDERS = 11;
	private static final int GET_FINISHED_ORDERS = 12;
	private static final int GET_UNFINISHED_ORDERS = 13;
	private static final int GET_ALL_LAST30DAYS = 14;
	private static final int GET_REQUEST_CANCEL_ORDERS = 15;
	
	public ManagerMainScreen(Context context) {
		super(context);
	}

	public void showMenu() {
		System.out.println();
		System.out.println("Please choose one of the following:");
		System.out.println(GET_ALL_PRODUCTS + ".  Get all products");
		System.out.println(GET_ACTIVE_PRODUCTS + ".  Get active products");
		System.out.println(GET_INACTIVE_PRODUCTS + ".  Get inactive products");
		System.out.println(ADD_PRODUCT + ".  Add product");
		System.out.println(MODIFY_PRODUCT + ".  Modify product");
		System.out.println(REMOVE_PRODUCT + ".  Remove product");
		System.out.println(ACTIVATE_PRODUCT + ".  Activate product");
		System.out.println(DEACTIVATE_PRODUCT + ".  Deactivate product");
		System.out.println(MODIFY_USER + ".  Modify user");
		System.out.println(MODIFY_ORDER + ". Modify order");
		System.out.println(GET_ALL_ORDERS + ". Get all orders");
		System.out.println(GET_FINISHED_ORDERS + ". Get all orders in the last 30 days");
		System.out.println(GET_UNFINISHED_ORDERS + ". Get finished orders");
		System.out.println(GET_ALL_LAST30DAYS + ". Get unfinished orders");
		System.out.println(GET_REQUEST_CANCEL_ORDERS + ". Get disputed orders");
		System.out.println(BACK + ".  Okay, i'm done, please log me off");	
	}

	@Override
	public void show() {
		System.out.println();
		System.out.println("Welcome " + context.getCurrentUser().getUsername().toString() + "!");
		System.out.println("You are currently logged in as a " + context.getCurrentUser().getRole().toString().toLowerCase());	
		
		while(true) {
			showMenu();
			int userInput = ConsoleReader.readMenu(Arrays.asList(
					BACK, GET_ALL_PRODUCTS, GET_ACTIVE_PRODUCTS, GET_INACTIVE_PRODUCTS, ADD_PRODUCT,
					MODIFY_PRODUCT, REMOVE_PRODUCT, ACTIVATE_PRODUCT, DEACTIVATE_PRODUCT,
					MODIFY_USER, MODIFY_ORDER, GET_ALL_ORDERS, GET_FINISHED_ORDERS, GET_UNFINISHED_ORDERS,
					GET_ALL_LAST30DAYS, GET_REQUEST_CANCEL_ORDERS));
			
			switch(userInput) {
			case GET_ALL_PRODUCTS:
				new GetAllProductsScreen(context).show();
				break;
				
			case GET_ACTIVE_PRODUCTS:
				new GetActiveProductsScreen(context).show();
				break;
				
			case GET_INACTIVE_PRODUCTS:
				new GetInactiveProductsScreen(context).show();
				break;
				
			case ADD_PRODUCT:
				new AddProductScreen(context).show();
				break;
				
			case MODIFY_PRODUCT:
				new ModifyProductScreen(context).show();
				break;
				
			case REMOVE_PRODUCT:
				new RemoveProductScreen(context).show();
				break;
				
			case ACTIVATE_PRODUCT:
				new ActivateProductScreen(context).show();
				break;
				
			case DEACTIVATE_PRODUCT:
				new DeactivateProductScreen(context).show();
				break;
				
			case MODIFY_USER:
				System.out.println("TODO: Expect soon!");
				break;
				
			case MODIFY_ORDER:
				System.out.println("TODO: Expect soon!");
				break;
				
			case GET_ALL_ORDERS:
				new GetAllOrdersScreen(context).show();
				break;
				
			case GET_FINISHED_ORDERS:
				System.out.println("TODO: Expect soon!");
				break;
				
			case GET_UNFINISHED_ORDERS:
				System.out.println("TODO: Expect soon!");
				break;
				
			case GET_ALL_LAST30DAYS:
				System.out.println("TODO: Expect soon!");
				break;
				
			case GET_REQUEST_CANCEL_ORDERS:
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
