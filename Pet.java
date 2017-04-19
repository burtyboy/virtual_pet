
public class Pet extends GameEnvironment {
	private String petName;
	private int actionsRemaning = 0;
	private int hunger;
	private int sleep;
	private int aggressive;
	private int happiness;
	private int stomach = 5;
	private int bladder = 5;
	private int fun = 5;
	private int energy = 5;
	private String favouriteToy;
	private String favouriteFood;
	private boolean isMisbehave = false;
	private boolean isSick = false;
	private boolean isDead = false;
	private boolean zombie = false;
	
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
	public int getStomach() {
		return stomach;
	}
	public void setStomach(int stomachAdd) {
		stomach += stomachAdd;
		if (stomach < 0) {
			stomach = 0;
		}
		if (stomach > 10) {
			stomach = 10;
		}
	}
	public void setFun(int changeFun) {
		fun += changeFun;
		if(fun < 0) {
			fun = 0;
		}
		if (fun > 10) {
			fun = 10;
		}
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
	public boolean getIsMisbehave() {
		return isMisbehave;
	}
	public void setbehave(boolean behaviour) {
		isMisbehave = behaviour;
	}
	public boolean getIsSick(){
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
	public boolean getIsDead() {
		return isDead;
	}
	public void setDead(boolean death) {
		isDead = death;
	}

	public void setActionsRemaning(int actionsRemaning) {
		this.actionsRemaning = actionsRemaning;
	}
	

}
