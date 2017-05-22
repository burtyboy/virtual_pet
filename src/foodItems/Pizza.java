package foodItems;
import main.Food;
/**
 * 
 * Subclass of Food. Adds a child called Pizza with associated construction method. All methods are provided on the parent level
 *
 */

public class Pizza extends Food{
	public Pizza(){
		super("Italian pizza", 14, 5, 9, 1);
	}
}