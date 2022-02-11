package GameMain;

import GameMenu.Sound;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Handles all the keyboard inputs for the Ai game mode
 *
 */
public class KeyInputHandlerAi implements EventHandler<KeyEvent> {

	private Handler handler;
	public static final long FIRE_RATE = 1300000000L;
	public long lastShot;

	/**Constructor
	 * Takes in handler so it can loop through game objects
	 * @param handler
	 */
	public KeyInputHandlerAi(Handler handler) {
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
			if ((event.getCode() == KeyCode.SPACE) & (System.nanoTime() - lastShot >= FIRE_RATE)) {
				lastShot = System.nanoTime();
				for (GameObject tempObject : handler.object) {
					if (tempObject.getId() == ID.Player) {
						Sound.fire();
						handler.addObject(new Projectile(Player.recX() + 12, Player.recY() + 12, Player.getAngle(), handler,
								ID.Projectile));
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
	}
}