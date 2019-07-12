package ui.console;

import core.context.Context;
import data.order.Order;
import data.user.User;

public class LogoutScreen extends BaseUI{
	
	public LogoutScreen(Context context) {
		super(context);
	}

	@Override
	public void show() {
		
		if(!context.getCurrentOrder().getItems().isEmpty()) {
			System.out.println("You have not confirmed your order!");
			new PlaceOrderScreen(context).show();
		}

		context.setCurrentUser(new User());
		context.setCurrentOrder(new Order());
	}

}
