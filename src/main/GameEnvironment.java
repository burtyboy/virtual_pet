package main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import foodItems.CurryRice;
import foodItems.FishAndChip;
import foodItems.FriedChicken;
import foodItems.Hamburger;
import foodItems.Pizza;
import foodItems.Steak;
import pets.Bird;
import pets.Cat;
import pets.CatDog;
import pets.Dog;
import pets.Ocelot;
import pets.Tiger;
import main.Food;
import main.GameEnvironment;
import main.Item;
import main.Pet;
import main.Player;
import main.Printable;
import main.Toy;
import toyItems.Doll;
import toyItems.Football;
import toyItems.Pillow;
import toyItems.Stick;
import toyItems.ToiletPaper;
import toyItems.ToyCar;

/**
 * Main loop for this game. Provides all control methods and calls and advances days. Running this main file will play a command line version of the game.
 * Implements the interface printable which provides the methods required for command line display and input collection
 * 
 * @author Josh Burt , Nobu Sato
 *
 */
public class GameEnvironment implements Printable {
	private int day = 1;
	private int numberOfPlayers, numberOfPets, lengthOfGame = 0;
	public ArrayList<Player> playerArray = new ArrayList<Player>(0); //changed to allow testing
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
	 *
	 * Adds a new player to the game and stores this player in the game environments arrayList playerArray.
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
				if(person.getPlayerName().equals(playerName)){
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
	 * 
	 * Adds a pet to a arrayList petArray owned by each player.
	 * Checks type and generates pet of that type.
	 * Provides validation for names by checking uniqueness against both players and any pets currently in game, length and Non-emptiness
	 * @param p Player that the pet is being added to.
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
				default: printToScreen("Please enter a valid number.");
			}
		}
	}
	/**
	 * 
	 * Provides validation for names by checking uniqueness against any pets currently in game, length and Non-emptiness.
	 * @return String.
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
				if(person.getPlayerName().equals(petName)){
					isValid = false;
					printToScreen("Names must be unique, please choose a new name.\n");
				}
			
			for(Pet animal:person.petArray){
				if(animal.getPetName().equals(petName)){
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
	 * 
	 * Returns a name of the item.
	 * @param item item that requires name to be returned for
	 * @return String.
	 */
	public String getItemName(Item item){
		if (item instanceof Food){
			return ((Food) item).getFoodName();
		}
		else{ //Item class contains only classes of Toy and Food.
			return ((Toy) item).getToyName();
		}
	}
	
	
	
	/**
	 * Method to implement Printable.
	 * opens a Scanner and returns the inputed String
	 * Scanner is left unclosed to allow function.
	 * Called by getInput()
	 * @return String
	 */
	public String getInput(){
		@SuppressWarnings("resource")
		Scanner inputString = new Scanner(System.in);
		String input =  inputString.nextLine();
		return input;
		
	}
	
	
	/**
	 * Prints a message to the output stream.
	 * Method to implement Printable.
	 * @param displayString string to be printed
	 */
	public void printToScreen(String displayString){
		System.out.println(displayString);
	}
	
	
	/**
	 * Sets up the game and creates pets and players.
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
			printToScreen("Player: " + person.getPlayerName());
			isValid = false;
			while(!isValid){
				
				printToScreen("How many Pets would you like(1-3)? \n");
				String numPets = getInput();
				try{
					numberOfPets  = Integer.parseInt(numPets);
					if(numberOfPets >= 1 && numberOfPets <=3){
						isValid = true;
					}
					else {
						printToScreen("Please enter a valid number.");
					}
				}catch(NumberFormatException e){
					printToScreen("Please enter a valid number.\n");
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
			catch(NumberFormatException e){
				printToScreen("Please enter a valid number.\n");
				isValid = false;
			}
		}
		
		
	}
	/**
	 * 
	 * Displays an instruction on how to play the game.
	 */
	public void displayInstruction() {
		printToScreen("Welcome to the World of The Virtual Pets. Your goal here is to look after your \r\n"
				+ "pets. The pets want your attention, and it is your job to lead them in the right \r\n"
				+ "direction. The game itself is 1-3 players, and each player has a choice of 1-3 \r\n"
				+ "pets (The more pet you have, the more responsibility you have). There are six \r\n"
				+ "different species of pets to pick from the list. Each species have different stats, \r\n"
				+ "favourite food, and favourite toy. Once all the questions been answered, the \r\n"
				+ "game will start.\r\n\r\nEvery new day, each player receives $20, and each pet has a set action points \r\n"
				+ "of 2 per day. The pet uses action point by sleeping, visiting the kitchen, \r\n"
				+ "lounge, naughty corner, bathroom or vet. The player has a choice of visiting \r\n"
				+ "the shop, feed the pet, play with the pet, send the pet to sleep, send the pet \r\n"
				+ "to the bathroom, discipline the pet, or move to the next day. \r\n\r\n"
				+ "The Shop\r\nThe shop is divided into four sections. There is a supermarket, toy shop, vet, \r\n"
				+ "and cemetery. The supermarket sells six different foods. Each food has \r\n"
				+ "different stats and price. Similarly, the toy shop sells six different toys. Each \r\n"
				+ "toy also has different stats and price. The vet heals the sickness of the pet \r\n"
				+ "with a cost of $10 each. The cemetery revives the pet with a cost of $30 each. \r\n"
				+ "The pet will turn into a zombie once it gets revived. However, the zombie pet \r\n"
				+ "can not be revived. \r\n\r\nUse the toilet\r\n"
				+ "When the pet visits the bathroom, the pet's bladder goes to maximum level \r\nand loses 1 kg.\r\n\r\n"
				+ "Feed the pet\r\nThe pet gains 1 kg by eating food. The pet's status changes vary depending on \r\n"
				+ "the food. If the pet consumes their favourite food, the happiness level goes up \r\n"
				+ "by one bar. If the pet is sick, the pet will not be able to taste the food, meaning \r\n"
				+ "the happiness would not change. Once the food is consumed, the food is \r\n"
				+ "removed from the inventory, and the bladder level will drop. The drop varies \r\ndepending on the meal size. \r\n"
				+ "\r\nPlay with the toy\r\nThe pet's status changes vary depending on the toy. If the pet plays with their \r\n"
				+ "favourite toy, the happiness level goes up by one. The toy's durability drops \r\n"
				+ "once the toy has been played.  The drop varies depending on how aggressive \r\n"
				+ "the pet is. The pet applies more damage on the toy than usual if the pet is \r\n"
				+ "misbehaving. Once the toy breaks, the toy is removed from the inventory.\r\n\r\nSleep\r\n"
				+ "Once the pet sleeps, the pet's energy level moves up to a maximum level. \r\n"
				+ "However, if the pet is misbehaving, the pet will pretend to sleep and won't \r\ngain much energy. \r\n\r\n"
				+ "Discipline\r\nIf the pet is misbehaving, you can discipline the pet. Once the pet is \r\n"
				+ "disciplined, the pet starts behaving. However, the happiness level drops \r\ndrastically. \r\n\r\nWeight\r\n"
				+ "If the pet is overweight, the pet will get hungry quicker than usual. If the \r\n"
				+ "pet is underweight, the pet will get sick. \r\n\r\nDead\r\n"
				+ "Once the pet dies, the pet will not be able to do anything. However, the pet \r\n"
				+ "can be revived by visiting the cemetery, and the pet will turn into a zombie. \r\n"
				+ "However, if the pet dies as a zombie, then the pet is no longer be able to revive.\r\n\r\n"
				+ "Next day\r\nOnce the player did all the necessary moves, the player can move to the next \r\n"
				+ "day which either moves on to the next player or move to the next day \r\ndepending on the situation.\r\n\r\n"
				+ "Score \r\nThe score is calculated daily depending on the pet's status. On the final day, \r\n"
				+ "the player can earn extra points depending on the pet's condition. However, \r\n"
				+ "the player can also lose points by having pets with a condition sick or dead. ");
	}
	/**
	 * 
	 * Prints off the selected food's status.
	 * Gives a player a choice to either buy them or not.
	 * Meal size depends on the fullness of the food.
	 * Also, prints off the cost of the food and the amount of money player currently have.
	 * @param p Player that is using the shop
	 * @param food food item passed here by the shop.
	 * @return Boolean
	 */
	public boolean foodStatusDisplay(Player p, Food food) {
		printToScreen("Here is the following status of " + food.getFoodName() + " (1 = Bad, 10 = Good)");
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
	 * 
	 * Prints off the selected toy's status.
	 * Gives a player a choice to either buy them or not.
	 * The exercise level varies depending which toy it is.
	 * Also, prints off the cost of the toy and the amount of money player currently have.
	 * @param p Player that is using the shop
	 * @param toy toy that is passed here by the shop.
	 * @return Boolean
	 */
	public boolean toyStatusDisplay(Player p, Toy toy) {
		printToScreen("Here is the following status of " + toy.getToyName() + " (1 = Low, 10 = High)");
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
	 *
	 * Prints of the selected pet's status.
	 * Player gets a choice of either keeping the pet or choose a different pet.
	 * @param a pet whos stats are to be displayed 
	 * @return Boolean
	 */
	public boolean petStatusDisplay(Pet a) {
		printToScreen("Here is the following status of " + a.getPetName() + " (1 = Low, 3 = High)");
		printToScreen("Name : " + a.getPetName());
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
	 * Displays the attribute status for selected pet.
	 * Each pet gets 2 actions per day.
	 * If the pet has neutral happiness or hunger, the pet will start misbehaving.
	 * If the pet has low hunger, bladder, or energy, the pet will get sick.
	 * If the pet weighs 3 kilogram, the pet will get sick.
	 * If any stats drops to 0, the pet will die.
	 * If the pet gets sick, the pet is no longer misbehaving.
	 * If the pet dies, then the pet is no longer misbehaving or sick.
	 * @param animal pet whos stats are to be changed 
	 */
	public void setPetCondition(Pet animal) {
		animal.setActionsRemaining(2);
		if (animal.getHappiness() <= 6 || animal.getHunger() <= 6) {
			if (animal.isSick() == false) {
			animal.setBehaviour(true);
			}
		}
		if (animal.getBladder() <= 3 || animal.getEnergy() <= 3 || animal.getHunger() <= 3 || animal.getWeight() == 3) {
			animal.setBehaviour(false);
			animal.setSick(true);
			}
		if (animal.getHappiness() == 0 || animal.getBladder() == 0 || animal.getHunger() == 0 || animal.getEnergy() == 0) {
			animal.setBehaviour(false);
			animal.setSick(false);
			if (animal.isDead() == false) {
			animal.setDead(true);
			printToScreen("Oh no! " + animal.getPetName() + " has died!");
			}
		}
	}
	/**
	 * Calculates and impletments daily stat drops for pets.
	 * Each pet drops stat such as energy, happiness, and hunger level.
	 * If the pet is dead, then the stats will not drop.
	 * If the pet is sick, then the bladder stat will also drop.
	 * If the pet weighs more than 10 kilogram, the pet will get hungrier than usual.
	 * @param animal pet whos stats are to be changed 
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
			if (animal.getWeight() > 10) {
				animal.setHunger(-1);
			}
		}
	}
	/**
	 * 
	 * Prints out all the items available in the inventory.
	 * If the inventory is empty, the player will be informed.
	 * If there is a duplicate item in the inventory, then it will calculate the number of occurrence of the duplicated item.
	 * @param p object that needs inventory displayed
	 */
	public void displayInventory(Player p) {
		printToScreen("Inventory: ");
		String itemName = "The inventory is empty!";
		ArrayList<String> itemNames = new ArrayList<String>(0);
		ArrayList<String> items = new ArrayList<String>(0);
		for (Item item:p.inventory) {
			itemName = getItemName(item);
			items.add(itemName);
			if (itemNames.contains(itemName) == false) {
				itemNames.add(itemName);
			}
		}
		for (String name:itemNames) {
			int occurrences = Collections.frequency(items, name);
			printToScreen(name + " x" + Integer.toString(occurrences));
		}
		if (p.inventory.size() == 0) {
			printToScreen(itemName);
		}
	}
	
	/**
	 * Starts the shop.
	 * Open up the shop by asking which section of the shop the player is willing to visit.
	 * Player also have an option to exit the shop (return to playDay method).
	 * @param p Player who is to use the shop 
	 */ 
	public void useShop(Player p){
		boolean isValid = false;
		printToScreen("Welcome "+p.getPlayerName()+" .\nYou have $"+p.getMoney()+" Avaliable.\nWhat would you like to purchase?");
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
	 * Gives the player  the option to revive any dead pets.
	 * The player is given a list of dead pets (Pet died as a zombie will not be on this list).
	 * Players cannot revive a zombie.
	 * Player can either revive them or cancel the visit.
	 * When the pet gets revived, the player loses $30, and the pet turns into a zombie and is no longer dead.
	 * Also, checks if the player currently has enough money to revive them.
	 * The pet's stat remains the same.
	 * @param p Player who's pets are to be accessed.
	 */
	public void revivePet(Player p){
		ArrayList<Integer> position = new ArrayList<Integer>(0);
		printToScreen("Which of the following pets would you like to revive? (Cost $30 per pet)");
		int count = 1;
		int index = 0;
		for(Pet animal:p.petArray) {
			if (animal.isDead() == true && animal.isZombie() == false) {
				printToScreen("(" + Integer.toString(count) + ")" + " " + animal.getPetName());
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
			Pet patientPet = p.petArray.get(selectedPet);
			patientPet.setDead(false);
			patientPet.setZombie(true);
			p.setMoney(-30);
			printToScreen(patientPet.getPetName() + " is back from the dead!");
			}
			else {
				printToScreen("You don't have enough money!");
			}
		}
		useShop(p);
		}
	/**
	 * 
	 * The player is given a list of sick pets, with the option to heal them.
	 * The player has a choice to heal them or cancel the visit.
	 * Checks if the player can currently afford to treat the pet.
	 * If the pet is healed, the player loses $10, and the pet is no longer sick and should feel happier.
	 * @param p Player who's pets are to be accessed.
	 */
	public void healPet(Player p){
		ArrayList<Integer> position = new ArrayList<Integer>(0);
		printToScreen("Which of the following pets would you like to heal? (Cost $10 per pet)");
		int count = 1;
		int index = 0;
		for(Pet animal:p.petArray) {
			if (animal.isSick() == true && animal.getActionsRemaning() > 0) {
				printToScreen("(" + Integer.toString(count) + ")" + " " + animal.getPetName());
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
			Pet patientPet = p.petArray.get(selectedPet);
			patientPet.setSick(false);
			patientPet.setHappiness(3);
			int waitingTime = patientPet.getActionsRemaning() - 1;
			patientPet.setActionsRemaining(waitingTime);
			printToScreen(patientPet.getPetName() + " is feeling much better now!");
			p.setMoney(-10);
			}
			else {
				printToScreen("You don't have enough money!");
			}
		}
		useShop(p);
		}
	
	/**
	 * 
	 * Gives a player list of food available to them.
	 * Player gets a choice of either buying them or cancel the visit.
	 * Once the player picks a valid option, it will show the status of the food and has a choice to buy them or not.
	 * Checks if the player can afford the food.
	 * If chosen to buy the food, the food will be added to the inventory and the player will lose some money (Depending on the price).
	 * @param p Player who's currently using the shop.
	 */
	public void purchaseFood(Player p){
		boolean isValid = false;
		Food food1 = new CurryRice();
		Food food2 = new FishAndChip();
		Food food3 = new Hamburger();
		Food food4 = new FriedChicken();
		Food food5 = new Pizza();
		Food food6 = new Steak();
		printToScreen("Food options available:\n(1)" + food1.getFoodName() + " $" + food1.getPrice());
		printToScreen("(2)" + food2.getFoodName() + " $" + food2.getPrice());
		printToScreen("(3)" + food3.getFoodName() + " $" + food3.getPrice());
		printToScreen("(4)" + food4.getFoodName() + " $" + food4.getPrice());
		printToScreen("(5)" + food5.getFoodName() + " $" + food5.getPrice());
		printToScreen("(6)" + food6.getFoodName() + " $" + food6.getPrice());
		printToScreen("(7)Cancel");
		while (!isValid) {
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
		}
		useShop(p);
	}
	
	/**
	 * Allows Player to purchase Toys.
	 * Player gets a choice of 6 different toys.
	 * Player can choose to either buy the toy or cancel the visit.
	 * Checks if the player can afford the toy.
	 * If the player picks a valid option, it will show a status of the toy and the player can choose to buy them or not.
	 * If the player has chosen to buy them, the toy gets added to the inventory and lose some money (Depending on the price).
	 * @param p Player who's currently using the shop.
	 */
	public void purchaseToy(Player p){
		boolean isValid = false;
		Toy toy1 = new Doll();
		Toy toy2 = new Football();
		Toy toy3 = new Pillow();
		Toy toy4 = new Stick();
		Toy toy5 = new ToiletPaper();
		Toy toy6 = new ToyCar();
		printToScreen("Toy options available:\n(1)" + toy1.getToyName() + " $" + toy1.getPrice());
		printToScreen("(2)" + toy2.getToyName() + " $" + toy2.getPrice());
		printToScreen("(3)" + toy3.getToyName() + " $" + toy3.getPrice());
		printToScreen("(4)" + toy4.getToyName() + " $" + toy4.getPrice());
		printToScreen("(5)" + toy5.getToyName() + " $" + toy5.getPrice());
		printToScreen("(6)" + toy6.getToyName() + " $" + toy6.getPrice());
		printToScreen("(7)Cancel");
		while (!isValid) {
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
		}
		useShop(p);
	}
	/**
	 * Increases Pets bladder satisfaction.
	 * By calling this method, the pet's bladder should change to maximum (10).
	 * The pet loses 1 kilogram after visiting the bathroom.
	 * @param a Pet who is to use toilet
	 */
	public void useToilet(Pet a) { 
		a.setBladder(10);
		a.setWeight(-1);
		printToScreen(a.getPetName() + " feels relief.");
	}
	/**
	 * Allows the Player to feed the selected pet.
	 * By calling this method, the player is given a choice of food available in the inventory.
	 * If the food does not contain inside the inventory, it will do nothing but lose 1 action point.
	 * If the food is given to a pet, the pet's hunger and happiness level go up, but the pet's bladder goes down.
	 * If the food given is pet's favourite food, the happiness goes up by 1.
	 * The food is removed from the inventory once eaten.
	 * If the pet is sick, the pet will not be able to taste the food.
	 * The pet gains 1 kilogram after consuming the food.
	 * @param p Player who's pet is to be feed.
	 * @param a Pet that is to be feed.
	 */
	public void feed(Player p, Pet a) {
		boolean isValid = false;
		int selected = 0;
		ArrayList<Integer> nums = new ArrayList<Integer>(0);
		ArrayList<String> foodNames = new ArrayList<String>(0);
		ArrayList<String> foodInventory = new ArrayList<String>(0);
		int index = 0;
		for (Item i : p.inventory) {
			if (i instanceof Food) {
				if (foodNames.contains(getItemName(i)) == false) {
				nums.add(index);
				foodNames.add(getItemName(i));
				}
			foodInventory.add(getItemName(i));
			}
			index++;
		}
		if (foodNames.size() == 0) {
			printToScreen(p.getPlayerName() + " is looking for food, but could not find any food inside his bag.");
		}
		else {
		printToScreen("Choose which food " + a.getPetName() +" would like to eat:");
		int count = 1;
		for (String name:foodNames) {
			int occurrences = Collections.frequency(foodInventory, name);
			printToScreen("("+Integer.toString(count)+") " + name + " x" + Integer.toString(occurrences));
			count++;
		}
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
			printToScreen(a.getPetName() + " is slowly eating " + food.getFoodName());
			a.setHunger(food.getNutrition());
			int bladderDrop = 0 - food.getFullness();
			a.setBladder(bladderDrop);
		}
		else {	
			if (a.getFavouriteFood() == food.getFoodName()) {
				printToScreen(a.getPetName() + " is happily eating " + food.getFoodName());
				a.setHunger(food.getNutrition());
				int bladderDrop = 0 - food.getFullness();
				a.setBladder(bladderDrop);
				int happyUp = food.getTaste() + 1;
				a.setHappiness(happyUp);
			}
			else {
				printToScreen(a.getPetName() + " is eating " + food.getFoodName());
				a.setHunger(food.getNutrition());
				int bladderDrop = 0 - food.getFullness();
				a.setBladder(bladderDrop);
				a.setHappiness(food.getTaste());
					}
				}
		a.setWeight(1);
		p.inventory.remove(selectedFood);
		}
	}
	/**
	 * Allows the Player to play with the pet using a toy. 
	 * By calling this method, the player is given a choice of toys available in the inventory.
	 * If the toy does not contain inside the inventory, it will do nothing but lose 1 action point.
	 * If the toy is given to a pet, the pet's happiness level go up, but energy and hunger may go down.
	 * If the food given is pet's favourite toy, the happiness goes up by 1.
	 * The toy's durability gets reduced once used (If the toy's durability drops to under 0 then the toy breaks).
	 * If the toy breaks, the toy is removed from the inventory.
	 * If the pet is sick, it will use up more energy to play.
	 * If the pet is misbehaving, it will try to break the toy.
	 * If exercise is required to play with the toy, the pet will lose 1 kilogram, hunger and energy. (Hunger and energy depends on the exercise level).
	 * @param p Player who's pet is to be played with
	 * @param a Pet to be exercised
	 */
	public void play(Player p , Pet a) {
		boolean isValid = false;
		int selected = 0;
		ArrayList<Integer> nums = new ArrayList<Integer>(0);
		ArrayList<String> toyNames = new ArrayList<String>(0);
		ArrayList<String> toyInventory = new ArrayList<String>(0);
		int index = 0;
		for (Item i : p.inventory) {
			if (i instanceof Toy) {
				if (toyNames.contains(getItemName(i)) == false) {
				nums.add(index);
				toyNames.add(getItemName(i));
				}
			toyInventory.add(getItemName(i));
			}
			index++;
		}
		if (toyNames.size() == 0) {
			printToScreen(p.getPlayerName() + " is looking for toy, but could not find any toy inside his bag.");
		}
		else {
		printToScreen("Choose which food " + a.getPetName() +" would like to eat:");
		int count = 1;
		for (String name:toyNames) {
			int occurrences = Collections.frequency(toyInventory, name);
			printToScreen("("+Integer.toString(count)+") " + name + " x" + Integer.toString(occurrences));
			count++;
		}
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
		if (a.getFavouriteToy() == toy.getToyName()) {
			printToScreen(a.getPetName() + " is happily playing with a " + toy.getToyName());
			int funUp = toy.getHappy() + 1;
			a.setHappiness(funUp);
		}
		else {
			printToScreen(a.getPetName() + " is playing with a " + toy.getToyName());
			a.setHappiness(toy.getHappy());
		}
		if (a.isMisbehave() == true) {
			printToScreen(a.getPetName() + " is trying to break the " + toy.getToyName());
			toy.setDurability(1);
		}
		toy.setDurability(a.getAggression());
		if (toy.getDurability() <= 0) {
			printToScreen("Oh no the " + toy.getToyName() + " is broken!");
			p.inventory.remove(selectedToy);
		}
		if (a.isSick() == true) {
			printToScreen(a.getPetName() + " is exhausted.");
			int drops1 = 0 - (toy.getExercise() + 1);
			a.setEnergy(drops1);
			a.setHunger(drops1);
			a.setWeight(-1);
		}
		else {
			int drops2 = 0 - toy.getExercise();
			if (drops2 != 0) {
				a.setWeight(-1);
				a.setEnergy(drops2);
				a.setHunger(drops2);
			}
			}
		}
	}
	/**
	 * Sleeps the Pet to increase energy.
	 * By calling this method, the pet's energy increases to maximum (10).
	 * If the pet is misbehaving, the pet will not sleep properly.
	 * @param a pet that is to sleep.
	 */
	public void sleep(Pet a) {
		if (a.isMisbehave() == true) {
			a.setEnergy(2);
			printToScreen(a.getPetName() + " is pretending to sleep.");
		}
		else {
			a.setEnergy(10);
			printToScreen(a.getPetName() + " is sleeping... zzzzzzzz");
			printToScreen(a.getPetName() + " has woken up and feels great!");
		}
	}
	/**
	 * Disciplines the Pet. 
	 * By calling this method, the pet's happiness drops to 0, but the pet is no longer misbehaving.
	 * @param a Pet that needs to be discplined.
	 */
	public void discipline(Pet a) {
		a.setHappiness(-6);
		a.setBehaviour(false);
		printToScreen(a.getPetName() + " is starting to behave now but is feeling unhappy.");
	}
	/**
	 * Display status of the Pet.
	 * By calling this method, it prints of the status of the given pet.
	 * @param a Pet thats score is to be calculated.
	 */
	public void status(Pet a) {
		printToScreen("Status of how " + a.getPetName() + " is feeling now: (0=low, 10=High)");
		printToScreen("Happiness: " + Integer.toString(a.getHappiness()));
		printToScreen("Bladder: " + Integer.toString(a.getBladder()));
		printToScreen("Energy: " + Integer.toString(a.getEnergy()));
		printToScreen("Hunger: " + Integer.toString(a.getHunger()));
		printToScreen("Weight: " + Integer.toString(a.getWeight()) + "kg");
		}
	/**
	 * Calculates the per day Scores. 
	 * Each pet's overall stats contributes to the score (Unless the pet is dead).
	 * Players earn extra points if the pet is not misbehaving/sick (Pets that are misbehaving earns more points than pets that are sick).
	 * @param p Player who is to have score calculated.
	 * @param a Pet that score is to be accessed.
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
	 * Adjusts the scores based on Pet condition on the final day. 
	 * On the final day, the pet's condition contributes to your final score.
	 * If the pet is dead or sick, the player will lose some points.
	 * If the pet is misbehaving, the score will not change.
	 * If the pet is in perfect condition, it will gain extra points.
	 * @param p Player who is to have score calculated.
	 * @param a Pet that score is to be accessed.
	 * @return Double
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
	 * Dispalys the final player scores.
	 * Prints off the final score for each player.
	 * @param players function is passed a list of players whos scores are to be displayed.
	 */
	public void finalScoreDisplay(ArrayList<Player> players) {
		for (Player person : players) {
			double scoreAdjust = 1;
			for(Pet animal:person.petArray){
				scoreAdjust = scoreAdjust + finalScoreAdjust(person, animal);
			}
			int finalScore = (int) (scoreAdjust * person.getScore());
			printToScreen(person.getPlayerName() + " your final score is: " + Integer.toString(finalScore));
			}
	}
	/**
	 * Performs the daily game action for each Player to set up their day. 
	 * Each pet gets 2 actions per day.
	 * The player is given $20 each day.
	 * Depending on the pet's stat, it can change the pet's condition.
	 * @param person Player whos pets daily condition change need to happen on. 
	 */
	public void setDailyCondition(Player person) {
		person.setStillTurn(true);
		person.setMoney(20);
		for(Pet animal:person.petArray){
			setPetCondition(animal);
		}
	}
	/**
	 * Prints off a list of pets and options to move to the next day, use the shop.
	 * @param p Player who is to have list of pets displayed.
	 */
	public void displayPets(Player p){
		int i = 1;
		for(Pet animal:p.petArray){
			printToScreen("("+Integer.toString(i)+") Interact with "+animal.getPetName());
			i++;
		}
		printToScreen("(" + Integer.toString(i) + ") Move to the next day");
		printToScreen("(" + Integer.toString(i + 1) + ") Use the shop");
		printToScreen("(" + Integer.toString(i + 2) + ") check the inventory");
	}
	
	/**
	 * Provides the calls to use daily actions. 
	 * The player gets a choice of either...
	 * interacting with one of the pets
	 * go to the shop
	 * check the inventory
	 * move to the next day.
	 * Gives an error message if the player didn't pick a valid option.
	 * @param p Player who is to start the daily loop.
	 */
	public void playDay(Player p){
		printHeader();
		printToScreen("Player: "+ p.getPlayerName());
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
				printToScreen(animal.getPetName() + " wants to call it a day. Please choose a different pet");
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
				displayInventory(p);
			}
		}
		}
		
	}
	
	/**
	 * Performs the actions for each pet daily. 
	 * If the pet is misbehaving or sick, then it will alert the player.
	 * If the pet is dead, then it will go back to playDay method (The player cannot interact with dead pet).
	 * The player is given a list of methods, which if the player picks a valid option, it will call the method.
	 * @param p Player whos pets are in use.
	 * @param a pet to be acted on
	 */
	public void dailyPetUse(Player p, Pet a){
		printHeader();
		printToScreen("Pet: "+a.getPetName());
		printHeader();
		if (a.isDead() == false) {
		boolean isValid = false;
		while(!isValid){
			if ( a.isMisbehave() == true) {
				printToScreen(a.getPetName() + " is misbehaving!");
			}
			if (a.isSick() == true) {
				printToScreen(a.getPetName() + " is feeling sick!");
			}
			printToScreen(a.getPetName() + " has " + a.getActionsRemaning() + " actions remaining!");
			printToScreen("What would you like to do with " +a.getPetName()+" ?");
			printToScreen("\t(1) Use the Shop\n\t(2) Use the Toilet\n\t(3) Feed "+a.getPetName()+"\n\t(4) Play with "+a.getPetName()+"\n\t(5) Put "+ a.getPetName() + " to sleep" +"\n\t(6) Discipline "+a.getPetName() + "\n\t(7) View " + a.getPetName() + "'s status");
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
			printToScreen(a.getPetName() + " is dead. Please choose a different pet!");
		}
	}
	/**
	 * Prints the current day number. 
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
	 * As each day passes, each stat drops (The bladder will only drop when the pet is sick).
	 * Players earn points each day (Unless the pet is dead).
	 * Final score is calculated depending on the pet's conditions.
	 * @param args command line arguments to be passed none exist for this main.
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
