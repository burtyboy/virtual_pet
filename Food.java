

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
	public int getPrice() {
		return price;
	}
	public int getNutrition() {
		return nutrition;
	}
	public int getTaste() {
		return tastiness;
	}
	public int getBladder() {
		return bladder;
	}
	
	public String toString(){
		return getFood() + ":\n\t Provides:\n\t\tNutrition: "+ getNutrition()+"\n\t\tTastiness: " + getTaste()+"\n\t\tBladder Fullness: "+getBladder();
	}
}
