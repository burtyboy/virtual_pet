

public class Food extends Item{
	private String food;
	private int price;
	private int nutrition;
	private int tastiness;
	private int bladder;
	public Food(String meal, int cost, int diet, int taste, int toiletLevel) {
		food = meal;
		price = cost;
		nutrition = diet;
		tastiness = taste;
		bladder = toiletLevel;
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
public int getbladder() {
	return bladder;
}
}
