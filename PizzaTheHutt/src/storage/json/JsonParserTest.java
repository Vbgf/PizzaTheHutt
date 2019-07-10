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

class JsonParserTest {

	private static final File testDbFile = new File("data\\testusers.json");
	
	@Test
	void testJsonParserFileListOfT() throws IOException{
		JsonParser<User> data = new JsonParser<User>(testDbFile);
		data.load();
	}

	@Test
	void testJsonParserFileListOfTCharset() throws IOException{
		Charset encoding = StandardCharsets.UTF_8;
		JsonParser<User> data = new JsonParser<User>(testDbFile, encoding);
		data.load();
	}

	@Test
	void testLoad() throws IOException{
		JsonParser<User> data = new JsonParser<User>(testDbFile);
		testDbFile.delete();
		assertThrows(IOException.class, () -> data.load());
		testDbFile.createNewFile();
		data.load();
	}

	@Test
	void testSave() throws IOException{
		JsonParser<User> data = new JsonParser<User>(testDbFile);
		ArrayList<User> items = new ArrayList<>();
		items.add(new User());
		items.add(new User());

		testDbFile.delete();
		
		data.save(items);
		data.load();
		data.save(items);
		
		assertThrows(IllegalArgumentException.class, () -> data.save(null));
		assertThrows(IllegalArgumentException.class, () -> data.save(new ArrayList<>()));
	}
	
	@AfterEach
	void testDbCleanup(){
		testDbFile.delete();
	}
}
