/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author zhangzhi
 */
public class Project extends Application {
    private final int numRows = 8;
    private final int boardWidth = 600;
    private final int boardHeight = 600;
    public maze maze;
    public int numR;
    public int startx;
    public int starty;
    public int endx;
    public int endy;
    @Override
    public void start(Stage primaryStage) {
        
        
/*       //start button 
        Button btn = new Button();
        btn.setText("start");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
       maze = new maze(numRows, startx, starty,endx,endy, boardWidth,boardHeight);

       
        GridPane grid = maze.build();
//        AnchorPane anchor = new AnchorPane();
//        grid.add(anchor, 0, 1);
        //root.getChildren().add(btn);
        
        Scene scene = new Scene(grid, boardWidth, boardHeight);
        
        primaryStage.setTitle("Motion planning of robots");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
