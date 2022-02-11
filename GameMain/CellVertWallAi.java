package GameMain;

import javafx.geometry.Rectangle2D;

//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Rectangle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Huzeifah
 * Class that creates a vertical wall at a given location
 */
public class CellVertWallAi extends GameObject {

	/**
	 * Constructor takes in x coordinate, y coordinate and ID
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param id id of wall
	 */
	public CellVertWallAi(int x, int y, ID id) {
		super(x,y,id);
	}

	/* (non-Javadoc)
	 * @see GameMain.GameObject#getBounds()
	 */
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x,y,5,GameAi.cellSize);
	}


	/* (non-Javadoc)
	 * @see GameMain.GameObject#render(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void render(GraphicsContext g) {

		g.setFill(Color.BLACK);
		g.fillRect(x, y, 5, GameAi.cellSize);
	}

	/* (non-Javadoc)
	 * @see GameMain.GameObject#tick()
	 */
	@Override
	public void tick() {
	}

}