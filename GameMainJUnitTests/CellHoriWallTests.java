package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.CellHoriWall;

public class CellHoriWallTests {

	@Test
	public void test() {
		CellHoriWall wall1 = new CellHoriWall(1, 1, null);
		CellHoriWall wall2 = new CellHoriWall(1, 1, null);
		CellHoriWall noWall = new CellHoriWall(0, 0, null);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}

}
