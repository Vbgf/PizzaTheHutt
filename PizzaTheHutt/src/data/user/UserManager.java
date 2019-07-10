package data.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import storage.json.Storage;
import storage.managers.Manager;
import storage.json.JsonParser;

public class UserManager implements Manager<User>{
	
	private static final File USERS_DB_FILE = new File("data\\users.json");

	private Storage<User> usersDb;
	private ArrayList<User> users;
	
	public UserManager() throws IOException {
		this(USERS_DB_FILE);
	}
	
	public UserManager(File dbFile) throws IOException {
		usersDb = new JsonParser<User>(dbFile);
		users = new ArrayList<User>();
		load();
	}

	@Override
	public void add(User newObject) throws IllegalArgumentException {
		if(newObject == null) {
			throw new IllegalArgumentException();
		}
		
		if(newObject.getUsername().isEmpty() || newObject.getPassword().isEmpty() || newObject.getRole().equals(UserRoles.UNASSIGNED)) {
			throw new IllegalArgumentException();
		}		
		
		if(!users.isEmpty()) {
			for(User user : users) {
				if(user.getId() == newObject.getId()) {
					throw new IllegalArgumentException();
				}
			}
		}
		
		users.add(newObject);
	}

	@Override
	public User get(long objectId) throws IllegalArgumentException {
		if(objectId < 0) {
			throw new IllegalArgumentException();
		}
		
		if(!users.isEmpty()) {
			for(User user : users) {
				if(user.getId() == objectId) {
					return user;
				}
			}
		}
		
		throw new IllegalArgumentException();
	}

	@Override
	public List<User> getAll() {
		return users;
	}

	@Override
	public void update(long objectId, User updatedObject) throws IllegalArgumentException {
		if(updatedObject == null || objectId < 0) {
			throw new IllegalArgumentException();
		}
		
		if(updatedObject.getUsername().isEmpty() || updatedObject.getPassword().isEmpty() || updatedObject.getRole().equals(UserRoles.UNASSIGNED)) {
			throw new IllegalArgumentException();
		}
		
		if(!users.isEmpty()) {
			for(User user : users) {
				if(user.getId() == objectId) {
					users.set(users.indexOf(user), updatedObject);
					return;
				}
			}
		}
		
		throw new IllegalArgumentException();
	}

	@Override
	public void delete(long objectId) throws IllegalArgumentException {
		if(objectId < 0) {
			throw new IllegalArgumentException();
		}
		
		if(!users.isEmpty()) {
			for(User user : users) {
				if(user.getId() == objectId) {
					users.remove(user);
					return;
				}
			}
		}
		
		throw new IllegalArgumentException();
	}
	
	@Override
	public void load() throws IOException {
		List<User> returnedData = usersDb.load();
		if(returnedData != null) {
			users = new ArrayList<User>(returnedData);
		}else {
			users = new ArrayList<User>();
		}
	}

	@Override
	public void save() throws IOException {
		usersDb.save(users);
	}
}
