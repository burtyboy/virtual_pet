package tamagochi;

public class Toy extends Item{
	private String name;
	private int prices;
	private int durability;
	private int happiness;
	private int exercise;
	public Toy(String brand, int price, int strength, int happy, int exercise) {
		name = brand;
		prices = price;
		durability = strength;
		happiness = happy;
		this.exercise = exercise;
	}
	public int getPrice() {
		return prices;
	}
	public String getName() {
		return name;
	}
	public int getDurability() {
		return durability;
	}
	public void setDurability(int drop) {
		durability -= drop;
	}
	public int getHappy() {
		return happiness;
	}
	public int getExercise() {
		return exercise;
	}
	public String toString(){
		return getName()+": \n\tProvides: \n\t\tHappiness: "+getHappy()+"\n\t\tDurability: "+getDurability();
	}
}