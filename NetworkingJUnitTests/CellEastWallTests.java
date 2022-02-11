package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Networking.CellEastWall;

public class CellEastWallTests {

	@Test
	public void test() {
		CellEastWall wall1 = new CellEastWall(1, 1, 0, 0);
		CellEastWall wall2 = new CellEastWall(1, 1, 0, 0);
		CellEastWall noWall = new CellEastWall(0, 0, 0, 0);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}
}
