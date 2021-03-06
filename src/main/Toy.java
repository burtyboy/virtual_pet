package main;
/**
 * 
 * Extends the Item class and provides the methods required for toys to function in game. 
 * All instances of Toy should be created on a subclass level eg. Doll doll = new Doll();
 * All methods required for the function of any children are provided in this class.  
 *
 */
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
	public String getToyName() {
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
		return getToyName()+": \n\tProvides: \n\t\tHappiness: "+getHappy()+"\n\t\tDurability: "+getDurability();
	}
}