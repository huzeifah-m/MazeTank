package Networking;

public class ConcreteNetGameObject {

	protected double x;
	protected double y;
	protected int id;
	protected int velX, velAngle;
	double velY;
	protected int bounces;


	public ConcreteNetGameObject(double x, double y, int id){
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public int getBounces() {
		return bounces;
	}

	public void setBounces(int bounces) {
		this.bounces = bounces;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double d) {
		this.velY = d;
	}

	public double getVelAngle() {
		return velAngle;
	}

	public void setVelAngle(int velAngle) {
		this.velAngle = velAngle;
	}


}
