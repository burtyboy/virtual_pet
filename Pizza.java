package tamagochi;

public class Pizza extends Food{
	public Pizza(){
		super("Italian pizza", 17, 5, 10, 1);
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