package tamagochi;

public class ItalianPizza {
	Food pizza = new Food("Italian pizza", 17, 5, 10, 1);
	public String food() {
		return pizza.getFood();
	}
	public int nutrition() {
		return pizza.getnutrition();
	}
	public int tastiness() {
		return pizza.gettaste();
	}
	public int bladdarDrop() {
		return pizza.getbladdar();
	}
}
