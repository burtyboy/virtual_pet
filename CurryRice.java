package tamagochi;

public class CurryRice {
	Food curry = new Food("Curry rice", 10, 10, 6, 6);
	public String food() {
		return curry.getFood();
	}
	public int nutrition() {
		return curry.getnutrition();
	}
	public int tastiness() {
		return curry.gettaste();
	}
	public int bladdarDrop() {
		return curry.getbladdar();
	}
}
