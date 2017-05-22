package foodItems;
import main.Food;
/**
 * 
 * Subclass of Food. Adds a child called CurryRice with associated construction method. All methods are provided on the parent level
 *
 */

public class CurryRice extends Food{
	public CurryRice(){
		super("Curry rice", 10, 10, 6, 6);
	}
}