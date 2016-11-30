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
    public int ox;
    public int oy;
    public Nodes startNode;
    public Nodes endNode;
     public Nodes parent;
    public int[][] maps;
    private Color lightColor;
    private Color darkColor;
    public ArrayList<Nodes> walkList;

    public maze(int numRows, int startx, int starty, int endx, int endy, int ox, int oy, int boardWidth, int boardHeight) {
        this.numRows = numRows;
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
        this.ox = ox;
        this.oy = oy;
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
        
        recs[startx][starty].setFill(Color.RED);
        recs[endx][endy].setFill(Color.GREENYELLOW);
        recs[ox][oy].setFill(Color.BLUE);
        startNode = new Nodes(startx, starty);  
        endNode = new Nodes(endx, endy);  
        parent = new AStar().findPath(numRows,startNode, endNode); 
      
        
        walkList = new ArrayList<Nodes>();
        while (parent != null) {  
           // changeColor(parent.x,parent.y);
            System.out.println(parent.x + ", " + parent.y);  
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
    public void backobstacleColor(int x, int y){
        recs[x][y].setFill(lightColor);
    }
    public void changeobstacleColor(int x, int y){
        recs[x][y].setFill(Color.BLUE);
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