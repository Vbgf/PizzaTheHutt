package ui.console;

import java.io.IOException;
import java.util.List;

import core.browsers.StoreItemBrowser;
import core.context.Context;
import data.storeItem.StoreItem;

public class ActivateProductScreen extends BaseUI{

	public ActivateProductScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		StoreItemBrowser browser = new StoreItemBrowser(context.getItemManager());		
		List<StoreItem> inactiveItems = browser.getInactive();
		
		if(inactiveItems.isEmpty()) {
			System.out.println("There are no inactive items!");
			return;
		}
		
		System.out.println(StoreItemBrowser.listToString(inactiveItems));

		System.out.println();
		System.out.println("Select an item you wish to activate");
		System.out.print("Item ID: ");
		
		long id;
		StoreItem item = null;
		try {
			id = Long.parseLong(ConsoleReader.read());
		}catch (IllegalArgumentException e) {
			System.out.println("Invalid ID! Please try again");
			return;
		}
		
		for(StoreItem inactiveItem : inactiveItems) {
			if(inactiveItem.getId() == id) {
				item = inactiveItem;
				break;
			}
		}
		
		if(item == null) {
			System.out.println("Invalid ID! Please try again");
			return;
		}
		
		item.setActive(!item.isActive());
		
		try {
			context.getItemManager().save();
		}catch(IOException e) {
			System.out.println("Something went wrong, please try again");
			return;
		}
		
		System.out.println("Done!");
	}

}
