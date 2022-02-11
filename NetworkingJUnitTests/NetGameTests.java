package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.Game;
import GameMain.GameEngine;

public class NetGameTests {

	@Test
	public void clampTest() {
		GameEngine engine = new GameEngine();
		Game test = new Game(engine);
		int expectedOutcome = 2;
		assertEquals((test.clamp(1, 2, 3)),expectedOutcome);

	}

}
