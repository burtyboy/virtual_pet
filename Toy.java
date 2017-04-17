
public class Toy extends Item{
	private String name;
	private int prices;
	private int durability;
	private int happiness;
	public Toy(String brand, int price, int strength, int happy) {
		name = brand;
		prices = price;
		durability = strength;
		happiness = happy;
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
	public int getHappy() {
		return happiness;
	}
	
	
	public String toString(){
		return getName()+": \n\tProvides: \n\t\tHappiness: "+getHappy()+"\n\t\tDurability: "+getDurability();
	}
}
