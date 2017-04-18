
public class Pet extends GameEnvironment {
	private String petName;
	private int actionsRemaning = 0;
	private int hunger;
	private int sleep;
	private int aggressive;
	private int happiness;
	private int stomach = 10;
	private int bladder = 10;
	private int fun = 10;
	private int energy = 10;
	private String favouriteToy;
	private String favouriteFood;
	
	public Pet(String name, int hungerDrop, int sleepDrop, int happinessDrop, int durabilityDrop, String toy, String food){
		petName = name;
		hunger = hungerDrop;
		sleep = sleepDrop;
		happiness = happinessDrop;
		aggressive = durabilityDrop;
		favouriteToy = toy;
		favouriteFood = food;
	}
	public String getToy() {
		return favouriteToy;
	}
	public String getFood() {
		return favouriteFood;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int changeEnergy) {
		energy += changeEnergy;
	}
	public int getBladder() {
		return bladder;
	}
	public void setBladder(int changeBladder){
		bladder += changeBladder;
	}
	public int getStomach() {
		return stomach;
	}
	public void setStomach(int stomachAdd) {
		stomach += stomachAdd;
	}
	public void setFun(int changeFun) {
		fun += changeFun;
	}
	public int getFun() {
		return fun;
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
