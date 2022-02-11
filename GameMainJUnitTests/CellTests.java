package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.Cell;

public class CellTests {

	@Test
	public void checkWallsTest() {
		Cell test = new Cell();
		boolean expectedOutput = true;
		boolean actualOutput = test.checkWalls();
		assertEquals(expectedOutput,actualOutput);
	}

}
