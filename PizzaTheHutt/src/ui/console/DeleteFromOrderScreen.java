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
		int currentIndex = 0;
		for(StoreItem item : items) {	
			currentIndex++;		
			itemIds.add(currentIndex);
			System.out.print("ID: " + currentIndex + "; ");
			System.out.print("Name: " + item.getName() + "; ");
			System.out.print("Price: " + item.getPrice() + "; ");
			System.out.println("Description: " + item.getDescription());
		}

		System.out.println();
		System.out.println("Select an item to delete");
		System.out.print("Item ID: ");
		
		int chosenId;
		try {
			chosenId = Integer.parseInt(ConsoleReader.read());
			
			if(!itemIds.contains(chosenId)) {
				throw new IllegalArgumentException("ID not found");
			}
			
		}catch (IllegalArgumentException e) {
			System.out.println("Invalid ID! Please try again");
			return;
		}
		int actualId = chosenId - 1;

		System.out.println();

		System.out.print("ID: " + chosenId + "; ");
		System.out.print("Name: " + items.get(actualId).getName() + "; ");
		System.out.print("Price: " + items.get(actualId).getPrice() + "; ");
		System.out.println("Description: " + items.get(actualId).getDescription());
		
		System.out.println("Do you want to delete the item from your order?");
		System.out.println(YES + ". Yes");
		System.out.println(BACK + ". No");
		int userInput = ConsoleReader.readMenu(Arrays.asList(BACK, YES));
		
		switch (userInput) {
		case YES:
			items.remove(actualId);
			System.out.println("Done!");
			
		case BACK:
			return;
		}
	}
}
