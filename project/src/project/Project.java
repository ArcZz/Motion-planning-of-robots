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

    private final int numRows = 20;
    private final int boardWidth = 600;
    private final int boardHeight = 600;
    public maze maze;
    public int startx = 2;
    public int starty = 6;
    public int endx = 15;
    public int endy = 13;
    public int locationx;
    public int locationy;
    public int step = 0;
    public Timeline time;
    public Nodes robot;
    public ArrayList<Nodes> walkList;
    
    @Override
    public void start(Stage primaryStage) {

       
        maze = new maze(numRows, startx, starty, endx, endy, boardWidth, boardHeight);
        GridPane grid = maze.build();
        Scene scene = new Scene(grid);  
        primaryStage.setTitle("Motion planning of robots");
        primaryStage.setScene(scene);
        primaryStage.show();
        locationx = 0;
        locationy = 0;
        walkList = maze.getList();
        step = walkList.size() - 1;    
        time = new Timeline(new KeyFrame(Duration.seconds(0.1), actionEvent -> update() ));
        time.setCycleCount(Animation.INDEFINITE);        
        time.play();
          
          
    }
     public void update() {
       
         if (step != 0) { 
             robot = walkList.get(step);  
          System.out.println(robot.x); 
          System.out.println(robot.y); 
          maze.changeColor(robot.x, robot.y);
           step--;
       
       
       
        }  
         else{
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
