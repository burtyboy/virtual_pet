package tamagochi;

public class Doll {
	Toy doll = new Toy("Dolls", 15, 5, 10);
	public String name() {
		return doll.getName();
	}
	public int price() {
		return doll.getPrice();
	}
	public int durability() {
		return doll.getDurability();
	}
	public int happiness() {
		return doll.getHappy();
	}
}
