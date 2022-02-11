package Networking;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

// Gets messages from other clients via the server (by the
// ServerSender thread).

public class ClientReceiver extends Thread {

	private DataInputStream server;
	private Handler handler;
	private int playerID;
	public static List<Integer> playerNumbers = new ArrayList<Integer>();

	/**
	 * 
	 * @param playerID
	 *            is taken from the server when a client connects.
	 * @param fromServer
	 *            is the stream from the server.
	 * @param handler
	 *            is sent from the server.
	 */

	ClientReceiver(int playerID, DataInputStream fromServer, Handler handler) {
		this.server = fromServer;
		this.handler = handler;
		this.playerID = playerID;
	}

	public void run() {
		while (true) {
			try {
				/**
				 * @param numPlayers
				 *            is the number of game objects in the handler. The
				 *            client receiver loops through continuously,
				 *            reading the data from the client.
				 */
				int numPlayers = 0;
				numPlayers = server.readInt();
				for (int i = 0; i < numPlayers; i++) {
					int otherPlayerID = server.readInt();
					double otherPlayerX = server.readDouble();
					double otherPlayerY = server.readDouble();
					int otherPlayerAngle = server.readInt();

					/*
					 * If the object doesn't share an ID with the player that is
					 * linked to this receiver and also the ID has not already
					 * been added to the clients handler, then a new player is
					 * added with the received information and the ID is added.
					 */
					if (otherPlayerID != playerID && otherPlayerID < 10) {
						if (!NetGame.idNumbers.contains(otherPlayerID)) {
							handler.addObject(
									new Player(otherPlayerX, otherPlayerY, otherPlayerID, otherPlayerAngle, handler));
							NetGame.idNumbers.add(otherPlayerID);
							System.out.println("adding player");
						}

						/*
						 * Any information received about other players is the
						 * updated in the handler.
						 */
						GameObject tempObject = handler.object.get(i);
						if (tempObject.getId() == otherPlayerID) {
							tempObject.setX(otherPlayerX);
							tempObject.setY(otherPlayerY);
							tempObject.setGAngle(otherPlayerAngle);
						}
					}

					/*
					 * Any ID over 104 is the ID of a projectile and needs to be
					 * added to this client if it was fired on a different
					 * client. The projectiles ID is based on the players ID so
					 * the player can be found by dividing the ID by 100. +3 is
					 * added as the first 4 objects in the handler are the
					 * walls. This ensures that the right player is selected and
					 * the projectile is created at the right location. The ID
					 * of the projectile is added to the list of IDs to ensure
					 * it isn't added multiple times.
					 */
					if (otherPlayerID > 104) {
						GameObject tempObject = handler.object.get((otherPlayerID / 100) + 3);
						if (!NetGame.idNumbers.contains(otherPlayerID)) {
							double currentX = tempObject.getX();
							double currentY = tempObject.getY();
							int currentAngle = tempObject.getGAngle();
							handler.addObject(new Projectile(currentX + 12, currentY + 12, otherPlayerID, currentAngle,
									0, handler));
							NetGame.idNumbers.add(otherPlayerID);
							System.out.println("Added projectile");
						}
					}
				}

			} catch (Exception e4) {
				System.err.println("Game ended");
				// e4.printStackTrace();
			}

		}
	}
}