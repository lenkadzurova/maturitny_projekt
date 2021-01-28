package hlavneMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ControllerMenu {

    @FXML
    public void nastavenia(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/nastavenia/Settings.fxml").toURI().toURL();
        Parent root;
        try {
            //root = FXMLLoader.load(getClass().getResource("/obvodyObsahy/ObvodyObsahy.fxml"));
            root = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.setTitle("Nastavenia");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
