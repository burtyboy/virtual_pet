

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
	
	
	public void printHeader(){
		printToScreen("##################################################################");
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
		printToScreen("What breed would you like your pet to be?");
		printToScreen("(1)Cat\n(2)Dog\n(3)CatDog\n(4)Tiger\n(5)Bird\n(6)Ocelot");
		
		boolean isValid = false;
		while(!isValid){
			String type = getInput();
			switch(type){
				case "1":petName = getName();
					p.petArray.add(new Cat(petName));
					printToScreen("Creating a Cat named "+petName+" for you.");
					isValid = true;
					break;
				case"2":petName = getName(); 
					p.petArray.add(new Dog(petName));
					printToScreen("Creating a Dog named "+petName+" for you.");
					isValid = true;
					break;
				case "3": petName = getName();
					p.petArray.add(new CatDog(petName));					
					printToScreen("Creating a CatDog named "+petName+" for you.");
					isValid = true;
					break;
				case "4": petName = getName();
					p.petArray.add(new Tiger(petName));
					printToScreen("Creating a Tiger named "+petName+" for you.");
					isValid = true;	
					break;
				case "5": petName = getName();
					p.petArray.add(new Bird(petName));
					printToScreen("Creating a Bird named "+petName+" for you.");
					isValid = true;		
					break;
				case "6": petName = getName();
					p.petArray.add(new Ocelot(petName));
					printToScreen("Creating a Ocelot named "+petName+" for you.");
					isValid = true;
					break;
				default: printToScreen("Please enter a valid option number");
					isValid = false;
					break;
		
			}
		}
	}
		
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
	
	//TODO set methods for purchasing to remove money from player. add ability to reuse the shop 
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
			case "2": purchaseToy(p);
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
		if(!isValid){
			purchaseFood(p);
		}
		useShop(p);
	}
	
	
	public void purchaseToy(Player p){
		boolean isValid = false;
		printToScreen("Toy options available:\n(1)Doll\n(2)Football\n(3)Pillow\n(4)Stick\n(5)Toilet Paper\n(6)Toy car\n(7)Cancel");
		String option = getInput();
		switch(option){
			case "1": p.inventory.add(new Doll());
				isValid = true;
				break;
			case "2": p.inventory.add(new Football());
				isValid = true;
				break;
			case "3": p.inventory.add(new Pillow());
				isValid = true;
				break;
			case "4": p.inventory.add(new Stick());
				isValid = true;
				break;
			case "5": p.inventory.add(new ToiletPaper());
				isValid = true;
				break;
			case "6": p.inventory.add(new ToyCar());
				isValid = true;
				break;
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
	
	public void displayPets(Player p){
		int i = 1;
		for(Pet animal:p.petArray){
			printToScreen("("+Integer.toString(i)+") "+animal.getName());
			i++;
		}
	}
	
	
	public void playDay(Player p){

		printHeader();
		printToScreen("Player: "+ p.getName());
		printHeader();
		printToScreen("Pets available:");
		for(Pet animal:p.petArray){
			animal.setActionsRemaning(2);
		}
		displayPets(p);
		boolean isValid = false;
		int selectedPet = 0;
		while(!isValid){
			
			printToScreen("Please Select a pet");
			String petNumber = getInput();
			try{
				selectedPet = Integer.parseInt(petNumber) - 1;
			}catch(NumberFormatException e){
				printToScreen("Please enter a valid number.\n");
			}
			if(selectedPet >= 0 && selectedPet <=p.petArray.size()){
				isValid = true;
			}
		}
		if(p.petArray.get(selectedPet).getActionsRemaning() !=0){
			dailyPetUse(p,p.petArray.get(selectedPet));
		}
		
	}
	
	//TODO finish impletmenting methods to feed, play with, toilet and disciplining 
	public void dailyPetUse(Player p, Pet a){
		printHeader();
		printToScreen("Pet: "+a.getName());
		printHeader();
		printToScreen("What would you like to do with " +a.getName()+" ?");
		printToScreen("\t(1) Use the Shop\n\t(2) Use the Toilet\n\t(3) Feed "+a.getName()+"\n\t(4)Play with "+a.getName()+"\n\t(5) Discipline "+a.getName());
		boolean isValid = false;
		while(!isValid){
			String option = getInput();
			switch(option){
				case "1": useShop(p);
					isValid = true;
			}
		}
		
	}
	
	public void printDay(){
		printHeader();
		printToScreen("|           Day"+getDay()+"           |");
		printHeader();
	}

	public static void main(String[] args) {
		GameEnvironment g = new GameEnvironment();
		g.printHeader();
		g.printToScreen("| Welcome to virtual pets! |");
		g.printHeader();
		g.createGame();
		g.printDay();
		while (g.day<=g.lengthOfGame) {
			for (Player person : g.playerArray) {
				person.setStillTurn(true);
				while(person.isStillTurn()){
					g.playDay(person);
				}
				
			} 
			g.day++;
		}
		
	}
}

	


