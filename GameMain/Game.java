package GameMain;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class that initializes the game
 *
 */
public class Game extends Canvas implements Runnable {

	public static int sizeX = 4; // the number of cells in the horizontal direction of maze
	public static int sizeY = 4; // the number of cells in the vertical direction of the maze
	public static int cellSize = 160; // the size of the cells in the maze

	// The width and height of the game/canvas
	public static final int WIDTH = (sizeX * cellSize) + 5;
	public static final int HEIGHT = (sizeY * cellSize) + 5;

	private Thread thread;
	public boolean running = false; // boolean value used to run/stop game

	public static Handler handler;
	GameEngine engine;

	Player player1 = null;
	Player2 player2 = null;

	public int player1Score = 0, player2Score = 0; // scores

	public int startPlayer1X = cellSize/2-16; // the starting x value of player 1
	public int startPlayer1Y = cellSize/2-16; // the starting y value of player 1
	public int startPlayer2X = (sizeX * cellSize) - cellSize/2-10; // the starting x value of player 2
	public int startPlayer2Y = (sizeY * cellSize) - cellSize/2-16; // the starting y value of player 2

	Maze m1;

	/**
	 * Constructor
	 * Takes in a GameEngine, creates a new handler and maze and 2 players and adds all the walls and players to the handler
	 * @param engine the GameEngine used outside of the Game class in order to change player scores and close the game
	 */
	public Game(GameEngine engine) {
		handler = new Handler();
		this.engine = engine;

		m1 = new Maze(sizeX, sizeY);
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);

		player1 = new Player(startPlayer1X, startPlayer1Y, ID.Player, handler);
		player2 = new Player2(startPlayer2X, startPlayer2Y, ID.Player2, handler);

		handler.addObject(player1);
		handler.addObject(player2);
		addWalls();
		this.setVisible(true);
	}

	/**
	 * This method starts the thread and sets running to true in order to start the game
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	/**
	 * This method pauses the game by setting the running boolean to false
	 */
	public synchronized void pause() {
		running = false;
	}

	/**
	 * This method resumes the game by setting the running boolean to true
	 */
	public synchronized void resume() {
		running = true;
	}

	/**
	 * This method stops the game by attempting to join and close the thread and setting the running boolean to false
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				tick();
				if (running)
					render();
				if (handler.player1Collision || handler.player2Collision)
					pause();
			}
		}.start();
	}

	/**
	 * This method is used in the running of the game as a wrapper method to make the handler tick
	 */
	private void tick() {
		handler.tick();
	}

	/**
	 * This method is used as a wrapper method to render the images by rendering the handler class
	 */
	private void render() {
		GraphicsContext g = this.getGraphicsContext2D();
		g.setFill(Color.SIENNA);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
	}

	/**
	 * This method adds all the walls of the maze into the handler by creating wall GameObjects and placing them in the list
	 */
	public void addWalls() {
		int x, y;
		// Adding the East Wall
		handler.addObject(new CellEastWall(sizeX * cellSize, 0, ID.VertWall));
		// Adding the South wall
		handler.addObject(new CellSouthWall(0, sizeX * cellSize, ID.HoriWall));

		for (Integer i = 0; i < m1.sizeX; i++) {
			x = i * cellSize;
			for (Integer j = 0; j < m1.sizeY; j++) {
				y = j * cellSize;
				// Adding all North Walls
				if (m1.cells[i][j].walls[0] == 1) {
					handler.addObject(new CellHoriWall(x, y, ID.HoriWall));
				}
				// Adding all West Walls
				if (m1.cells[i][j].walls[3] == 1) {
					handler.addObject(new CellVertWall(x, y, ID.VertWall));
				}
			}
		}
	}

	/**
	 * This method creates the border walls of the game and adds them to the handler list
	 */
	public void mapEdge() {
		handler.addObject(new CellEastWall(sizeX * cellSize, 0, ID.VertWall));
		handler.addObject(new CellSouthWall(0, sizeX * cellSize, ID.HoriWall));
		handler.addObject(new CellSouthWall(0, 0, ID.HoriWall));
		handler.addObject(new CellEastWall(0, 0, ID.VertWall));
	}

	/**
	 * This method creates the walls of the set map 1
	 */
	public void Map1() {
		mapEdge();
		handler.addObject(new CellVertWall(250, 95, ID.VertWall));
		handler.addObject(new CellHoriWall(90, 250, ID.HoriWall));

		handler.addObject(new CellVertWall(400, 400, ID.VertWall));
		handler.addObject(new CellHoriWall(400, 400, ID.HoriWall));

		handler.addObject(new CellVertWall(325, 250, ID.VertWall));
		handler.addObject(new CellHoriWall(250, 325, ID.HoriWall));

		handler.addObject(new CellVertWall(400, 95, ID.VertWall));
		handler.addObject(new CellHoriWall(400, 250, ID.HoriWall));

		handler.addObject(new CellVertWall(250, 400, ID.VertWall));
		handler.addObject(new CellHoriWall(90, 400, ID.HoriWall));
	}

	/**
	 * This method creates the walls of the set map 2
	 */
	public void Map2() {
		mapEdge();
		handler.addObject(new CellVertWall(160, 0, ID.VertWall));
		handler.addObject(new CellVertWall(320, 90, ID.VertWall));
		handler.addObject(new CellVertWall(480, 0, ID.VertWall));

		handler.addObject(new CellVertWall(160, 480, ID.VertWall));
		handler.addObject(new CellVertWall(320, 390, ID.VertWall));
		handler.addObject(new CellVertWall(480, 480, ID.VertWall));

		handler.addObject(new CellHoriWall(320, 250, ID.HoriWall));
		handler.addObject(new CellHoriWall(160, 250, ID.HoriWall));
		handler.addObject(new CellHoriWall(320, 390, ID.HoriWall));
		handler.addObject(new CellHoriWall(160, 390, ID.HoriWall));

		handler.addObject(new CellHoriWall(-60, 320, ID.HoriWall));
		handler.addObject(new CellHoriWall(550, 320, ID.HoriWall));
	}

	/**
	 * This method creates the walls of the set map 3
	 */
	public void Map3() {
		mapEdge();
		handler.addObject(new CellVertWall(160, 0, ID.VertWall));
		handler.addObject(new CellVertWall(160, 100, ID.VertWall));
		handler.addObject(new CellVertWall(160, 200, ID.VertWall));
		handler.addObject(new CellVertWall(160, 300, ID.VertWall));
		handler.addObject(new CellVertWall(160, 380, ID.VertWall));

		handler.addObject(new CellVertWall(480, 480, ID.VertWall));
		handler.addObject(new CellVertWall(480, 380, ID.VertWall));
		handler.addObject(new CellVertWall(480, 280, ID.VertWall));
		handler.addObject(new CellVertWall(480, 180, ID.VertWall));
		handler.addObject(new CellVertWall(480, 100, ID.VertWall));

		handler.addObject(new CellHoriWall(160, 95, ID.HoriWall));
		handler.addObject(new CellHoriWall(320, 190, ID.HoriWall));
		handler.addObject(new CellHoriWall(160, 285, ID.HoriWall));
		handler.addObject(new CellHoriWall(320, 380, ID.HoriWall));
		handler.addObject(new CellHoriWall(160, 475, ID.HoriWall));
		handler.addObject(new CellHoriWall(320, 570, ID.HoriWall));
	}

	/**
	 * This method is used whenever new game (irrespective of map type)
	 * is pressed mid-game in order to start a new game and change and store points
	 */
	public void everyGame() {
		if (running)
			pause();

		handler.removeAll();

		m1 = new Maze(sizeX, sizeY);

		Player.angle = 90;
		Player2.angle = 90;

		player1 = new Player(startPlayer1X, startPlayer1Y, ID.Player, handler);
		player2 = new Player2(startPlayer2X, startPlayer2Y, ID.Player2, handler);

		handler.addObject(player1);
		handler.addObject(player2);


		if (handler.player1Collision)
			player2Score++;
		else if (handler.player2Collision)
			player1Score++;

		handler.player1Collision = false;
		handler.player2Collision = false;

		engine.setPlayer1ScoreValue(this.player1Score);
		engine.setPlayer2ScoreValue(this.player2Score);
		resume();
	}
	/**
	 * This method is called only when the game uses random generated mazes
	 */
	public void newGameLocalRND() {
		everyGame();
		addWalls();
	}

	/**
	 * This method is called only when the game uses set map 1
	 */
	public void newGameLocalMap1() {
		everyGame();
		Map1();
	}

	/**
	 * This method is called only when the game uses set map 2
	 */
	public void newGameLocalMap2() {
		everyGame();
		Map2();
	}

	/**
	 * This method is called only when the game uses set map 3
	 */
	public void newGameLocalMap3() {
		everyGame();
		Map3();
	}

	/**
	 * This method makes sure that the player tank cannot leave the borders of the game
	 * @param var the variable to be changed in order that it does not exceed the max or is less than the min
	 * @param min the minimum value that var can be
	 * @param max the maximum value that var can be
	 * @return the integer var changed
	 */
	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}
}