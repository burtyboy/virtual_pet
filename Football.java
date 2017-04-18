public class Football extends Toy{
	
	public Football(){
		super("Football", 20, 10, 10, 3);
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
	public int exercise() {
		return super.getExercise();
	}
}


