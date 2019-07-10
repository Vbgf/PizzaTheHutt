package storage.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import data.user.User;
import data.user.UserRoles;
import storage.managers.Manager;

public class StorageUsersJson implements Manager{

	private static final File DB_FILE = new File("data\\users.json");
	private static final Charset ENCODING = StandardCharsets.UTF_8;
	
	private Gson gson;
	private ArrayList<User> users;
	private File dbFile;
	
	public StorageUsersJson() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		users = new ArrayList<User>();
		dbFile = DB_FILE;
	}
	
	public StorageUsersJson(File dbFile){
		gson = new GsonBuilder().setPrettyPrinting().create();
		users = new ArrayList<User>();
		
		this.dbFile = dbFile;
	}
	
	public void load() throws IOException {
		if (!dbFile.exists()) {
			dbFile.createNewFile();
			return;
		}

		FileInputStream inputStream = new FileInputStream(dbFile);
		InputStreamReader streamReader = new InputStreamReader(inputStream, ENCODING);

		users = gson.fromJson(streamReader, new TypeToken<ArrayList<User>>(){}.getType());
		
		inputStream.close();
		streamReader.close();
	}
	
	public void save() throws IOException{
		if (!dbFile.exists()) {
			dbFile.createNewFile();
		}

		FileOutputStream outputStream = new FileOutputStream(dbFile, false);
		OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, ENCODING);
		
		gson.toJson(users, streamWriter);
		
		streamWriter.close();
		outputStream.close();
	}

	@Override
	public UserRoles authUser(String username, String password) throws IllegalArgumentException, NullPointerException{
		if(username.isEmpty() || password.isEmpty()) {
			throw new NullPointerException();
		}
		
		if(!users.isEmpty()) {
			for(User user : users) {
				if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
					return user.getRole();
				}
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public void addUser(String username, String password) throws IllegalArgumentException, NullPointerException{
		if(username.isEmpty() || password.isEmpty()) {
			throw new NullPointerException();
		}
		
		if(!users.isEmpty()) {
			for(User user : users) {
				if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
					throw new IllegalArgumentException();
				}
			}
		}
		
		users.add( new User(username, password) );
	}

	@Override
	public void removeUser(String username, String password) throws IllegalArgumentException, NullPointerException {
		if(username.isEmpty() || password.isEmpty()) {
			throw new NullPointerException();
		}
		
		if(!users.isEmpty()) {
			for(User user : users) {
				if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
					users.remove(user);
					return;
				}
			}
		}

		throw new IllegalArgumentException();
		
	}
}
