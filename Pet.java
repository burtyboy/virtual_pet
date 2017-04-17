

public class Pet extends GameEnvironment {
	private String petName;
	private int actionsRemaning = 0;
	
	public Pet(String name){
		petName = name;
	}
	
	public String getName(){
		return petName;
	}
	
	public int getActionsRemaning() {
		return actionsRemaning;
	}

	public void setActionsRemaning(int actionsRemaning) {
		this.actionsRemaning = actionsRemaning;
	}

}
