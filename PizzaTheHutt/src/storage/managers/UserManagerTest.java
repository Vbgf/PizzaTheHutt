package storage.managers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.user.User;
import data.user.UserRoles;

class UserManagerTest {

	private static final File testDbFile = new File("data\\testusers.json");
	
	@Test
	void testUserManager() throws IOException {
		UserManager manager = new UserManager();
		manager.getAll();
	}

	@Test
	void testUserManagerFile() throws IOException {
		UserManager manager = new UserManager(testDbFile);
		manager.load();
		assertThrows(IllegalArgumentException.class, () -> manager.save());
	}

	@Test
	void testAdd() throws IOException {
		UserManager manager = new UserManager(testDbFile);
		
		long userId = 23;
		String username = "asd";
		String password = "asd";
		UserRoles role = UserRoles.MANAGER;
		User user1 = new User(userId, username, password, role);
		User user2 = new User(userId, username + "123", password + "123", UserRoles.USER);
		User user3 = new User();
		user3.setId(34);
		User user4 = new User(userId + 1, username + "123", password + "123", UserRoles.USER);
		
		manager.add(user1);
		manager.add(user4);
		assertThrows(IllegalArgumentException.class, () -> manager.add(user1));
		assertThrows(IllegalArgumentException.class, () -> manager.add(user2));
		assertThrows(IllegalArgumentException.class, () -> manager.add(null));
		assertThrows(IllegalArgumentException.class, () -> manager.add(user3));
	}

	@Test
	void testGet() throws IOException {
		UserManager manager = new UserManager(testDbFile);
		
		long userId = 23;
		String username = "asd";
		String password = "asd";
		UserRoles role = UserRoles.MANAGER;
		User user1 = new User(userId, username, password, role);
		
		assertThrows(IllegalArgumentException.class, () -> manager.get(-1));
		assertThrows(IllegalArgumentException.class, () -> manager.get(0));
		manager.add(user1);
		assertEquals(user1, manager.get(userId));
		assertThrows(IllegalArgumentException.class, () -> manager.get(userId + 1));
	}

	@Test
	void testGetAll() throws IOException {
		UserManager manager = new UserManager(testDbFile);
		assertTrue(manager.getAll().isEmpty());
		
		User user = new User(manager.reserveId(), "Username", "Password", UserRoles.ADMINISTRATOR);
		manager.add(user);
		User user2 = new User(manager.reserveId(), "Username", "Password", UserRoles.ADMINISTRATOR);
		manager.add(user2);
		assertTrue(!manager.getAll().isEmpty());
		
		ArrayList<User> users = new ArrayList<User>(manager.getAll());
		assertEquals(user, users.get(0));
		assertEquals(user2, users.get(1));		
	}

	@Test
	void testUpdate() throws IOException {
		UserManager manager = new UserManager(testDbFile);
		
		long userId = 23;
		String username = "asd";
		String password = "asd";
		UserRoles role = UserRoles.MANAGER;
		User user1 = new User(userId, username, password, role);
		User user2 = new User(userId, username + "123", password + "123", UserRoles.USER);

		assertThrows(IllegalArgumentException.class, () -> manager.update(0, user1));
		manager.add(user1);
		assertThrows(IllegalArgumentException.class, () -> manager.update(0, null));
		assertThrows(IllegalArgumentException.class, () -> manager.update(-1, new User()));
		assertThrows(IllegalArgumentException.class, () -> manager.update(0 , new User()));
		assertThrows(IllegalArgumentException.class, () -> manager.update(0 , user2));
		manager.update(userId , user2);
		assertEquals(user2, manager.get(userId));
	}

	@Test
	void testDelete() throws IOException {
		UserManager manager = new UserManager(testDbFile);
		User user1 = new User(23, "asd", "asd", UserRoles.MANAGER);

		assertThrows(IllegalArgumentException.class, () -> manager.delete(0));
		assertThrows(IllegalArgumentException.class, () -> manager.delete(-1));
		manager.add(user1);
		assertThrows(IllegalArgumentException.class, () -> manager.delete(0));
		manager.delete(user1.getId());
	}

	@Test
	void testLoad() throws IOException {
		UserManager manager = new UserManager(testDbFile);

		manager.load();
		assertTrue(manager.getAll().isEmpty());
		
		User user1 = new User(23, "asd", "asd", UserRoles.MANAGER);
		manager.add(user1);
		manager.save();
		
		manager.load();
		assertEquals(user1.toString(), manager.get(user1.getId()).toString());
	}

	@Test
	void testSave() throws IOException {
		UserManager manager = new UserManager(testDbFile);
		assertThrows(IllegalArgumentException.class, () -> manager.save());
		User user1 = new User(23, "asd", "asd", UserRoles.MANAGER);
		manager.add(user1);
		manager.save();
		manager.load();
		assertEquals(user1.toString(), manager.get(user1.getId()).toString());
	}
	
	@Test
	void testReserveId() throws IOException {
		UserManager manager = new UserManager(testDbFile);
		assertEquals(0, manager.reserveId());
		assertEquals(1, manager.reserveId());

		User user1 = new User(23, "asd", "asd", UserRoles.MANAGER);
		manager.add(user1);
		assertEquals(user1.getId() + 1, manager.reserveId());
		
		manager.load();
		assertEquals(0, manager.reserveId());
		
		manager.add(user1);
		User user = new User(manager.reserveId(), "Username", "Password", UserRoles.ADMINISTRATOR);
		manager.add(user);
		
		assertEquals(user.getId(), user1.getId() + 1);
	}
	
	@BeforeEach
	@AfterEach
	void testDbCleanup(){
		testDbFile.delete();
	}
}
