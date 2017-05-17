package testPackage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameEnvironmentTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		GameEnvironment game = new GameEnvironment();
		Ocelot ocelot = new Ocelot("Babou");
	}

	@Test
	public void testCreateGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPetCondition() {
		fail("Not yet implemented");
	}

	@Test
	public void testStatDrops() {
		fail("Not yet implemented");
	}

	@Test
	public void testUseShop() {
		fail("Not yet implemented");
	}

	@Test
	public void testRevivePet() {
		fail("Not yet implemented");
	}

	@Test
	public void testHealPet() {
		fail("Not yet implemented");
	}

	@Test
	public void testPurchaseFood() {
		fail("Not yet implemented");
	}

	@Test
	public void testPurchaseToy() {
		fail("Not yet implemented");
	}

	@Test
	public void testUseToilet() {
		g.useToilet(ocelot);
		assertEquals(ocelot.getBladder(), 10);
		assertEquals(ocelot.getWeight(), 4);
	}

	@Test
	public void testFeed() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlay() {
		fail("Not yet implemented");
	}

	@Test
	public void testSleep() {
		ocelot.setEnergy(0);
		g.sleep(ocelot);
		assertEquals(ocelot.getEnergy(), 10);
	}

	@Test
	public void testDiscipline() {
		ocelot.setBehaviour(True);
		g.discipline(ocelot);
		assertEquals(ocelot.getHappiness(), 1);
		assertFalse(ocelot.getIsMisbehave());
	}

	@Test
	public void testStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDailyCondition() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayDay() {
		fail("Not yet implemented");
	}

	@Test
	public void testDailyPetUse() {
		fail("Not yet implemented");
	}

}
