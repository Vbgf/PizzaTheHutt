package ui.console;

import java.io.IOException;
import java.util.Arrays;

import core.context.Context;
import data.storeItem.StoreItem;

public class AddProductScreen extends BaseUI {

	private static final int YES = 1;
	private static final int NO = 2;
	
	public AddProductScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		System.out.println();
		System.out.print("Item name: ");
		String name = ConsoleReader.read();
		
		if(name.isEmpty()) {
			System.out.println("Item name cannot be empty! Please try again");
			return;
		}
		
		System.out.print("Item price: ");
		double price;
		try {
			price = Double.parseDouble(ConsoleReader.read());
		}catch (NumberFormatException e) {
			System.out.println("Invalid price! Please try again");
			return;
		}

		System.out.print("Description: ");
		String description = ConsoleReader.read();

		System.out.println("Should the item be active?");
		System.out.println(YES + ". Yes");
		System.out.println(NO + ". No");
		System.out.println(BACK + ". Cancel");
		int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, YES, NO));
		
		boolean active = false;
		
		switch (userInput) {
		case YES:
			active = true;
			break;
			
		case NO:
			active = false;
			break;
			
		case BACK:
			return;
		}
		
		StoreItem item = new StoreItem(context.getItemManager().reserveId(), name, price, description, active);
						
		try {
			context.getItemManager().add(item);
		}catch(IllegalArgumentException e) {
			System.out.println("Something went wrong, please try again");
			return;
		}
		
		try {
			context.getItemManager().save();
		}catch(IOException e) {
			context.getItemManager().delete(item.getId());
			System.out.println("Something went wrong, please try again");
			return;
		}
		
		System.out.println("Item added successfully!");
	}

}
