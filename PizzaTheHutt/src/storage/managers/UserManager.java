package storage.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.user.User;
import data.user.UserRoles;
import storage.json.Storage;
import storage.json.JacksonJsonParser;

public class UserManager implements Manager<User>{
	
	private static final File USERS_DB_FILE = new File("data\\users.json");

	private long nextAvailableId;
	private Storage<User> usersDb;
	private ArrayList<User> users;
	
	public UserManager() throws IOException {
		this(USERS_DB_FILE);
	}
	
	public UserManager(File dbFile) throws IOException {
		this.nextAvailableId = 0;
		dbFile.getParentFile().mkdirs();
		this.usersDb = new JacksonJsonParser<User>(User.class, dbFile);
		this.users = new ArrayList<User>();
		load();
	}

	@Override
	public void add(User newObject) throws IllegalArgumentException {
		if(newObject == null) {
			throw new IllegalArgumentException("Object is null");
		}
		
		if(newObject.getUsername().isEmpty() || newObject.getPassword().isEmpty() || newObject.getRole().equals(UserRoles.UNASSIGNED)) {
			throw new IllegalArgumentException("Username or password is empty or role is unassigned");
		}		
		
		for(User user : users) {
			if(user.getId() == newObject.getId() || user.getUsername().equals(newObject.getUsername())) {
				throw new IllegalArgumentException("User already exists");
			}
		}
		
		users.add(newObject);
		if(newObject.getId() >= nextAvailableId) {
			nextAvailableId = newObject.getId() + 1;
		}
	}

	@Override
	public User get(long objectId) throws IllegalArgumentException {
		if(objectId < 0) {
			throw new IllegalArgumentException("Invalid objectId");
		}
		
		for(User user : users) {
			if(user.getId() == objectId) {
				return user;
			}
		}
		
		throw new IllegalArgumentException("User not found");
	}

	@Override
	public List<User> getAll() {
		return users;
	}

	@Override
	public void update(long objectId, User updatedObject) throws IllegalArgumentException {
		if(updatedObject == null || objectId < 0) {
			throw new IllegalArgumentException("Object is null or objectId is invalid");
		}
		
		if(updatedObject.getUsername().isEmpty() || updatedObject.getPassword().isEmpty() || updatedObject.getRole().equals(UserRoles.UNASSIGNED)) {
			throw new IllegalArgumentException("Username or password is empty or role is unassigned");
		}
		
		for(User user : users) {
			if(user.getId() == objectId) {
				users.set(users.indexOf(user), updatedObject);
				return;
			}
		}
		
		throw new IllegalArgumentException("User not found");
	}

	@Override
	public void delete(long objectId) throws IllegalArgumentException {
		if(objectId < 0) {
			throw new IllegalArgumentException("Invalid objectId");
		}
		
		for(User user : users) {
			if(user.getId() == objectId) {
				users.remove(user);
				return;
			}
		}
		
		throw new IllegalArgumentException("User not found");
	}
	
	@Override
	public void load() throws IOException {
		List<User> returnedData = usersDb.load();
		users = new ArrayList<User>(returnedData);
		nextAvailableId = 0;
		for(User user : users) {
			if(user.getId() >= nextAvailableId) {
				nextAvailableId = user.getId() + 1;
			}
		}
	}

	@Override
	public void save() throws IOException {
		usersDb.save(users);
	}

	@Override
	public long reserveId() {
		long reservedId = nextAvailableId;
		nextAvailableId ++;
		return reservedId;
	}
}
