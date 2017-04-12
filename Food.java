package tamagochi;

public class Food {
	private String food;
	private int price;
	private int nutrition;
	private int tastiness;
	private int bladdar;
	public Food(String meal, int cost, int diet, int taste, int toiletLevel) {
		food = meal;
		price = cost;
		nutrition = diet;
		tastiness = taste;
		bladdar = toiletLevel;
	}
public String getFood() {
	return food;
}
public int getprice() {
	return price;
}
public int getnutrition() {
	return nutrition;
}
public int gettaste() {
	return tastiness;
}
public int getbladdar() {
	return bladdar;
}
}
