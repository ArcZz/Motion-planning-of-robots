/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author zhangzhi
 */
public class Obstacle extends Nodes {

    //public int x;
    //public int y;
    //public boolean pass = false;
    //already fields of parent class

    public int speed;
    
    public int xDirection;
    public int yDirection;
    
    public int gridWidth;
    
    public int distanceToWall;
    public int distanceToZero;
    public int wallHits;
    
    private int lastX;
    private int lastY;
    private int lastXDirection;
    private int lastYDirection;

    public Obstacle(int x, int y, int speed, int xDirection, int yDirection, int gridWidth) {
        super(x, y);
        this.speed = speed;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.gridWidth = gridWidth;
        this.lastX = x;
        this.lastY = y;
        this.lastXDirection = xDirection;
        this.lastYDirection = yDirection;
    }
    public void hitwall(int x, int y){
        x++;
    }
    
    public void moveOnce() {
        //get new x-coordinate and direction
        lastX = x;
        lastY = y;
        lastXDirection = xDirection;
        lastYDirection = yDirection;
        
        if (xDirection == -1) {
            distanceToWall = distanceToZero = x;
            if (speed < distanceToWall) {
                x = Math.abs(speed - distanceToZero);
            } else {
                if ((speed - distanceToWall) >= 0) {
                    x = (speed - distanceToWall) % (gridWidth);
                } else {    //Java rounds towards zero for modulus operator
                    x = ((speed - distanceToWall) % (gridWidth) + gridWidth) % gridWidth;
                }
            }
        } else if (xDirection == 1) {    //xDirection == 1
            distanceToWall = gridWidth - 1 - x;
            distanceToZero = 2 * gridWidth - 2 - x;
            if (speed >= distanceToWall) {
                x = Math.abs(speed - distanceToZero);
            } else {
                x += speed;
                /*
                if ((speed - distanceToWall) >= 0) {
                    x = (speed - distanceToWall) % (gridWidth);
                } else {    //Java rounds towards zero for modulus operator with negative numbers
                    x = ((speed - distanceToWall) % (gridWidth) + gridWidth) % gridWidth;
                }
                */
            }
        }
        
        wallHits = 0;
        if (speed >= distanceToWall) {
            wallHits = (speed + lastX) / gridWidth;
            if (xDirection == -1) {
                ++wallHits;
            }
            if ((wallHits % 2) == 1) {
                xDirection *= -1;
            }
        }
        
        //get new y-coordinate and direction
        
        if (yDirection == -1) {
            distanceToWall = distanceToZero = y;
            if (speed < distanceToWall) {
                y = Math.abs(speed - distanceToZero);
            } else {
                if ((speed - distanceToWall) >= 0) {
                    y = (speed - distanceToWall) % (gridWidth);
                } else {    //Java rounds towards zero for modulus operator
                    y = ((speed - distanceToWall) % (gridWidth) + gridWidth) % gridWidth;
                }
            }
        } else if (yDirection == 1) {    //xDirection == 1
            distanceToWall = gridWidth - 1 - y;
            distanceToZero = 2 * gridWidth - 2 - y;
            if (speed >= distanceToWall) {
                y = Math.abs(speed - distanceToZero);
            } else {
                y += speed;
                /*
                if ((speed - distanceToWall) >= 0) {
                    y = (speed - distanceToWall) % (gridWidth);
                } else {    //Java rounds towards zero for modulus operator with negative numbers
                    y = ((speed - distanceToWall) % (gridWidth) + gridWidth) % gridWidth;
                }
                */
            }
        }
        
        wallHits = 0;
        if (speed >= distanceToWall) {
            wallHits = (speed + lastY) / gridWidth;
            if (yDirection == -1) {
                ++wallHits;
            }
            if ((wallHits % 2) == 1) {
                yDirection *= -1;
            }
        }
        
       // System.out.println("New (x,y) " + x + " " + y);
       // System.out.println("New dir " + xDirection + " " + yDirection);
    }
    
    public void unPass(Nodes node){
        node.pass = false;
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void moveBack() {
        x = lastX;
        y = lastY;
        xDirection = lastXDirection;
        yDirection = lastYDirection;
    }
}
