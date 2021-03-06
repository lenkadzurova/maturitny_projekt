package hlavneMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        primaryStage.setTitle("Hlevné Menu");
        primaryStage.setScene(new Scene(root, 1350, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
