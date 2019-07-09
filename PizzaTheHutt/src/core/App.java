package core;

import java.io.IOException;

import storage.json.StorageUsersJson;

public class App {

	public static void main(String[] args) {		
		IPersistantData storage = new StorageUsersJson();
		try {
			storage.load();
		} catch (IOException e) {
			System.out.println("Could not connect to the database, program cannot continue.");
		}
		
		try {
			storage.addUser("", "");
		}catch(IllegalArgumentException e ) {
			System.out.println("Could not add user! User already exists!");
		}catch (NullPointerException e) {
			System.out.println("Invalid username and password!");
		}

		try {
			storage.addUser("Pesho", "123");
		}catch(IllegalArgumentException e ) {
			System.out.println("Could not add user! User already exists!");
		}catch (NullPointerException e) {
			System.out.println("Invalid username and password!");
		}
		
		try {
			storage.save();
		} catch (IOException e) {
			System.out.println("Could not connect to the database, program cannot continue.");
		}
	}
}
