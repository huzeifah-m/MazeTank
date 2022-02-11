package GameMain;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<>();
	boolean player1Collision = false, player2Collision = false, playerAiCollision = false;
	boolean playerAiShot = false;
	boolean player1CellMove = false;
	int player1x = 0, player1y = 0;

	/**
	 * Constantly iterates through the list of objects and updates them
	 * constantly.
	 */

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			// If a collision is found then the outcome is decided.

			if (tempObject.getId() == ID.Projectile || tempObject.getId() == ID.Projectile2
					|| tempObject.getId() == ID.ProjectileAi) {
				if (tempObject.getBounces() > 2) {
					removeObject(tempObject);
					if (tempObject.getId() == ID.ProjectileAi) {
						PlayerAi.shotTaken = false;
					}
				}
			}
			tempObject.tick();
		}
	}

	/**
	 * Renders objects passed through GameObject
	 * @param g
	 */
	public void render(GraphicsContext g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	/**
	 * Adds an object to GameObject
	 * @param object
	 */
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	/**
	 * Removes an object from GameObject
	 * @param object
	 */
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	/**
	 * Removes all objects from GameObject
	 */
	public void removeAll() {
		this.object.clear();
	}
}