package GameMain;

import GameMenu.Sound;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
//import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 * Player 2 Tank extending GameObject. Contains all parameters for player 2 tank.
 */
public class Player2 extends GameObject {

    Handler handler;

    private static Image tank = null;

    static double recX = (Game.sizeX * Game.cellSize) - Game.cellSize/2-10;
    static double recY = (Game.sizeY * Game.cellSize) - Game.cellSize/2-16;
    public static int angle = 90;
    double velocityX = 0;
    double velocityY = 0;
    double velF = 0;
    double accel = 0.1;
    double deccel = 0.2;
    /**
     * Constructor takes in x, y, id, handler
     * @param x x-coordinate
     * @param y y-coordinate
     * @param id id of player
     * @param handler
     */
    public Player2(int x, int y, ID id, Handler handler) {
        super(x, y,id);
        this.handler = handler;
        recX = this.x;
        recY = this.y;
    }

    /* (non-Javadoc)
     * @see GameMain.GameObject#getBounds()
     */
    @Override
    public Rectangle2D getBounds(){
        return new Rectangle2D(x,y,32,32);
    }

    /**
     * Gets bounds of edges of tank
     * @return
     */
    public Rectangle2D getOffsetBounds() {
        return new Rectangle2D(x+(int)velocityX,y+(int)velocityY,32,32);
    }

    /* (non-Javadoc)
     * @see GameMain.GameObject#tick()
     */
    @Override
    public void tick() {
        angle = (angle + velAngle);

        acceleration();

        velocityX = Math.cos(Math.toRadians(angle))*velF;
        velocityY = Math.sin(Math.toRadians(angle))*velF;


        collision();

        x += velocityX;
        y += velocityY;

        recX += velocityX;
        recY += velocityY;

    //    recX = Game.clamp(recX, 0, Game.WIDTH -35);
    //    recY = Game.clamp(recY, 0, Game.HEIGHT -35);
    //    x = Game.clamp(x, 0, Game.WIDTH -35);
    //    y = Game.clamp(y, 0, Game.HEIGHT -35);
    }


    /**
     * Method that calculates acceleration and decelaeration of tank
     */
    public void acceleration(){
        if (velY == 3 & velF>-3) velF -= accel;
        if (velY == -3 & velF<3) velF += accel;
        if (velY == 2 & velF<0){
            velF += deccel;
            if(velF >-deccel) velF = 0;
        }
        if (velY == -2 & velF>0){
            velF -= deccel;
            if(velF < deccel) velF = 0;
        }

    }

    /**
     * Method that check collision between tank and walls, projectiles
     */
    public void collision() {

        for(int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Projectile) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    handler.player2Collision = true;
                    tank = new Image(getClass().getResource("tankD.png").toString());
                    Sound.explode();
                    Sound.muteFire();

                }
            }
            if (tempObject.getId() == ID.VertWall) {
                if(getOffsetBounds().intersects(tempObject.getBounds())) {
                    velocityX = 0;
                    if(getOffsetBounds().getMaxY() > tempObject.getY()) {
                        velocityY = 0;
                    }
                }
            }
            if (tempObject.getId() == ID.HoriWall) {
                if(getOffsetBounds().intersects(tempObject.getBounds())) {
                    velocityY = 0;
                    if(getOffsetBounds().getMaxX() > tempObject.getX()) {
                        velocityX = 0;
                    }
                }
            }
        }
    }

    public static void resetTank() {
        tank = null;
    }

    /**
     * Method that rotates the tank
     * @param gc
     * @param angle
     * @param px
     * @param py
     */
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /* (non-Javadoc)
     * @see GameMain.GameObject#render(javafx.scene.canvas.GraphicsContext)
     */
    @Override
    public void render(GraphicsContext g) {
        if (tank == null)
            tank = new Image(getClass().getResource("tankR.png").toString());
        g.save();
        rotate(g, angle-90, x+16 ,y+16);
        g.drawImage(tank, x, y);
        g.restore();
    }

    /**
     * x-coordinate of rectangle
     * @return x-coordinate
     */
    public static double recX(){
        return recX;
    }

    /**
     * y-coordinate of rectangle
     * @return y-coordinate
     */
    public static double recY(){
        return recY;
    }

    /**
     * angle of tank
     * @return angle of tank
     */
    public static int getAngle()
    {
        return angle;
    }

}

