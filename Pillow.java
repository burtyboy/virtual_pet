package tamagochi;

public class Pillow {
	Toy pillow = new Toy("Pillow", 8, 3, 5);
	public String name() {
		return pillow.getName();
	}
	public int price() {
		return pillow.getPrice();
	}
	public int durability() {
		return pillow.getDurability();
	}
	public int happiness() {
		return pillow.getHappy();
	}
}
