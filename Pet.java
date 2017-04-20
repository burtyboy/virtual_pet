package tamagochi;

public class Pet extends GameEnvironment {
	private String petName;
	private int actionsRemaning = 0;
	private int hungerDrop;
	private int energyDrop;
	private int aggression;
	private int happinessDrop;
	private int hunger = 5;
	private int bladder = 5;
	private int happiness = 5;
	private int energy = 5;
	private String favouriteToy;
	private String favouriteFood;
	private boolean isMisbehave = false;
	private boolean isSick = false;
	private boolean isDead = false;
	private boolean zombie = false;
	
	public Pet(String name, int hungerDrop, int energyDrop, int happinessDrop, int durabilityDrop, String toy, String food){
		petName = name;
		this.hungerDrop = hungerDrop;
		this.energyDrop = energyDrop;
		this.happinessDrop = happinessDrop;
		aggression = durabilityDrop;
		favouriteToy = toy;
		favouriteFood = food;
	}
	public int getOverallStat() {
		return hunger + bladder + happiness + energy;
	}
	
	public String getFavouriteToy() {
		return favouriteToy;
	}
	public String getFavouriteFood() {
		return favouriteFood;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int changeEnergy) {
		energy += changeEnergy;
		if (energy < 0) {
			energy = 0;
		}
		if (energy > 10) {
			energy = 10;
		}
	}
	public int getBladder() {
		return bladder;
	}
	public void setBladder(int changeBladder){
		bladder += changeBladder;
		if (bladder < 0) {
			bladder = 0;
		}
	}
	public int getHunger() {
		return hunger;
	}
	public void setHunger(int food) {
		hunger += food;
		if (hunger < 0) {
			hunger = 0;
		}
		if (hunger > 10) {
			hunger = 10;
		}
	}
	public void setHappiness(int happiness) {
		this.happiness += happiness;
		if(this.happiness < 0) {
			this.happiness = 0;
		}
		if (this.happiness > 10) {
			this.happiness = 10;
		}
	}
	public int getHappiness() {
		return happiness;
	}
	public String getName(){
		return petName;
	}
	public int getHungerDrop(){
		return hungerDrop;
	}
	public int getEnergyDrop(){
		return energyDrop;
	}
	public int getAggression(){
		return aggression;
	}
	public int getHappinessDrop(){
		return happinessDrop;
	}
	public int getActionsRemaning() {
		return actionsRemaning;
	}
	public boolean isMisbehave() {
		return isMisbehave;
	}
	public void setbehaviour(boolean isMisbehave) {
		this.isMisbehave = isMisbehave;
	}
	public boolean isSick(){
		return isSick;
	}
	public void setSick(boolean sickness) {
		isSick = sickness;
	}
	public boolean isZombie() {
		return zombie;
	}
	public void setZombie(boolean zombie) {
		this.zombie = zombie;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean death) {
		isDead = death;
	}

	public void setActionsRemaining(int actionsRemaning) {
		this.actionsRemaning = actionsRemaning;
	}
	

}