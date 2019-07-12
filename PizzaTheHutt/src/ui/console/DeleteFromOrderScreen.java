package ui.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.context.Context;
import data.storeItem.StoreItem;

public class DeleteFromOrderScreen extends BaseUI{

	public static final int YES = 1;
	
	public DeleteFromOrderScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		System.out.println();
		List<StoreItem> items = context.getCurrentOrder().getItems();
		if(items.isEmpty()) {
			System.out.println("Your order is empty!");
			return;
		}
		
		List<Integer> itemIds = new ArrayList<Integer>();
		for(StoreItem item : items) {
			int currentIndex = items.indexOf(item);
			itemIds.add(currentIndex);
			System.out.print("ID: " + currentIndex + "; ");
			System.out.print("Name: " + item.getName() + "; ");
			System.out.print("Price: " + item.getPrice() + "; ");
			System.out.println("Description: " + item.getDescription());
		}

		System.out.println();
		System.out.println("Select an item to delete");
		System.out.print("Item ID: ");
		
		long id;
		StoreItem item = null;
		try {
			id = Long.parseLong(ConsoleReader.read());
		}catch (IllegalArgumentException e) {
			System.out.println("Invalid ID! Please try again");
			return;
		}
		
		for(StoreItem orderItem : items) {
			if(orderItem.getId() == id) {
				item = orderItem;
				break;
			}
		}
		
		if(item == null) {
			System.out.println("Invalid ID! Please try again");
			return;
		}

		System.out.println();
		System.out.println(item);
		
		System.out.println("Do you want to delete the item from your order?");
		System.out.println(YES + ". Yes");
		System.out.println(BACK + ". No");
		int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, YES));
		
		switch (userInput) {
		case YES:
			context.getCurrentOrder().getItems().remove(items.indexOf(item));
			System.out.println("Done!");
			
		case BACK:
			return;
		}

		
	}

}
