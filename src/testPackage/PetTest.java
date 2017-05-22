package testPackage;

import static org.junit.Assert.*;
import main.*;
import pets.*;
import org.junit.Before;
import org.junit.Test;

public class PetTest {
	GameEnvironment game;
	Ocelot ocelot;
	@Before
	public void setUp() throws Exception {
		game = new GameEnvironment();
		ocelot = new Ocelot("b");
	}

	@Test
	public void testSetEnergy5() {
		ocelot.setEnergy(-2);
		assertEquals(ocelot.getEnergy(), 5);
	}
	
	@Test
	public void testSetEnergyMax() {
		ocelot.setEnergy(10);
		assertEquals(ocelot.getEnergy(), 10);
	}
	
	@Test
	public void testSetEnergyMin() {
		ocelot.setEnergy(-10);
		assertEquals(ocelot.getEnergy(), 0);
	}

	
	@Test
	public void testSetWeightMin(){
		ocelot.setWeight(-10);
		assertEquals(ocelot.getWeight(), 3);
	}
	
	@Test
	public void testSetHapinessMax(){
		ocelot.setHappiness(10);
		assertEquals(ocelot.getHappiness(), 10);
	}
	
	@Test
	public void testSetHungerMax(){
		ocelot.setHunger(10);
		assertEquals(ocelot.getHunger(), 10);
	}
}
