package ui.console;

import core.browsers.StoreItemBrowser;
import core.context.Context;

public class GetAllProductsScreen extends BaseUI{

	public GetAllProductsScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		System.out.println();
		System.out.println(StoreItemBrowser.listToString(context.getItemManager().getAll()));
	}

}
