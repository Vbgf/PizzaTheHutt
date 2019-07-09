package storage.json;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import data.user.UserRoles;

class StorageUsersJsonTest {
	
	private static final File testDbFile = new File("data\\testusers.json");

	@Test
	void testConstructor() throws IOException{
		StorageUsersJson data = new StorageUsersJson();
		data.load();
	}
	
	@Test
	void testLoad() throws IOException{
		StorageUsersJson data = new StorageUsersJson(testDbFile);
		if(testDbFile.exists()) {
			testDbFile.delete();
		}
		data.load();
		data.save();
		data.load();
	}

	@Test
	void testSave() throws IOException {
		StorageUsersJson data = new StorageUsersJson(testDbFile);
		if(testDbFile.exists()) {
			testDbFile.delete();
		}
		data.save();
		data.load();
		data.save();
	}

	@Test
	void testAuthUser() {
		String invalidUsername = "";
		String invalidPassword = "";
		String validUsername = "validUsername";
		String validPassword = "validPassword";
		
		StorageUsersJson data = new StorageUsersJson(testDbFile);
		assertThrows(NullPointerException.class, () -> data.authUser(invalidUsername, invalidPassword));
		assertThrows(IllegalArgumentException.class, () -> data.authUser(validUsername, validPassword));

		data.addUser(validUsername, validPassword);
		assertEquals(UserRoles.USER, data.authUser(validUsername, validPassword));
		data.removeUser(validUsername, validPassword);
		
	}

	@Test
	void testAddUser() {
		String invalidUsername = "";
		String invalidPassword = "";
		String validUsername = "TestThisUsername";
		String validPassword = "TestThisPassword";
		
		StorageUsersJson data = new StorageUsersJson(testDbFile);
		assertThrows(NullPointerException.class, () -> data.addUser(invalidUsername, invalidPassword));

		assertThrows(IllegalArgumentException.class, () -> data.authUser(validUsername, validPassword));
		data.addUser(validUsername, validPassword);
		assertEquals(UserRoles.USER, data.authUser(validUsername, validPassword));
		
		assertThrows(IllegalArgumentException.class, () -> data.addUser(validUsername, validPassword));
		
		data.removeUser(validUsername, validPassword);
		assertThrows(IllegalArgumentException.class, () -> data.authUser(validUsername, validPassword));
	}
	
	@Test
	void testRemoveUser() {
		String invalidUsername = "";
		String invalidPassword = "";
		String validUsername = "TestThisUsername";
		String validPassword = "TestThisPassword";
		
		StorageUsersJson data = new StorageUsersJson(testDbFile);
		assertThrows(NullPointerException.class, () -> data.removeUser(invalidUsername, invalidPassword));
		
		assertThrows(IllegalArgumentException.class, () -> data.authUser(validUsername, validPassword));
		assertThrows(IllegalArgumentException.class, () -> data.removeUser(validUsername, validPassword));
		
		data.addUser(validUsername, validPassword);
		data.removeUser(validUsername, validPassword);
		
		assertThrows(IllegalArgumentException.class, () -> data.authUser(validUsername, validPassword));
	}
	
	@AfterAll
	static void testDBCleanup(){
		if(testDbFile.exists()) {
			testDbFile.delete();
		}
	}

}
