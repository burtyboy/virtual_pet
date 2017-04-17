
public class ToiletPaper extends Toy{
	public ToiletPaper() {
		super("Toilet paper", 2, 1, 5);
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
