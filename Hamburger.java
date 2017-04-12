package tamagochi;

public class Hamburger {
Food hamburger = new Food("Hamburger", 5,  3, 5, 3);
public String food() {
	return hamburger.getFood();
}
public int nutrition() {
	return hamburger.getnutrition();
}
public int tastiness() {
	return hamburger.gettaste();
}
public int bladdarDrop() {
	return hamburger.getbladdar();
}
}
