package GameMain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class that contains the parameters for projectiles
 *
 */
public class Projectile extends GameObject {

	Handler handler;

	private Image projectile = null;

	double velocityX = 0;
	double velocityY = 0;
	int angle;
	int directionY = 1;
	int directionX = 1;
	static int bounces = 0;

	/**
	 * Constructor takes in d, e, angle, handler, id, creates new projectile
	 * @param d x-position of projectile
	 * @param e y-position of projectile
	 * @param angle angle for projectile to be fired at
	 * @param handler
	 * @param id id of projectile
	 */
	public Projectile(double d, double e, int angle, Handler handler, ID id) {

		super(d, e, id);
		this.handler = handler;
		this.angle = angle;

	}

	/* (non-Javadoc)
	 * @see GameMain.GameObject#getBounds()
	 */
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x, y, 8, 8);
	}

	/* (non-Javadoc)
	 * @see GameMain.GameObject#tick()
	 */
	@Override
	public void tick() {
		velocityX = Math.round(((Math.cos(Math.toRadians(angle)) * -8) * directionX) * 1 / 1);
		velocityY = Math.round(((Math.sin(Math.toRadians(angle)) * -8) * directionY) * 1 / 1);
		x += velocityX;
		y += velocityY;

		/*
		 * if (y <= 0 || y >= Game.HEIGHT -35) { directionY = -directionY;
		 * this.setBounces(this.getBounces() + 1); } if (x <= 0 || x >=
		 * Game.WIDTH - 35) { directionX = -directionX;
		 * this.setBounces(this.getBounces() + 1); }
		 */

		collision();
	}

	/**
	 * Method that decides what to do if projectile collides with a wall
	 */
	public void collision() {

		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.HoriWall) {
				if (getBounds().intersects(tempObject.getBounds())) {
					directionY = -directionY;
					this.setBounces(this.getBounces() + 1);
				}
			}

			if (tempObject.getId() == ID.SouthWall) {
				if (getBounds().intersects(tempObject.getBounds())) {
					directionY = -directionY;
					this.setBounces(this.getBounces() + 1);
				}
			}
			if (tempObject.getId() == ID.EastWall) {
				if (getBounds().intersects(tempObject.getBounds())) {
					directionX = -directionX;
					this.setBounces(this.getBounces() + 1);
				}
			}
			if (tempObject.getId() == ID.VertWall) {
				if (getBounds().intersects(tempObject.getBounds())) {
					directionX = -directionX;
					this.setBounces(this.getBounces() + 1);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see GameMain.GameObject#render(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void render(GraphicsContext g) {
		if (projectile == null)
			projectile = new Image(getClass().getResource("projectile.png").toString());
		g.save();
		g.drawImage(projectile, x, y);
		g.restore();

	}
}
