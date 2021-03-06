package main;


import java.util.ArrayList;
/**
 * 
 *Provides the Player class and methods. The most important of these are the two arrayLists inventory and petArray.  
 *
 */

public class Player {
	private int money = 0;
	private int score = 0;
	private String name;

	private boolean stillTurn = false;

	public ArrayList<Item> inventory = new ArrayList<Item>(0);
	public ArrayList<Pet> petArray  = new ArrayList<Pet>(0);
	
	
	public Player(String playerName){
		name = playerName;
	}
	
	public void setMoney(int payment){
		money += payment;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void setScore(int pointsScored){
		score += pointsScored;
	}
	
	public int getScore(){
		return score;
	}
	
	public String getPlayerName(){
		return name;
	}
	
	public void setName(String playerName){
		name = playerName;
	}

	public boolean isStillTurn() {
		return stillTurn;
	}

	public void setStillTurn(boolean stillTurn) {
		this.stillTurn = stillTurn;
	}

}