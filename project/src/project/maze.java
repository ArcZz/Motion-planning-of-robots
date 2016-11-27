/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author zhangzhi
 */
public class maze {

    private final int numRows;

    private final int boardWidth;
    private final int boardHeight;
    private final int squareHeight;

    private GridPane gridPane;
    public Rectangle[][] recs;
    public int startx;
    public int starty;
    public int endx;
    public int endy;
    public int[][] maps;
    private Color lightColor;
    private Color darkColor;
    public ArrayList<Nodes> walkList;

    public maze(int numRows, int startx, int starty, int endx, int endy, int boardWidth, int boardHeight) {
        this.numRows = numRows;
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.squareHeight = boardHeight / numRows;
        this.lightColor = Color.WHITE;
        this.darkColor = Color.BLACK;

    }

    public GridPane build() {
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        recs = new Rectangle[numRows][numRows];
       
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                Rectangle rec = new Rectangle(squareHeight, squareHeight);
                rec.setFill(lightColor);
                rec.setStroke(darkColor);
                recs[i][j] = rec;
                gridPane.add(rec, i, j);
            }
        }
        
      
        Nodes startNode = new Nodes(startx, starty);  
        Nodes endNode = new Nodes(endx, endy);  
        Nodes parent = new AStar().findPath(numRows,startNode, endNode); 
        recs[startx][starty].setFill(Color.RED);
        recs[endx][endy].setFill(Color.GREENYELLOW);
        
        walkList = new ArrayList<Nodes>();
        while (parent != null) {  
           // changeColor(parent.x,parent.y);
           // System.out.println(parent.x + ", " + parent.y);  
            walkList.add(new Nodes(parent.x, parent.y));  
            parent = parent.parent;  
        }  
        return gridPane;
    }
    
    
     public ArrayList<Nodes>  getList() {
        return walkList;
    }
 
    public void changeColor(int x, int y){
        recs[x][y].setFill(Color.CADETBLUE);
    }
            
    public GridPane getBoard() {
        return gridPane;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getWidth() {
        return boardWidth;
    }

    public double getSquareHeight() {
        return squareHeight;
    }
}
