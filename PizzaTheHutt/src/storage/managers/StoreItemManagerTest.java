package storage.managers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.storeItem.StoreItem;

class StoreItemManagerTest {
	
	private static final File testDbFile = new File("data\\testitems.json");
	
	@Test
	void testStoreItemManager() throws IOException {
		StoreItemManager manager = new StoreItemManager();
		manager.getAll();
	}

	@Test
	void testStoreItemManagerFile() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);
		manager.load();
		assertThrows(IllegalArgumentException.class, () -> manager.save());
	}

	@Test
	void testAdd() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);
		
		long id = 334;
		String name = "sampleName";
		double price = 9.99;
		String description = "The best one!";
		boolean active = true;
		
		StoreItem item1 = new StoreItem(id, name, price, description, active);
		StoreItem item2 = new StoreItem(id, name + "123", price + 45.95, description + " For sure!", active);
		StoreItem item3 = new StoreItem();
		item3.setId(34);
		StoreItem item4 = new StoreItem(id + 1, name + "123", price + 45.95, description + " For sure!", active);
		
		manager.add(item1);
		manager.add(item4);
		assertThrows(IllegalArgumentException.class, () -> manager.add(item1));
		assertThrows(IllegalArgumentException.class, () -> manager.add(item2));
		assertThrows(IllegalArgumentException.class, () -> manager.add(null));
		assertThrows(IllegalArgumentException.class, () -> manager.add(item3));
	}

	@Test
	void testGet() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);
		
		long id = 334;
		String name = "sampleName";
		double price = 9.99;
		String description = "The best one!";
		boolean active = true;
		StoreItem item1 = new StoreItem(id, name, price, description, active);
		
		assertThrows(IllegalArgumentException.class, () -> manager.get(-1));
		assertThrows(IllegalArgumentException.class, () -> manager.get(0));
		manager.add(item1);
		assertEquals(item1, manager.get(id));
		assertThrows(IllegalArgumentException.class, () -> manager.get(id + 1));
	}

	@Test
	void testGetAll() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);
		assertTrue(manager.getAll().isEmpty());
		
		StoreItem item = new StoreItem(manager.reserveId(), "Name", 45.95, "Bes one For sure!", true);
		manager.add(item);
		StoreItem item2 = new StoreItem(manager.reserveId(), "Name", 45.95, "Bes one For sure!", true);
		manager.add(item2);
		assertTrue(!manager.getAll().isEmpty());
		
		ArrayList<StoreItem> users = new ArrayList<StoreItem>(manager.getAll());
		assertEquals(item, users.get(0));
		assertEquals(item2, users.get(1));	
	}

	@Test
	void testUpdate() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);
		
		long id = 334;
		String name = "sampleName";
		double price = 9.99;
		String description = "The best one!";
		boolean active = true;
		StoreItem item1 = new StoreItem(id, name, price, description, active);
		StoreItem item2 = new StoreItem(id, name + "123", price + 45.95, description + " For sure!", active);

		assertThrows(IllegalArgumentException.class, () -> manager.update(0, item1));
		manager.add(item1);
		assertThrows(IllegalArgumentException.class, () -> manager.update(0, null));
		assertThrows(IllegalArgumentException.class, () -> manager.update(-1, new StoreItem()));
		assertThrows(IllegalArgumentException.class, () -> manager.update(0 , new StoreItem()));
		assertThrows(IllegalArgumentException.class, () -> manager.update(0 , item2));
		manager.update(id , item2);
		assertEquals(item2, manager.get(id));
	}

	@Test
	void testDelete() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);
		StoreItem item = new StoreItem(33, "Name", 45.95, "Bes one For sure!", true);

		assertThrows(IllegalArgumentException.class, () -> manager.delete(0));
		assertThrows(IllegalArgumentException.class, () -> manager.delete(-1));
		manager.add(item);
		assertThrows(IllegalArgumentException.class, () -> manager.delete(0));
		manager.delete(item.getId());
	}

	@Test
	void testLoad() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);

		manager.load();
		assertTrue(manager.getAll().isEmpty());

		StoreItem item = new StoreItem(33, "Name", 45.95, "Bes one For sure!", true);
		manager.add(item);
		manager.save();
		
		manager.load();
		assertEquals(item.toString(), manager.get(item.getId()).toString());
	}

	@Test
	void testSave() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);
		assertThrows(IllegalArgumentException.class, () -> manager.save());
		StoreItem item = new StoreItem(33, "Name", 45.95, "Bes one For sure!", true);
		manager.add(item);
		manager.save();
		manager.load();
		assertEquals(item.toString(), manager.get(item.getId()).toString());
	}

	@Test
	void testReserveId() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);
		assertEquals(0, manager.reserveId());
		assertEquals(1, manager.reserveId());

		StoreItem item = new StoreItem(33, "Name", 45.95, "Bes one For sure!", true);
		manager.add(item);
		assertEquals(item.getId() + 1, manager.reserveId());
		
		manager.load();
		assertEquals(0, manager.reserveId());
		
		manager.add(item);
		StoreItem item2 = new StoreItem(manager.reserveId(), "Name", 45.95, "Bes one For sure!", true);
		manager.add(item2);
		
		assertEquals(item2.getId(), item.getId() + 1);
	}
	
	@BeforeEach
	@AfterEach
	void testDbCleanup(){
		testDbFile.delete();
	}
}
