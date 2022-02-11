package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.CellEastWall;
import GameMain.CellEastWallAi;

public class CellEastWallAiTests {

	@Test
	public void test() {
		CellEastWallAi wall1 = new CellEastWallAi(1, 1, null);
		CellEastWallAi wall2 = new CellEastWallAi(1, 1, null);
		CellEastWallAi noWall = new CellEastWallAi(0, 0, null);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}

}
