
public class Stick extends Toy{
	
	
	public Stick(){
		super("Stick", 6, 5, 2);
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
