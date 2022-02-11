package Networking;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.*;
import java.net.*;

import GameMenu.Sound;

public class KeyInput implements EventHandler<KeyEvent> {

	private Handler handler;

	private int playerID;
	public static boolean send = false;
	public static final long FIRE_RATE = 1500000000L;
	public long lastShot;

	public KeyInput(Handler handler, int playerID) {
		this.handler = handler;

		this.playerID = playerID;
	}

	public void handle(KeyEvent event) {
		// For KeyPressed scenario
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {
			// Player 1 actions when key pressed
			if (event.getCode() == KeyCode.W) {
				for (GameObject tempObject : handler.object) {
					/**
					 * @param velY
					 *            isn't a velocity but an input for the
					 *            accelerator to determine the correct velocity.
					 */
					if (tempObject.getId() == playerID) {
						tempObject.setVelY(3);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.S) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == playerID) {
						tempObject.setVelY(-3);
						break;
					}

				}
			}
			if (event.getCode() == KeyCode.A) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == playerID) {
						tempObject.setVelAngle(-3);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.D) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == playerID) {
						tempObject.setVelAngle(3);
						break;
					}
				}
			}

			if ((event.getCode() == KeyCode.SPACE) & (System.nanoTime() - lastShot >= FIRE_RATE)) {
				lastShot = System.nanoTime();
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == playerID) {
						/**
						 * When space is pressed the handler adds a projectile
						 * object at the location of the player and in the
						 * correct direction.
						 */
						Sound.fire();
						NetGame.numProj++;
						handler.addObject(new Projectile(tempObject.getX() + 12, tempObject.getY() + 12,
								tempObject.getId() * 100 + NetGame.numProj, tempObject.getGAngle(), 0, handler));

						break;
					}
				}
			}
		}

		// For KeyReleased scenario
		if (event.getEventType() == KeyEvent.KEY_RELEASED) {
			// Player 1 actions when key released
			if (event.getCode() == KeyCode.W) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == playerID) {
						send = false;
						tempObject.setVelY(2);
						break;
					}
				}
			}

			if (event.getCode() == KeyCode.S) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == playerID) {
						tempObject.setVelY(-2);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.A) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == playerID) {
						tempObject.setVelAngle(0);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.D) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == playerID) {
						tempObject.setVelAngle(0);
						break;
					}
				}
			}
		}

	}
}
