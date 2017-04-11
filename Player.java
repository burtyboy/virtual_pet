package jbu71_nsa73_virtualPet;

public class Player{
	private int money = 100;
	private int score;
	private String name;
	
	
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
