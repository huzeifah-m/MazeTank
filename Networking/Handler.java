package Networking;

import javafx.scene.canvas.GraphicsContext;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	boolean player1Collision = false, player2Collision = false;

	/**
	 * Constantly iterates through the list of objects and updates them
	 * constantly.
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}

	public void render(GraphicsContext g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void setObject(LinkedList<GameObject> object) {
		this.object = object;
	}

	public void removeAll() {
		this.object.clear();
	}
}