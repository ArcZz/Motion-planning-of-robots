/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

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
    private Rectangle rec;
    public int startx;
    public int starty;
    public int endx;
    public int endy;
    private Color lightColor;
    private Color darkColor;    
    public maze(int numRows, int startx, int starty, int endx, int endy, int boardWidth,int boardHeight) {
        this.numRows = numRows;
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.squareHeight = boardHeight/numRows;
        this.lightColor = Color.WHITE;
        this.darkColor = Color.BLACK;
      

   }



    public GridPane build() {
        gridPane = new GridPane();
     
        gridPane.setGridLinesVisible(true);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                rec = new Rectangle(squareHeight, squareHeight);
                rec.setFill(lightColor);
                rec.setStroke(darkColor);
                if((i == startx)&&(j==starty)){
                rec.setFill(Color.RED);
                }
                  if((i == endx)&&(j==endy)){
                rec.setFill(Color.GREENYELLOW);
                }

                gridPane.add(rec, i, j);
            }
        }
        
        return gridPane;
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

    public int getHeight() {
        return boardHeight;
    }

    public double getSquareHeight() {
        return squareHeight;
    }
}


