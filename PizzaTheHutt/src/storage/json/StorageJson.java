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
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import core.IPersistantData;
import data.user.User;
import data.user.UserRoles;

public class StorageJson implements IPersistantData{

	private static final File DB_FILE = new File("data\\users.json");
	private static final Charset ENCODING = StandardCharsets.UTF_8;
	
	private Gson gson;
	private ArrayList<User> users;
	private File dbFile;
	
	public StorageJson() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		users = new ArrayList<User>();
		dbFile = DB_FILE;
	}
	
	public StorageJson(File dbFile){
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

		User[] userArray = gson.fromJson(streamReader, User[].class);
		if(userArray != null) {
			users = new ArrayList<User>(Arrays.asList(userArray));
		}
		
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
