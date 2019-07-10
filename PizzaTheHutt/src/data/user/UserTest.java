package data.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void testUser() {
		User user = new User();
		assertEquals(-1, user.getId());
		assertEquals(0, user.getUsername().length());
		assertEquals(0, user.getPassword().length());
		assertEquals(UserRoles.UNASSIGNED, user.getRole());
	}

	@Test
	void testUserIntStringStringUserRoles() {
		long id = 123;
		String username = "Sasho";
		String password = "123456";
		UserRoles role = UserRoles.USER;
		
		User user = new User(id, username, password, role);
		assertEquals(id, user.getId());
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		assertEquals(role, user.getRole());
	}

	@Test
	void testSetId() {
		long id = 55;
		User user = new User();
		user.setId(id);
		assertEquals(id, user.getId());
	}
	
	@Test
	void testSetUsername() {
		String username = "Sasha";
		User user = new User();
		user.setUsername(username);
		assertEquals(username, user.getUsername());
	}
	
	@Test
	void testSetPassword() {
		String password = "78979789";
		User user = new User();
		user.setPassword(password);
		assertEquals(password, user.getPassword());
	}
	
	@Test
	void testSetRole() {
		UserRoles role = UserRoles.ADMINISTRATOR;
		User user = new User();
		user.setRole(role);
		assertEquals(role, user.getRole());
	}
	
	@Test
	void testToString() {
		long id = 123;
		String username = "Sasho";
		String password = "123456";
		UserRoles role = UserRoles.USER;
		User user = new User(id, username, password, role);
		
		String expected = "ID: " + id + "; Username: " + username + "; Password: " + password + "; Role: " + role;
		assertEquals(expected, user.toString());
	}

}
