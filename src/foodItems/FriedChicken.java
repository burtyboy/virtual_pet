package foodItems;
import main.Food;
/**
 * 
 * Subclass of Food. Adds a child called FriedChicken with associated construction method. All methods are provided on the parent level
 *
 */

public class FriedChicken extends Food{
	
	public FriedChicken(){
		super("Fried Chicken", 2, 1, 4, 2);
	}
}