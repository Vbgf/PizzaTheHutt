package ui.console;

import java.util.List;

import core.browsers.StoreItemBrowser;
import core.context.Context;
import data.storeItem.StoreItem;

public class GetActiveProductsScreen extends BaseUI{
	public GetActiveProductsScreen(Context context) {
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
	}
}
