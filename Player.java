package jbu71_nsa73_virtualPet;

import java.util.ArrayList;

public class Player extends GameEnvironment{
	private int money = 100;
	private int score = 0;
	private String name;
	private ArrayList<Printable> inventory = new ArrayList<Printable>(0);
	
	
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
	
	public String getName(){
		return name;
	}
	
	public void setName(String playerName){
		name = playerName;
	}

}
