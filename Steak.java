package tamagochi;

public class Steak {
	Food steak = new Food("Steak", 23, 8, 9, 4);
	public String food() {
		return steak.getFood();
	}
	public int nutrition() {
		return steak.getnutrition();
	}
	public int tastiness() {
		return steak.gettaste();
	}
	public int bladdarDrop() {
		return steak.getbladdar();
	}
}
