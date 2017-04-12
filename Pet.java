package jbu71_nsa73_virtualPet;

public class Pet extends GameEnvironment {
	private String petName;
	
	public Pet(String name){
		petName = name;
	}
	
	public String getName(){
		return petName;
	}

}
