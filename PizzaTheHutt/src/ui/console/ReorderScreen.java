package ui.console;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import core.browsers.OrderBrowser;
import core.context.Context;
import data.order.Order;
import data.order.OrderStatus;

public class ReorderScreen extends BaseUI{

	private static final int YES = 1;
	
	public ReorderScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		OrderBrowser browser = new OrderBrowser(context.getOrderManager());
		
		List<Order> previousOrders = browser.getPreviousOrders(context.getCurrentUser().getId());
		if(previousOrders.isEmpty()) {
			System.out.println();
			System.out.println("You don't have any previous orders!");
			return;
		}
		
		new GetPreviousOrdersScreen(context).show();
		
		System.out.println("Which order should we repeat?");
		System.out.print("Order ID: ");
		
		long id;
		Order order = null;
		try {
			id = Long.parseLong(ConsoleReader.read());
		}catch (IllegalArgumentException e) {
			System.out.println("Invalid ID! Please try again");
			return;
		}
		
		for(Order previousOrder : previousOrders) {
			if(previousOrder.getId() == id) {
				order = new Order(previousOrder);
				break;
			}
		}
		
		if(order == null) {
			System.out.println("Invalid ID! Please try again");
			return;
		}
		
		System.out.println();
		System.out.println(order.toStringPartial());
		
		System.out.println("Do you want to repeat this order?");
		System.out.println(YES + ". Yes");
		System.out.println(BACK + ". No");
		int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, YES));
		
		switch (userInput) {
		case YES:

			order.setId(context.getOrderManager().reserveId());
			order.setStatus(OrderStatus.NEW);
			order.setTimestamp(System.currentTimeMillis());
			
			System.out.println(order.toStringPartial());
			
			try {
				context.getOrderManager().add(order);
			}catch (IllegalArgumentException e) {
				System.out.println("Something went wrong, please try again");
				return;
			}
			
			try {
				context.getOrderManager().save();
			}catch (IOException e) {
				context.getOrderManager().delete(order.getId());
				System.out.println("Something went wrong, please try again");
				return;
			}
			
			System.out.println("Done!");
			break;
			
		case BACK:
			return;
		}
	}

}
