package storage.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import data.user.UserRoles;
import storage.json.JsonParser;
import data.order.Order;
import data.storeItem.StoreItem;
import data.user.User;

public class PersistantDataManager {
	private static final File USERS_DB_FILE = new File("data\\users.json");
	private static final File ITEMS_DB_FILE = new File("data\\items.json");
	private static final File ORDERS_DB_FILE = new File("data\\orders.json");
	
	private JsonParser<User> usersDB;
	private JsonParser<StoreItem> itemsDB;
	private JsonParser<Order> ordersDB;	
	
	private ArrayList<User> users;
	private ArrayList<StoreItem> items;
	private ArrayList<Order> orders;
	
	public PersistantDataManager() throws IOException {
		usersDB = new JsonParser<>(USERS_DB_FILE);
		itemsDB = new JsonParser<>(ITEMS_DB_FILE);
		ordersDB = new JsonParser<>(ORDERS_DB_FILE);
		
		users = usersDB.load();
		items = itemsDB.load();
		orders = ordersDB.load();
	}

}
