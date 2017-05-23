package testPackage;
import main.*;
import pets.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameEnvironmentTest {
	Ocelot ocelot;
	GameEnvironment game;
	Player p;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		 game = new GameEnvironment();
		 ocelot = new Ocelot("Babou");
		 p = new Player("Josh");
		 p.petArray.add(ocelot);
	}



	@Test
	public void testSetPetConditionHappy() {
		game.setPetCondition(ocelot);
		assertEquals(ocelot.getActionsRemaning(), 2);
	}
	
	@Test
	public void testSetPetConditionSickWeight() {
		ocelot.setWeight(-2);
		game.setPetCondition(ocelot);
		assertTrue(ocelot.isSick());
	}
	
	@Test
	public void testSetPetConditionSickBladder() {
		ocelot.setBladder(-4);
		game.setPetCondition(ocelot);
		assertTrue(ocelot.isSick());
	}
	
	@Test
	public void testSetPetConditionSickHunger() {
		ocelot.setHunger(-4);
		game.setPetCondition(ocelot);
		assertTrue(ocelot.isSick());
	}
	
	@Test
	public void testSetPetConditionSickEnergy() {
		ocelot.setEnergy(-4);
		game.setPetCondition(ocelot);
		assertTrue(ocelot.isSick());
	}
	
	@Test
	public void testSetPetConditionDeadHappiness() {
		ocelot.setHappiness(-10); //happiness is added to this value till 0 with max of 10 will ensure happiness is 0
		game.setPetCondition(ocelot);
		assertTrue(ocelot.isDead());
	}
	
	@Test
	public void testSetPetConditionDeadBladder() {
		ocelot.setBladder(-10); //bladder is added to this value till 0 with max of 10 will ensure bladder is 0
		game.setPetCondition(ocelot);
		assertTrue(ocelot.isDead());
	}
	
	@Test
	public void testSetPetConditionDeadHunger() {
		ocelot.setHunger(-10); //hunger is added to this value till 0 with max of 10 will ensure hunger is 0
		game.setPetCondition(ocelot);
		assertTrue(ocelot.isDead());
	}
	
	@Test
	public void testSetPetConditionDeadEnergy() {
		ocelot.setEnergy(-10); //energy is added to this value till 0 with max of 10 will ensure energy is 0
		game.setPetCondition(ocelot);
		assertTrue(ocelot.isDead());
	}

	@Test
	public void testStatDrops() {
		game.statDrops(ocelot);
		assertEquals(ocelot.getEnergy(), 5);
		assertEquals(ocelot.getHappiness(), 5);
		assertEquals(ocelot.getHunger(), 5);
	}
	
	@Test
	public void testStatDropsSick() {
		ocelot.setSick(true);
		game.statDrops(ocelot);
		assertEquals(ocelot.getBladder(), 5);		
	}

	@Test
	public void testStatDropsFat() {
		ocelot.setWeight(10);
		game.statDrops(ocelot);
		assertEquals(ocelot.getHunger(), 4);		
	}

	@Test
	public void testUseToilet() {
		game.useToilet(ocelot);
		assertEquals(ocelot.getBladder(), 10);
		assertEquals(ocelot.getWeight(), 4);
	}

	@Test
	public void testSleep() {
		ocelot.setEnergy(0);
		game.sleep(ocelot);
		assertEquals(ocelot.getEnergy(), 10);
	}

	@Test
	public void testDiscipline() {
		ocelot.setBehaviour(true);
		game.discipline(ocelot);
		assertEquals(ocelot.getHappiness(), 1);
		assertFalse(ocelot.isMisbehave());
	}
	
	@Test
	public void perDayScoresMisbehaving(){
		ocelot.setBehaviour(true);
		game.perDayScores(p, ocelot);
		assertEquals(p.getScore(), 56);
	}
	
	@Test
	public void perDayScoresSick(){
		ocelot.setSick(true);
		game.perDayScores(p, ocelot);
		assertEquals(p.getScore(), 28);
	}

	
	@Test
	public void perDayScoresHealthy(){
		game.perDayScores(p, ocelot);
		assertEquals(p.getScore(), 112);
	}
	
}
