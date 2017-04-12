package tamagochi;

public class Stick {
	Toy stick = new Toy("Stick", 6, 5, 2);
	public String name() {
		return stick.getName();
	}
	public int price() {
		return stick.getPrice();
	}
	public int durability() {
		return stick.getDurability();
	}
	public int happiness() {
		return stick.getHappy();
	}
}
