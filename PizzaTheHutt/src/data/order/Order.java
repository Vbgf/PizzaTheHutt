package data.order;

import java.util.ArrayList;
import java.util.Date;

import data.storeItem.StoreItem;

public class Order {
	private static int lastID;
	
	private int id;
	private ArrayList<StoreItem> items;
	private OrderStatus status;
	private long timestamp;
	
	public Order() {
		setId(-1);
		this.items = null;
		this.status = OrderStatus.UNASSIGNED;
		this.timestamp = 0;
	}
	
	public Order(ArrayList<StoreItem> items, OrderStatus status, long timestamp) {
		setId(lastID + 1);
		this.items = items;
		this.status = status;
		this.timestamp = timestamp;
	}
	
	public Order(int id, ArrayList<StoreItem> items, OrderStatus status, long timestamp) {
		setId(id);
		this.items = items;
		this.status = status;
		this.timestamp = timestamp;
	}
	
	public static int getLastId() {
		return lastID;
	}

	public static void setLastId(int lastID) {
		Order.lastID = lastID;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
		
		if(lastID < id) {
			lastID = id;
		}
	}
	
	public ArrayList<StoreItem> getItems() {
		return items;
	}
	
	public void setItems(ArrayList<StoreItem> items) {
		this.items = items;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString () {
		StringBuilder builder = new StringBuilder();
		builder.append("ID: " + this.id + "; ");
		builder.append("Status: " + this.status + "; ");
		builder.append("Timestamp: " + new Date(timestamp) + "; ");
		builder.append("Item count: " + this.items.size() + "; ");
		builder.append("Items: \n");
		
		for(StoreItem item : this.items) {
			builder.append(item + "\n");
		}
		return builder.toString();
	}
}
