

public class Pillow extends Toy {
	public Pillow(){
		super("Pillow", 8, 3, 5);
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
