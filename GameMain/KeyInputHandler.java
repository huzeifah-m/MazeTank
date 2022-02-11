package GameMain;

import GameMenu.Sound;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Handles all the keyboard inputs for the 2 player local game mode
 *
 */
public class KeyInputHandler implements EventHandler<KeyEvent> {

	private Handler handler;
	public static final long FIRE_RATE = 1300000000L;
	public long lastShot;
	public static final long FIRE_RATEP2 = 1300000000L;
	public long lastShotP2;


	/**
	 * Constructor
	 * Takes in a handler so it can loop through game objects
	 * @param handler
	 */
	public KeyInputHandler(Handler handler) {
		this.handler = handler;
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(KeyEvent event) {
		// For KeyPressed scenario
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {
			// Player 1 actions when key pressed
			if ((event.getCode() == KeyCode.SPACE) & (System.nanoTime() - lastShot >= FIRE_RATE)) {
				lastShot = System.nanoTime();
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player) {
						Sound.fire();
						handler.addObject(new Projectile(Player.recX()+12, Player.recY()+12, Player.getAngle(), handler,
								ID.Projectile));
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.W) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player) {
						tempObject.setVelY(3);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.S) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player) {
						tempObject.setVelY(-3);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.A) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player) {
						tempObject.setVelAngle(-3);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.D) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player) {
						tempObject.setVelAngle(3);
						break;
					}
				}
			}

			// Player 2 actions when key pressed
			if ((event.getCode() == KeyCode.M) & (System.nanoTime() - lastShotP2 >= FIRE_RATEP2)) {
				lastShotP2 = System.nanoTime();
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player) {
						Sound.fire();
						handler.addObject(new Projectile(Player2.recX()+12, Player2.recY()+12, Player2.getAngle(), handler,
							ID.Projectile2));
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.UP) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player2) {
						tempObject.setVelY(3);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.DOWN) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player2) {
						tempObject.setVelY(-3);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.LEFT) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player2) {
						tempObject.setVelAngle(-3);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.RIGHT) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player2) {
						tempObject.setVelAngle(3);
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
					if (tempObject.getId() == ID.Player) {
						tempObject.setVelY(2);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.S) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player) {
						tempObject.setVelY(-2);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.A) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player) {
						tempObject.setVelAngle(0);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.D) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player) {
						tempObject.setVelAngle(0);
						break;
					}
				}
			}
		}

		if (event.getEventType() == KeyEvent.KEY_RELEASED) {
			// Player 2 actions when key released
			if (event.getCode() == KeyCode.DOWN) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player2) {
						tempObject.setVelY(-2);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.LEFT) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player2) {
						tempObject.setVelAngle(0);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.RIGHT) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player2) {
						tempObject.setVelAngle(0);
						break;
					}
				}
			}
			if (event.getCode() == KeyCode.UP) {
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player2) {
						tempObject.setVelY(2);
						break;
					}
				}
			}
		}
	}
}