/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author zhangzhi
 */
public class Project extends Application {

    private final int numRows = 400;
    private final int boardWidth = 600;
    private final int boardHeight = 600;
    public maze maze;
    public int startx = 2;
    public int starty = 6;
    public int endx = 15;
    public int endy = 13;
    public int ox = 1;
    public int oy = 3;
    public int step = 0;
    public Timeline time;
    public boolean pass = true;
    public Nodes robot;
    
    public Nodes obstacle;
    public ArrayList<Nodes> walkList;

    @Override
    public void start(Stage primaryStage) {

        maze = new maze(numRows, startx, starty, endx, endy, ox, oy, boardWidth, boardHeight);
        // obstacle = new Obstacle(ox,oy);
        GridPane grid = maze.build();
        Scene scene = new Scene(grid);
        primaryStage.setTitle("Motion planning of robots");
        primaryStage.setScene(scene);
        primaryStage.show();

        walkList = maze.getList();
        step = walkList.size() - 1;
        time = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> update()));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

    }

    public void obstacle() {

    }

    public void reCalcPath() {

    }

    public boolean checkCollision(int x, int ox, int y, int oy) {
        if (x == ox && y == oy) {
            return true;
        } else {
            return false;
        }
    }
    public void move(){
            maze.backobstacleColor(ox, oy);
            ox++;
            maze.changeobstacleColor(ox, oy);
    }
    public void update() {

        if (step != 0) {
            robot = walkList.get(step);
  
            move();
            pass = checkCollision(robot.x,ox,robot.y,oy);
            if(pass == false){
               step = step - 1;
                maze.changeColor(robot.x, robot.y); 
                
            }
           
            
           
                
                
               System.out.println(step);
          
          
        } else {
            maze.changeColor(endx, endy);
            time.stop();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}
