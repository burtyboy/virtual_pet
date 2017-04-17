

public class Doll extends Toy{
	public Doll(){
		super("Doll", 15, 5, 10);
	}
	public String name() {
		return super.getName();
	}
	public int price() {
		return super.getPrice();
	}
	public int durability() {
		return super.getDurability();
	}
	public int happiness() {
		return super.getHappy();
	}
}
