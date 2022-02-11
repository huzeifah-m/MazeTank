package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.CellSouthWall;

public class CellSouthWallTests {

	@Test
	public void test() {
		CellSouthWall wall1 = new CellSouthWall(1, 1, null);
		CellSouthWall wall2 = new CellSouthWall(1, 1, null);
		CellSouthWall noWall = new CellSouthWall(0, 0, null);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}

}
