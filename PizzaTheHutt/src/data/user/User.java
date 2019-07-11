package data.user;

public class User {
	
	private static final long DEFAULT_ID = -1;
	private static final UserRoles DEFAULT_ROLE = UserRoles.UNASSIGNED;
	
	
	private long id;
	private String username;
	private String password;
	private UserRoles role;
	
	public User() {
		this(DEFAULT_ID, null, null, DEFAULT_ROLE);
	}
	
	public User(long id, String username, String password, UserRoles role) {
		setId(id);
		
		if(username != null) {
			this.username = username;
		}else {
			this.username = "";
		}
		
		if(password != null) {
			this.password = password;
		}else {
			this.password = "";
		}
		
		this.role = role;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
