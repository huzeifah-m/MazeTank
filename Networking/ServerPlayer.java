package Networking;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ServerPlayer  {

	private int playerID;
	private int x;
	private int y;
	private int velX;
	private int velY;

	public ServerPlayer(int x, int y, int ID) {
		this.playerID = ID;
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getVelX() {
		return velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setID(int playerID) {
		this.playerID = playerID;
	}
	
	public int getID() {
		return playerID;
	}

}