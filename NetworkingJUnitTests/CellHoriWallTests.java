package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Networking.CellHoriWall;

public class CellHoriWallTests {

	@Test
	public void test() {
		CellHoriWall wall1 = new CellHoriWall(1, 1, 0, 0);
		CellHoriWall wall2 = new CellHoriWall(1, 1, 0, 0);
		CellHoriWall noWall = new CellHoriWall(0, 0, 0, 0);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}

}
