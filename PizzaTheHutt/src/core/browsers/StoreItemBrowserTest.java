package core.browsers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.storeItem.StoreItem;
import storage.managers.StoreItemManager;

class StoreItemBrowserTest {

	private static final File testDbFile = new File("data\\testitems.json");
	
	@Test
	void testStoreItemBrowser() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);
		StoreItemBrowser browser = new StoreItemBrowser(manager);
		browser.getActive();
	}

	@Test
	void testGetActive() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);
		StoreItemBrowser browser = new StoreItemBrowser(manager);
		
		StoreItem item1 = new StoreItem(manager.reserveId(), "Pizza 1", 9.99, "The first pizza", true);
		StoreItem item2 = new StoreItem(manager.reserveId(), "Pizza 2", 19.99, "The second pizza", false);
		StoreItem item3 = new StoreItem(manager.reserveId(), "Pizza 3", 29.99, "The third pizza", false);
		StoreItem item4 = new StoreItem(manager.reserveId(), "Pizza 4", 29.99, "The fourth pizza", true);
		
		assertTrue(browser.getActive().isEmpty());
		
		manager.add(item1);
		manager.add(item2);
		manager.add(item3);
		manager.add(item4);
		
		assertEquals(2, browser.getActive().size());
		assertEquals(item1.toString(), browser.getActive().get(0).toString());
		assertEquals(item4.toString(), browser.getActive().get(1).toString());
	}

	@Test
	void testGetInactive() throws IOException {
		StoreItemManager manager = new StoreItemManager(testDbFile);
		StoreItemBrowser browser = new StoreItemBrowser(manager);
		
		StoreItem item1 = new StoreItem(manager.reserveId(), "Pizza 1", 9.99, "The first pizza", true);
		StoreItem item2 = new StoreItem(manager.reserveId(), "Pizza 2", 19.99, "The second pizza", false);
		StoreItem item3 = new StoreItem(manager.reserveId(), "Pizza 3", 29.99, "The third pizza", false);
		StoreItem item4 = new StoreItem(manager.reserveId(), "Pizza 4", 29.99, "The fourth pizza", true);
		
		assertTrue(browser.getInactive().isEmpty());
		
		manager.add(item1);
		manager.add(item2);
		manager.add(item3);
		manager.add(item4);
		
		assertEquals(2, browser.getInactive().size());
		assertEquals(item2.toString(), browser.getInactive().get(0).toString());
		assertEquals(item3.toString(), browser.getInactive().get(1).toString());
	}
	
	@BeforeEach
	@AfterEach
	void testDbCleanup(){
		testDbFile.delete();
	}
}
