package jbu71_nsa73_virtualPet;

import java.util.ArrayList;
import java.util.Scanner;

public class GameEnvironment implements Printable {
	private int day;
	private int numberOfPlayers, numberOfPets, lengthOfGame = 0;
	private ArrayList<Player> playerArray = new ArrayList<Player>(0);
	private ArrayList<Pet> petArray  = new ArrayList<Pet>(0);
	public int getDay(){
		return day;
	}
	
	public void setDay(int newDay){
		day = newDay;
	}
	public void nextDay(){
		day++;
	}
	
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
	private void addPet(){
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
			}
			for(Pet animal:petArray){
				if(animal.getName().equals(petName)){
					isValid = false;
					printToScreen("Names must be unique, please choose a new name.\n");
				}
			}
				}
			}
		}
		petArray.add(new Pet(petName));
	}
	
	public String getInput(){
		Scanner inputString = new Scanner(System.in);
		String input =  inputString.nextLine();
		return input;
		
	}
	
	public void printToScreen(String displayString){
		System.out.println(displayString);
	}
	
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
			addPet();
			i++;
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

	public static void main(String[] args) {
		GameEnvironment g = new GameEnvironment();
		g.printToScreen("############################"+ "\n| Welcome to virtual pets! |\n"+"############################\n");
		g.createGame();
		
	}
}

	


