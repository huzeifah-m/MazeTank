package Networking;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServerReceiver extends Thread {
	private int playerID;
	private DataInputStream receiver;
	private Handler serverHandler;
	private boolean size = true;
	public static List<Integer> idNumbers = new ArrayList<Integer>();

	/**
	 * 
	 * @param playerID
	 *            is taken from the server.
	 * @param receiver
	 *            is the stream from the client.
	 * @param serverHandler
	 *            is the handler sent from the server.
	 */
	public ServerReceiver(int playerID, DataInputStream receiver, Handler serverHandler) {
		this.playerID = playerID;
		this.receiver = receiver;
		this.serverHandler = serverHandler;
	}

	public void run() {
		try {
			while (true) {

				size = true;
				int i = 0;
				while (size) {

					// information is received from the client sender from the
					// objects in the handler.

					int clientSize = receiver.readInt();
					int checkID = receiver.readInt();
					double playerX = receiver.readDouble();
					double playerY = receiver.readDouble();
					int playerAngle = receiver.readInt();

					/*
					 * The ID recieved is checked if over 104 as all objects
					 * over 104 are projectiles. If an object is found and is
					 * not already in the list of projectiles, then a projectile
					 * is added to the server handler.
					 */
					if (checkID > 104) {
						if (!idNumbers.contains(checkID)) {

							serverHandler.addObject(
									new Projectile(playerX + 12, playerY + 12, checkID, playerAngle, 0, serverHandler));
							idNumbers.add(checkID);
							System.out.println("Added proj  " + checkID);
						}
					}

					/*
					 * The Game object is then taken from the server handler and
					 * updated based on the information taken from the client
					 * sender.
					 */
					GameObject tempObject = serverHandler.object.get(i);

					i = (i + 1);
					if (tempObject.getId() == playerID) {
						tempObject.setX(playerX);
						tempObject.setY(playerY);
						tempObject.setGAngle(playerAngle);

					}
					// A while loop with integer incrementing is used here to
					// ensure that a null object is not taken.

					if (i == clientSize) {
						size = false;

					}

				}
			}

		} catch (Exception e) {
			System.err.println("Could not get player coordinates: " + e.getMessage());

		}
	}
}
