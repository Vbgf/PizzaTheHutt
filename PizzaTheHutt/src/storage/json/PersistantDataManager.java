package storage.json;

import java.util.ArrayList;

import core.IPersistantData;
import data.user.UserRoles;
import data.order.Order;
import data.storeItem.StoreItem;
import data.user.User;

public class PersistantDataManager implements IPersistantData{
	protected ArrayList<User> users;
	protected ArrayList<StoreItem> items;
	protected ArrayList<Order> orders;
	
	

	@Override
	public UserRoles authUser(String username, String password) throws IllegalArgumentException, NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(String username, String password) throws IllegalArgumentException, NullPointerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(String username, String password) throws IllegalArgumentException, NullPointerException {
		// TODO Auto-generated method stub
		
	}

}
