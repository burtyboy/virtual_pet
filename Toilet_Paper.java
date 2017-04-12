package tamagochi;

public class Toilet_Paper {
	Toy paper = new Toy("Toilet paper", 2, 1, 5);
	public String name() {
		return paper.getName();
	}
	public int price() {
		return paper.getPrice();
	}
	public int durability() {
		return paper.getDurability();
	}
	public int happiness() {
		return paper.getHappy();
	}
}
