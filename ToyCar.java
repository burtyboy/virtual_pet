

public class ToyCar extends Toy{
	
	public ToyCar(){
		super("Toy Car", 10, 10, 4);
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