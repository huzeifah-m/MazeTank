package Networking;

import GameMain.Game;
import javafx.geometry.Rectangle2D;

//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Rectangle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CellVertWall extends GameObject {
	/**
	 * @param x
	 *            is the starting X coord of the wall.
	 * @param y
	 *            is the starting Y coord of the wall.
	 * @param id
	 *            is the ID given by the client.
	 */

	public CellVertWall(int x, int y, int id, int angle) {
		super(x,y,id,angle);
	}

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x, y, NetGame.sizeY * NetGame.cellSize,5);
	}



	@Override
	public void render(GraphicsContext g) {

		g.setFill(Color.BLACK);
		g.fillRect(x, y, 5, NetGame.cellSize);
	}

	@Override
	public void tick() {
	}

}