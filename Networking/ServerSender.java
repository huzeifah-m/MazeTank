package Networking;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class ServerSender extends Thread {

	private DataOutputStream sender;
	private Handler serverHandler;
	private int playerID;
	private double playerX;
	private double playerY;
	private int playerAngle;

	/**
	 * 
	 * @param playerID
	 *            is taken from the server.
	 * @param sender
	 *            is the stream to the client.
	 * @param serverHandler
	 *            is the handler sent from the server.
	 */
	public ServerSender(int playerID, DataOutputStream sender, Handler serverHandler) {
		this.sender = sender;
		this.serverHandler = serverHandler;
		this.playerID = playerID;
	}

	public void run() {
		try {
			while (true) {

				// handler is ticked and then updated players are sent to the
				// client.

				Thread.sleep(1000 / 60);
				serverHandler.tick();
				sendPlayers();
			}
		} catch (IOException e3) {
			System.err.println("Could not send players to client: " + e3.getMessage());
			e3.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendPlayers() throws IOException {

		/*
		 * information is taken from each object in the server handler and then
		 * sent through the sender to the client to be updated on the client
		 * side.
		 */
		int objectSize = serverHandler.object.size();
		sender.writeInt(objectSize);

		for (int i = 0; i < objectSize; i++) {
			GameObject tempObject = serverHandler.object.get(i);

			if (playerID != tempObject.getId() && tempObject.getId() < 10) {

				playerX = tempObject.getX();
				playerY = tempObject.getY();
				playerAngle = tempObject.getGAngle();

			}

			sender.writeInt(tempObject.getId());

			sender.writeDouble(playerX);

			sender.writeDouble(playerY);

			sender.writeInt(playerAngle);

		}
	}
}
