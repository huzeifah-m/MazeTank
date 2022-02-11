package Networking;

import GameMenu.Sound;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
//import javafx.scene.paint.Color;

/**
 * @author Creating a player uses this object.
 *
 */
public class Player extends GameObject {

	Handler handler;
	static double recX = 10;
	static double recY = 10;
	private Image tank = null;

	double velocityX = 0;
	double velocityY = 0;
	double velF = 0;
	double accel = 0.1;
	double deccel = 0.2;
	double startX;
	double startY;
	int player1Score = 0;
	int player2Score = 0;

	/**
	 * @param x
	 *            is the starting X position of the player.
	 * @param y
	 *            is the starting Y position of the player.
	 * @param id
	 *            is the integer ID of the player.
	 * @param angle
	 *            is the angle of the player.
	 * @param handler
	 *            is the handler given by the client to manage the player.
	 */
	public Player(double x, double y, int id, int angle, Handler handler) {
		super(x, y, id, angle);
		this.handler = handler;
		recX = this.x;
		recY = this.y;
		this.id = id;
		this.startX = x;
		this.startY = y;
	}

	/**
	 * @return a rectangle hit box around the tank.
	 */
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x, y, 32, 32);
	}

	/**
	 * @return a rectangle hitbox slightly off set around the tank.
	 */
	public Rectangle2D getOffsetBounds() {
		return new Rectangle2D(x + (int) velocityX, y + (int) velocityY, 32, 32);
	}

	/**
	 * This is the main method for the player that iterates every tick it
	 * calculates the needed velocities for the player based on the angle that
	 * the tank is at. It also calculates the acceleration for the tank.
	 */
	@Override
	public void tick() {
		angle = (angle + velAngle);

		acceleration();

		velocityX = Math.cos(Math.toRadians(angle)) * velF;
		velocityY = Math.sin(Math.toRadians(angle)) * velF;

		collision();

		x += velocityX;
		y += velocityY;

		recX += velocityX;
		recY += velocityY;

	}

	/**
	 * This method takes the value given to it by the KeyInput class and changes
	 * the acceleration and deceleration accordingly.
	 */
	public void acceleration() {
		if (velY == 3 & velF > -3)
			velF -= accel;
		if (velY == -3 & velF < 3)
			velF += accel;
		if (velY == 2 & velF < 0) {
			velF += deccel;
			if (velF > -deccel)
				velF = 0;
		}
		if (velY == -2 & velF > 0) {
			velF -= deccel;
			if (velF < deccel)
				velF = 0;
		}
	}

	/**
	 * The handler checks if the player is colliding with any of the other
	 * objects and reacts accordingly by changing velocity or destroying the
	 * tank.
	 */
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() > 104 && (tempObject.getId() < id * 100 || tempObject.getId() > id * 100 + 100)) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (this.getId() == 1) {

					}
					if (this.getId() == 2) {

					}
					this.setX(startX);
					this.setY(startY);
					System.out.println("HIT");
					Sound.explode();

				}
			}

			if (tempObject.getId() == 101) {
				if (getOffsetBounds().intersects(tempObject.getBounds())) {
					velocityX = 0;

				}
			}
			if (tempObject.getId() == 104) {
				if (getOffsetBounds().intersects(tempObject.getBounds())) {
					velocityX = 0;

				}
			}
			if (tempObject.getId() == 102) {
				if (getOffsetBounds().intersects(tempObject.getBounds())) {
					velocityY = 0;

				}
			}
			if (tempObject.getId() == 103) {
				if (getOffsetBounds().intersects(tempObject.getBounds())) {
					velocityY = 0;
				}
			}
		}
	}

	private void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

	/**
	 * The image used for the tank is changed according to the ID given by the client.
	 */
	@Override
	public void render(GraphicsContext g) {
		if (tank == null) {
			if (id == 1) {
				tank = new Image(getClass().getResource("tankG.png").toString());
			}
			if (id == 2) {
				tank = new Image(getClass().getResource("tankR.png").toString());
			}
			if (id == 3) {
				tank = new Image(getClass().getResource("tankB.png").toString());
			}
			if (id > 3) {
				tank = new Image(getClass().getResource("tankD.png").toString());
			}

		}
		g.save();
		rotate(g, this.getGAngle() - 90, x + 16, y + 16);
		g.drawImage(tank, x, y);
		g.restore();
	}

	public static double recX() {
		return recX;
	}

	public static double recY() {
		return recY;
	}

	public int getAngle() {
		return angle;
	}
}