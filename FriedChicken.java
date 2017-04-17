


public class FriedChicken extends Food{
	
	public FriedChicken(){
		super("Fried Chicken", 2, 1, 4, 2);
	}
	public String food() {
		return super.getFood();
	}
	public int nutrition() {
		return super.getNutrition();
	}
	public int tastiness() {
		return super.getTaste();
	}
	public int bladderDrop() {
		return super.getBladder();
	}
}
