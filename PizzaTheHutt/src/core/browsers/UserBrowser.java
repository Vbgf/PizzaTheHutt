package core.browsers;

import java.util.List;

import data.user.User;
import storage.managers.UserManager;

public class UserBrowser {
	
	private UserManager manager;
	
	public UserBrowser(UserManager manager) {
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
	
	public static String listToString(List<User> users) {
		StringBuilder builder = new StringBuilder();
		for(User user : users) {
			builder.append(user.toString());
			builder.append("\n");
		}
		return builder.toString();
	}
}
