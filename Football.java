package tamagochi;

public class Football {
	Toy ball = new Toy("Football", 20, 8, 9);
	public String name() {
		return ball.getName();
	}
	public int price() {
		return ball.getPrice();
	}
	public int durability() {
		return ball.getDurability();
	}
	public int happiness() {
		return ball.getHappy();
	}
}
