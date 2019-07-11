package core.highLevel;

import data.user.User;
import storage.managers.UserManager;

public class UserAuthenticator {
	
	private UserManager manager;
	
	public UserAuthenticator(UserManager manager) {
		this.manager = manager;
	}
	
	public User authenticate(String username, String password) throws IllegalArgumentException{
		
		for(User user : manager.getAll()) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		
		throw new IllegalArgumentException("User not found");
	}
}
