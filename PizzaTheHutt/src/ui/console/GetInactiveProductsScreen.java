package ui.console;

import java.util.List;

import core.browsers.StoreItemBrowser;
import core.context.Context;
import data.storeItem.StoreItem;

public class GetInactiveProductsScreen extends BaseUI{
	public GetInactiveProductsScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		StoreItemBrowser browser = new StoreItemBrowser(context.getItemManager());
		List<StoreItem> inactiveItems = browser.getInactive();

		System.out.println();
		if(inactiveItems.isEmpty()) {
			System.out.println("There are no inactive items!");
			return;
		}else {
			System.out.println(StoreItemBrowser.listToString(inactiveItems));
		}
	}
}
