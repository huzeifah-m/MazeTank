package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.CellEastWall;

public class CellEastWallTests {

	@Test
	public void test() {
		CellEastWall wall1 = new CellEastWall(1, 1, null);
		CellEastWall wall2 = new CellEastWall(1, 1, null);
		CellEastWall noWall = new CellEastWall(0, 0, null);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}

}
