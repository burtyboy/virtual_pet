

public class Hamburger extends Food{
	public Hamburger(){
		super("Hamburger", 5,  3, 5, 3);
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



