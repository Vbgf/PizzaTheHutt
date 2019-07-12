package ui.console;

import java.util.List;

import core.context.Context;
import data.storeItem.StoreItem;

public class GetCurrentOrderScreen extends BaseUI {

	public GetCurrentOrderScreen(Context context) {
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
		
		double totalPrice = 0;		
		for(StoreItem item : items) {
			System.out.print("Name: " + item.getName() + "; ");
			System.out.print("Price: " + item.getPrice() + "BGN; ");
			System.out.println("Description: " + item.getDescription());
			totalPrice += item.getPrice();
		}
		
		System.out.println("Total: " + totalPrice + "BGN");
	}

}
