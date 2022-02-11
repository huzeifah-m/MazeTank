package GameMain;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Class that creates a random maze.
 *
 */
public class Maze {
	public int sizeX;
	public int sizeY;
	public Cell[][] cells;

	/**
	 *
	 */
	public Maze() {
		int sizeX = 0;
		int sizeY = 0;
		cells = new Cell[sizeX][sizeY];
		initialiseCells();
		generateMaze();
	}

	/**
	 * @param x
	 * @param y
	 */
	public Maze(int x, int y) {
		sizeX = x;
		sizeY = y;
		cells = new Cell[sizeX][sizeY];
		initialiseCells();
		generateMaze();
	}

	/**
	 * Initializes cells in the array
	 */
	private void initialiseCells() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				cells[i][j] = new Cell();
				cells[i][j].x = i;
				cells[i][j].y = j;
			}
		}
	}

	/**
	 * The maze generator algorithm. Uses DFS, chooses a cell at random and
	 * checks its neighbours. If a neighbour has all 4 walls up, it removes a
	 * wall. Goes through the whole array of cells until all cells have at least
	 * one wall knocked down.
	 */
	private void generateMaze() {
		Random rand = new Random();

		int x = rand.nextInt(sizeX);
		int y = rand.nextInt(sizeY);

		Stack<Cell> cellStack = new Stack<>();
		int totalCells = sizeX * sizeY;
		int visitedCells = 1;
		Cell currentCell = cells[x][y];

		ArrayList<VHelper> neighbourCellList = new ArrayList<>();

		VHelper hV = new VHelper();

		while (visitedCells < totalCells) {
			neighbourCellList.clear();

			hV = new VHelper();
			if (y - 1 >= 0 && cells[x][y - 1].checkWalls()) {
				hV.x1 = x;
				hV.y1 = y;
				hV.x2 = x;
				hV.y2 = y - 1;
				hV.wall1 = 0;
				hV.wall2 = 2;
				neighbourCellList.add(hV);
			}

			hV = new VHelper();
			if (y + 1 < sizeY && cells[x][y + 1].checkWalls()) {
				hV.x1 = x;
				hV.y1 = y;
				hV.x2 = x;
				hV.y2 = y + 1;
				hV.wall1 = 2;
				hV.wall2 = 0;
				neighbourCellList.add(hV);
			}

			hV = new VHelper();
			if (x - 1 >= 0 && cells[x - 1][y].checkWalls()) {
				hV.x1 = x;
				hV.y1 = y;
				hV.x2 = x - 1;
				hV.y2 = y;
				hV.wall1 = 3;
				hV.wall2 = 1;
				neighbourCellList.add(hV);
			}
			hV = new VHelper();
			if (x + 1 < sizeY && cells[x + 1][y].checkWalls()) {
				hV.x1 = x;
				hV.y1 = y;
				hV.x2 = x + 1;
				hV.y2 = y;
				hV.wall1 = 1;
				hV.wall2 = 3;
				neighbourCellList.add(hV);
			}

			if (neighbourCellList.size() >= 1) {
				int r1 = rand.nextInt(neighbourCellList.size());
				hV = neighbourCellList.get(r1);

				cells[hV.x1][hV.y1].walls[hV.wall1] = 0;
				cells[hV.x2][hV.y2].walls[hV.wall2] = 0;

				cellStack.push(currentCell);

				currentCell = cells[hV.x2][hV.y2];

				x = currentCell.x;
				y = currentCell.y;

				visitedCells++;
			} else {
				currentCell = cellStack.pop();
				x = currentCell.x;
				y = currentCell.y;
			}
		}
	}
}