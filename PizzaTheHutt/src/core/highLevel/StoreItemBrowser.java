package core.highLevel;

import java.util.ArrayList;
import java.util.List;

import data.storeItem.StoreItem;
import storage.managers.StoreItemManager;

public class StoreItemBrowser {
	
	private StoreItemManager manager;
	
	public StoreItemBrowser(StoreItemManager manager) {
		this.manager = manager;
	}
	
	public List<StoreItem> getActive(){
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		
		for(StoreItem item : manager.getAll()) {
			if(item.isActive()) {
				items.add(item);
			}
		}
		
		return items;
	}
	
	public List<StoreItem> getInactive(){
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		
		for(StoreItem item : manager.getAll()) {
			if(!item.isActive()) {
				items.add(item);
			}
		}
		
		return items;
	}
}
