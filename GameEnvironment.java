import java.util.ArrayList;
import java.util.Scanner;
/**
 * Main loop for this game. Provides all control methods and calls and advances days
 * 
 * @author Josh, Nobu
 *
 */
public class GameEnvironment implements Printable {
	private int day = 1;
	private int numberOfPlayers, numberOfPets, lengthOfGame = 0;
	public ArrayList<Player> playerArray = new ArrayList<Player>(0);//changed to allow testing
	public int getDay(){
		return day;
	}
	
	public void setDay(int newDay){
		day = newDay;
	}
	public void nextDay(){
		day++;
	}
	
	
	public void printHeader(){
		printToScreen("##################################################################");
	}
	
	
	/**
	 * Returns void.
	 * Adds a new player to the game and stores this player in the game environments arrayList playerArray
	 * Provides validation for provided names testing length, uniqueness and non-emptiness
	 * called by addPlayer() 
	 */
	private void addPlayer(){
		String playerName = "";
		printToScreen("What is your Name? ");
		boolean isValid = false;
		while(!isValid){
			playerName = getInput();
			isValid = true;
			if (playerName.trim().length() == 0) { 
				isValid = false;
				printToScreen("Names must have at least 1 letter, please choose a new name.");
			}
			else {
				if (playerName.length() > 20) {
					isValid = false;
					printToScreen("Names must not exceed 20 characters, please choose a new name.");
				}
				else {
			for(Player person:playerArray){
				if(person.getName().equals(playerName)){
					isValid = false;
					printToScreen("Names must be unique, please choose a new name.");
				}
			}
			}
				}
		}
		playerArray.add(new Player(playerName));
	}
	
	
	/**
	 * returns void.
	 * Adds a pet to a arrayList petArray owned by each player.
	 * Checks type and generates pet of that type.
	 * Provides validation for names by checking uniqueness against both players and any pets currently in game, length and Non-emptiness
	 * Called by addPet(Player p) taking a Player as a base argument. 
	 */
	private void addPet(Player p){
		String petName = "";
		printToScreen("What breed would you like your pet to be?");
		printToScreen("(1)Cat\n(2)Dog\n(3)CatDog\n(4)Tiger\n(5)Bird\n(6)Ocelot");
		
		boolean isValid = false;
		while(!isValid){
			String type = getInput();
			switch(type){
				case "1":petName = getName();
					Pet cat = new Cat(petName);
					boolean petWanted = petStatusDisplay(cat);
					if (petWanted == true) {
					p.petArray.add(cat);
					printToScreen("Creating a Cat named "+petName+" for you.");
					isValid = true;
					break;
					}
					else {
						printToScreen("What breed would you like your pet to be?");
						printToScreen("(1)Cat\n(2)Dog\n(3)CatDog\n(4)Tiger\n(5)Bird\n(6)Ocelot");
						break;
					}
				case"2":petName = getName(); 
					Pet dog = new Dog(petName);
					boolean dogWanted = petStatusDisplay(dog);
					if (dogWanted == true) {
						p.petArray.add(dog);
						printToScreen("Creating a Dog named "+petName+" for you.");
						isValid = true;
						break;
					}
				else {
					printToScreen("What breed would you like your pet to be?");
					printToScreen("(1)Cat\n(2)Dog\n(3)CatDog\n(4)Tiger\n(5)Bird\n(6)Ocelot");
					break;
				}
				case "3":petName = getName(); 
				Pet catDog = new CatDog(petName);
				boolean catDogWanted = petStatusDisplay(catDog);
				if (catDogWanted == true) {
					p.petArray.add(catDog);
					printToScreen("Creating a CatDog named "+petName+" for you.");
					isValid = true;
					break;
				}
			else {
				printToScreen("What breed would you like your pet to be?");
				printToScreen("(1)Cat\n(2)Dog\n(3)CatDog\n(4)Tiger\n(5)Bird\n(6)Ocelot");
				break;
			}
				case "4": petName = getName(); 
				Pet tiger = new Tiger(petName);
				boolean tigerWanted = petStatusDisplay(tiger);
				if (tigerWanted == true) {
					p.petArray.add(tiger);
					printToScreen("Creating a Tiger named "+petName+" for you.");
					isValid = true;
					break;
				}
			else {
				printToScreen("What breed would you like your pet to be?");
				printToScreen("(1)Cat\n(2)Dog\n(3)CatDog\n(4)Tiger\n(5)Bird\n(6)Ocelot");
				break;
			}
				case "5": petName = getName(); 
				Pet bird = new Bird(petName);
				boolean birdWanted = petStatusDisplay(bird);
				if (birdWanted == true) {
					p.petArray.add(bird);
					printToScreen("Creating a Bird named "+petName+" for you.");
					isValid = true;
					break;
				}
			else {
				printToScreen("What breed would you like your pet to be?");
				printToScreen("(1)Cat\n(2)Dog\n(3)CatDog\n(4)Tiger\n(5)Bird\n(6)Ocelot");
				break;
			}
				case "6": petName = getName(); 
				Pet ocelot = new Ocelot(petName);
				boolean ocelotWanted = petStatusDisplay(ocelot);
				if (ocelotWanted == true) {
					p.petArray.add(ocelot);
					printToScreen("Creating a Ocelot named "+petName+" for you.");
					isValid = true;
					break;
				}
			else {
				printToScreen("What breed would you like your pet to be?");
				printToScreen("(1)Cat\n(2)Dog\n(3)CatDog\n(4)Tiger\n(5)Bird\n(6)Ocelot");
				break;
			}
		
			}
		}
	}
	/**
	 * returns string.
	 * Provides validation for names by checking uniqueness against any pets currently in game, length and Non-emptiness
	 */
	public String getName(){
		String petName = "";
		boolean isValid = false;
		printToScreen("What is your Pets Name?");
		while(!isValid){
			isValid = true;
			petName = getInput();
			if (petName.trim().length() == 0) {
				isValid = false;
				printToScreen("Names must have at least 1 letter, please choose a new name.");
			}
			else {
				if ( petName.length() > 20) {
					isValid = false;
					printToScreen("Names must not exceed 20 characters, please choose a new name.");
				}
				else {
			for(Player person:playerArray){
				if(person.getName().equals(petName)){
					isValid = false;
					printToScreen("Names must be unique, please choose a new name.\n");
				}
			
			for(Pet animal:person.petArray){
				if(animal.getName().equals(petName)){
					isValid = false;
					printToScreen("Names must be unique, please choose a new name.\n");
				}
			}
			}
				}
			}
		}
		return petName;
	}
	
	
	
	/**
	 * Method to implement Printable.
	 * opens a Scanner and returns the inputed String
	 * Scanner is left unclosed to allow function.
	 * Called by getInput()
	 */
	public String getInput(){
		@SuppressWarnings("resource")
		Scanner inputString = new Scanner(System.in);
		String input =  inputString.nextLine();
		return input;
		
	}
	
	
	/**
	 * Method to implement Printable.
	 * Returns void.
	 * Prints a message to the output stream
	 * called by printToScreen(String displayString) with the required message passed to the method.
	 */
	public void printToScreen(String displayString){
		System.out.println(displayString);
	}
	
	
	/**
	 * returns Void.
	 * Before the set up, the game would ask if you like to start a new game or check the instruction.
	 * Sets up the game by asking for number of players,number of days, number of pets, pet types.
	 * MUST ONLY BE CALLED AT THE START OF THE GAME
	 * Provides validation of numbers and only adds 3 players and up to 3 pets per player. 
	 * Warn the players, if they picked 10 or more days.
	 */
	public void createGame(){
		boolean startGame = false;
		while(!startGame){
		printToScreen("(1) Start a new game!");
		printToScreen("(2) Check the instruction");
		String option = getInput();
		switch(option){
			case "1":
				startGame = true;
				break;
			case "2":
				displayInstruction();
				break;
			default: 
				printToScreen("Please select a valid number!");
			}
		}
		boolean isValid = false;
		while(!isValid){
			printToScreen("How many Players would you like(1-3)? \n");
			String numPlayers = getInput();
			try{
				numberOfPlayers  = Integer.parseInt(numPlayers);
				if(numberOfPlayers >= 1 && numberOfPlayers <=3){
					isValid = true;
				}
				else {
					printToScreen("Please enter a valid number.");
				}
			}catch(NumberFormatException e){
				printToScreen("Please enter a valid number.\n");
			}
		}
		int i = 0;
		while( i < numberOfPlayers){
			addPlayer();
			i++;
		}
		
		for(Player person:playerArray){
			printToScreen("Player: " + person.getName());
			isValid = false;
			while(!isValid){
				
				printToScreen("How many Pets would you like(1-3)? \n");
				String numPets = getInput();
				try{
					numberOfPets  = Integer.parseInt(numPets);
				}catch(NumberFormatException e){
					printToScreen("Please enter a valid number.\n");
				}
				if(numberOfPets >= 1 && numberOfPets <=3){
					isValid = true;
				}
			}
		
			i=0;
			while(i < numberOfPets){
				addPet(person);
				i++;
			}
		}
		printToScreen("How many days would you like to play?");
		isValid = false;
		while(!isValid){
			isValid = true;
			String numDays = getInput();
			try{
				lengthOfGame = Integer.parseInt(numDays);
			}catch(NumberFormatException e){
				printToScreen("Please enter a valid number.\n");
				isValid = false;
			}
			if(lengthOfGame< 0){
				printToScreen("Please enter a positive number.\n");
				isValid  = false;
			}
			if(lengthOfGame >= 10){
				printToScreen(numDays +" is a long amount of time. Do you really want to play that long?\n(y/n)");
				String proceed  = getInput();
				if(!proceed.equals("y")){
					isValid = false;
					printToScreen("How many days would you like to play?");
				}
			}
			
		}
		
		
	}
	/**
	 * returns void
	 * Displays an instruction on how to play the game.
	 */
	public void displayInstruction() {
		printToScreen("Learn it yourself!");
	}
	/**
	 * returns Boolean.
	 * Prints off the selected food's status.
	 * Gives a player a choice to either buy them or not.
	 * Meal size depends on the fullness of the food.
	 * Also, prints off the cost of the food and the amount of money player currently have.
	 */
	public boolean foodStatusDisplay(Player p, Food food) {
		printToScreen("Here is the following status of " + food.getFood() + " (1 = Bad, 10 = Good)");
		printToScreen("Nutrition: " + Integer.toString(food.getNutrition()));
		printToScreen("Taste: " + Integer.toString(food.getTaste()));
		if (food.getFullness() <= 1) {
			printToScreen("Meal size: Small");
			}
		else {
			if (food.getFullness() <= 3) {
				printToScreen("Meal size: Medium");
				}
			else {
				printToScreen("Meal size: Large");
				}
			}
		printToScreen("price: $" + Integer.toString(food.getPrice()));
		printToScreen("Available money: $" + Integer.toString(p.getMoney()));
		printToScreen("Would you like to buy this?");
		printToScreen("(1) Yes\n(2) No");
		boolean isValid = false;
		boolean selected = false;
		while(!isValid) {
			String option = getInput();
			switch(option){
				case "1":
					isValid = true;
					selected = true;
					break;
				case "2":
					isValid = true;
					selected = false;
					break;
				default: 
					printToScreen("Please select a valid number!");
				}
				}
		return selected;
		}
	/**
	 * returns Boolean.
	 * Prints off the selected toy's status.
	 * Gives a player a choice to either buy them or not.
	 * The exercise level varies depending which toy it is.
	 * Also, prints off the cost of the toy and the amount of money player currently have.
	 */
	public boolean toyStatusDisplay(Player p, Toy toy) {
		printToScreen("Here is the following status of " + toy.getName() + " (1 = Low, 10 = High)");
		printToScreen("Happiness: " + Integer.toString(toy.getHappy()));
		printToScreen("Durablilty: " + Integer.toString(toy.getDurability()));
		if (toy.getExercise() == 0) {
		printToScreen("Exercise: None");
		}
		else {
			if (toy.getExercise() >= 3) {
				printToScreen("Exercise: High");
			}
			else {
				if (toy.getExercise() == 1) {
				printToScreen("Exercise: low");
				}
				else {
					printToScreen("Exercise: Medium");
				}
			}
		}
		printToScreen("price: $" + Integer.toString(toy.getPrice()));
		printToScreen("Available money: $" + Integer.toString(p.getMoney()));
		printToScreen("Would you like to buy this?");
		printToScreen("(1) Yes\n(2) No");
		boolean isValid = false;
		boolean selected = false;
		while(!isValid) {
			String option = getInput();
			switch(option){
				case "1":
					isValid = true;
					selected = true;
					break;
				case "2":
					isValid = true;
					selected = false;
					break;
				default: 
					printToScreen("Please select a valid number!");
				}
				}
		return selected;
	}
	/**
	 * returns boolean
	 * Prints of the selected pet's status.
	 * Player gets a choice of either keeping the pet or choose a different pet.
	 */
	public boolean petStatusDisplay(Pet a) {
		printToScreen("Here is the following status of " + a.getName() + " (1 = Low, 3 = High)");
		printToScreen("Name : " + a.getName());
		printToScreen("Appetite: " + Integer.toString(a.getHungerDrop()));
		printToScreen("Fatigue: " + Integer.toString(a.getEnergyDrop()));
		printToScreen("Depression: " + Integer.toString(a.getHappinessDrop()));
		printToScreen("Aggression: " + Integer.toString(a.getAggression()));
		printToScreen("Favourite food: " + a.getFavouriteFood());
		printToScreen("Favourite toy: " + a.getFavouriteToy());
		printToScreen("Are you sure this is the right pet for you?");
		printToScreen("(1) Yes\n(2) No");
		boolean isValid = false;
		boolean selected = false;
		while(!isValid) {
			String option = getInput();
			switch(option){
			case "1":
				isValid = true;
				selected = true;
				break;
			case "2":
				isValid = true;
				selected = false;
				break;
			default: 
				printToScreen("Please select a valid number!");
			}
			}
	return selected;
	}
	/**
	 * return void
	 * Each pet gets 2 actions per day.
	 * If the pet has (medium/low) happiness or hunger, the pet will start misbehaving.
	 * If the pet has low hunger, bladder, or energy, the pet will get sick.
	 * If any stats drops to 0, the pet will die.
	 * If the pet gets sick, the pet is no longer misbehaving.
	 * If the pet dies, then the pet is no longer misbehaving or sick.
	 */
	public void setPetCondition(Pet animal) {
		animal.setActionsRemaining(2);
		if (animal.getHappiness() <= 6 || animal.getHunger() <= 6) {
			if (animal.isSick() == false) {
			animal.setbehaviour(true);
			}
		}
		if (animal.getBladder() <= 3 || animal.getEnergy() <= 3 || animal.getHunger() <= 3) {
			animal.setbehaviour(false);
			animal.setSick(true);
			}
		if (animal.getHappiness() == 0 || animal.getBladder() == 0 || animal.getHunger() == 0 || animal.getEnergy() == 0) {
			animal.setbehaviour(false);
			animal.setSick(false);
			if (animal.isDead() == false) {
			animal.setDead(true);
			printToScreen("Oh no! " + animal.getName() + " has died!");
			}
		}
	}
	/**
	 * returns void
	 * Each pet drops stat such as energy, happiness, and hunger level.
	 * If the pet is dead, then the stats will not drop.
	 * If the pet is sick, then the bladder stat will also drop.
	 */
	public void statDrops(Pet animal) {
		if (animal.isDead() == false) {
			int sleepDrop = 0 - animal.getEnergyDrop();
			int happyDrop = 0 - animal.getHappinessDrop();
			int hungerDrop = 0 - animal.getHungerDrop();
			animal.setEnergy(sleepDrop);
			animal.setHappiness(happyDrop);
			animal.setHunger(hungerDrop);
			if (animal.isSick() == true) {
				animal.setBladder(-2);
			}
		}
	}
	/**
	 * returns Void.
	 * Prints out all the items available in the inventory.
	 * If the inventory is empty, the player will be informed.
	 */
	public void checkInventory(Player p) {
		printToScreen("Inventory: ");
		String items = "The inventory is empty!";
		int index = 1;
		for (Item item:p.inventory) {
			if (item instanceof Food) {
				items = ((Food) item).getFood();
			}
			if (item instanceof Toy) {
				items = ((Toy) item).getName();
			}
			printToScreen("(" + Integer.toString(index) + ") " + items);
			index++;
		}
		if (p.inventory.size() == 0) {
			printToScreen(items);
		}
	}
	
	/**
	 * returns Void.
	 * Open up the shop by asking which section of the shop the player is willing to visit.
	 * Player also have an option to exit the shop (return to playDay method). 
	 */ 
	public void useShop(Player p){
		boolean isValid = false;
		printToScreen("Welcome "+p.getName()+" .\nYou have $"+p.getMoney()+" Avaliable.\nWhat would you like to purchase?");
		printToScreen("(1)Food\n(2)Toys\n(3)Revive\n(4)Treatment\n(5)Cancel");
		while(!isValid){
			String option = getInput();
			switch(option){
			case "1": purchaseFood(p);
				isValid = true;
				break;
			case "2": purchaseToy(p);
				isValid = true;
				break;
			case "3": revivePet(p);
				isValid = true;
				break;
			case "4": 
				healPet(p);
				isValid = true;
				break;
			case "5": isValid = true;
				printToScreen("Thank you for shopping with us!");
				break;
			default: printToScreen("Please enter a valid option number");
			isValid = false;
			break;
		
		}
		}
		
	}
	
	/**
	 * returns Void.
	 * The player is given a list of dead pets (Pet died as a zombie will not be on this list).
	 * Players cannot revive a zombie.
	 * Player can either revive them or cancel the visit.
	 * When the pet gets revived, the player loses $30, and the pet turns into a zombie and is no longer dead.
	 * Also, checks if the player currently has enough money to revive them.
	 * The pet's stat remains the same.
	 */
	public void revivePet(Player p){
		ArrayList<Integer> position = new ArrayList<Integer>(0);
		printToScreen("Which of the following pets would you like to revive? (Cost $30 per pet)");
		int count = 1;
		int index = 0;
		for(Pet animal:p.petArray) {
			if (animal.isDead() == true && animal.isZombie() == false) {
				printToScreen("(" + Integer.toString(count) + ")" + " " + animal.getName());
				count++;
				position.add(index);
				}
			index++;
			}
		printToScreen("(" + Integer.toString(count) + ") Cancel");
		boolean isValid = false;
		int selected = 0;
		while(!isValid){
			String patientNum = getInput();
			try {
				selected = Integer.parseInt(patientNum) - 1;
				if(selected >= 0 && selected <= position.size()){
					isValid = true;
			}
			}
			catch(NumberFormatException e){
				printToScreen("Please enter a valid number.\n");
			}
		}
		if (selected != position.size()) {
			if (p.getMoney() >= 30) {
			int selectedPet = position.get(selected);
			Pet patientP = p.petArray.get(selectedPet);
			patientP.setDead(false);
			patientP.setZombie(true);
			p.setMoney(-30);
			printToScreen(patientP.getName() + " is back from the dead!");
			}
			else {
				printToScreen("You don't have enough money!");
			}
		}
		useShop(p);
		}
	/**
	 * returns Void.
	 * The player is given a list of sick pets.
	 * The player has a choice to heal them or cancel the visit.
	 * Checks if the player can currently afford to treat the pet.
	 * If the pet is healed, the player loses $10, and the pet is no longer sick and should feel happier.
	 */
	public void healPet(Player p){
		ArrayList<Integer> position = new ArrayList<Integer>(0);
		printToScreen("Which of the following pets would you like to heal? (Cost $10 per pet)");
		int count = 1;
		int index = 0;
		for(Pet animal:p.petArray) {
			if (animal.isSick() == true && animal.getActionsRemaning() > 0) {
				printToScreen("(" + Integer.toString(count) + ")" + " " + animal.getName());
				count++;
				position.add(index);
				}
			index++;
			}
		printToScreen("(" + Integer.toString(count) + ") Cancel");
		boolean isValid = false;
		int selected = 0;
		while(!isValid){
			String patientNum = getInput();
			try {
				selected = Integer.parseInt(patientNum) - 1;
				if(selected >= 0 && selected <= position.size()){
					isValid = true;
			}
			}
			catch(NumberFormatException e){
				printToScreen("Please enter a valid number.\n");
			}
		}
		if (selected != position.size()) {
			if (p.getMoney() >= 10) {
			int selectedPet = position.get(selected);
			Pet patientP = p.petArray.get(selectedPet);
			patientP.setSick(false);
			patientP.setHappiness(3);
			int waitingTime = patientP.getActionsRemaning() - 1;
			patientP.setActionsRemaining(waitingTime);
			printToScreen(patientP.getName() + " is feeling much better now!");
			p.setMoney(-10);
			}
			else {
				printToScreen("You don't have enough money!");
			}
		}
		useShop(p);
		}
	
	/**
	 * returns Void.
	 * Gives a player list of food available to them.
	 * Player gets a choice of either buying them or cancel the visit.
	 * Once the player picks a valid option, it will show the status of the food and has a choice to buy them or not.
	 * Checks if the player can afford the food.
	 * If chosen to buy the food, the food will be added to the inventory and the player will lose some money (Depending on the price).
	 */
	public void purchaseFood(Player p){
		boolean isValid = false;
		Food food1 = new CurryRice();
		Food food2 = new FishAndChip();
		Food food3 = new Hamburger();
		Food food4 = new FriedChicken();
		Food food5 = new Pizza();
		Food food6 = new Steak();
		printToScreen("Food options available:\n(1)" + food1.getFood() + " $" + food1.getPrice());
		printToScreen("(2)" + food2.getFood() + " $" + food2.getPrice());
		printToScreen("(3)" + food3.getFood() + " $" + food3.getPrice());
		printToScreen("(4)" + food4.getFood() + " $" + food4.getPrice());
		printToScreen("(5)" + food5.getFood() + " $" + food5.getPrice());
		printToScreen("(6)" + food6.getFood() + " $" + food6.getPrice());
		printToScreen("(7)Cancel");
		String option = getInput();
		switch(option){ 
			case "1": 
				if (food1.getPrice() > p.getMoney()) {
					printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
					isValid = false;
					break;
				}
				else {
				boolean bought1 = foodStatusDisplay(p, food1);
				if (bought1 == true) {
				p.inventory.add(food1);
				int cost1 = 0 - food1.getPrice();
				p.setMoney(cost1);
				}
				isValid = true;
				break;
				}
			case "2": 
				if (food2.getPrice() > p.getMoney()) {
					printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
					isValid = false;
					break;
				}
				else {
				boolean bought2 = foodStatusDisplay(p, food2);
				if (bought2 == true) {
				p.inventory.add(food2);
				int cost2 = 0 - food2.getPrice();
				p.setMoney(cost2);
				}
				isValid = true;
				break;
				}
			case "3": 
				if (food3.getPrice() > p.getMoney()) {
					printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
					isValid = false;
					break;
				}
				else {
				boolean bought3 = foodStatusDisplay(p, food3);
				if (bought3 == true) {
				p.inventory.add(food3);
				int cost3 = 0 - food3.getPrice();
				p.setMoney(cost3);
				}
				isValid = true;
				break;
				}
			case "4": 
				if (food4.getPrice() > p.getMoney()) {
					printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
					isValid = false;
					break;
				}
				else {
				boolean bought4 = foodStatusDisplay(p, food4);
				if (bought4 == true) {
				p.inventory.add(food4);
				int cost4 = 0 - food4.getPrice();
				p.setMoney(cost4);
				}
				isValid = true;
				break;
				}
			case "5": 
				if (food5.getPrice() > p.getMoney()) {
					printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
					isValid = false;
					break;
				}
				else {
				boolean bought5 = foodStatusDisplay(p, food5);
				if (bought5 == true) {
				p.inventory.add(food5);
				int cost5 = 0 - food5.getPrice();
				p.setMoney(cost5);
				}
				isValid = true;
				break;
				}
			case "6": 
				if (food6.getPrice() > p.getMoney()) {
					printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
					isValid = false;
					break;
				}
				else {
				boolean bought6 = foodStatusDisplay(p, food6);
				if(bought6 == true){
				p.inventory.add(food6);
				int cost6 = 0 - food6.getPrice();
				p.setMoney(cost6);
				}
				isValid = true;
				break;
				}
			case "7": isValid = true;
				break;
			default: printToScreen("Please enter a valid option number");
				isValid = false;
				break;
			
			
		}
		if(!isValid){
			purchaseFood(p);
		}
		useShop(p);
	}
	
	/**
	 * returns Void.
	 * Player gets a choice of 6 different toys.
	 * Player can choose to either buy the toy or cancel the visit.
	 * Checks if the player can afford the toy.
	 * If the player picks a valid option, it will show a status of the toy and the player can choose to buy them or not.
	 * If the player has chosen to buy them, the toy gets added to the inventory and lose some money (Depending on the price).
	 */
	public void purchaseToy(Player p){
		boolean isValid = false;
		Toy toy1 = new Doll();
		Toy toy2 = new Football();
		Toy toy3 = new Pillow();
		Toy toy4 = new Stick();
		Toy toy5 = new ToiletPaper();
		Toy toy6 = new ToyCar();
		printToScreen("Toy options available:\n(1)" + toy1.getName() + " $" + toy1.getPrice());
		printToScreen("(2)" + toy2.getName() + " $" + toy2.getPrice());
		printToScreen("(3)" + toy3.getName() + " $" + toy3.getPrice());
		printToScreen("(4)" + toy4.getName() + " $" + toy4.getPrice());
		printToScreen("(5)" + toy5.getName() + " $" + toy5.getPrice());
		printToScreen("(6)" + toy6.getName() + " $" + toy6.getPrice());
		printToScreen("(7)Cancel");
		String option = getInput();
		switch(option){
			case "1":
			if (toy1.getPrice() > p.getMoney()) {
				printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
				isValid = false;
				break;
			}
			else {
			boolean bought1 = toyStatusDisplay(p, toy1);
			if (bought1 == true) {
			p.inventory.add(toy1);
			int cost1 = 0 - toy1.getPrice();
			p.setMoney(cost1);
			}
			isValid = true;
			break;
			}
			case "2": 
				if (toy2.getPrice() > p.getMoney()) {
					printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
					isValid = false;
					break;
				}
				else {
				boolean bought2 = toyStatusDisplay(p, toy2);
				if (bought2 == true) {
				p.inventory.add(toy2);
				int cost2 = 0 - toy2.getPrice();
				p.setMoney(cost2);
				}
				isValid = true;
				break;
				}
			case "3":
				if (toy3.getPrice() > p.getMoney()) {
					printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
					isValid = false;
					break;
				}
				else {
				boolean bought3 = toyStatusDisplay(p, toy3);
				if (bought3 == true) {
				p.inventory.add(toy3);
				int cost3 = 0 - toy3.getPrice();
				p.setMoney(cost3);
				}
				isValid = true;
				break;
				}
			case "4":
				if (toy4.getPrice() > p.getMoney()) {
					printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
					isValid = false;
					break;
				}
				else {
				boolean bought4 = toyStatusDisplay(p, toy4);
				if (bought4 == true) {
				p.inventory.add(toy4);
				int cost4 = 0 - toy4.getPrice();
				p.setMoney(cost4);
				}
				isValid = true;
				break;
				}
			case "5": 
				if (toy5.getPrice() > p.getMoney()) {
					printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
					isValid = false;
					break;
				}
				else {
				boolean bought5 = toyStatusDisplay(p, toy5);
				if (bought5 == true) {
				p.inventory.add(toy5);
				int cost5 = 0 - toy5.getPrice();
				p.setMoney(cost5);
				}
				isValid = true;
				break;
				}
			case "6":
				if (toy6.getPrice() > p.getMoney()) {
					printToScreen("YOU DON'T HAVE ENOUGH MONEY!");
					isValid = false;
					break;
				}
				else {
				boolean bought6 = toyStatusDisplay(p, toy6);
				if (bought6 == true) {
				p.inventory.add(toy6);
				int cost6 = 0 - toy6.getPrice();
				p.setMoney(cost6);
				}
				isValid = true;
				break;
				}
			case "7": isValid = true;
				break;
			default: printToScreen("Please enter a valid option number");
				isValid = false;
				break;
			
			
		}
		if(!isValid){
			purchaseToy(p);
		}
		useShop(p);
	}
	/**
	 * returns Void.
	 * By calling this method, the pet's bladder should change to maximum (10).
	 */
	public void useToilet(Pet a) { 
		a.setBladder(10);
		printToScreen(a.getName() + " feels relief.");
	}
	/**
	 * returns Void.
	 * By calling this method, the player is given a choice of food available in the inventory.
	 * If the food does not contain inside the inventory, it will do nothing but lose 1 action point.
	 * If the food is given to a pet, the pet's hunger and happiness level go up, but the pet's bladder goes down.
	 * If the food given is pet's favourite food, the happiness goes up by 1.
	 * The food is removed from the inventory once eaten.
	 * If the pet is sick, the nutrition of the food will drop. Also the pet will not be able to taste the food.
	 */
	public void feed(Player p, Pet a) {
		boolean isValid = false;
		int selected = 0;
		ArrayList<Integer> nums = new ArrayList<Integer>(0);
		int index = 0;
		int count = 1;
		for (Item i : p.inventory) {
			if (i instanceof Food) {
				printToScreen("("+Integer.toString(count)+") " + ((Food) i).getFood());
				count++;
				nums.add(index);
			}
			index++;
		}
		if (count == 1) {
			printToScreen(p.getName() + " is looking for food, but could not find any food inside his bag.");
		}
		else {
		printToScreen("Choose which food " + a.getName() +" would like to eat:");
		while(!isValid){
		String foodOption = getInput();
		try {
			selected = Integer.parseInt(foodOption) - 1;
			if(selected >= 0 && selected < nums.size()){
				isValid = true;
			}
			else {
				printToScreen("Please enter a valid number.");
			}
		}
		catch(NumberFormatException e){
			printToScreen("Please enter a valid number.\n");
		}
	}
		int selectedFood = nums.get(selected);
		Food food = (Food) p.inventory.get(selectedFood);
		if (a.isSick() == true) {
			printToScreen(a.getName() + " is slowly eating " + food.getFood());
			int nutritionAdjust = (food.getNutrition() / 2);
			a.setHunger(nutritionAdjust);
			int bladderDrop = 0 - food.getFullness();
			a.setBladder(bladderDrop);
		}
		else {	
			if (a.getFavouriteFood() == food.getFood()) {
				printToScreen(a.getName() + " is happily eating a " + food.getFood());
				a.setHunger(food.getNutrition());
				int bladderDrop = 0 - food.getFullness();
				a.setBladder(bladderDrop);
				int happyUp = food.getTaste() + 1;
				a.setHappiness(happyUp);
			}
			else {
				printToScreen(a.getName() + " is eating a " + food.getFood());
				a.setHunger(food.getNutrition());
				int bladderDrop = 0 - food.getFullness();
				a.setBladder(bladderDrop);
				a.setHappiness(food.getTaste());
			}
		}
		p.inventory.remove(selectedFood);
		}
	}
	/**
	 * returns Void.
	 * By calling this method, the player is given a choice of toys available in the inventory.
	 * If the toy does not contain inside the inventory, it will do nothing but lose 1 action point.
	 * If the toy is given to a pet, the pet's happiness level go up, but energy and hunger may go down.
	 * If the food given is pet's favourite toy, the happiness goes up by 1.
	 * The toy's durability gets reduced once used (If the toy's durability drops to under 0 then the toy breaks).
	 * If the toy breaks, the toy is removed from the inventory.
	 * If the pet is sick, it will use up more energy to play.
	 * If the pet is misbehaving, it will try to break the toy.
	 */
	public void play(Player p , Pet a) {
		boolean isValid = false;
		int selected = 0;
		ArrayList<Integer> nums = new ArrayList<Integer>(0);
		int index = 0;
		int count = 1;
		for (Item i : p.inventory) {
			if (i instanceof Toy) {
				printToScreen("("+Integer.toString(count)+") " + ((Toy) i).getName());
				count++;
				nums.add(index);
			}
			index++;
		}
		if (count == 1) {
			printToScreen(p.getName() + " is looking for toys, but could not find any toy inside his bag.");
		}
		else {
		printToScreen("Choose which toy " + a.getName() +" would like to play:");
		while(!isValid){
		String toyOption = getInput();
		try {
			selected = Integer.parseInt(toyOption) - 1;
			if(selected >= 0 && selected < nums.size()){
				isValid = true;
				}
			else {
				printToScreen("Please enter a valid number.\n");
			}
		}
		catch(NumberFormatException e){
			printToScreen("Please enter a valid number.\n");
		}
	}
		int selectedToy = nums.get(selected);
		Toy toy = (Toy) p.inventory.get(selectedToy);
		if (a.getFavouriteToy() == toy.getName()) {
			printToScreen(a.getName() + " is happily playing with a " + toy.getName());
			int funUp = toy.getHappy() + 1;
			a.setHappiness(funUp);
		}
		else {
			printToScreen(a.getName() + " is playing with a " + toy.getName());
			a.setHappiness(toy.getHappy());
		}
		if (a.isMisbehave() == true) {
			printToScreen(a.getName() + " is trying to break the " + toy.getName());
			toy.setDurability(1);
		}
		toy.setDurability(a.getAggression());
		if (toy.getDurability() <= 0) {
			printToScreen("Oh no the " + toy.getName() + " is broken!");
			p.inventory.remove(selectedToy);
		}
		if (a.isSick() == true) {
			printToScreen(a.getName() + " is exhausted.");
			int drops1 = 0 - (toy.getExercise() + 1);
			a.setEnergy(drops1);
			a.setHunger(drops1);
		}
		else {
			int drops2 = 0 - toy.getExercise();
			a.setEnergy(drops2);
			a.setHunger(drops2);
		}
		}
	}
	/**
	 * returns Void.
	 * By calling this method, the pet's energy increases to maximum (10).
	 * If the pet is misbehaving, the pet will not sleep properly.
	 */
	public void sleep(Pet a) {
		if (a.isMisbehave() == true) {
			a.setEnergy(2);
			printToScreen(a.getName() + " is pretending to sleep.");
		}
		else {
			a.setEnergy(10);
			printToScreen(a.getName() + " is sleeping... zzzzzzzz");
			printToScreen(a.getName() + " has woken up and feels great!");
		}
	}
	/**
	 * returns Void.
	 * By calling this method, the pet's happiness drops to 0, but the pet is no longer misbehaving.
	 */
	public void discipline(Pet a) {
		int dropFun = 0 - a.getHappiness();
		a.setHappiness(dropFun);
		a.setbehaviour(false);
		printToScreen(a.getName() + " is starting to behave now but is feeling unhappy.");
	}
	/**
	 * returns Void.
	 * By calling this method, it prints of the status of the given pet
	 */
	public void status(Pet a) {
		printToScreen("Status of how " + a.getName() + " is feeling now: (0=low, 10=High)");
		printToScreen("Happiness: " + Integer.toString(a.getHappiness()));
		printToScreen("Bladder: " + Integer.toString(a.getBladder()));
		printToScreen("Energy: " + Integer.toString(a.getEnergy()));
		printToScreen("Hunger: " + Integer.toString(a.getHunger()));
		}
	/**
	 * returns Void
	 * Each pet's overall stats contributes to the score (Unless the pet is dead).
	 * Players earn extra points if the pet is not misbehaving/sick (Pets that are misbehaving earns more points than pets that are sick).
	 */
	public void perDayScores(Player p, Pet a) {
		int score = 0;
		if (a.isMisbehave() == true) {
			score = a.getOverallStat() * 2;
		}
		else {
			if (a.isSick() == true) {
				score = a.getOverallStat();
			}
			else {
				if (a.isDead() == false) {
					score = a.getOverallStat() * 4;
				}
			}
		}
		p.setScore(score);
	}
	/**
	 * returns double.
	 * On the final day, the pet's condition contributes to your final score.
	 * If the pet is dead or sick, the player will lose some points.
	 * If the pet is misbehaving, the score will not change.
	 * If the pet is in perfect condition, it will gain extra points.
	 */
	public double finalScoreAdjust(Player p, Pet a) {
		double finalScore = 0;
		if (a.isDead() == true) {
			finalScore = -0.3;
		}
		else {
			if (a.isSick() == true) {
				finalScore = -0.1;
			}
			else {
				if (a.isMisbehave() == false) {
					finalScore = 0.2;
				}
			}
		}
		return finalScore;
	}
	/**
	 * returns Void.
	 * Prints off the final score for each player.
	 */
	public void finalScoreDisplay(ArrayList<Player> players) {
		for (Player person : players) {
			double scoreAdjust = 1;
			for(Pet animal:person.petArray){
				scoreAdjust = scoreAdjust + finalScoreAdjust(person, animal);
			}
			int finalScore = (int) (scoreAdjust * person.getScore());
			printToScreen(person.getName() + " your final score is: " + Integer.toString(finalScore));
			}
	}
	/**
	 * returns void.
	 * Each pet gets 2 actions per day.
	 * The player is given $20 each day.
	 * Depending on the pet's stat, it can change the pet's condition.
	 */
	public void setDailyCondition(Player person) {
		person.setStillTurn(true);
		person.setMoney(20);
		for(Pet animal:person.petArray){
			setPetCondition(animal);
		}
	}
	/**
	 * returns Void.
	 * Prints off a list of pets and options to move to the next day, use the shop.
	 */
	public void displayPets(Player p){
		int i = 1;
		for(Pet animal:p.petArray){
			printToScreen("("+Integer.toString(i)+") Interact with "+animal.getName());
			i++;
		}
		printToScreen("(" + Integer.toString(i) + ") Move to the next day");
		printToScreen("(" + Integer.toString(i + 1) + ") Use the shop");
		printToScreen("(" + Integer.toString(i + 2) + ") check the inventory");
	}
	
	/**
	 * returns Void.
	 * The player gets a choice of either...
	 * interacting with one of the pets
	 * go to the shop
	 * check the inventory
	 * move to the next day.
	 * Gives an error message if the player didn't pick a valid option.
	 */
	public void playDay(Player p){
		printHeader();
		printToScreen("Player: "+ p.getName());
		printHeader();
		printToScreen("What would you like to do?");
		displayPets(p);
		boolean isValid = false;
		int selectedPet = 0;
		while(!isValid){
			String petNumber = getInput();
			try{
				selectedPet = Integer.parseInt(petNumber) - 1;
				if(selectedPet >= 0 && selectedPet <=(p.petArray.size() + 2)){
					isValid = true;
				}
				else {
					printToScreen("Please enter a valid number.\n");
				}
			}catch(NumberFormatException e){
				printToScreen("Please enter a valid number.\n");
			}
		}
		if (selectedPet < p.petArray.size()){
			if(p.petArray.get(selectedPet).getActionsRemaning() !=0){
				dailyPetUse(p,p.petArray.get(selectedPet));
			}
			else {
				Pet animal = p.petArray.get(selectedPet);
				printToScreen(animal.getName() + " wants to call it a day. Please choose a different pet");
			}
			}
		else {
		if (selectedPet == p.petArray.size()){
			p.setStillTurn(false);
		}
		else {
			if (selectedPet == p.petArray.size() + 1) {
			useShop(p);
			}
			else {
				checkInventory(p);
			}
		}
		}
		
	}
	
	/**
	 * returns Void.
	 * If the pet is misbehaving or sick, then it will alert the player.
	 * If the pet is dead, then it will go back to playDay method (The player cannot interact with dead pet).
	 * The player is given a list of methods, which if the player picks a valid option, it will call the method.
	 */
	public void dailyPetUse(Player p, Pet a){
		printHeader();
		printToScreen("Pet: "+a.getName());
		printHeader();
		if (a.isDead() == false) {
		boolean isValid = false;
		while(!isValid){
			if ( a.isMisbehave() == true) {
				printToScreen(a.getName() + " is misbehaving!");
			}
			if (a.isSick() == true) {
				printToScreen(a.getName() + " is feeling sick!");
			}
			printToScreen(a.getName() + " has " + a.getActionsRemaning() + " actions remaining!");
			printToScreen("What would you like to do with " +a.getName()+" ?");
			printToScreen("\t(1) Use the Shop\n\t(2) Use the Toilet\n\t(3) Feed "+a.getName()+"\n\t(4) Play with "+a.getName()+"\n\t(5) Put "+ a.getName() + " to sleep" +"\n\t(6) Discipline "+a.getName() + "\n\t(7) View " + a.getName() + "'s status");
			String option = getInput();
			switch(option){
				case "1": useShop(p);
					isValid = true;
					break;
				case "2": 
					useToilet(a);
					isValid = true;
					int actions = a.getActionsRemaning();
					int newActions = actions - 1;
					a.setActionsRemaining(newActions);
					break;
				case "3":
					feed(p, a);
					isValid = true;
					int actions1 = a.getActionsRemaning() - 1;
					a.setActionsRemaining(actions1);
					break;
				case "4":
					play(p, a);
					isValid = true;
					int actions2 = a.getActionsRemaning() - 1;
					a.setActionsRemaining(actions2);
					break;
				case "5":
					sleep(a);
					isValid = true;
					int actions3 = a.getActionsRemaning() - 1;
					a.setActionsRemaining(actions3);
					break;
				case "6":
					discipline(a);
					isValid = true;
					int actions4 = a.getActionsRemaning() - 1;
					a.setActionsRemaining(actions4);
					break;
				case "7":
					status(a);
					isValid = true;
					break;
			}
			if (isValid == false){
				printToScreen("Please enter a valid option number");
				isValid = false;
				break;
			}
		}
		
		
	}
		else {
			printToScreen(a.getName() + " is dead. Please choose a different pet!");
		}
	}
	/**
	 * returns Void.
	 * Prints off which day the players are currently on.
	 */
	public void printDay(){
		printHeader();
		printToScreen("|           Day"+getDay()+"           |");
		printHeader();
	}
	/**
	 * The main function.
	 * Each pet gets 2 actions per day.
	 * The player is given $20 each day.
	 * Depending on the pet's stat, it can change the pet's condition.
	 * As each day passes, each stat drops (Except the final day).
	 * Players earn points each day (Unless the pet is dead).
	 * Final score is calculated depending on the pet's conditions. 
	 */
	public static void main(String[] args) {
		GameEnvironment g = new GameEnvironment();
		g.printHeader();
		g.printToScreen("| Welcome to virtual pets! |");
		g.printHeader();
		g.createGame();
		while (g.day<=g.lengthOfGame) {
			g.printDay();
			for (Player person : g.playerArray) {
				g.setDailyCondition(person);
				while(person.isStillTurn()){
					g.playDay(person);
				}
				for(Pet animal:person.petArray){
					g.perDayScores(person, animal);
					g.statDrops(animal);
				}
			}
			g.day++;
		}
		g.finalScoreDisplay(g.playerArray);
	}
}
