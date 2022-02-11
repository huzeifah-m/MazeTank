package Networking;
//import java.awt.Color;

//import java.awt.Graphics;
//import java.awt.Rectangle;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CellEastWall extends GameObject {

	/**
	 * @param x
	 *            is the starting X coord of the wall.
	 * @param y
	 *            is the starting Y coord of the wall.
	 * @param id
	 *            is the ID given by the client.
	 */
	public CellEastWall(double x, double y, int id, int angle) {
		super(x, y, id, angle);
	}

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x, y, 5, (NetGame.sizeY * NetGame.cellSize + 10));
	}

	@Override
	public void render(GraphicsContext g) {

		g.setFill(Color.BLACK);
		g.fillRect(x, y, 5, (NetGame.sizeY * NetGame.cellSize + 10));
	}

	@Override
	public void tick() {
	}

}