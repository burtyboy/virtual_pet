package foodItems;
import main.Food;
/**
 * 
 * Subclass of Food. Adds a child called FishAndChip with associated construction method. All methods are provided on the parent level
 *
 */

public class FishAndChip extends Food{
	public FishAndChip(){
		super("Fish and chips", 3, 6, 2, 4);
	}
}