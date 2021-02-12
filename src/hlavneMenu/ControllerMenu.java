package hlavneMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import static connection.DatabaseCon.databaseCon;

public class ControllerMenu {

    @FXML
    TextField vyhladavatMesto;
    @FXML
    Label nazovMesta;


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

    public void hladat() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet vystupZDatabazy = null;
        String mesto = null;

        try {
            connection = databaseCon.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!vyhladavatMesto.getText().trim().isEmpty()) {
            String sql1 = "SELECT * From POCASIE WHERE mesto ='" + vyhladavatMesto.getText().trim() + "'";
            vystupZDatabazy = statement.executeQuery(sql1);
            while (vystupZDatabazy.next()) {

                mesto = vystupZDatabazy.getString("mesto");

                System.out.println(mesto);
            }
            nazovMesta.setText(vyhladavatMesto.getText());

        }
    }
}
