package core;

import data.user.UserRoles;

public interface IPersistantData {	
	public UserRoles authUser(String username, String password) throws IllegalArgumentException, NullPointerException;
	public void addUser(String username, String password) throws IllegalArgumentException, NullPointerException;
	public void removeUser(String username, String password) throws IllegalArgumentException, NullPointerException;
}