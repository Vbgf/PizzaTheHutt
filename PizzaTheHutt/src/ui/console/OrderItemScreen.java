package ui.console;

import java.util.Arrays;
import java.util.List;

import core.browsers.StoreItemBrowser;
import core.context.Context;
import data.storeItem.StoreItem;

public class OrderItemScreen extends BaseUI{

	private static final int YES = 1;
	
	public OrderItemScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		StoreItemBrowser browser = new StoreItemBrowser(context.getItemManager());
		List<StoreItem> activeItems = browser.getActive();
		
		System.out.println();
		if(activeItems.isEmpty()) {
			System.out.println("There are no active items!");
			return;
		}else {
			System.out.println(StoreItemBrowser.listToString(activeItems));
		}		

		System.out.println("Select an item you wish to buy");
		System.out.print("Item ID: ");
		
		long id;
		StoreItem item = null;
		try {
			id = Long.parseLong(ConsoleReader.read());
		}catch (IllegalArgumentException e) {
			System.out.println("Invalid ID! Please try again");
			return;
		}
		
		for(StoreItem activeItem : activeItems) {
			if(activeItem.getId() == id) {
				item = activeItem;
				break;
			}
		}
		
		if(item == null) {
			System.out.println("Invalid ID! Please try again");
			return;
		}

		System.out.println();
		System.out.println(item);
		
		System.out.println("Do you want to add this item to your order?");
		System.out.println(YES + ". Yes");
		System.out.println(BACK + ". No");
		int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, YES));
		
		switch (userInput) {
		case YES:
			context.getCurrentOrder().getItems().add(item);
			System.out.println("Done!");
			return;
			
		case BACK:
			return;
		}
		
	}
}
