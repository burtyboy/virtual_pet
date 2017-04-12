package tamagochi;

public class Toy_Car {
	Toy car = new Toy("Toy Car", 10, 10, 4);
	public String name() {
		return car.getName();
	}
	public int price() {
		return car.getPrice();
	}
	public int durability() {
		return car.getDurability();
	}
	public int happiness() {
		return car.getHappy();
	}
}
