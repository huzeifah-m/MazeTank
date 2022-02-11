package NetworkingJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Networking.CellVertWall;

public class CellVertWallTests {

	@Test
	public void test() {
		CellVertWall wall1 = new CellVertWall(1, 1, 0, 0);
		CellVertWall wall2 = new CellVertWall(1, 1, 0, 0);
		CellVertWall noWall = new CellVertWall(0, 0, 0, 0);
		assertEquals(wall1.getBounds(), wall2.getBounds());
		assertNotEquals(wall1.getBounds(), noWall.getBounds());
	}

}
