

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
	private ArrayList<Player> playerArray = new ArrayList<Player>(0);
	public int getDay(){
		return day;
	}
	
	public void setDay(int newDay){
		day = newDay;
	}
	public void nextDay(){
		day++;
	}
	
	
	/**
	 * Returns void.
	 * Adds a new player to the game and stores this player in the game environments arrayList playerArray
	 * Provides validation for provided names testing length, uniquness and non-emptiness
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
	 * Provides validation for names by checking uniquness against both players and any pets currently in game, length and Non-emptiness
	 * Called by addPet(Player p) taking a Player as a base argument. 
	 */
	private void addPet(Player p){
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
		printToScreen("What breed would you like your pet to be?");
		printToScreen("(1)Cat\n(2)Dog\n(3)CatDog\n(4)Tiger\n(5)Bird\n(6)Ocelot");
		
		isValid = false;
		while(!isValid){
			String type = getInput();
			switch(type){
				case "1": p.petArray.add(new Cat(petName));
					printToScreen("Creating a Cat named "+petName+" for you.");
					isValid = true;
					break;
				case"2": p.petArray.add(new Dog(petName));
					printToScreen("Creating a Dog named "+petName+" for you.");
					isValid = true;
					break;
				case "3": p.petArray.add(new CatDog(petName));
					printToScreen("Creating a CatDog named "+petName+" for you.");
					isValid = true;
					break;
				case "4": p.petArray.add(new Tiger(petName));
					printToScreen("Creating a Tiger named "+petName+" for you.");
					isValid = true;	
					break;
				case "5": p.petArray.add(new Bird(petName));
					printToScreen("Creating a Bird named "+petName+" for you.");
					isValid = true;		
					break;
				case "6": p.petArray.add(new Ocelot(petName));
					printToScreen("Creating a Ocelot named "+petName+" for you.");
					isValid = true;
					break;
				default: printToScreen("Please enter a valid option number");
					isValid = false;
					break;
		
			}
		}
	}
	
	
	/**
	 * Method to impliment Printable.
	 * opens a Scanner and returns the inputted String
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
	 * Sets up the game by asking for number of players, number of pets, pet types.
	 * MUST ONLY BE CALLED AT THE START OF THE GAME
	 * Provides valdation of numbers and only adds 3 players and upto 3 pets per player. 
	 */
	public void createGame(){
		boolean isValid = false;
		while(!isValid){
			printToScreen("How many Players would you like(1-3)? \n");
			String numPlayers = getInput();
			try{
				numberOfPlayers  = Integer.parseInt(numPlayers);
			}catch(NumberFormatException e){
				printToScreen("Please enter a valid number.\n");
			}
			if(numberOfPlayers >= 1 && numberOfPlayers <=3){
				isValid = true;
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
				}
			}
			
		}
		
		
	}
	
	
	public void useShop(Player p){
		boolean isValid = false;
		printToScreen("Welcome "+p.getName()+" .\nYou have $"+p.getMoney()+" Avaliable.\nWhat would you like to purchase?");
		printToScreen("(1)Food\n(2)Toys\n(3)Revive\n(4)Cancel");
		while(!isValid){
			String option = getInput();
			switch(option){
			case "1": purchaseFood(p);
				isValid = true;
				break;
			case "2": purchaseToys(p);
				isValid = true;
				break;
			case "3": revivePet(p);
				isValid = true;
				break;
			case "4": isValid = true;
				break;
			default: isValid = false;
				break;
				
			}
		}
		
	}
	
	
	public void revivePet(Player p){
		
	}
	
	
	public void purchaseFood(Player p){
		boolean isValid = false;
		printToScreen("Food options available:\n(1)Curry Rice\n(2)Fish and Chips\n(3)Hamburger\n(4)Fried Chicken\n(5)Pizza\n(6)Steak\n(7)Cancel");
		String option = getInput();
		switch(option){
			case "1": p.inventory.add(new CurryRice());
				isValid = true;
				break;
			case "2": p.inventory.add(new FishAndChip());
				isValid = true;
				break;
			case "3": p.inventory.add(new Hamburger());
				isValid = true;
				break;
			case "4": p.inventory.add(new FriedChicken());
				isValid = true;
				break;
			case "5": p.inventory.add(new Pizza());
				isValid = true;
				break;
			case "6": p.inventory.add(new Steak());
				isValid = true;
				break;
			case "7": isValid = true;
				break;
			default: printToScreen("Please enter a valid option number");
				isValid = false;
				break;
				
			
		}
		
	}
	
	
	public void purchaseToys(Player p){
		
	}
	
	
	public void printDay(){
		printToScreen("############################"+"\n|           Day"+getDay()+"           |\n"+"############################");
	}

	public static void main(String[] args) {
		GameEnvironment g = new GameEnvironment();
		g.printToScreen("############################"+ "\n| Welcome to virtual pets! |\n"+"############################\n");
		g.createGame();
		g.printDay();
		
	}
}

	


