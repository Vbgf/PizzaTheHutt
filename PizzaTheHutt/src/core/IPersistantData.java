package core;

import java.io.IOException;

import data.user.UserRoles;

public interface IPersistantData {
	public void load() throws IOException;
	public void save() throws IOException;
	
	public UserRoles authUser(String username, String password) throws IllegalArgumentException, NullPointerException;
	public void addUser(String username, String password) throws IllegalArgumentException, NullPointerException;
	public void removeUser(String username, String password) throws IllegalArgumentException, NullPointerException;
}