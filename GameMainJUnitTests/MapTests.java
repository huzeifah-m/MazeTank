package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.Map;
import GameMain.Maze;

public class MapTests {

	@Test
	public void getPointsTest() {
		Maze maze = new Maze(10, 10);
		Maze maze2 = new Maze(10, 10);
		Maze maze3 = new Maze(15, 15);
		Map map = new Map(maze);
		Map map2 = new Map(maze2);
		Map map3 = new Map(maze3);
		assertEquals(map.getPoints(), map2.getPoints());
		assertNotEquals(map.getPoints(), map3.getPoints());
	}

}
