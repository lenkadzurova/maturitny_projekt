package hlavneMenu;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nastavenia.Uzivatel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static connection.DatabaseCon.databaseCon;

public class ControllerMenu {

    @FXML
    TextField vyhladavatMesto;

    @FXML
    Label mojeMesto;

    @FXML
    Label menoUzivatela;

    @FXML
    TableView tableView;

    Uzivatel uzivatel = Uzivatel.getInstance();
    TEST pokus = TEST.getInstance();


    List<Mesto> mestoList  = new ArrayList<>();


    public void initialize(){

        mojeMesto.setText(pokus.getNazov());

        mestoList.add(new Mesto("Holic","naj 1","min 1"," jasno", "pondelok"));
        mestoList.add(new Mesto("Holic","naj 2","min 2"," jasno2", "utorok"));
        mestoList.add(new Mesto("Holic","naj 3","min 3"," jasno4", "streda"));
        mestoList.add(new Mesto("Holic","naj 4","min 4"," jasno55", "stvrtok"));
        mestoList.add(new Mesto("Holic","naj 5","min 5"," jasno5", "piatok"));
        mestoList.add(new Mesto("Holic","naj 6","min 6"," jasno5", "sobota"));

        TableColumn<Mesto, String> col1 = new TableColumn<>("Datum");
        col1.setCellValueFactory(new PropertyValueFactory<>("datum"));

        TableColumn<Mesto, String> col2 = new TableColumn<>("Oblano");
        col2.setCellValueFactory(new PropertyValueFactory<>("oblacno"));

        TableColumn<Mesto, String> col3 = new TableColumn<>("Naj teploa");
        col3.setCellValueFactory(new PropertyValueFactory<>("najTeplota"));

        TableColumn<Mesto, String> col4 = new TableColumn<>("Min teplota");
        col4.setCellValueFactory(new PropertyValueFactory<>("minTeplota"));

        TableColumn<Mesto, String> col5 = new TableColumn<>("Pitny rezim");
        col5.setCellValueFactory(new PropertyValueFactory<>("datum"));

        tableView.getColumns().add(col1);
        tableView.getColumns().add(col2);
        tableView.getColumns().add(col3);
        tableView.getColumns().add(col4);
        tableView.getColumns().add(col5);

        tableView.getItems().addAll(FXCollections.observableArrayList(mestoList));
    }

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



    @FXML
    public void hladat() {
        Connection connection = null;
        Statement statement = null;
        ResultSet vystupZDatabazy = null;
        String mestoDB = null;
        String oblacnostDB = null;
        int najvyysiaTeplotaCezDenDB = 0;
        int najnizsiaTeplotaVNociDB = 0;


        try {
            connection = databaseCon.getConnection();
            statement = connection.createStatement();
            String sql1 = "SELECT * From POCASIE WHERE mesto ='"+ vyhladavatMesto.getText().trim()+"'";
            vystupZDatabazy = statement.executeQuery(sql1);
            while (vystupZDatabazy.next()) {
                Mesto mesto = new Mesto();
                mestoDB =  vystupZDatabazy.getString("mesto");
                oblacnostDB = vystupZDatabazy.getString("oblacnost");
                najvyysiaTeplotaCezDenDB = vystupZDatabazy.getInt("najvyysiaTeplotaCezDen");
                najnizsiaTeplotaVNociDB = vystupZDatabazy.getInt("najnizsiaTeplotaVNoci");
                mesto.setPitnyRezim(PitnyRezim.vypocet(uzivatel.getHmotnost(), Double.parseDouble(mesto.najTeplota)));

                System.out.println(mestoDB + " " + oblacnostDB + " " + najvyysiaTeplotaCezDenDB + " " + najnizsiaTeplotaVNociDB);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }


    public void vyhodnotit(){


    }

    public void pouziNastavenie(){
        menoUzivatela.setText(Uzivatel.getInstance().getPriezvisko());
        System.out.println(Uzivatel.getInstance().getPriezvisko());
    }


}


