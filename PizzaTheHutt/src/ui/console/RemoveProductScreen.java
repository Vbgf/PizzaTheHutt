package ui.console;

import java.io.IOException;
import java.util.Arrays;

import core.context.Context;
import data.storeItem.StoreItem;

public class RemoveProductScreen extends BaseUI{
	
	private static final int YES = 1;
	
	public RemoveProductScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		new GetAllProductsScreen(context).show();
		
		System.out.println("Select an item you wish to remove");
		System.out.print("Item ID: ");
		
		long id;
		StoreItem item;
		try {
			id = Long.parseLong(ConsoleReader.read());
			item = context.getItemManager().get(id);
		}catch (IllegalArgumentException e) {
			System.out.println("Invalid ID! Please try again");
			return;
		}
		
		System.out.println();
		System.out.println(item);
		
		System.out.println("Are you sure you want to permanently delete this item?");
		System.out.println(YES + ". Yes");
		System.out.println(BACK + ". No");
		int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, YES));
					
		switch (userInput) {
		case YES:
			context.getItemManager().delete(item.getId());
			
			try {
				context.getItemManager().save();
			}catch(IOException e) {
				System.out.println("Something went wrong, please try again");
				return;
			}
			return;
			
		case BACK:
			return;
		}
	}
}
