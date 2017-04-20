package tamagochi;

public class CurryRice extends Food{
	public CurryRice(){
		super("Curry rice", 10, 10, 6, 6);
	}
	public String food() {
		return super.getFood();
	}
	public int price() {
		return super.getPrice();
	}
	public int nutrition() {
		return super.getNutrition();
	}
	public int tastiness() {
		return super.getTaste();
	}
	public int bladderDrop() {
		return super.getFullness();
	}
}