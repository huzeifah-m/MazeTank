package GameMain;

import javafx.geometry.Rectangle2D;

//import java.awt.Graphics;
//import java.awt.Rectangle;

import javafx.scene.canvas.GraphicsContext;

/**
 * Class that contains the required parameters for classes that extends GameObject
 */
public abstract class GameObject {

	protected double x;
	protected double y;
	protected ID id;
	protected int velX, velAngle;
	double velY;
	protected int bounces;

	/**
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param id id of object
	 */
	public GameObject(double x, double y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}

	/**
	 * Gets the number of times a projectile has bounced off a wall
	 * @return number of bounces
	 */
	public int getBounces() {
		return bounces;
	}

	/**
	 * Sets the number of times a projectiles has bounced off wall. If greater than 2, projectile will disappear
	 * @param bounces bounces to set
	 */
	public void setBounces(int bounces) {
		this.bounces = bounces;
	}

	/**
	 * Gets the x-position of the object
	 * @return x-coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the x-coordinate of the object
	 * @param x x-coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y-position of the object
	 * @return y-coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the y-coordinate of the object
	 * @param y y-coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the id of the object
	 * @return id of object
	 */
	public ID getId() {
		return id;
	}

	/**
	 * Sets the id of the object
	 * @param id id of the object
	 */
	public void setId(ID id) {
		this.id = id;
	}

	/**
	 * Gets the velocity in x direction of the object
	 * @return velocity in x direction
	 */
	public int getVelX() {
		return velX;
	}

	/**
	 * Sets the velocity of object in x direction
	 * @param velX velocity in x direction
	 */
	public void setVelX(int velX) {
		this.velX = velX;
	}

	/**
	 * Gets the velocity in y direction of the object
	 * @return velocity in y direction
	 */
	public double getVelY() {
		return velY;
	}

	/**
	 * Sets the velocity of object in y direction
	 * @param d velocity in y direction
	 */
	public void setVelY(double d) {
		this.velY = d;
	}

	/**
	 * Gets the angle of the velocity of the object
	 * @return angle of velocity
	 */
	public double getVelAngle() {
		return velAngle;
	}

	/**
	 * @param velAngle
	 */
	public void setVelAngle(int velAngle) {
		this.velAngle = velAngle;
	}

	/**
	 * Method to make the handler tick
	 */
	public abstract void tick();

	/**
	 * Renders object g
	 * @param g
	 */
	public abstract void render(GraphicsContext g);

	/**
	 * Gets the bounds of a rectangle
	 * @return bounds of rectangle
	 */
	public abstract Rectangle2D getBounds();


}
