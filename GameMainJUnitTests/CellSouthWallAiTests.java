package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.CellSouthWallAi;

public class CellSouthWallAiTests {

	@Test
	public void test() {
		CellSouthWallAi wall1 = new CellSouthWallAi(1, 1, null);
		CellSouthWallAi wall2 = new CellSouthWallAi(1, 1, null);
		CellSouthWallAi noWall = new CellSouthWallAi(0, 0, null);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}

}
