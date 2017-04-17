

public class FishAndChip extends Food{
	public FishAndChip(){
		super("Fish and chips", 3, 6, 2, 4);
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
