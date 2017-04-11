package jbu71_nsa73_virtualPet;

public class GameEnvironment {
	private int day;
	private Player[] playerArray = new Player[3];
	public int getDay(){
		return day;
	}
	
	public void setDay(int newDay){
		day = newDay;
	}
	public void nextDay(){
		day++;
	}
	
	private void addPlayer(String playerName){
		Player p1 = new Player(playerName);
	}
	

	public static void main(String[] args) {
		GameEnvironment g = new GameEnvironment();
		g.setDay(0);
		System.out.println(g.getDay());
		g.nextDay();
		System.out.println(g.getDay());
		g.setDay(6);
		System.out.println(g.getDay());
		g.addPlayer("Josh");
		//System.out.println(p1.getScore());
	}

}
