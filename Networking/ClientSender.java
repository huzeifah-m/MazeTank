package Networking;

import java.io.DataOutputStream;
import java.io.IOException;

import javafx.animation.AnimationTimer;

//Repeatedly reads recipient's nickname and text from the user in two
//separate lines, sending them to the server (read by ServerReceiver
//thread).

public class ClientSender extends Thread {

	private DataOutputStream server;
	private int playerID;
	private Handler clientObjects;
	private double playerX;
	private double playerY;
	private int playerAngle;

	/**
	 * 
	 * @param playerID
	 *            is taken from the client when the client is started.
	 * @param fromServer
	 *            is the stream from the server.
	 * @param handler
	 *            is sent from the client.
	 */
	ClientSender(int playerID, DataOutputStream toServer, Handler clientObjects) {
		this.playerID = playerID;
		this.server = toServer;
		this.clientObjects = clientObjects;
	}

	public void run() {

		try {

			while (true) {
				Thread.sleep(1000 / 60);
				
				/**
				 * The client sender loops through all the objects in the
				 * handler and sends them continuously to the server receiver
				 * via the data output stream.
				 */
				for (int i = 0; i < clientObjects.object.size(); i++) {

					GameObject tempObject = clientObjects.object.get(i);

					int clientSize = clientObjects.object.size();

					/*
					 * As the player coordinates are needed when a projectile is
					 * created, the player coordinates are only updated the when
					 * ID of the object matches the ID that the sender received
					 * from the client.
					 */
					if (playerID == tempObject.getId()) {

						playerX = tempObject.getX();
						playerY = tempObject.getY();
						playerAngle = tempObject.getGAngle();
					}
					server.writeInt(clientSize);
					server.writeInt(tempObject.getId());
					server.writeDouble(playerX);
					server.writeDouble(playerY);
					server.writeInt(playerAngle);

					// If the current ID is not in the list of IDs, then it is
					// added.

					if (!NetGame.idNumbers.contains(tempObject.getId())) {
						NetGame.idNumbers.add(tempObject.getId());
					}
				}
			}

		} catch (IOException e) {
			System.err.println("Communication broke in ClientSender" + e.getMessage());
			System.exit(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
