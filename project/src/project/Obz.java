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
public class Obz extends Nodes {

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
    
    public int lastX;
    public int lastY;
    public int lastXDirection;
    public int lastYDirection;

    public Obz(int x, int y, int speed, int xDirection, int yDirection, int gridWidth) {
        super(x, y);
        this.speed = speed;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.gridWidth = gridWidth;
        this.lastX = x;
        this.lastY = y;
    }
//    public boolean hitwall(){
//        
//        
//    }
    
    public void moveOnce() {
        //get new x-coordinate and direction
        lastX = x;
        lastY = y;
        lastXDirection = xDirection;
        lastYDirection = yDirection;
        
        x = x+speed*xDirection;
        y = y+speed*yDirection;
        
        
       
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
    public int moe(int x, int y){
        return (x%y+y)%y;
    
    }
}
