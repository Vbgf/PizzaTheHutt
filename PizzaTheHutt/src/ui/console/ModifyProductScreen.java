package ui.console;

import java.io.IOException;
import java.util.Arrays;

import core.context.Context;
import data.storeItem.StoreItem;

public class ModifyProductScreen extends BaseUI{

	private static final int NAME = 1;
	private static final int PRICE = 2;
	private static final int DESCRIPTION = 3;
	private static final int ACTIVE = 4;
	
	public ModifyProductScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		new GetAllProductsScreen(context).show();

		System.out.println("Select an item you wish to modify");
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
		StoreItem referenceItem = new StoreItem(item);
		
		while(true) {
			System.out.println();
			System.out.println(item);
			
			System.out.println("What do you want to modify?");
			System.out.println(NAME + ". Name");
			System.out.println(PRICE + ". Price");
			System.out.println(DESCRIPTION + ". Description");
			System.out.println(ACTIVE + ". Toggle active");
			System.out.println(BACK + ". Cancel");
			int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, NAME, PRICE, DESCRIPTION, ACTIVE));
			
			switch(userInput) {
			case NAME:
				System.out.print("New name: ");
				String name = ConsoleReader.read();
				
				if(name.isEmpty()) {
					System.out.println("Item name cannot be empty! Please try again");
					continue;
				}
				
				item.setName(name);
				break;
				
			case PRICE:
				System.out.print("New price: ");
				double price;
				try {
					price = Double.parseDouble(ConsoleReader.read());
				}catch (NumberFormatException e) {
					System.out.println("Invalid price! Please try again");
					continue;
				}

				item.setPrice(price);
				break;
				
			case DESCRIPTION:
				System.out.print("New description: ");
				String description = ConsoleReader.read();
				item.setDescription(description);
				break;
				
			case ACTIVE:
				item.setActive(!item.isActive());
				break;
				
			case BACK:
				if(!item.equals(referenceItem)) {				
					try {
						context.getItemManager().save();
					}catch(IOException e) {
						System.out.println("Something went wrong, please try again");
						return;
					}
				}
				return;
			}
		}
	}
}
