package data.user;

public class User {
	private static int lastID;
	
	private int id;
	private String username;
	private String password;
	private UserRoles role;
	
	public User() {
		this(-1, "", "", UserRoles.UNASSIGNED);
	}
	
	public User(String name, String password) {
		this(lastID + 1, name, password, UserRoles.USER);
	}
	
	public User(int id, String name, String password, UserRoles role) {
		setId(id);
		this.username = name;
		this.password = password;
		this.role = role;
	}

	public static int getLastId() {
		return lastID;
	}

	public static void setLastId(int lastID) {
		User.lastID = lastID;
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserRoles getRole() {
		return role;
	}
	
	public void setRole(UserRoles role) {
		this.role = role;
	}
	
	@Override
	public String toString () {
		StringBuilder builder = new StringBuilder();
		builder.append("ID: " + this.id + "; ");
		builder.append("Username: " + this.username + "; ");
		builder.append("Password: " + this.password + "; ");
		builder.append("Role: " + this.role);
		return builder.toString();
	}
}
