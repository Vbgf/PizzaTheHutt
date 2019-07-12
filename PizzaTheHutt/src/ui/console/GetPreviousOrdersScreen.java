package ui.console;

import java.util.List;

import core.browsers.OrderBrowser;
import core.context.Context;
import data.order.Order;

public class GetPreviousOrdersScreen extends BaseUI{

	public GetPreviousOrdersScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		System.out.println();
		OrderBrowser browser = new OrderBrowser(context.getOrderManager());
		
		List<Order> orders = browser.getPreviousOrders(context.getCurrentUser().getId());
		if(orders.isEmpty()) {
			System.out.println();
			System.out.println("You don't have any previous orders!");
			return;
		}
		
		for(Order order : orders) {
			System.out.println(order.toStringPartial());
		}
	}
}
