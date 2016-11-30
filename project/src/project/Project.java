/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;

/**
 *
 * @author zhangzhi
 */
public class Project extends Application {

    private final int numRows = 15;
    private final int boardWidth = 600;
    private final int boardHeight = 600;
    public int startx = 2;
    public int starty = 6;
    public int endx = 12;
    public int endy = 5;
    public int ox = 1;
    public int oy = 3;
    public int step = 0;
    public maze maze;
    public Timeline time;
    public boolean pass = true;
    public Nodes robot;
    public Nodes startNode;
    public Nodes endNode;
    public Nodes parent;
    public Nodes obstacle;
    public Path path;
    public ArrayList<Nodes> walkList;

    @Override
    public void start(Stage primaryStage) {

        maze = new maze(numRows, startx, starty, endx, endy, ox, oy, boardWidth, boardHeight);
        startNode = new Nodes(startx, starty);
        endNode = new Nodes(endx, endy);
        parent = new AStar().findPath(numRows, startNode, endNode,ox,oy);
        walkList = new ArrayList<Nodes>();
        while (parent != null) {
            //System.out.println(parent.x + ", " + parent.y);
            walkList.add(new Nodes(parent.x, parent.y));
            parent = parent.parent;
        }
        
        step = walkList.size() - 1;
       
        path = new Path(walkList, step);
        
        GridPane grid = maze.build();
        Scene scene = new Scene(grid);
        primaryStage.setTitle("Motion planning of robots");
        primaryStage.setScene(scene);
        primaryStage.show();

       
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

    public void move() {
        maze.backobstacleColor(ox, oy);
        ox++;
        maze.changeobstacleColor(ox, oy);
    }
   
   public Path reFindPath(int x, int y, int ox, int oy) {
       
        startNode = new Nodes(x, y);
        parent = new AStar().findPath(numRows, startNode, endNode,ox,oy);
        
        walkList.clear();
        while (parent != null) {
            //System.out.println(parent.x + ", " + parent.y);
            walkList.add(new Nodes(parent.x, parent.y));
            parent = parent.parent;
        }
        step = walkList.size() - 1;
        path = new Path(walkList, step);
        
        return path;
    }
   

    public void update() {

        if (path.step != 0) {
            robot = path.path.get(path.step);

            move();
            pass = checkCollision(robot.x, ox, robot.y, oy);
            if (pass == false) {
                path.step = path.step - 1;
                maze.changeColor(robot.x, robot.y);

            }
            else{
            //path = reFindPath(robot.x,robot.y,ox,oy);
            }

            //System.out.println(step);

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
