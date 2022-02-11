package Networking;
/**
Usage: java GameMain.Game [hostname]
e.g. java GameMain.Game localhost
**/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import GameMain.GameEngine;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NetGame extends Canvas implements Runnable {

	private static final long serialVersionUID = 470565766440560791L;

	public static int sizeX = 4;

	public static int sizeY = 4;

	public static int cellSize = 160;

	public static final int WIDTH = (sizeX * cellSize) + 5;
	public static final int HEIGHT = (sizeY * cellSize) + 5;

	private int startX = cellSize / 2 - 16;
	public int startY = cellSize / 2 - 16;

	public static boolean check = false;

	private Thread thread;
	private boolean running = false;

	public static Handler handler;

	public int player1Score = 0;

	public int player2Score = 0;

	NetGameEngine engineNet;
	private Socket socket;
	DataOutputStream toServer = null;
	DataInputStream fromServer = null;
	public static List<Integer> idNumbers = new ArrayList<Integer>();
	public static int playerID = 0;
	public static int numProj = 5;

	/**
	 * @author Lewis Deakin-Davies
	 * 
	 * @param host
	 *            is taken from the network input.
	 * @param port
	 *            is taken from the network input.
	 */

	public NetGame(String host, int port) {
		try {
			// Creates a new client socket and creates input and output streams.

			socket = new Socket(host, port);

			System.out.println("Connect");

			toServer = new DataOutputStream(socket.getOutputStream());
			fromServer = new DataInputStream(socket.getInputStream());
		} catch (IOException e1) {
			System.err.println("Could not connect to server: " + e1.getMessage());
			System.exit(1);
		}

		System.out.println("Connected");

		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);

		// A handler is created to manage the clients objects.
		handler = new Handler();

		// The client player information is sent to the server.
		sendPlayers();

		// The ID is taken from the server to give to client player.
		playerID = getID();

		System.out.println("THIS IS THE ID " + playerID);

		idNumbers.add(playerID);

		// The current players on the server are taken and then the correct
		// starting place based on ID is taken.
		getPlayers();
		setCoord();

		// The player is created and added to the handler and then a sender and
		// a receiver is created to send information to the server.
		Player player = new Player(startX, startY, playerID, 90, handler);
		handler.addObject(player);
		this.setVisible(true);

		ClientSender sender = new ClientSender(playerID, toServer, handler);
		ClientReceiver receiver = new ClientReceiver(playerID, fromServer, handler);

		sender.start();
		receiver.start();

	}

	public void setPlayer1(int score1) {
		player1Score = score1;
	}

	public void setPlayer2(int score2) {
		player1Score = score2;
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void pause() {
		running = false;
	}

	public synchronized void resume() {
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			fromServer.close();
			toServer.close();

		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * takes the ID given.
	 * 
	 * @return playerIDA is the ID given by the server.
	 */
	public int getID() {
		int playerIDA = 0;
		try {
			playerIDA = fromServer.readInt();
		} catch (Exception e3) {
			System.err.println("Could not send ID to client: " + e3.getMessage());
			e3.printStackTrace();
		}
		return playerIDA;

	}

	public void setCoord() {
		if (playerID == 1) {
			startX = 100;
			startY = 100;
		}
		if (playerID == 2) {
			startX = 500;
			startY = 500;
		}
		if (playerID == 3) {
			startX = 100;
			startY = 500;
		}
		if (playerID == 4) {
			startX = 500;
			startY = 100;
		}
	}

	// This method resets the location of each player back to its starting
	// position.

	public void newGame() {
		for (int i = 0; i < handler.object.size(); i++) {
			System.out.println("THIS PLAYER " + playerID);
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == playerID) {
				tempObject.setX(startX);
				tempObject.setY(startY);
			}
		}
	}

	// runs the game on a reasonable tick rate and renders the game.
	public void run() {
		new AnimationTimer() {

			private long lastUpdate = 0;

			public void handle(long currentNanoTime) {
				if (currentNanoTime - lastUpdate >= 1_000_000) {
					lastUpdate = currentNanoTime;
					tick();
				}
				if (running)
					render();
			}
		}.start();
	}

	// Sends this clients information to the server.
	private void sendPlayers() {
		try {
			Double x = (double) (WIDTH / 2 - 32);
			Double y = (double) (HEIGHT / 2 - 32);
			toServer.writeDouble(x);
			toServer.writeDouble(y);
			toServer.writeInt(90);
		} catch (IOException e2) {
			System.err.println("Could not send new player info to server: " + e2.getMessage());
			e2.printStackTrace();
			System.exit(1);
		}
	}

	// Takes the player info from the server.
	private void getPlayers() {
		try {
			int numPlayers = 0;
			numPlayers = (int) fromServer.readInt();
			System.out.println(numPlayers);
			for (int i = 0; i < numPlayers; i++) {

				int otherPlayerID = fromServer.readInt();
				double otherPlayerX = fromServer.readDouble();
				double otherPlayerY = fromServer.readDouble();
				int otherPlayerAngle = fromServer.readInt();

				// Adds the 4 starting walls as the first 4 objects in the
				// handler.
				if (otherPlayerID >= 100) {
					if (otherPlayerID == 101) {
						handler.addObject(new CellEastWall(otherPlayerX, otherPlayerY, 101, 0));
						idNumbers.add(otherPlayerID);
					}

					if (otherPlayerID == 102) {
						handler.addObject(new CellSouthWall(otherPlayerX, otherPlayerY, 102, 0));
						idNumbers.add(otherPlayerID);
					}

					if (otherPlayerID == 103) {
						handler.addObject(new CellSouthWall(otherPlayerX, otherPlayerY, 103, 0));
						idNumbers.add(otherPlayerID);
					}

					if (otherPlayerID == 104) {
						handler.addObject(new CellEastWall(otherPlayerX, otherPlayerY, 104, 0));
						idNumbers.add(otherPlayerID);
					}

				}
				// if the ID of another player is received and is not already
				// added, it is added.
				if (otherPlayerID != playerID && otherPlayerID < 100) {
					handler.addObject(new Player(otherPlayerX, otherPlayerY, otherPlayerID, otherPlayerAngle, handler));
					idNumbers.add(otherPlayerID);
				}

			}
		} catch (Exception e3) {
			System.err.println("Could not get objects: " + e3.getMessage());
			System.exit(1);
		}
	}

	private void tick() {
		handler.tick();
	}

	private void render() {
		GraphicsContext g = this.getGraphicsContext2D();
		g.setFill(Color.SIENNA);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
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
