package GameMainJUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.Handler;
import GameMain.Map;
import GameMain.Maze;
import GameMain.PathFinder;
import GameMain.Player;
import GameMain.PlayerAi;

public class PlayerAiTests {

	@Test
	public void recYTest() {
		Handler handler = new Handler();
		Maze maze = new Maze(10, 10);
		Map map = new Map(maze);
		PathFinder pathfind = new PathFinder(map);
		PlayerAi player = new PlayerAi(10, 10, null, handler, map, pathfind);
		PlayerAi player2 = new PlayerAi(10, 10, null, handler, map, pathfind);
		int p = (int) player.recY();
		int p2 = (int) player2.recY();
		assertEquals(p, p2);
	}
	@Test
	public void recXTest() {
		Handler handler = new Handler();
		Maze maze = new Maze(10, 10);
		Map map = new Map(maze);
		PathFinder pathfind = new PathFinder(map);
		PlayerAi player = new PlayerAi(10, 10, null, handler, map, pathfind);
		PlayerAi player2 = new PlayerAi(10, 10, null, handler, map, pathfind);
		int p = (int) player.recX();
		int p2 = (int) player2.recX();
		assertEquals(p, p2);
	}
	@Test
	public void getAngle() {
		Handler handler = new Handler();
		Maze maze = new Maze(10, 10);
		Map map = new Map(maze);
		PathFinder pathfind = new PathFinder(map);
		PlayerAi player = new PlayerAi(10, 10, null, handler, map, pathfind);
		PlayerAi player2 = new PlayerAi(10, 10, null, handler, map, pathfind);
		assertEquals(player.getAngle(), player2.getAngle());
	}
	@Test
	public void distanceTest() {
		Handler handler = new Handler();
		Maze maze = new Maze(100, 100);
		Map map = new Map(maze);
		PathFinder pathfind = new PathFinder(map);
		PlayerAi aiplayer = new PlayerAi(10, 10, null, handler, map, pathfind);
		Player player1 = new Player(20, 20, null, handler);

		Handler handler2 = new Handler();
		Maze maze2 = new Maze(100, 100);
		Map map2 = new Map(maze2);
		PathFinder pathfind2 = new PathFinder(map2);
		PlayerAi aiplayer2 = new PlayerAi(10, 10, null, handler2, map2, pathfind2);
		Player player2 = new Player(20, 20, null, handler2);
		
		int ai1 = (int)aiplayer.distance();
		int ai2 = (int)aiplayer2.distance();
		assertEquals(ai1, ai2);
		
	}
}
