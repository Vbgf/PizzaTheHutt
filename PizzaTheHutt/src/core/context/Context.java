package core.context;

import java.io.IOException;

import storage.managers.OrderManager;
import storage.managers.StoreItemManager;
import storage.managers.UserManager;

public class Context {
	
	private static Context instance = null;
	
	public static Context getInstance() throws IOException {
		if(instance == null) {
			instance = new Context();
		}
		return instance;
	}
	
	private UserManager userManager;
	private StoreItemManager itemManager;
	private OrderManager orderManager;
	
	private Context () throws IOException {
		userManager = new UserManager();
		itemManager = new StoreItemManager();
		orderManager = new OrderManager();
	}
	
	public void save() throws IOException {
		getUserManager().save();
		getItemManager().save();
		getOrderManager().save();
	}

	public UserManager getUserManager() {
		return userManager;
	}


	public StoreItemManager getItemManager() {
		return itemManager;
	}

	public OrderManager getOrderManager() {
		return orderManager;
	}
}