package data.storeItem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StoreItemTest {

	@Test
	void testStoreItem() {
		StoreItem item = new StoreItem();
		assertEquals(-1, item.getId());
		assertEquals(0, item.getName().length());
		assertEquals(0, item.getPrice());
		assertEquals(0, item.getDescription().length());
		assertEquals(false, item.isActive());
	}

	@Test
	void testStoreItemIntStringDouble() {
		long id = 234;
		String name = "Pizza";
		double price = 9.99;
		
		StoreItem item = new StoreItem(id, name, price);
		
		assertEquals(id, item.getId());
		assertEquals(name, item.getName());
		assertEquals(price, item.getPrice());
		assertEquals("", item.getDescription());
		assertEquals(true, item.isActive());
	}
	
	@Test
	void testStoreItemIntStringDoubleString() {
		long id = 234;
		String name = "Pizza";
		double price = 9.99;
		String description = "This is a very tasty pizza!";
		
		StoreItem item = new StoreItem(id, name, price, description);
		
		assertEquals(id, item.getId());
		assertEquals(name, item.getName());
		assertEquals(price, item.getPrice());
		assertEquals(description, item.getDescription());
		assertEquals(true, item.isActive());
	}

	@Test
	void testStoreItemIntStringDoubleStringBoolean() {
		long id = 333;
		String name = "Pizza";
		double price = 9.99;
		String description = "This is a very tasty pizza!";
		boolean active = false;
		
		StoreItem item = new StoreItem(id, name, price, description, active);
		
		assertEquals(id, item.getId());
		assertNotEquals(-1, item.getId());
		assertEquals(name, item.getName());
		assertEquals(price, item.getPrice());
		assertEquals(description, item.getDescription());
		assertEquals(active, item.isActive());
	}
	
	@Test
	void testSetId() {
		long id = 344;
		StoreItem item = new StoreItem();
		item.setId(id);
		assertEquals(id, item.getId());
	}
	
	@Test
	void testSetName() {
		String name = "Pizza";
		StoreItem item = new StoreItem();
		item.setName(name);
		assertEquals(name, item.getName());
	}

	@Test
	void testSetPrice() {
		double price = 9.99;
		StoreItem item = new StoreItem();
		item.setPrice(price);
		assertEquals(price, item.getPrice());
	}

	@Test
	void testSetDescription() {
		String description = "This is a very tasty pizza!";
		StoreItem item = new StoreItem();
		item.setDescription(description);
		assertEquals(description, item.getDescription());
	}

	@Test
	void testSetActive() {
		boolean active = true;
		StoreItem item = new StoreItem();
		item.setActive(active);
		assertEquals(active, item.isActive());
	}

	@Test
	void testToString() {
		long id = 333;
		String name = "Pizza";
		double price = 9.99;
		String description = "This is a very tasty pizza!";
		boolean active = false;
		
		StoreItem item = new StoreItem(id, name, price, description, active);
		
		String expected = "ID: " + id + "; Name: " + name + "; Price: " + price + "BGN; Description: " + description + "; Active: " + active;
		assertEquals(expected, item.toString());
	}
}
