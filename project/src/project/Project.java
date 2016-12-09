/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

/**
 *
 * @author zhangzhi
 */
public class Project extends Application {

    private final int boardWidth = 600;
    private final int boardHeight = 600;
    public int numRows;
    public int startx;
    public int starty;
    public int endx;
    public int endy;
    public maze maze;
    public Timeline time;
    public boolean pass = true;
    public boolean notend = true;
    public boolean nocoll = true;
    public Nodes robot;
    public Nodes startNode;
    public Nodes endNode;
    public Nodes parent;
    public Obstacle obstaclea;
    public Paint saveColora = Color.WHITE;
    public int oax;
    public int oay;
    public int xaDirection;
    public int yaDirection;
    public int aspeed;
    public Obstacle obstacleb;
    public Paint saveColorb = Color.WHITE;
    public int obx;
    public int oby;
    public int xbDirection;
    public int ybDirection;
    public int bspeed;
    public Path walkpath;
    public AStar star;
    public int step = 0;
    public ArrayList<Nodes> walkList;
    public ParseTxtFile parse;
    public FileReader file1;
    public FileReader file2;
    public FileReader file3;
    public FileReader file4;
    public Gather gather;
    public File file;
    public int move = 0;
    public static String filename;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException, URISyntaxException {

        //String filePath = new File("").getAbsolutePath();
        // filename = filePath + "/" + filename;
        FileChooser fileChooser = new FileChooser();
        URI thisFilePath = Project.class.getProtectionDomain().getCodeSource().getLocation().toURI();
        fileChooser.setInitialDirectory(new File(thisFilePath).getParentFile().getParentFile().getParentFile());
        file = fileChooser.showOpenDialog(primaryStage);
        //filename = "src/project/room.txt";
        parse = new ParseTxtFile();

        // file1 = new FileReader(filename);
        file1 = new FileReader(file);
        if (parse.IsItEmpty(file1) == false) {
            System.out.print("\nThe file is empty.\n");
            file1.close();//I need to close the file
            System.exit(0);
        }

        //file2 = new FileReader(filename);
        file2 = new FileReader(file);
        if (parse.CorrectNumLines(file2) == false) {
            System.out.print("\nWrong format: error 1\n");
            file2.close();//again
            System.exit(0);
        }

        //file3 = new FileReader(filename);
        file3 = new FileReader(file);
        if (parse.IsItValid(file3) == false) {
            System.out.print("\nWrong format: error 2\n");
            file3.close();//again
            System.exit(0);
        }
        System.out.print("\nParsing complete.\n");

        gather = new Gather();
        //If this was c, then I would rewind the file then.

        //file4 = new FileReader(filename);
        file4 = new FileReader(file);
        gather.collectYourData(file4);//again

        /*
    //robot F
    public int sFx;//Robot F starting location X
    public int sFy;//Robot F starting location Y
    //robot L
    public int eLx;//Robot L exiting location X
    public int eLy;//Robot L exiting location Y
    //First Obstacle
    public int sFstObX;//1st obstacle starting location X
    public int sFstObY;//1st obstacle starting location Y
    public int spdFstOb;//speed of the 1st obstacle
    public int dirFstObX;//direction X of the 1st obstacle
    public int dirFstObY;//direction Y of the 1st obstacle
    //Second Obstacle
    public int sSndObX;//2nd obstacle starting location X
    public int sSndObY;//2nd obstacle starting location Y
    public int spdSndOb;//speed of the 2nd obstacle
    public int dirSndObX;//direction X of the 2nd obstacle
    public int dirSndObY;//direction Y of the 2nd obstacle
         */
        numRows = gather.gSize;
        //robot F
        startx = gather.sFy - 1;
        starty = gather.sFx - 1;
        //robot L
        endx = gather.eLy - 1;
        endy = gather.eLx - 1;
        //First Obstacle
        oax = gather.sFstObY - 1;
        oay = gather.sFstObX - 1;
        xaDirection = gather.dirFstObY;

        yaDirection = gather.dirFstObX;

        aspeed = gather.spdFstOb;
        //Second Obstacle
        obx = gather.sSndObY - 1;
        oby = gather.sSndObX - 1;
        xbDirection = gather.dirSndObY;

        ybDirection = gather.dirSndObX;
        bspeed = gather.spdSndOb;
        //int dir = -1;
        System.out.print(gather.gSize);
        System.out.print(gather.sFx);
        System.out.print(gather.sFy);
        System.out.print(gather.eLx);
        System.out.print(gather.eLy);
        System.out.print(gather.sFstObX);
        System.out.print(gather.sFstObY);
        System.out.print("xxxx" + gather.dirFstObX);
        System.out.print("yyy" + gather.dirFstObY);

        System.out.print(gather.dirSndObX);
        System.out.print(gather.dirSndObY);

        maze = new maze(numRows, startx, starty, endx, endy, oax, oay, obx, oby, boardWidth, boardHeight);

        startNode = new Nodes(startx, starty);
        endNode = new Nodes(endx, endy);
        parent = new AStar().findPath(numRows, startNode, endNode, oax, oay, obx, oby);

        walkList = new ArrayList<Nodes>();
        while (parent != null) {
            System.out.println(parent.x + ", " + parent.y);
            walkList.add(new Nodes(parent.x, parent.y));
            parent = parent.parent;
        }

        step = walkList.size() - 1;

        //System.out.println(step);
        walkpath = new Path(walkList, step);
        obstaclea = new Obstacle(oax, oay, aspeed, xaDirection, yaDirection, numRows);
        obstacleb = new Obstacle(obx, oby, bspeed, xbDirection, ybDirection, numRows);
        walkpath.step = walkpath.step - 1;
        if (walkpath.step < 0) {
            System.out.print("uable to generate a map");
            System.exit(0);

        }
        robot = walkpath.path.get(walkpath.step);

        GridPane grid = maze.build();
        Scene scene = new Scene(grid);

        primaryStage.setTitle("Motion planning of robots");
        primaryStage.setScene(scene);
        primaryStage.show();

        time = new Timeline(new KeyFrame(Duration.seconds(3), actionEvent -> update()));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

    }

    public boolean checkCollision(int x, int y) {
        obstaclea.moveOnce();
        obstacleb.moveOnce();
        int oax = obstaclea.getX();
        int oay = obstaclea.getY();
        int obx = obstacleb.getX();
        int oby = obstacleb.getY();
        obstaclea.moveBack();
        obstacleb.moveBack();
        if (x == oax && y == oay) {

            return false;
        }
        if (x == obx && y == oby) {
            return false;
        } else {
            return true;
        }
    }

    public boolean CheckonthePath(int x, int endx, int y, int endy) {

        if (x == endx && y == endy) {
            return false;
        } else {
            return true;
        }
    }

    public void movea() {

        int x = obstaclea.getX();
        int y = obstaclea.getY();
        maze.getArectangle(x, y).setFill(saveColora);
        obstaclea.moveOnce();

        saveColora = maze.getArectangle(obstaclea.getX(), obstaclea.getY()).getFill();
        maze.changeobstacleColor(obstaclea.getX(), obstaclea.getY());

    }

    public void moveb() {
        int x = obstacleb.getX();
        int y = obstacleb.getY();
        maze.getArectangle(x, y).setFill(saveColorb);

        obstacleb.moveOnce();
        saveColorb = maze.getArectangle(obstacleb.getX(), obstacleb.getY()).getFill();
        if (saveColorb.equals(Color.BLUE)) {
            saveColorb = saveColora;
        }

        maze.changeobstacleColor(obstacleb.getX(), obstacleb.getY());

    }

    public void reFindPath(int x, int y) {
        obstaclea.moveOnce();
        obstacleb.moveOnce();
        int oax = obstaclea.getX();
        int oay = obstaclea.getY();
        int obx = obstacleb.getX();
        int oby = obstacleb.getY();
        obstaclea.moveBack();
        obstacleb.moveBack();

        startNode = new Nodes(x, y);

        parent = new AStar().findPath(numRows, startNode, endNode, oax, oay, obx, oby);
        walkList.clear();
        while (parent != null) {
            System.out.println(parent.x + "real, " + parent.y);
            walkList.add(new Nodes(parent.x, parent.y));
            parent = parent.parent;
        }
        step = walkList.size() - 1;
        walkpath.step = walkpath.step - 1;
        if (walkpath.step < 0) {
            System.out.print("uable to generate a map");
            System.exit(0);

        }
        robot = walkpath.path.get(walkpath.step);

    }

    public void update() {

        notend = CheckonthePath(robot.x, endx, robot.y, endy);
        nocoll = checkCollision(robot.x, robot.y);
        System.out.print(nocoll);
        if (notend) {
            if (nocoll) {

                //next step of robot
                //robot = walkpath.path.get(walkpath.step-1);
                //go aheah and change color
                maze.changeColor(robot.x, robot.y);
                movea();
                moveb();
                //get the next round for robot
                if (walkpath.step != 0) {
                    walkpath.step--;
                }
                robot = walkpath.path.get(walkpath.step);

            } else {
                if (walkpath.step > walkpath.path.size()) {

                    System.out.print("cant find path");
                }
                walkpath.step = walkpath.step + 1;
                robot = walkpath.path.get(walkpath.step);
                reFindPath(robot.x, robot.y);
            }

        } else {
            maze.changeColor(endx, endy);
            time.stop();

            System.out.print("success");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        launch(args);

    }

}
