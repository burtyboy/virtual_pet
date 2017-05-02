package tamagochi;

public class Food extends Item{
	private String food;
	private int price;
	private int nutrition;
	private int tastiness;
	private int fullness;
	public Food(String meal, int cost, int diet, int taste, int mealSize) {
		food = meal;
		price = cost;
		nutrition = diet;
		tastiness = taste;
		fullness = mealSize;
	}
	public String getName() {
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
	public int getFullness() {
		return fullness;
	}
	
	public String toString(){
		return getName() + ":\n\t Provides:\n\t\tNutrition: "+ getNutrition()+"\n\t\tTastiness: " + getTaste()+"\n\t\tBladder Fullness: "+getFullness();
	}
}
