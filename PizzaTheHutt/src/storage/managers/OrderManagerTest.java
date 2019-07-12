package storage.managers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.order.Order;
import data.order.OrderStatus;
import data.storeItem.StoreItem;

class OrderManagerTest {
	
	private static final File testDbFile = new File("data\\testorders.json");

	@Test
	void testOrderManager() throws IOException {
		OrderManager manager = new OrderManager();
		manager.getAll();
	}

	@Test
	void testOrderManagerFile() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		manager.load();
		assertThrows(IllegalArgumentException.class, () -> manager.save());
	}

	@Test
	void testAdd() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		
		long id = 334;
		long userId = 123;
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.NEW;
		long timestamp = 23452354;
		
		Order order1 = new Order(id, userId, items, status, timestamp);
		Order order2 = new Order(id, userId, items, OrderStatus.FINISHED, timestamp + 2345);
		Order order3 = new Order();
		order3.setId(34);
		Order order4 = new Order(id + 1, userId, items, status, timestamp);
		
		manager.add(order1);
		manager.add(order4);
		assertThrows(IllegalArgumentException.class, () -> manager.add(order1));
		assertThrows(IllegalArgumentException.class, () -> manager.add(order2));
		assertThrows(IllegalArgumentException.class, () -> manager.add(null));
		assertThrows(IllegalArgumentException.class, () -> manager.add(order3));
	}

	@Test
	void testGet() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		
		long id = 334;
		long userId = 123;
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.NEW;
		long timestamp = 23452354;
		Order order1 = new Order(id, userId, items, status, timestamp);
		
		assertThrows(IllegalArgumentException.class, () -> manager.get(-1));
		assertThrows(IllegalArgumentException.class, () -> manager.get(0));
		manager.add(order1);
		assertEquals(order1, manager.get(id));
		assertThrows(IllegalArgumentException.class, () -> manager.get(id + 1));
	}

	@Test
	void testGetAll() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		assertTrue(manager.getAll().isEmpty());
		
		long id = 334;
		long userId = 123;
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.NEW;
		long timestamp = 23452354;
		Order order = new Order(id, userId, items, status, timestamp);
		
		manager.add(order);
		Order order2 = new Order(manager.reserveId(), userId, items, status, timestamp);
		manager.add(order2);
		assertTrue(!manager.getAll().isEmpty());
		
		ArrayList<Order> users = new ArrayList<Order>(manager.getAll());
		assertEquals(order, users.get(0));
		assertEquals(order2, users.get(1));	
	}

	@Test
	void testUpdate() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		
		long id = 334;
		long userId = 123;
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.NEW;
		long timestamp = 23452354;
		Order order1 = new Order(id, userId, items, status, timestamp);
		Order order2 = new Order(id, userId, items, OrderStatus.FINISHED, timestamp ++);

		assertThrows(IllegalArgumentException.class, () -> manager.update(0, order1));
		manager.add(order1);
		assertThrows(IllegalArgumentException.class, () -> manager.update(0, null));
		assertThrows(IllegalArgumentException.class, () -> manager.update(-1, new Order()));
		assertThrows(IllegalArgumentException.class, () -> manager.update(0 , new Order()));
		assertThrows(IllegalArgumentException.class, () -> manager.update(0 , order2));
		manager.update(id , order2);
		assertEquals(order2, manager.get(id));
	}

	@Test
	void testDelete() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		
		long id = 334;
		long userId = 123;
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.NEW;
		long timestamp = 23452354;
		Order order = new Order(id, userId, items, status, timestamp);

		assertThrows(IllegalArgumentException.class, () -> manager.delete(0));
		assertThrows(IllegalArgumentException.class, () -> manager.delete(-1));
		manager.add(order);
		assertThrows(IllegalArgumentException.class, () -> manager.delete(0));
		manager.delete(order.getId());
	}

	@Test
	void testLoad() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);

		manager.load();
		assertTrue(manager.getAll().isEmpty());

		long id = 334;
		long userId = 123;
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.NEW;
		long timestamp = 23452354;
		Order order = new Order(id, userId, items, status, timestamp);
		
		manager.add(order);
		manager.save();
		
		manager.load();
		assertEquals(order.toString(), manager.get(order.getId()).toString());
	}

	@Test
	void testSave() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		assertThrows(IllegalArgumentException.class, () -> manager.save());

		long id = 334;
		long userId = 123;
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.NEW;
		long timestamp = 23452354;
		Order order = new Order(id, userId, items, status, timestamp);
		
		manager.add(order);
		manager.save();
		manager.load();
		assertEquals(order.toString(), manager.get(order.getId()).toString());
	}

	@Test
	void testReserveId() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		assertEquals(0, manager.reserveId());
		assertEquals(1, manager.reserveId());

		long id = 334;
		long userId = 123;
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.NEW;
		long timestamp = 23452354;
		Order order = new Order(id, userId, items, status, timestamp);
		
		manager.add(order);
		assertEquals(order.getId() + 1, manager.reserveId());
		
		manager.load();
		assertEquals(0, manager.reserveId());
		
		manager.add(order);
		Order order2 = new Order(manager.reserveId(), userId, items, status, timestamp);
		manager.add(order2);
		
		assertEquals(order2.getId(), order.getId() + 1);
	}
	
	@BeforeEach
	@AfterEach
	void testDbCleanup(){
		testDbFile.delete();
	}
}
