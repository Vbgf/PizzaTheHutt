package storage.json;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import data.user.User;
import data.user.UserRoles;

class JacksonJsonParserTest {
	private static final File testDbFile = new File("data\\testusers.json");
	private static final File realDbFile = new File("data\\users.json");

	@Test
	void testJacksonJsonParserFile() throws IOException {
		JacksonJsonParser<User> data = new JacksonJsonParser<User>(testDbFile);
	}

	@Test
	void testJacksonJsonParserFileCharset() throws IOException {
		Charset encoding = StandardCharsets.UTF_8;
		JacksonJsonParser<User> data = new JacksonJsonParser<User>(testDbFile, encoding);
	}

	@Test
	void testLoad() throws IOException {
		/*
		JacksonJsonParser<User> data = new JacksonJsonParser<User>(realDbFile);
		//realDbFile.delete();
		assertThrows(IOException.class, () -> data.load());
		realDbFile.createNewFile();
		ArrayList<User> returnedData = new ArrayList<User>(data.load());
		
		for(User user : returnedData) {
			System.out.println(user);
		}
		*/
	}

	@Test
	void testSave() throws IOException {
		JacksonJsonParser<User> data = new JacksonJsonParser<User>(testDbFile);
		ArrayList<User> items = new ArrayList<User>();

		User user1 = new User(23, "asd", "asd", UserRoles.MANAGER);
		User user2 = new User(234, "asdf", "asdf", UserRoles.USER);
		items.add(user1);
		items.add(user2);

		testDbFile.delete();
		
		data.save(items);
		ArrayList<User> users = new ArrayList<>(data.load());
		
		assertEquals(user1.getId(), users.get(0).getId());
		assertEquals(user1, users.get(1));
		
		data.save(items);
		
		assertThrows(IllegalArgumentException.class, () -> data.save(null));
		assertThrows(IllegalArgumentException.class, () -> data.save(new ArrayList<>()));
	}

	/*
	@AfterEach
	void testDbCleanup(){
		testDbFile.delete();
	}
	*/
}
