package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.CellVertWallAi;

public class CellVertWallAiTests {

	@Test
	public void test() {
		CellVertWallAi wall1 = new CellVertWallAi(1, 1, null);
		CellVertWallAi wall2 = new CellVertWallAi(1, 1, null);
		CellVertWallAi noWall = new CellVertWallAi(0, 0, null);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}

}
