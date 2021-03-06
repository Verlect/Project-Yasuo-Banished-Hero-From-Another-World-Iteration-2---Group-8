package model;

public class User extends Entity {
	private String sprite;
	
	public User () {
		super();
	}
	
	public User (String name) {
		this.setName(name);
		
	}
	
	public User(User user) {
		this.initialize(user.getName(), user.getHealth(), user.getAbility(), user.getItems());
	}
	
	public String getSprite() {
		return sprite;
	}
	
}
