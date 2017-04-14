

public class CurryRice extends Food{
	public CurryRice(){
		super("Curry rice", 10, 10, 6, 6);
	}
	public String food() {
		return super.getFood();
	}
	public int nutrition() {
		return super.getnutrition();
	}
	public int tastiness() {
		return super.gettaste();
	}
	public int bladderDrop() {
		return super.getbladder();
	}
}
