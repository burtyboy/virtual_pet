package tamagochi;

public class Toy {
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
}
