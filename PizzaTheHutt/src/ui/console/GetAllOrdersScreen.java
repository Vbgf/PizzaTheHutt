package ui.console;

import java.util.List;

import core.browsers.OrderBrowser;
import core.context.Context;
import data.order.Order;

public class GetAllOrdersScreen extends BaseUI{

	public GetAllOrdersScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		List<Order> orders = context.getOrderManager().getAll();

		System.out.println();
		if(orders.isEmpty()) {
			System.out.println("There are no placed orders");
			return;
		}else {
			System.out.println(OrderBrowser.listToString(context.getOrderManager().getAll()));
		}
	}

}
