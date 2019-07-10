package storage.json;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import data.user.User;
import data.user.UserRoles;

class JacksonJsonParserTest {
	private static final File testDbFile = new File("data\\testusers.json");
	
	@Test
	void testJacksonJsonParserFile() throws IOException {
		JacksonJsonParser<User> data = new JacksonJsonParser<User>(User.class, testDbFile);
		data.load();
	}

	@Test
	void testLoad() throws IOException {
		JacksonJsonParser<User> data = new JacksonJsonParser<User>(User.class, testDbFile);
		testDbFile.delete();
		assertThrows(IOException.class, () -> data.load());
		testDbFile.createNewFile();
		ArrayList<User> returnedData = new ArrayList<User>(data.load());
		assertTrue(returnedData.isEmpty());
		
		ArrayList<User> items = new ArrayList<User>();
		User user1 = new User(23, "asd", "asd", UserRoles.MANAGER);
		User user2 = new User(234, "asdf", "asdf", UserRoles.USER);
		items.add(user2);
		items.add(user1);
		
		data.save(items);
		returnedData = new ArrayList<User>(data.load());

		assertFalse(returnedData.isEmpty());
		assertEquals(items.size(), returnedData.size());
		assertEquals(user2.toString(), returnedData.get(0).toString());
		assertEquals(user1.toString(), returnedData.get(1).toString());
	}

	@Test
	void testSave() throws IOException {
		JacksonJsonParser<User> data = new JacksonJsonParser<User>(User.class, testDbFile);
		ArrayList<User> items = new ArrayList<User>();
		User user1 = new User(23, "asd", "asd", UserRoles.MANAGER);
		User user2 = new User(234, "asdf", "asdf", UserRoles.USER);
		items.add(user1);
		items.add(user2);

		testDbFile.delete();
		
		assertThrows(IllegalArgumentException.class, () -> data.save(null));
		data.save(items);
		ArrayList<User> users = new ArrayList<>(data.load());
		
		assertEquals(user1.toString(), users.get(0).toString());
		assertEquals(user2.toString(), users.get(1).toString());
		
		data.save(items);
		
		assertThrows(IllegalArgumentException.class, () -> data.save(null));
		assertThrows(IllegalArgumentException.class, () -> data.save(new ArrayList<>()));
	}

	@AfterEach
	void testDbCleanup(){
		testDbFile.delete();
	}
}
