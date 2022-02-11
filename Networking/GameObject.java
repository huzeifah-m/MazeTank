package Networking;

import javafx.geometry.Rectangle2D;

//import java.awt.Graphics;
//import java.awt.Rectangle;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {

	protected double x;
	protected double y;
	protected int id;
	protected int velX, velAngle;
	double velY;
	protected int bounces;
	protected int angle;


	public GameObject(double x, double y, int id, int angle){
		this.x = x;
		this.y = y;
		this.id = id;
		this.angle = angle;
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

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
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
		return velY;
	}

	public void setVelAngle(int velAngle) {
		this.velAngle = velAngle;
	}

	public void setGAngle(int angle){
		this.angle = angle;
	}
	public int getGAngle(){
		return angle;
	}
	public abstract void tick();
	public abstract void render(GraphicsContext g);
	public abstract Rectangle2D getBounds();


}
