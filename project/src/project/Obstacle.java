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

    public Obstacle(int x, int y, int speed, int xDirection, int yDirection, int gridWidth) {
        super(x, y);
        this.speed = speed;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.gridWidth = gridWidth;
    }
    public void hitwall(int x, int y){
        x++;
    }
    
    public void moveOnce() {
        //get new x-coordinate and direction
        
        if (xDirection == -1) {
            distanceToWall = distanceToZero = x;
            if (speed < distanceToWall) {
                x = Math.abs(speed - distanceToZero);
            } else {
                if (speed >= distanceToZero) {
                    x = (speed - distanceToZero) % (gridWidth - 1);
                } else {    //Java rounds towards zero for modulus operator
                    x = (speed - distanceToZero) % (gridWidth - 1) + gridWidth - 1;
                }
            }
        } else {    //xDirection == 1
            distanceToWall = gridWidth - 1 - x;
            distanceToZero = 2 * gridWidth - 2 - x;
            if (speed >= distanceToZero) {
                x = Math.abs(speed - distanceToWall);
            } else {
                if (speed >= distanceToZero) {
                    x = (speed - distanceToZero) % (gridWidth - 1);
                } else {    //Java rounds towards zero for modulus operator
                    x = (speed - distanceToZero) % (gridWidth - 1) + gridWidth - 1;
                }
            }
        }
        
        wallHits = 0;
        if (speed >= distanceToWall) {
            if (speed >= distanceToZero) {
                wallHits = (speed - distanceToWall) % (gridWidth - 1) + 1;
            } else {
                wallHits = (speed - distanceToWall) % (gridWidth - 1) + 1;
            }
            if ((wallHits % 2) == 1) {
                yDirection *= -1;
            }
        }
        
        //get new y-coordinate and direction
        
        if (yDirection == -1) {
            distanceToWall = distanceToZero = y;
            if (speed < distanceToWall) {
                y = Math.abs(speed - distanceToZero);
            } else {
                if (speed >= distanceToZero) {
                    y = (speed - distanceToZero) % (gridWidth - 1);
                } else {    //Java rounds towards zero for modulus operator
                    y = (speed - distanceToZero) % (gridWidth - 1) + gridWidth - 1;
                }
            }
        } else {    //yDirection == 1
            distanceToWall = gridWidth - 1 - y;
            distanceToZero = 2 * gridWidth - 2 - y;
            if (speed >= distanceToZero) {
                y = Math.abs(speed - distanceToWall);
            } else {
                if (speed >= distanceToZero) {
                    y = (speed - distanceToZero) % (gridWidth - 1);
                } else {    //Java rounds towards zero for modulus operator
                    y = (speed - distanceToZero) % (gridWidth - 1) + gridWidth - 1;
                }
            }
        }
        
        wallHits = 0;
        if (speed >= distanceToWall) {
            if (speed >= distanceToZero) {
                wallHits = (speed - distanceToWall) % (gridWidth - 1) + 1;
            } else {
                wallHits = (speed - distanceToWall) % (gridWidth - 1) + 1;
            }
            if ((wallHits % 2) == 1) {
                yDirection *= -1;
            }
        }
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
}
