package GameMain;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import GameMenu.Sound;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

/** 
 * AI Tank extending GameObject for player to play against. AI
 * makes use of the path finding algorithm to draw and follow path
 * towards player tank
 */
public class PlayerAi extends GameObject {

    Handler handler; // list of all game objects in game

    private static Image tank = null; // PNG image of tank to render in game

    static double recX = (GameAi.sizeX * GameAi.cellSize) - GameAi.cellSize / 2 - 10; // x-coordinate
                                                                                        // of
                                                                                        // where
                                                                                        // the
                                                                                        // bullets
                                                                                        // come
                                                                                        // from
    static double recY = (GameAi.sizeY * GameAi.cellSize) - GameAi.cellSize / 2 - 16; // y-coordinate
                                                                                        // of
                                                                                        // where
                                                                                        // the
                                                                                        // bullets
                                                                                        // come
                                                                                        // from
    public static int angle = 90; // current angle of the tank
    double velocityX = 0; // velocity of tank in x-axis
    double velocityY = 0; // velocity of tank in y-axis
    double velF = 0; // velocity value used for acceleration and deceleration
    double accel = 0.1; // acceleration value
    double deccel = 0.2; // deceleration value
    
    public static int speed;

    Map map; // array implementation of maze
    PathFinder algorithm; // algorithm and methods for path finding
    LinkedList<Point2D> path; // linked list of points used to get to other
                                // player
    Player targetPlayer; // Player instance of human player

    int nextInPath = 1; // integer used to get next point to go to in path
    boolean angleSet = false; // boolean used to say whether angle is set to go
                                // to next point in path
    boolean straightShotAvailable = false;
    static boolean shotTaken = false;
    boolean shooting = false;

    /**
     * Constructor finds human player in list of game objects also sets path
     * from bottom left of map (where AI is) to the top right of map (where
     * human player is)
     *
     * @param x
     *            the x-coordinate of the tank where it will be positioned at
     *            start of game
     * @param y
     *            the y-coordinate of the tank where it will be positioned at
     *            start of game
     * @param id
     *            the ID of the AI tank
     * @param handler
     *            the list which holds all of the game objects
     * @param map
     *            the array-based implementation of the maze used for path
     *            finding
     * @param algorithm
     *            the algorithm class to develop a path to the other player
     */
    public PlayerAi(int x, int y, ID id, Handler handler, Map map, PathFinder algorithm) {
        super(x, y, id);
        this.handler = handler;
        for (GameObject tempObject : handler.object) {
            if (tempObject.getId() == ID.Player)
                targetPlayer = (Player) tempObject;
        }
        recX = this.x;
        recY = this.y;
        this.map = map;
        this.algorithm = algorithm;
        path = algorithm.findPath(map.getPoints()[3][3], map.getPoints()[handler.player1x][handler.player1y]);
        this.speed = 3;
    }

    // ------------------------------ Public Methods
    // ------------------------------ //

    /*
     * (non-Javadoc)
     * 
     * @see GameMain.GameObject#getBounds()
     */
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, 32, 32);
    }

    /**
     * Returns bounds of tank slightly in front of direction it is moving in
     * 
     * @return rectangle bounds of the tank
     */
    public Rectangle2D getOffsetBounds() {
        return new Rectangle2D(x + (int) velocityX, y + (int) velocityY, 32, 32);
    }

    /*
     * (non-Javadoc)
     * 
     * @see GameMain.GameObject#tick()
     */
    @Override
    public void tick() {
        angle = (angle + velAngle);
        System.err.println("ANGLE: " + angle);

       if(findTargetAngle() instanceof Integer) {
			if(!shotTaken) {
				this.setVelY(0);
				System.err.println((int)findTargetAngle());
				handler.addObject(new Projectile(this.recX,this.recY,(int)findTargetAngle(),handler,ID.ProjectileAi));
				shotTaken = true;
			}
		}
        movement();
        acceleration();

        velocityX = Math.cos(Math.toRadians(angle)) * velY;
        velocityY = Math.sin(Math.toRadians(angle)) * velY;

        collision();

        x += velocityX;
        y += velocityY;

        recX += velocityX;
        recY += velocityY;
        setRecWithAngle();
    }

    /**
     * This methods makes the tank accelerate or decelerate when starting
     * movement of stopping movement
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
     * This method calculates whether the tank has collided into a wall or into
     * a bullet by using getBounds() or getOffsetBounds()
     */
    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Projectile) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.playerAiCollision = true;
                    tank = new Image(getClass().getResource("tankD.png").toString());
                    Sound.explode();    
                    Sound.muteFire();
                }
            }
            if (tempObject.getId() == ID.VertWall) {
                if (getOffsetBounds().intersects(tempObject.getBounds())) {
                    velocityX = 0;
                    if (getOffsetBounds().getMaxY() > tempObject.getY()) {
                        velocityY = 0;
                    }
                }
            }
            if (tempObject.getId() == ID.HoriWall) {
                if (getOffsetBounds().intersects(tempObject.getBounds())) {
                    velocityY = 0;
                    if (getOffsetBounds().getMaxX() > tempObject.getX()) {
                        velocityX = 0;
                    }
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see GameMain.GameObject#render(javafx.scene.canvas.GraphicsContext)
     */
    @Override
    public void render(GraphicsContext g) {
        if (tank == null)
            tank = new Image(getClass().getResource("tankB.png").toString());
        g.save();
        rotate(g, angle - 90, x + 16, y + 16);
        g.drawImage(tank, x, y);
        g.restore();
        g.setStroke(Color.BLUE);
    }

    public double distance() {
        return Math.sqrt((PlayerAi.recX() - Player.recX()) * (PlayerAi.recX() - Player.recX())
                + (PlayerAi.recY() - Player.recY()) * (PlayerAi.recY() - Player.recY()));
    }

    public static double recX() {
        return recX;
    }

    public static double recY() {
        return recY;
    }

    public static int getAngle() {
        return angle;
    }

    // ------------------------------ Private Methods ------------------------------ //

    /**
     * This methods rotates the tank by the angle is has through rotating the
     * parent canvas
     * 
     * @param gc
     * @param angle
     * @param px
     * @param py
     */
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /**
     * This method sets the rotation of the tank to a given point
     * 
     * @param pointToRotateTo
     *            the point to which the tank needs to face
     * @param pointComingFrom
     *            the point from which the tank is coming from, used to decide
     *            whether to turn left or right
     */
    private void setRotateToPoint(Point2D pointToRotateTo, Point2D pointComingFrom) {
        if (!pointingToPoint(pointToRotateTo)) {
        	int playerAiAngle = PlayerAi.getAngle();
			if (playerAiAngle > 360) 
				playerAiAngle %= 360;
			if (playerAiAngle < 0)
				playerAiAngle += 360;
            angleSet = false;
            if ((pointToRotateTo.getX() < pointComingFrom.getX())
                    && (pointToRotateTo.getY() == pointComingFrom.getY()) && playerAiAngle <= 180) {
                this.setVelAngle(-speed/2);
            } else if ((pointToRotateTo.getX() < pointComingFrom.getX())
                    && (pointToRotateTo.getY() == pointComingFrom.getY()) && playerAiAngle > 180) {
                this.setVelAngle(speed/2);
            } else if ((pointToRotateTo.getX() > pointComingFrom.getX())
                    && (pointToRotateTo.getY() == pointComingFrom.getY()) && playerAiAngle <= 180) {
                this.setVelAngle(speed/2);
            } else if ((pointToRotateTo.getX() > pointComingFrom.getX())
                    && (pointToRotateTo.getY() == pointComingFrom.getY()) && playerAiAngle > 180) {
                this.setVelAngle(-speed/2);
            } else if ((pointToRotateTo.getY() < pointComingFrom.getY())
                    && (pointToRotateTo.getX() == pointComingFrom.getX()) && (playerAiAngle <= 90 || playerAiAngle > 270)) {
                this.setVelAngle(speed/2);
            } else if ((pointToRotateTo.getY() < pointComingFrom.getY())
                    && (pointToRotateTo.getX() == pointComingFrom.getX()) && (playerAiAngle > 90 && playerAiAngle <= 270)) {
                this.setVelAngle(-speed/2);
            } else if ((pointToRotateTo.getY() > pointComingFrom.getY())
                    && (pointToRotateTo.getX() == pointComingFrom.getX()) && (playerAiAngle <= 90 || playerAiAngle > 270)) {
                this.setVelAngle(-speed/2);
            } else if ((pointToRotateTo.getY() > pointComingFrom.getY())
                    && (pointToRotateTo.getX() == pointComingFrom.getX()) && (playerAiAngle > 90 && playerAiAngle <= 270)) {
                this.setVelAngle(speed/2);
            }
        } else {
            angleSet = true;
            this.setVelAngle(0);
        }
    }

    /**
     * This method tells us if the tank has reached a specified point in the
     * map/path
     * 
     * @param pointToReach
     *            the point to which the tank needs to get to
     * @return a boolean value returning true if the tank is at the point, false
     *         otherwise
     */
    private boolean reachedPoint(Point2D pointToReach) {
        if (getBounds().contains(pointToReach))
            return true;
        else
            return false;
    }

    /**
     * This method is responsible for getting the next point to go to in the
     * path, making the tank face that point and then moving to the point. Once
     * it has reached the point it moves on to the next point in the path
     */
    private void movement() {
        if (handler.player1CellMove) {
            this.setVelY(0);
            int aiX = (int) this.getX() / 160, aiY = (int) this.getY() / 160;
            path = algorithm.findPath(map.getPoints()[aiX][aiY], map.getPoints()[handler.player1x][handler.player1y]);
            path.addFirst(new Point2D(this.recX(),this.recY()));
            path.addLast(new Point2D(playerCoordinates()[0],playerCoordinates()[1]));
            nextInPath = 3;
            handler.player1CellMove = false;
            angleSet = false;
        }
        Point2D rec = new Point2D(this.recX, this.recY);
        Point2D pointToMoveTo = path.get(nextInPath);
        Point2D pointComingFrom = path.get(nextInPath -1);
        if (nextInPath < path.size() ) {
            if (!angleSet) {
                this.setVelY(0);
                //System.err.println("Angle not set. Setting now...");
                setRotateToPoint(pointToMoveTo, pointComingFrom);
            }
            if (angleSet) {
                //System.err.println("Angle now set. Currently moving...");
                this.setVelY(-speed);
            }
            if (reachedPoint(pointToMoveTo)) {
                //System.err.println("Reached point. Now setting for new point...");
                if (nextInPath == path.size() - 1)
                    this.setVelY(0);
                else
                    this.setVelY(0);
                this.nextInPath++;
                this.angleSet = false;
            }
        }
    }

    /**
     * This method tells us if the tank is pointing towards a given point in the
     * map/path
     * 
     * @param point
     *            the point to which the tank must face
     * @return a boolean value returning true if the tank is facing the point,
     *         false otherwise
     */
    private boolean pointingToPoint(Point2D point) {
        Point2D recPoint = new Point2D(recX + 5 * Math.cos(Math.toRadians(angle - 180)),
                recY + 5 * Math.sin(Math.toRadians(angle - 180)));
        Line line = new Line(point.getX(), point.getY(), recX, recY);
        if (line.contains(recPoint))
            return true;
        else
            return false;
    }

    /**
     * This methods sets the recX and recY values so that they are in front of
     * the tanks turret as it changes angle
     */
    private void setRecWithAngle() {
        double angle = Math.toRadians(velAngle);
        recX = Math.cos(angle) * (recX - (x + 16)) - Math.sin(angle) * (recY - (y + 16)) + (x + 16);
        recY = Math.sin(angle) * (recX - (x + 16)) + Math.cos(angle) * (recY - (y + 16)) + (y + 16);
    }

    public static void resetTank() {
        tank = null;

    }

    /**
     * Checks to see if there is a straight shot available from the AI to the
     * player
     * 
     * @return a boolean value true if there is a straight shot, false otherwise
     */
    private boolean isStraightShot() {
        double targetX = 0, targetY = 0;
        for (GameObject object : handler.object) {
            if (object instanceof Player) {
                targetX = object.getX();
                targetY = object.getY();
                break;
            }
        }
        Line line = new Line(targetX, targetY, this.recX(), this.recY());
        for (GameObject object : handler.object) {
            if (object.getId() == ID.EastWall || object.getId() == ID.HoriWall || object.getId() == ID.SouthWall
                    || object.getId() == ID.VertWall) {
                if (line.getBoundsInParent().intersects(object.getX(), object.getY(), object.getBounds().getWidth(),
                        object.getBounds().getHeight())) {
                    this.straightShotAvailable = false;
                    return false;
                }
            }
        }
        this.straightShotAvailable = true;
        return true;
    }
    
    /**
	 * This methods searches through the handler list of objects and gets the x and y coordinates of the player
	 * @return an integer array with the x and y coordinates of the player
	 */
	private double[] playerCoordinates() {
		double targetX = 0, targetY = 0;
		for(GameObject object : handler.object) {
			if(object instanceof Player) {
				targetX = object.getX();
				targetY = object.getY();
				break;
			}
		}
		return new double[]{targetX,targetY};
	}
	
	/**
	 * Helper method used in finding shooting angle for AI.
	 * Keeps adding to a line until it hits a player, at which point it returns true,
	 * or hits a wall at which point it calls the method on itself but with the start of the line bouncing off the wall
	 * @param startX the starting x coordinate of the line
	 * @param startY the starting y coordinate of the line
	 * @param angle the angle of the tank at which it shoots
	 * @param directionX a variable used when the line hits a wall, as in the projectile class
	 * @param directionY a variable used when the line hits a wall, as in the projectile class
	 * @param bounces the number of bounces allowed before the mimicked projectile must disappear
	 * @return a boolean value if the line hits the player, false if it doesn't (i.e. if bounces is 0)
	 */
	private boolean addLine(double startX, double startY, double angle, int directionX, int directionY, int bounces) {
		if(bounces == 0) return false;
		Line line = new Line(startX,startY,startX+1,startY+1);//startX+1*Math.cos(Math.toRadians(angle-180)),startY+1*Math.sin(Math.toRadians(angle-180)));
		outerloop: for(int i = 1; i < 640; i++) {
			line.setEndX(line.getEndX()+i/2*Math.cos(Math.toRadians(angle-180)));
			line.setEndY(line.getEndY()+i/2*Math.sin(Math.toRadians(angle-180)));
			if(line.intersects(playerCoordinates()[0],playerCoordinates()[1],32,32)) {
				break outerloop;
			}
			for(GameObject wall : handler.object) {
				if(line.intersects(wall.getX(),wall.getY(),wall.getBounds().getWidth(),wall.getBounds().getHeight())) {
					if(wall.getId() == ID.HoriWall || wall.getId() == ID.SouthWall) {
						double x = -1*Math.cos(Math.toRadians(angle)*directionX);
						double y = -1*Math.sin(Math.toRadians(angle)*-directionY);
						return addLine(x,y,angle,directionX,-directionY,bounces-1);
					}
					if(wall.getId() == ID.VertWall || wall.getId() == ID.EastWall) {
						double x = -1*Math.cos(Math.toRadians(angle)*-directionX);
						double y = -1*Math.sin(Math.toRadians(angle)*directionY);
						return addLine(x,y,angle,-directionX,directionY,bounces-1);
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * This method checks all angles of the tank and sees if there is a shot which will hit the player
	 * @return an object value: integer if there is an angle, null if not
	 */
	public Object findTargetAngle() {
		for(int i = 0; i < 360; i++) {
			int playerAiAngle = PlayerAi.getAngle() + i;
			if (playerAiAngle > 360) 
				playerAiAngle %= 360;
			if (playerAiAngle < 0)
				playerAiAngle += 360;
			if(addLine(PlayerAi.recX,PlayerAi.recY,playerAiAngle,1,1,2) == true)
				return playerAiAngle;
		}
		return null;
	}
}