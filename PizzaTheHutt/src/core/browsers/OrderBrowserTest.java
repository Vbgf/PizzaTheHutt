package core.browsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.order.Order;
import data.order.OrderStatus;
import data.storeItem.StoreItem;
import storage.managers.OrderManager;

class OrderBrowserTest {

	private static final File testDbFile = new File("data\\testorders.json");
	
	@Test
	void testOrderBrowser() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		OrderBrowser browser = new OrderBrowser(manager);
		browser.getStatus(OrderStatus.NEW);
	}

	@Test
	void testGetStatus() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		OrderBrowser browser = new OrderBrowser(manager);
		
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		
		long userId = 123;
		
		Order order1 = new Order(manager.reserveId(), userId, items, OrderStatus.NEW, 100);
		Order order2 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 200);
		Order order3 = new Order(manager.reserveId(), userId, items, OrderStatus.CANCELED, 300);
		Order order4 = new Order(manager.reserveId(), userId, items, OrderStatus.FINISHED, 400);
		Order order5 = new Order(manager.reserveId(), userId, items, OrderStatus.NEW, 500);
		Order order6 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 600);
		Order order7 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 700);
		
		assertTrue(browser.getStatus(OrderStatus.NEW).isEmpty());
		assertTrue(browser.getStatus(OrderStatus.PROCESSING).isEmpty());
		assertTrue(browser.getStatus(OrderStatus.CANCELED).isEmpty());
		assertTrue(browser.getStatus(OrderStatus.FINISHED).isEmpty());
		
		manager.add(order1);
		manager.add(order2);
		manager.add(order3);
		manager.add(order4);
		manager.add(order5);
		manager.add(order6);
		manager.add(order7);
		
		assertEquals(2, browser.getStatus(OrderStatus.NEW).size());
		assertEquals(3, browser.getStatus(OrderStatus.PROCESSING).size());
		assertEquals(1, browser.getStatus(OrderStatus.CANCELED).size());
		assertEquals(1, browser.getStatus(OrderStatus.FINISHED).size());
		
	}

	@Test
	void testGetAllAfter() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		OrderBrowser browser = new OrderBrowser(manager);
		
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());

		long userId = 123;
		
		Order order1 = new Order(manager.reserveId(), userId, items, OrderStatus.NEW, 100);
		Order order2 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 200);
		Order order3 = new Order(manager.reserveId(), userId, items, OrderStatus.CANCELED, 300);
		Order order4 = new Order(manager.reserveId(), userId, items, OrderStatus.FINISHED, 400);
		Order order5 = new Order(manager.reserveId(), userId, items, OrderStatus.NEW, 500);
		Order order6 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 600);
		Order order7 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 700);
		
		assertTrue(browser.getAllAfter(0).isEmpty());
		
		manager.add(order1);
		manager.add(order2);
		manager.add(order3);
		manager.add(order4);
		manager.add(order5);
		manager.add(order6);
		manager.add(order7);
		
		assertEquals(3, browser.getAllAfter(order4.getTimestamp()).size());
	}

	@Test
	void testGetAllBefore() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		OrderBrowser browser = new OrderBrowser(manager);
		
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());

		long userId = 123;
		
		Order order1 = new Order(manager.reserveId(), userId, items, OrderStatus.NEW, 100);
		Order order2 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 200);
		Order order3 = new Order(manager.reserveId(), userId, items, OrderStatus.CANCELED, 300);
		Order order4 = new Order(manager.reserveId(), userId, items, OrderStatus.FINISHED, 400);
		Order order5 = new Order(manager.reserveId(), userId, items, OrderStatus.NEW, 500);
		Order order6 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 600);
		Order order7 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 700);
		
		assertTrue(browser.getAllBefore(0).isEmpty());
		
		manager.add(order1);
		manager.add(order2);
		manager.add(order3);
		manager.add(order4);
		manager.add(order5);
		manager.add(order6);
		manager.add(order7);
		
		assertEquals(3, browser.getAllBefore(order4.getTimestamp()).size());
	}

	@Test
	void testGetAllBetween() throws IOException {
		OrderManager manager = new OrderManager(testDbFile);
		OrderBrowser browser = new OrderBrowser(manager);
		
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem());
		items.add(new StoreItem());

		long userId = 123;
		
		Order order1 = new Order(manager.reserveId(), userId, items, OrderStatus.NEW, 100);
		Order order2 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 200);
		Order order3 = new Order(manager.reserveId(), userId, items, OrderStatus.CANCELED, 300);
		Order order4 = new Order(manager.reserveId(), userId, items, OrderStatus.FINISHED, 400);
		Order order5 = new Order(manager.reserveId(), userId, items, OrderStatus.NEW, 500);
		Order order6 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 600);
		Order order7 = new Order(manager.reserveId(), userId, items, OrderStatus.PROCESSING, 700);
		
		assertTrue(browser.getAllBetween(0, 1000).isEmpty());
		
		manager.add(order1);
		manager.add(order2);
		manager.add(order3);
		manager.add(order4);
		manager.add(order5);
		manager.add(order6);
		manager.add(order7);
		
		assertEquals(7, browser.getAllBetween(0, 1000).size());
		assertEquals(1, browser.getAllBetween(order2.getTimestamp(), order4.getTimestamp()).size());
		assertEquals(order3, browser.getAllBetween(order2.getTimestamp(), order4.getTimestamp()).get(0));
	}

	@BeforeEach
	@AfterEach
	void testDbCleanup(){
		testDbFile.delete();
	}
}
