package GameMain;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameAi extends Canvas implements Runnable {

	public static int sizeX = 4; // the number of cells in the horizontal direction of maze
	public static int sizeY = 4; // the number of cells in the vertical direction of maze
	public static int cellSize = 160; // the size of the cells in the maze

	// difficulty settings of the AI
	public static boolean easyAi = false; 
	public static boolean mediumAi = false;
	public static boolean hardAi = false;
	public static boolean dieAi = false;
	
	// The width and height of the game/canvas
	public static final int WIDTH = (sizeX * cellSize) + 5;
	public static final int HEIGHT = (sizeY * cellSize) + 5;

	private Thread thread;
	public boolean running = false; // boolean value used to run/stop game

	public static Handler handler;
	GameEngineAi engine;

	Player player1 = null;
	public PlayerAi playerAi = null;

	public int player1Score = 0, playerAiScore = 0; // scores

	Maze m1;

	// The starting coordinates of the player and the AI
	public int startPlayer1X = cellSize/2-16;
	public int startPlayer1Y = cellSize/2-16;
	public int startPlayerAiX = (sizeX * cellSize) - cellSize/2-10;
	public int startPlayerAiY = (sizeY * cellSize) - cellSize/2-16;


	/**
	 * Constructor
	 * Takes in a GameEngine, creates a new handler, maze, player and AI and adds all the walls and players to the handler
	 * @param engine the GameEngine used outside of the Game class in order to change player scores and close the game
	 */
	public GameAi(GameEngineAi engine) {
		handler = new Handler();
		this.engine = engine;

		m1 = new Maze(sizeX, sizeY);
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);

		Map map = new Map(m1);
		PathFinder algorithm = new PathFinder(map);
		
		Player.angle = 180;
		PlayerAi.angle = 45;

		player1 = new Player(cellSize/2-17, cellSize/2 -17, ID.Player, handler);
		playerAi = new PlayerAi((sizeX * cellSize) - cellSize/2 -17, (sizeY * cellSize) - cellSize/2 -17,
																	ID.PlayerAi, handler, map, algorithm);

		handler.addObject(player1);
		handler.addObject(playerAi);
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
				if (running) {
					tick();
					render();
				}
				if (handler.player1Collision || handler.playerAiCollision)
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
		handler.addObject(new CellEastWallAi(sizeX * cellSize, 0, ID.VertWall));
		// Adding the South wall
		handler.addObject(new CellSouthWallAi(0, sizeX * cellSize, ID.HoriWall));
		
		for (Integer i = 0; i < m1.sizeX; i++) {
			x = i * cellSize;
			for (Integer j = 0; j < m1.sizeY; j++) {
				y = j * cellSize;
				// Adding all North Walls
				if (m1.cells[i][j].walls[0] == 1) {
					handler.addObject(new CellHoriWallAi(x, y, ID.HoriWall));
				}
				// Adding all West Walls
				if (m1.cells[i][j].walls[3] == 1) {
					handler.addObject(new CellVertWallAi(x, y, ID.VertWall));
				}
			}
		}
	}
	
	/**
	 * This method is called to start a new game, mid-game
	 */
	public void newGameAi() {
		everyGame();
		addWalls();
	}
	/**
	 * This method is called only when the difficulty of the AI is easy
	 */
	public void newGameAiEasy() {
		//easyAiGame();
		everyGame();
		addWalls();
	}
	/**
	 * This method is called only when the difficulty of the AI is medium
	 */
	public void newGameAiMed() {
		everyGame();
		medAiGame();
		addWalls();
	}
	/**
	 * This method is called only when the difficulty of the AI is hard
	 */
	public void newGameAiHard() {
		everyGame();
		hardAiGame();
		addWalls();
	}
	/**
	 * This method is called only when the difficulty of the AI is the toughest
	 */
	public void newGameAiDead() {
		everyGame();
		deadAiGame();
		addWalls();
	}
	
    /**
     * This method is only used in the newGameAiEasy() method
     */
    private void easyAiGame() {
        //stuff goes here like everyGame()
    }   
    /**
     * This method is only used in the newGameAiMed() method to set the speed of the AI on a new game
     */
    private void medAiGame() {
        playerAi.speed = 6;
    }
    /**
     * This method is only used in the newGameAiHard() method to set the speed of the AI on a new game
     */
    private void hardAiGame() {
        playerAi.speed = 9;
    }
    /**
     * This method is only used in the newGameAiDead() method to set the speed of the AI on a new game
     */
    private void deadAiGame() {
        playerAi.speed = 12;
    }
            
    /**
	 * This method is used whenever new game (irrespective of AI difficulty)
	 * is pressed mid-game in order to start a new game and change and store points
	 */
	private void everyGame() {
		if (running)
			pause();

		handler.removeAll();

		m1 = new Maze(sizeX, sizeY);
		Map map = new Map(m1);
		PathFinder algorithm = new PathFinder(map);
		
		player1 = new Player(startPlayer1X, startPlayer1Y, ID.Player, handler);
		playerAi = new PlayerAi((sizeX * cellSize) - cellSize/2 -17, (sizeY * cellSize) - cellSize/2 -17,
				ID.PlayerAi, handler, map, algorithm);
		
		playerAi.shotTaken = false;
		player1.angle = 180;
		playerAi.angle = 45;
		
		handler.addObject(player1);
		handler.addObject(playerAi);
		
		if (handler.player1Collision) playerAiScore++;
		else if (handler.playerAiCollision) player1Score++;

		handler.player1Collision = false;
		handler.playerAiCollision = false;

		engine.setPlayer1ScoreValue(this.player1Score);
		engine.setPlayerAiScoreValue(this.playerAiScore);
		
		resume();	
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

	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}
}