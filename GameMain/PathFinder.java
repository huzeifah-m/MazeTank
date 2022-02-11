package GameMain;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import javafx.geometry.Point2D;

/**
 * @author Kevin
 * Algorithm class to create path for AI tank to follow
 */
public class PathFinder {

	Map map;

	/**
	 * Constructor takes a map and makes it its own
	 * @param map
	 */
	public PathFinder(Map map) {
		this.map = map;
	}

	// ------------------------------- Public Methods ------------------------------- //

	/**
	 * Algorithm to find path between two points in the maze
	 * @param start the starting point in the path
	 * @param goal the point to get to
	 * @return a LinkedList with the points in the path
	 */
	public LinkedList<Point2D> findPath(Point2D start, Point2D goal) {
		LinkedList<Point2D> frontier = new LinkedList<>();
		HashMap<Point2D,Point2D> came_from = new HashMap<>();
		frontier.add(start);
		System.err.println("Added start to frontier");
		came_from.put(start, null);
		System.err.println("Added start to came_from");
		while(!frontier.isEmpty()) {
			Point2D current = frontier.peek();
			frontier.remove(current);
			System.err.println("Got current by peeking at frontier");
			if(current == goal) {
				System.err.println("Break");
				break;
			}
			LinkedList<Point2D> neighbours = map.pointNeigbours(current);
			//System.out.println(neighbours.toString());
			if(neighbours.isEmpty())
				System.err.println("Empty neighbours");
			for(Point2D next: neighbours) {
				System.err.println("Got next neighbour of current: " + next.toString());
				if(!came_from.containsKey(next)) {
					frontier.add(next);
					System.err.println("Added next to frontier");
					came_from.put(next, current);
					System.err.println("Added current to next came_from");
				}
			}
		}
		LinkedList<Point2D> path = addToPath(came_from, start, goal);
		return path;
	}

	// ------------------------------- Private Methods ------------------------------- //

	/**
	 * Private method to add the points in a path from the HashMap to the LinkedList and then reversing the list
	 * @param came_from the HashMap with the points in the path being the keys and the previous points in the path being
	 * the values
	 * @param start the starting point in the path
	 * @param goal the point to reach in the path
	 * @return the LinkedList with the points in the path
	 */
	private LinkedList<Point2D> addToPath(HashMap<Point2D,Point2D> came_from, Point2D start, Point2D goal) {
		LinkedList<Point2D> path = new LinkedList<>();
		Point2D current = goal;
		path.add(current);
		while(current != start) {
			current = came_from.get(current);
			path.add(current);
		}
		path.add(start);
		Collections.reverse(path);
		return path;
	}
}
