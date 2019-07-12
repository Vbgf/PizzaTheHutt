package data.storeItem;

public class StoreItem {
	
	private static final long DEFAULT_ID = -1;
	private static final double  DEFAULT_PRICE = 0;
	private static final boolean  DEFAULT_STATE = false;

	private long id;
	private String name;
	private double price;
	private String description;
	private boolean active;
	
	public StoreItem() {
		this(DEFAULT_ID, null, DEFAULT_PRICE, null, DEFAULT_STATE);
	}
	
	public StoreItem(long id, String name, double price) {
		this(id, name, price, null, true);
	}
	
	public StoreItem(long id, String name, double price, String description) {
		this(id, name, price, description, true);
	}
	
	public StoreItem(long id, String name, double price, String description, boolean active) {
		this.id = id;
		
		if(name != null) {
			this.name = name;
		}else {
			this.name = new String();
		}
		
		this.price = price;
		
		if(description != null) {
			this.description = description;
		}else {
			this.description = new String();
		}
		
		this.active = active;
	}
	
	public StoreItem(StoreItem item) {
		this.id = item.id;
		this.name = item.name;
		this.price = item.price;
		this.description = item.description;
		this.active = item.active;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
		builder.append("Price: " + this.price + "BGN; ");
		builder.append("Description: " + this.description + "; ");
		builder.append("Active: " + this.active);
		return builder.toString();
	}
	
	public boolean equals(StoreItem item) {
		if(this.id != item.id) {
			return false;
		}else if(!this.name.equals(item.name)) {
			return false;
		}else if(this.price != item.price){
			return false;
		}else if(!this.description.equals(item.description)){
			return false;
		}else if(this.active != item.active) {
			return false;
		}else {
			return true;
		}
	}
}
