
public class Pet extends GameEnvironment {
	private String petName;
	private int actionsRemaning = 0;
	private int hunger;
	private int sleep;
	private int aggressive;
	private int happiness;
	public Pet(String name, int hungerDrop, int sleepDrop, int durabilityDrop, int happinessDrop){
		petName = name;
		hunger = hungerDrop;
		sleep = sleepDrop;
		aggressive = durabilityDrop;
		happiness = happinessDrop;
	}
	
	public String getName(){
		return petName;
	}
	public int getHunger(){
		return hunger;
	}
	public int getSleep(){
		return sleep;
	}
	public int getAgressive(){
		return aggressive;
	}
	public int getHappiness(){
		return happiness;
	}
	
	public int getActionsRemaning() {
		return actionsRemaning;
	}

	public void setActionsRemaning(int actionsRemaning) {
		this.actionsRemaning = actionsRemaning;
	}

}
