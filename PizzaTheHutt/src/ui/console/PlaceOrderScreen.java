package ui.console;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import core.context.Context;
import data.order.Order;
import data.order.OrderStatus;
import data.storeItem.StoreItem;

public class PlaceOrderScreen extends BaseUI{

	private static final int YES = 1;
	
	public PlaceOrderScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		
		List<StoreItem> items = context.getCurrentOrder().getItems();
		if(items.isEmpty()) {
			System.out.println("Your order is empty!");
			return;
		}
		new GetCurrentOrderScreen(context).show();
		
		System.out.println("Do you want to order these items?");
		System.out.println(YES + ". Yes");
		System.out.println(BACK + ". No");
		int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, YES));
		
		switch (userInput) {
		case YES:
			context.getCurrentOrder().setId(context.getOrderManager().reserveId());
			context.getCurrentOrder().setUserId(context.getCurrentUser().getId());
			context.getCurrentOrder().setStatus(OrderStatus.NEW);
			context.getCurrentOrder().setTimestamp(System.currentTimeMillis());
			
			try {
				context.getOrderManager().add(context.getCurrentOrder());
			}catch (IllegalArgumentException e) {
				System.out.println("Something went wrong, please try again.");
				return;
			}
			
			try {
				context.getOrderManager().save();
			}catch (IOException e) {
				context.getOrderManager().delete(context.getCurrentOrder().getId());
				System.out.println("Something went wrong, please try again");
				return;
			}
			
			context.setCurrentOrder(new Order());
			System.out.println("Done!");
			break;

		case BACK:
			return;
		}
		
		
	}

}
