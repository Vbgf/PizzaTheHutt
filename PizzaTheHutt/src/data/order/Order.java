package data.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.storeItem.StoreItem;

public class Order {
	private static final long DEFAULT_ID = -1;
	private static final OrderStatus DEFAULT_STATUS = OrderStatus.UNASSIGNED;
	private static final long DEFAULT_TIMESTAMP = -1;
	
	private long id;
	private List<StoreItem> items;
	private OrderStatus status;
	private long timestamp;
	
	public Order() {
		this(DEFAULT_ID, null, DEFAULT_STATUS, DEFAULT_TIMESTAMP);
	}
	
	public Order(long id, List<StoreItem> items, OrderStatus status, long timestamp) {
		this.id = id;
		if(items != null) {
			this.items = new ArrayList<>(items);
		}else {
			this.items = new ArrayList<>();
		}
		this.status = status;
		this.timestamp = timestamp;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public List<StoreItem> getItems() {
		return items;
	}
	
	public void setItems(List<StoreItem> items) {
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
