package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Networking.Player;

public class PlayerTests {

	@Test
	public void getBoundsTest() {
		Player player = new Player(0, 0, 0, 0, null);
		Player player2 = new Player(0, 0, 0, 0, null);
		Player player3 = new Player(1, 1, 0, 0, null);
		assertEquals(player.getBounds(), player2.getBounds());
		assertNotEquals(player.getBounds(), player3.getBounds());
	}
	@Test
	public void getOffsetBoundsTest() {
		Player player = new Player(0, 0, 0, 0, null);
		Player player2 = new Player(0, 0, 0, 0, null);
		Player player3 = new Player(1, 1, 0, 0, null);
		assertEquals(player.getOffsetBounds(), player2.getOffsetBounds());
		assertNotEquals(player.getOffsetBounds(), player3.getOffsetBounds());
	}
	@Test
	public void recX() {
		Player player = new Player(0, 0, 0, 0, null);
		Player player2 = new Player(0, 0, 0, 0, null);
		int p = (int) player.recX();
		int p2 = (int) player2.recX();
		assertEquals(p, p2);
	}
	@Test
	public void recY() {
		Player player = new Player(0, 0, 0, 0, null);
		Player player2 = new Player(0, 0, 0, 0, null);
		int p = (int) player.recY();
		int p2 = (int) player2.recY();
		assertEquals(p, p2);
	}
	@Test
	public void getAngle() {
		Player player = new Player(0, 0, 0, 0, null);
		Player player2 = new Player(0, 0, 0, 0, null);
		assertEquals(player.getAngle(), player2.getAngle());
	}
	
}
