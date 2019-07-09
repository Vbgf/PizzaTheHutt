package storage.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import data.storeItem.StoreItem;

public class StorageStoreItemsJson {

	private static final File DB_FILE = new File("data\\storeItems.json");
	private static final Charset ENCODING = StandardCharsets.UTF_8;
	
	private Gson gson;
	private ArrayList<StoreItem> items;
	private File dbFile;
	
	public StorageStoreItemsJson() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		items = new ArrayList<StoreItem>();
		dbFile = DB_FILE;
	}
	
	public StorageStoreItemsJson(File dbFile){
		gson = new GsonBuilder().setPrettyPrinting().create();
		items = new ArrayList<StoreItem>();
		
		this.dbFile = dbFile;
	}
	
	public void load() throws IOException {
		if (!dbFile.exists()) {
			dbFile.createNewFile();
			return;
		}

		FileInputStream inputStream = new FileInputStream(dbFile);
		InputStreamReader streamReader = new InputStreamReader(inputStream, ENCODING);

		items = gson.fromJson(streamReader, new TypeToken<ArrayList<StoreItem>>(){}.getType());
		
		inputStream.close();
		streamReader.close();
	}
	
	public void save() throws IOException{
		if (!dbFile.exists()) {
			dbFile.createNewFile();
		}

		FileOutputStream outputStream = new FileOutputStream(dbFile, false);
		OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, ENCODING);
		
		gson.toJson(items, streamWriter);
		
		streamWriter.close();
		outputStream.close();
	}

	public void addStoreItem(StoreItem addItem) throws IllegalArgumentException, NullPointerException{
		if(addItem == null) {
			throw new NullPointerException();
		}
		items.add(addItem);
	}
	
	public void removeStoreItem(StoreItem removeItem) throws IllegalArgumentException, NullPointerException{
		if(removeItem == null) {
			throw new NullPointerException();
		}
		for(StoreItem item: items) {
			if(item.equals(removeItem)) {
				items.remove(item);
				return;
			}
		}
	}
	
	public void modifyStoreItem(StoreItem oldItem, StoreItem newItem) throws IllegalArgumentException, NullPointerException{
		if(oldItem == null || newItem == null) {
			throw new NullPointerException();
		}
		for(StoreItem item: items) {
			if(item.equals(oldItem)) {
				item = newItem;
				return;
			}
		}
	}
	
	public StoreItem fetchStoreItemById(int id) throws IllegalArgumentException{
		if(id <= 0) {
			throw new IllegalArgumentException();
		}
		for(StoreItem item: items) {
			if(item.getId() == id) {
				return item;
			}
		}
		throw new IllegalArgumentException();
	}
}


