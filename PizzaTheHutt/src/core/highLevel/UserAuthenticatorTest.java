package core.highLevel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.user.User;
import data.user.UserRoles;
import storage.managers.UserManager;

class UserAuthenticatorTest {

	private static final File testDbFile = new File("data\\testusers.json");
	
	@Test
	void testUserAuthenticator() throws IOException {
		UserManager manager = new UserManager(testDbFile);
		UserAuthenticator authenticator = new UserAuthenticator(manager);
		assertThrows(IllegalArgumentException.class, () -> authenticator.authenticate("", ""));
	}

	@Test
	void testAuthenticate() throws IOException {
		UserManager manager = new UserManager(testDbFile);
		UserAuthenticator authenticator = new UserAuthenticator(manager);
		
		String username = "Gosho";
		String password = "12345";
		UserRoles role = UserRoles.USER;
		User user = new User(manager.reserveId(), username, password, role);
		
		assertThrows(IllegalArgumentException.class, () -> authenticator.authenticate(username, password));
		
		manager.add(user);
		assertEquals(role, authenticator.authenticate(username, password));
		

		assertThrows(IllegalArgumentException.class, () -> authenticator.authenticate(username + "asd", password + "asd"));
	}
	
	@BeforeEach
	@AfterEach
	void testDbCleanup(){
		testDbFile.delete();
	}
}
