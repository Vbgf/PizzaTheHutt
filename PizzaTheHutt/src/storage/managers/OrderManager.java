package storage.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.order.Order;
import data.order.OrderStatus;
import storage.json.JacksonJsonParser;
import storage.json.Storage;

public class OrderManager implements Manager<Order>{
	
	private static final File ORDERS_DB_FILE = new File("data\\orders.json");

	private long nextAvailableId;
	private Storage<Order> ordersDb;
	private ArrayList<Order> orders;
	
	public OrderManager() throws IOException {
		this(ORDERS_DB_FILE);
	}
	
	public OrderManager(File dbFile) throws IOException {
		this.nextAvailableId = 0;
		dbFile.getParentFile().mkdirs();
		this.ordersDb = new JacksonJsonParser<Order>(Order.class, dbFile);
		this.orders = new ArrayList<Order>();
		load();
	}

	@Override
	public void add(Order newObject) throws IllegalArgumentException {
		if(newObject == null) {
			throw new IllegalArgumentException();
		}
		
		if(newObject.getItems().isEmpty() || newObject.getStatus() == OrderStatus.UNASSIGNED || newObject.getTimestamp() == 0) {
			throw new IllegalArgumentException("Items are empty, status in unassigned or timestamp is 0");
		}		
		
		for(Order order : orders) {
			if(order.getId() == newObject.getId()) {
				throw new IllegalArgumentException("Order exists");
			}
		}
		
		orders.add(newObject);
		if(newObject.getId() >= nextAvailableId) {
			nextAvailableId = newObject.getId() + 1;
		}		
	}

	@Override
	public Order get(long objectId) throws IllegalArgumentException {
		if(objectId < 0) {
			throw new IllegalArgumentException("Invalid objectId");
		}
		
		for(Order order : orders) {
			if(order.getId() == objectId) {
				return order;
			}
		}
		
		throw new IllegalArgumentException("Order not found");
	}

	@Override
	public List<Order> getAll() {
		return orders;
	}

	@Override
	public void update(long objectId, Order updatedObject) throws IllegalArgumentException {
		if(updatedObject == null || objectId < 0) {
			throw new IllegalArgumentException("Object is null or objectId is invalid");
		}

		if(updatedObject.getItems().isEmpty() || updatedObject.getStatus() == OrderStatus.UNASSIGNED || updatedObject.getTimestamp() == 0) {
			throw new IllegalArgumentException("Items are empty, status in unassigned or timestamp is 0");
		}

		for(Order order : orders) {
			if(order.getId() == objectId) {
				orders.set(orders.indexOf(order), updatedObject);
				return;
			}
		}
		
		throw new IllegalArgumentException("Order not found");
	}

	@Override
	public void delete(long objectId) throws IllegalArgumentException {
		if(objectId < 0) {
			throw new IllegalArgumentException("Invalid objectId");
		}
		
		for(Order order : orders) {
			if(order.getId() == objectId) {
				orders.remove(order);
				return;
			}
		}
		
		throw new IllegalArgumentException("Order not found");
	}

	@Override
	public void load() throws IOException {
		List<Order> returnedData = ordersDb.load();
		orders = new ArrayList<Order>(returnedData);
		nextAvailableId = 0;
		for(Order order : orders) {
			if(order.getId() >= nextAvailableId) {
				nextAvailableId = order.getId() + 1;
			}
		}
	}

	@Override
	public void save() throws IOException {
		ordersDb.save(orders);
	}

	@Override
	public long reserveId() {
		long reservedId = nextAvailableId;
		nextAvailableId ++;
		return reservedId;
	}

}
