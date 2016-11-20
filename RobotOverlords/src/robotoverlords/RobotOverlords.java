/*
 * Project:    RobotOverlords
 * File:       RobotOverlords.java
 * Author:     Michael Esker
 * Date:       Nov 19, 2016
 * Time:       8:11:09 PM
 */
package robotoverlords;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Michael Esker
 */
public class RobotOverlords extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Battleground.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
