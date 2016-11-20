/*
 * Project:    RobotOverlords
 * File:       BattlegroundController.java
 * Author:     Michael Esker
 * Date:       Nov 19, 2016
 * Time:       8:12:36 PM
 */
package robotoverlords;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Michael Esker
 */
public class BattlegroundController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox battleArea;
    
    @FXML
    private TextField filenameTextField;
    
    private String inputFilename;
    
    private Integer gridWidth;
    private GridPane battleGrid;
    double hueOffset;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridWidth = 5;
        battleGrid = new GridPane();
        battleArea.getChildren().add(battleGrid);
        hueOffset = 0.0;
    }    
    
    @FXML
    private void handleAction(ActionEvent ae) {
        battleGrid.getChildren().clear();
        battleGrid.setPrefHeight(battleArea.getHeight()/gridWidth);
        battleGrid.setPrefWidth(battleArea.getWidth()/gridWidth);
        double rectWidth = battleGrid.getWidth()/gridWidth;
        Rectangle rect;
        Circle circ;
        for (int i = 0; i < gridWidth; ++i) {
            for (int j = 0; j < gridWidth; ++j) {
                rect = new Rectangle(rectWidth, rectWidth, Color.hsb(i*20+j*10+hueOffset, 1.0, 1.0));
                battleGrid.add(rect, j, i);
                circ = new Circle(rectWidth/4, Color.hsb(180+i*20+j*20+hueOffset, 1.0, 1.0));
                circ.setOnMousePressed((MouseEvent me) -> {
                    Circle c = (Circle)me.getSource();
                    GridPane gp = (GridPane)c.getParent();
                    
                    System.out.print("Clicked cirlce (");
                    System.out.print(GridPane.getRowIndex(c));
                    System.out.print(",");
                    System.out.print(GridPane.getColumnIndex(c));
                    System.out.println(")");
                    gp.getChildren().remove(c);
                });
                battleGrid.add(circ, j, i);
                GridPane.setHalignment(circ, HPos.CENTER);
                GridPane.setValignment(circ, VPos.CENTER);
            }
        }
        hueOffset += 20.0;
        
        if (!filenameTextField.getText().equals("")) {
            inputFilename = filenameTextField.getText();
            System.out.println("You wanted to open " + inputFilename);
        }
        
        System.out.println("You were vaporized by lasers.");
    }
}
