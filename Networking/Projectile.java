package Networking;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Projectile extends GameObject {

	Handler handler;

	private Image projectile = null;

	double velocityX = 0;
	double velocityY = 0;
	int angle;
	int directionY = 1;
	int directionX = 1;

	int hits = 0;
	static int bounces = 0;
	/**
	 * @param d
	 *            is the starting X coord
	 * @param e
	 *            is the starting Y coord
	 * @param angle
	 *            is the angle the projectile will be fired.
	 * @param handler
	 *            is the handler given by the client.
	 * @param id
	 *            is the id of the projectile.
	 */
	public Projectile(double x, double y, int id, int angle,int hits, Handler handler) {

		super(x, y, angle, id);
		this.handler = handler;
		this.angle = angle;
		this.id = id;
		this.hits = hits;

	}

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x, y, 8, 8);
	}

	@Override
	public void tick() {
		velocityX = Math.round(((Math.cos(Math.toRadians(angle)) * -8) * directionX) * 1 / 1);
		velocityY = Math.round(((Math.sin(Math.toRadians(angle)) * -8) * directionY) * 1 / 1);
		x += velocityX;
		y += velocityY;


		collision();
	}

	public void collision() {

		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == 105) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(this.getBounces() < 2){ 
						directionY = -directionY;
						this.setBounces(this.getBounces() + 1);
					}
					
					hits++;
				}
			}

			if (tempObject.getId() == 102||tempObject.getId()==103) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(this.getBounces() < 2){ 
						directionY = -directionY;
						this.setBounces(this.getBounces() + 1);
					}
					hits++;
					
				}
			}
			if (tempObject.getId() == 101 || tempObject.getId() == 104) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(this.getBounces() < 2){ 
						directionX = -directionX;
						this.setBounces(this.getBounces() + 1);
					}
					hits++;
				}
			}
			if (tempObject.getId() == 106) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(this.getBounces() < 2){ 
						directionX = -directionX;
						this.setBounces(this.getBounces() + 1);
					}
					hits++;
				}
			}
		}
	}
	@Override
	public void render(GraphicsContext g) {
		if (projectile == null)
			projectile = new Image(getClass().getResource("projectile.png").toString());
		g.save();
		g.drawImage(projectile, x, y);
		g.restore();

	}
}
