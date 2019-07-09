package data.storeItem;

public class StoreItem {
	private static int lastID;
	
	private int id;
	private String name;
	private double price;
	private String description;
	private boolean active;
	
	public StoreItem() {
		this(-1, "", 0, "", false);
	}
	
	public StoreItem(String name, double price) {
		this(lastID + 1, name, price, "", true);
	}
	
	public StoreItem(String name, double price, String description) {
		this(lastID + 1, name, price, description, true);
	}
	
	public StoreItem(String name, double price, String description, boolean active) {
		this(lastID + 1, name, price, description, active);
	}
	
	public StoreItem(int id, String name, double price, String description, boolean active) {
		setId(id);
		this.name = name;
		this.price = price;
		this.description = description;
		this.active = active;
	}
		
	public static int getLastId() {
		return lastID;
	}

	public static void setLastId(int lastID) {
		StoreItem.lastID = lastID;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString () {
		StringBuilder builder = new StringBuilder();
		builder.append("ID: " + this.id + "; ");
		builder.append("Name: " + this.name + "; ");
		builder.append("Price: " + this.price + "; ");
		builder.append("Description: " + this.description + "; ");
		builder.append("Active: " + this.active);
		return builder.toString();
	}
}
