package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.Player;
import GameMain.Player2;

public class Player2Tests {

	@Test
	public void getBoundsTest() {
		Player2 player = new Player2(10, 10, null, null);
		Player2 player2 = new Player2(10, 10, null, null);
		Player2 player3 = new Player2(0, 0, null, null);
		assertEquals(player.getBounds(), player2.getBounds());
		assertNotEquals(player.getBounds(), player3.getBounds());
	}
	@Test
	public void getOffsetBoundsTest() {
		Player2 player = new Player2(10, 10, null, null);
		Player2 player2 = new Player2(10, 10, null, null);
		Player2 player3 = new Player2(0, 0, null, null);
		assertEquals(player.getOffsetBounds(), player2.getOffsetBounds());
		assertNotEquals(player.getOffsetBounds(), player3.getOffsetBounds());
	}
	@Test
	public void recX() {
		Player2 player = new Player2(10, 10, null, null);
		Player2 player2 = new Player2(10, 10, null, null);
		int p = (int) player.recX();
		int p2 = (int) player2.recX();
		assertEquals(p, p2);
	}
	@Test
	public void recY() {
		Player2 player = new Player2(10, 10, null, null);
		Player2 player2 = new Player2(10, 10, null, null);
		int p = (int) player.recY();
		int p2 = (int) player2.recY();
		assertEquals(p, p2);
	}
	@Test
	public void getAngle() {
		Player2 player = new Player2(10, 10, null, null);
		Player2 player2 = new Player2(10, 10, null, null);
		assertEquals(player.getAngle(), player2.getAngle());
	}
	
}
