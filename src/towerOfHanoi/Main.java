package towerOfHanoi;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;



public class Main extends Application {

    Label source=new Label("hi");
    @FXML
     ListView<String> sourceList;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/TowerOfHanoi.fxml"));



//        System.out.print("f....l"+sourceList.getItems().get(0));
        primaryStage.setTitle("Tower Of Hanoi");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 800, 275));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
