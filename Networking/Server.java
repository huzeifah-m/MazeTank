package Networking;

import java.io.*;
import java.net.*;
import java.lang.Integer;
import java.util.LinkedList;

public class Server {

	public static Handler serverHandler;

	public static int sizeX = 4;

	public static int sizeY = 4;

	public static int cellSize = 160;

	public static final int WIDTH = (sizeX * cellSize) + 5;
	public static final int HEIGHT = (sizeY * cellSize) + 5;

	/**
	 * @param port
	 *            is used to choose server port.
	 */
	public Server(int port) {

		// Creates a new server socket and handler and populates it with the 4
		// outside walls.

		ServerSocket serverSocket = null;
		serverHandler = new Handler();
		serverHandler.addObject(new CellEastWall(sizeX * cellSize, 0, 101, 0));
		serverHandler.addObject(new CellSouthWall(0, sizeX * cellSize, 102, 0));
		serverHandler.addObject(new CellSouthWall(0, 0, 103, 0));
		serverHandler.addObject(new CellEastWall(0, 0, 104, 0));
		try {

			// Creates the server with the port.
			serverSocket = new ServerSocket(port);
		} catch (Exception e1) {
			System.err.println("Could not listen on port " + "1111");
			System.exit(1);
		}

		try {
			while (true) {

				// A connection is accepted and data input and output streams
				// are created.
				Socket socket = serverSocket.accept();

				DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
				DataInputStream fromClient = new DataInputStream(socket.getInputStream());

				/**
				 * @param playerX
				 *            takes first value from the client.
				 * @param playerY
				 *            takes the second value from the client.
				 * @param playerAngle
				 *            takes the third value from the client.
				 */
				double playerX = fromClient.readDouble();
				double playerY = fromClient.readDouble();
				int playerAngle = fromClient.readInt();

				// Player ID is calculated by this method.
				int playerID = PlayerID.ID;
				PlayerID.increment();

				// Player ID is sent to the client.
				sendID(toClient, playerID);
				System.out.println("Player " + playerID + " connected");

				// The current players on the server are sent to the client.
				sendPlayers(toClient);

				// The new player is added using the received variables.
				serverHandler.addObject(new Player(playerX, playerY, playerID, playerAngle, serverHandler));

				// A receiver and sender is set up to communicate with the
				// client.
				(new ServerReceiver(playerID, fromClient, serverHandler)).start();
				(new ServerSender(playerID, toClient, serverHandler)).start();
			}
		} catch (Exception e2) {
			System.err.println("IO Error: " + e2.getMessage());
			e2.printStackTrace();
		}
	}

	/**
	 * sendID method sends the new player ID back to the client to tell it what
	 * it needs to be set as.
	 * 
	 * @param toClient
	 *            Is the output stream of the current client.
	 * @param playerID
	 *            Is the ID decided by the server.
	 */
	private static void sendID(DataOutputStream toClient, int playerID) {
		try {
			toClient.writeInt(playerID);
		} catch (Exception e3) {
			System.err.println("Could not send ID to client: " + e3.getMessage());
			e3.printStackTrace();
		}
	}

	/**
	 * This method writes each of the objects in the handler to the client.
	 * 
	 * @param toClient
	 *            is the data stream.
	 * @param objectSize
	 *            takes the handlers size to know how many times to loop through
	 *            sending data. It then sends information on each object in the
	 *            handler to the client.
	 */
	private static void sendPlayers(DataOutputStream toClient) {
		try {
			int objectSize = serverHandler.object.size();
			toClient.writeInt(objectSize);
			for (int i = 0; i < objectSize; i++) {
				GameObject tempObject = serverHandler.object.get(i);

				toClient.writeInt(tempObject.getId());
				System.out.println("Sent ID " + tempObject.getId());

				toClient.writeDouble(tempObject.getX());
				System.out.println("Sent x " + tempObject.getX());

				toClient.writeDouble(tempObject.getY());
				System.out.println("Sent y " + tempObject.getY());
				toClient.writeInt(tempObject.getGAngle());
			}
		} catch (Exception e3) {
			System.err.println("Could not send players to client: " + e3.getMessage());
			e3.printStackTrace();
		}
	}
}
