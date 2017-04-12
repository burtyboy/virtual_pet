package tamagochi;

public class FriedChicken {
	Food chicken = new Food("Fried Chicken", 2, 1, 4, 2);
	public String food() {
		return chicken.getFood();
	}
	public int nutrition() {
		return chicken.getnutrition();
	}
	public int tastiness() {
		return chicken.gettaste();
	}
	public int bladdarDrop() {
		return chicken.getbladdar();
	}
}
