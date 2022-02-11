package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Networking.CellSouthWall;

public class CellSouthWallTests {

	@Test
	public void test() {
		CellSouthWall wall1 = new CellSouthWall(1, 1, 0, 0);
		CellSouthWall wall2 = new CellSouthWall(1, 1, 0, 0);
		CellSouthWall noWall = new CellSouthWall(0, 0, 0, 0);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}

}
