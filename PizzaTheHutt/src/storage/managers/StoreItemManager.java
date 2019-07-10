package storage.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.storeItem.StoreItem;
import storage.json.Storage;
import storage.json.JacksonJsonParser;

public class StoreItemManager implements Manager<StoreItem>{
	
	private static final File ITEMS_DB_FILE = new File("data\\items.json");

	private long nextAvailableId;
	private Storage<StoreItem> itemsDb;
	private ArrayList<StoreItem> items;
	
	public StoreItemManager() throws IOException {
		this(ITEMS_DB_FILE);
	}
	
	public StoreItemManager(File dbFile) throws IOException {
		this.nextAvailableId = 0;
		this.itemsDb = new JacksonJsonParser<StoreItem>(StoreItem.class, dbFile);
		this.items = new ArrayList<StoreItem>();
		load();
	}

	@Override
	public void add(StoreItem newObject) throws IllegalArgumentException {
		if(newObject == null) {
			throw new IllegalArgumentException();
		}
		
		if(newObject.getName().isEmpty() || newObject.getPrice() == 0) {
			throw new IllegalArgumentException();
		}		
		
		if(!items.isEmpty()) {
			for(StoreItem item : items) {
				if(item.getId() == newObject.getId()) {
					throw new IllegalArgumentException();
				}
			}
		}
		
		items.add(newObject);
		if(newObject.getId() >= nextAvailableId) {
			nextAvailableId = newObject.getId() + 1;
		}
	}

	@Override
	public StoreItem get(long objectId) throws IllegalArgumentException {
		if(objectId < 0) {
			throw new IllegalArgumentException();
		}
		
		if(!items.isEmpty()) {
			for(StoreItem item : items) {
				if(item.getId() == objectId) {
					return item;
				}
			}
		}
		
		throw new IllegalArgumentException();
	}

	@Override
	public List<StoreItem> getAll() {
		return items;
	}

	@Override
	public void update(long objectId, StoreItem updatedObject) throws IllegalArgumentException {
		if(updatedObject == null || objectId < 0) {
			throw new IllegalArgumentException();
		}
		
		if(updatedObject.getName().isEmpty() || updatedObject.getPrice() == 0) {
			throw new IllegalArgumentException();
		}
		
		if(!items.isEmpty()) {
			for(StoreItem item : items) {
				if(item.getId() == objectId) {
					items.set(items.indexOf(item), updatedObject);
					return;
				}
			}
		}
		
		throw new IllegalArgumentException();
	}

	@Override
	public void delete(long objectId) throws IllegalArgumentException {
		if(objectId < 0) {
			throw new IllegalArgumentException();
		}
		
		if(!items.isEmpty()) {
			for(StoreItem item : items) {
				if(item.getId() == objectId) {
					items.remove(item);
					return;
				}
			}
		}
		
		throw new IllegalArgumentException();
	}
	
	@Override
	public void load() throws IOException {
		List<StoreItem> returnedData = itemsDb.load();
		items = new ArrayList<StoreItem>(returnedData);
		nextAvailableId = 0;
		for(StoreItem item : items) {
			if(item.getId() >= nextAvailableId) {
				nextAvailableId = item.getId() + 1;
			}
		}
	}

	@Override
	public void save() throws IOException {
		itemsDb.save(items);
	}

	@Override
	public long reserveId() {
		long reservedId = nextAvailableId;
		nextAvailableId ++;
		return reservedId;
	}
}
