package core.highLevel;

import java.util.ArrayList;
import java.util.List;

import data.order.Order;
import data.order.OrderStatus;
import storage.managers.OrderManager;

public class OrderBrowser {
	private OrderManager manager;
	
	public OrderBrowser(OrderManager manager) {
		this.manager = manager;
	}
	
	public List<Order> getStatus(OrderStatus status){
		ArrayList<Order> items = new ArrayList<Order>();
		
		for(Order item : manager.getAll()) {
			if(item.getStatus().equals(status)) {
				items.add(item);
			}
		}
		
		return items;
	}
	
	public List<Order> getAllAfter(long timestamp){
		ArrayList<Order> items = new ArrayList<Order>();
		
		for(Order item : manager.getAll()) {
			if(item.getTimestamp() > timestamp) {
				items.add(item);
			}
		}
		
		return items;
	}

	public List<Order> getAllBefore(long timestamp){
		ArrayList<Order> items = new ArrayList<Order>();
		
		for(Order item : manager.getAll()) {
			if(item.getTimestamp() < timestamp) {
				items.add(item);
			}
		}
		
		return items;
	}
	
	public List<Order> getAllBetween(long low, long high){
		ArrayList<Order> items = new ArrayList<Order>();
		
		for(Order item : manager.getAll()) {
			if(item.getTimestamp() < high && item.getTimestamp() > low) {
				items.add(item);
			}
		}
		
		return items;
	}
}
