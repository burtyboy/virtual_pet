
public class Steak extends Food{
	public Steak(){
		super("Steak", 23, 8, 9, 4);
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
