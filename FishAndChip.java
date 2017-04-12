package tamagochi;

public class FishAndChip {
	Food fish = new Food("Fish and chips", 3, 6, 2, 4);
	public String food() {
		return fish.getFood();
	}
	public int nutrition() {
		return fish.getnutrition();
	}
	public int tastiness() {
		return fish.gettaste();
	}
	public int bladdarDrop() {
		return fish.getbladdar();
	}
}
