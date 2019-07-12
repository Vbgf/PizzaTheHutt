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
		assertEquals(0, order.getItems().size());
		assertEquals(OrderStatus.UNASSIGNED, order.getStatus());
		assertEquals(-1, order.getTimestamp());
	}

	@Test
	void testOrderIntArrayListOfStoreItemOrderStatusDate() {
		long id = 445;
		long userid = 7;
		ArrayList<StoreItem> items = new ArrayList<>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.CANCELED;
		long timestamp = 3345;
		
		Order order = new Order(id, userid, items, status, timestamp);
		assertEquals(id, order.getId());
		assertEquals(items, order.getItems());
		assertEquals(status, order.getStatus());
		assertEquals(timestamp, order.getTimestamp());
	}

	@Test
	void testSetUserId() {
		long newUserId = 223454;
		Order order = new Order();
		order.setUserId(newUserId);
		
		assertEquals(newUserId, order.getUserId());
	}
	
	@Test
	void testSetId() {
		long newId = 2234;
		Order order = new Order();
		order.setId(newId);
		
		assertEquals(newId, order.getId());
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
		long id = 445;
		long userId = 13;
		ArrayList<StoreItem> items = new ArrayList<>();
		items.add(new StoreItem());
		items.add(new StoreItem());
		OrderStatus status = OrderStatus.FINISHED;
		long timestamp = 3345;
		
		Order order = new Order(id, userId, items, status, timestamp);
		
		StringBuilder expected = new StringBuilder();
		expected.append("ID: " + id + "; UserID: " + userId + "; Status: " + status);
		expected.append("; Timestamp: " + new Date(timestamp));
		expected.append("; Item count: " + items.size() + "; Items: \n");
		for(StoreItem item : items) {
			expected.append(item + "\n");
		}
		assertEquals(expected.toString(), order.toString());
	}

}
