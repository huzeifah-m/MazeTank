package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.CellVertWall;

public class CellVertWallTests {

	@Test
	public void test() {
		CellVertWall wall1 = new CellVertWall(1, 1, null);
		CellVertWall wall2 = new CellVertWall(1, 1, null);
		CellVertWall noWall = new CellVertWall(0, 0, null);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}

}
