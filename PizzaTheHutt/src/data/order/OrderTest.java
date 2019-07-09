package data.order;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import data.storeItem.StoreItem;

class OrderTest {

	@Test
	void testOrder() {
		Order order = new Order();
		assertEquals(-1, order.getId());
		assertEquals(null, order.getItems());
		assertEquals(OrderStatus.UNASSIGNED, order.getStatus());
		assertEquals(0, order.getTimestamp());
	}

	@Test
	void testOrderArrayListOfStoreItemOrderStatusDate() {
		ArrayList<StoreItem> items = new ArrayList<>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.NEW;
		long timestamp = 3345;
		int lastID = Order.getLastId();
		
		Order order = new Order(items, status, timestamp);
		assertEquals(lastID + 1, order.getId());
		assertEquals(items, order.getItems());
		assertEquals(status, order.getStatus());
		assertEquals(timestamp, order.getTimestamp());
	}

	@Test
	void testOrderIntArrayListOfStoreItemOrderStatusDate() {
		int id = 445;
		ArrayList<StoreItem> items = new ArrayList<>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.NEW;
		long timestamp = 3345;
		
		Order order = new Order(id, items, status, timestamp);
		assertEquals(id, order.getId());
		assertEquals(items, order.getItems());
		assertEquals(status, order.getStatus());
		assertEquals(timestamp, order.getTimestamp());
	}

	@Test
	void testSetLastId() {
		int lastID = 33;
		Order.setLastId(lastID);
		assertEquals(lastID, Order.getLastId());
	}

	@Test
	void testSetItems() {
		ArrayList<StoreItem> items = new ArrayList<>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		
		Order order = new Order();
		order.setItems(items);
		
		assertEquals(items, order.getItems());
	}

	@Test
	void testSetStatus() {
		OrderStatus status = OrderStatus.PROCESSING;
		
		Order order = new Order();
		order.setStatus(status);
		
		assertEquals(status, order.getStatus());
	}

	@Test
	void testSetTimestamp() {
		long timestamp = 5565;
		
		Order order = new Order();
		order.setTimestamp(timestamp);
		
		assertEquals(timestamp, order.getTimestamp());
	}

	@Test
	void testToString() {
		int id = 445;
		ArrayList<StoreItem> items = new ArrayList<>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.NEW;
		long timestamp = 3345;
		
		Order order = new Order(id, items, status, timestamp);
		
		StringBuilder expected = new StringBuilder();
		expected.append("ID: " + id + "; Status: " + status);
		expected.append("; Timestamp: " + new Date(timestamp));
		expected.append("; Item count: " + items.size() + "; Items: \n");
		for(StoreItem item : items) {
			expected.append(item + "\n");
		}
		assertEquals(expected.toString(), order.toString());
	}

}
