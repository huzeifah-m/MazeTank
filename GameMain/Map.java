package GameMain;

import java.util.LinkedList;

import javafx.geometry.Point2D;

/**
 * @author Kevin
 * Array-based implementation of the maze, using Point2D points in
 * each cell of the maze.
 * Map can return neighbours of a certain point as well.
 */

public class Map {

	private Point2D[][] points;
	private Maze maze;

	/**
	 * Constructor sets up map by taking the dimensions of the array in the maze and then
	 * creating the points array with the same dimensions and points in each cell.
	 * @param maze the maze which is created in the Game.java class in which the walls are.
	 */
	public Map(Maze maze) {
		this.maze = maze;
		points = new Point2D[maze.sizeX][maze.sizeY];
		createPoints();
	}

	// -------------------------- Public Methods -------------------------- //

	/**
	 * This method gets the array of points of the map
	 * @return the Point2D array of points
	 */
	public Point2D[][] getPoints() {
		return points;
	}


	/**
	 * This methods returns all the neighbours of the given point.
	 * Adds all direct neighbours of given point to the list and then removes points
	 * depending on where the walls are in the maze,
	 * i.e. if direct right neighbour has west wall, remove right neighbour
	 * or if direct south neighbour has north wall, remove neighbour
	 * @param point
	 * @return a LinkedList containing the neighbouring points
	 */
	public LinkedList<Point2D> pointNeigbours(Point2D point) {
		LinkedList<Point2D> neighbours = new LinkedList<>();
		int row = 0, column = 0;
		outerloop:
		for(int i = 0; i < maze.sizeX; i++) {
			for(int j = 0; j < maze.sizeY; j++) {
				if(points[i][j] == point) {
					row = i; column = j;
					break outerloop;
				}
			}
		}

		// Neighbours of top-left point in map
		if(row == 0 && column == 0) {
			System.err.println(row + " " + column);
			neighbours.add(points[row][column+1]);
			neighbours.add(points[row+1][column]);
			System.err.println("Added points");
			if(maze.cells[row][column+1].walls[0] == 1) {
				neighbours.remove(points[row][column+1]);
				System.err.println("Removed right one");
			}
			if(maze.cells[row+1][column].walls[3] == 1) {
				neighbours.remove(points[row+1][column]);
				System.err.println("Removed bottom one");
			}
		}
		// Neighbours of bottom-left point in map
		else if(row == 0 && column == maze.sizeY-1) {
			neighbours.add(points[row][column-1]);
			neighbours.add(points[row+1][column]);
			if(maze.cells[row][column].walls[0] == 1)
				neighbours.remove(points[row][column-1]);
			if(maze.cells[row+1][column].walls[3] == 1)
				neighbours.remove(points[row+1][column]);
		}
		// Neighbours of top-right point in map
		else if(row == maze.sizeX-1 && column == 0) {
			neighbours.add(points[row-1][column]);
			neighbours.add(points[row][column+1]);
			if(maze.cells[row][column+1].walls[0] == 1)
				neighbours.remove(points[row][column+1]);
			if(maze.cells[row][column].walls[3] == 1)
				neighbours.remove(points[row-1][column]);
		}
		// Neighbours of bottom-right point in map
		else if(row == maze.sizeX-1 && column == maze.sizeY-1) {
			neighbours.add(points[row-1][column]);
			neighbours.add(points[row][column-1]);
			if(maze.cells[row][column].walls[0] == 1)
				neighbours.remove(points[row][column-1]);
			if(maze.cells[row][column].walls[3] == 1)
				neighbours.remove(points[row-1][column]);
		}
		// Neighbours of left column of points in map, minus the top-left and bottom-left
		else if((0 < column && column < maze.sizeY-1) && row == 0) {
			neighbours.add(points[row][column-1]);
			neighbours.add(points[row][column+1]);
			neighbours.add(points[row+1][column]);
			if(maze.cells[row][column].walls[0] == 1)
				neighbours.remove(points[row][column-1]);
			if(maze.cells[row+1][column].walls[3] == 1)
				neighbours.remove(points[row+1][column]);
			if(maze.cells[row][column+1].walls[0] == 1)
				neighbours.remove(points[row][column+1]);
		}
		// Neighbours of top row of points in map, minus the top-left and top right
		else if((0 < row && row < maze.sizeX-1) && column == 0) {
			neighbours.add(points[row-1][column]);
			neighbours.add(points[row][column+1]);
			neighbours.add(points[row+1][column]);
			if(maze.cells[row][column].walls[3] == 1)
				neighbours.remove(points[row-1][column]);
			if(maze.cells[row][column+1].walls[0] == 1)
				neighbours.remove(points[row][column+1]);
			if(maze.cells[row+1][column].walls[3] == 1)
				neighbours.remove(points[row+1][column]);
		}
		// Neighbours of bottom row in map, minus the bottom-right and bottom left
		else if((0 < row && row < maze.sizeX-1) && column == maze.sizeY - 1) {
			neighbours.add(points[row-1][column]);
			neighbours.add(points[row][column-1]);
			neighbours.add(points[row+1][column]);
			if(maze.cells[row][column].walls[3] == 1)
				neighbours.remove(points[row-1][column]);
			if(maze.cells[row][column].walls[0] == 1)
				neighbours.remove(points[row][column-1]);
			if(maze.cells[row+1][column].walls[3] == 1)
				neighbours.remove(points[row+1][column]);
		}
		// Neighbours of right column of points, minus the top-right and bottom-right
		else if((0 < column && column < maze.sizeY -1) && row == maze.sizeX - 1) {
			neighbours.add(points[row][column-1]);
			neighbours.add(points[row-1][column]);
			neighbours.add(points[row][column+1]);
			if(maze.cells[row][column].walls[0] == 1)
				neighbours.remove(points[row][column-1]);
			if(maze.cells[row][column].walls[3] == 1)
				neighbours.remove(points[row-1][column]);
			if(maze.cells[row][column+1].walls[0] == 1)
				neighbours.remove(points[row][column+1]);
		}
		// Neighbours of all other points
		else {
			neighbours.add(points[row-1][column]);
			neighbours.add(points[row][column-1]);
			neighbours.add(points[row][column+1]);
			neighbours.add(points[row+1][column]);
			if(maze.cells[row][column].walls[0] == 1)
				neighbours.remove(points[row][column-1]);
			if(maze.cells[row][column].walls[3] == 1)
				neighbours.remove(points[row-1][column]);
			if(maze.cells[row][column+1].walls[0] == 1)
				neighbours.remove(points[row][column+1]);
			if(maze.cells[row+1][column].walls[3] == 1)
				neighbours.remove(points[row+1][column]);
		}
		return neighbours;
	}

	// -------------------------- Private Methods -------------------------- //

	/**
	 * Private method to create the points in the cells of the points array.
	 * Coordinates of points are in the middle of each cell in the maze
	 */
	private void createPoints() {
		for(int i = 0; i < maze.sizeX; i++) {
			for(int j = 0; j < maze.sizeY; j++) {
				points[i][j] = new Point2D(i*GameAi.cellSize + GameAi.cellSize/2, j*GameAi.cellSize + GameAi.cellSize/2);
			}
		}
	}
}
