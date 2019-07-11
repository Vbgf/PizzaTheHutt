package core.context;

import java.io.IOException;

import data.user.User;
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
	private User currentUser;
	
	private Context () throws IOException {
		this.userManager = new UserManager();
		this.itemManager = new StoreItemManager();
		this.orderManager = new OrderManager();
		this.setCurrentUser(new User());
	}
	
	public void saveAll() throws IOException {
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

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
}