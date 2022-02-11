package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.CellHoriWallAi;

public class CellHoriWallAiTests {

	@Test
	public void test() {
		CellHoriWallAi wall1 = new CellHoriWallAi(1, 1, null);
		CellHoriWallAi wall2 = new CellHoriWallAi(1, 1, null);
		CellHoriWallAi noWall = new CellHoriWallAi(0, 0, null);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}

}
