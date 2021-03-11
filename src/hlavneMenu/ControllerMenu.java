package hlavneMenu;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nastavenia.Uzivatel;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    @FXML
    TextField nalacno;

    @FXML
    TextField osmej;

    @FXML
    TextField desiatej;

    @FXML
    TextField dvanastej;

    @FXML
    TextField strnastej;

    @FXML
    TextField sestnastej;

    @FXML
    TextField osemnastej;

    Uzivatel uzivatel = Uzivatel.getInstance();
    TEST pokus = TEST.getInstance();


    List<Mesto> mestoList  = new ArrayList<>();
    List<Mesto>mesta = new ArrayList<>();



    public void initialize(){

        //mojeMesto.setText(pokus.getNazov());

       /* mestoList.add(new Mesto("Holic",1,1," jasno", "pondelok"));
        mestoList.add(new Mesto("Holic",2,2," jasno2", "utorok"));
        mestoList.add(new Mesto("Holic",3,3," jasno4", "streda"));
        mestoList.add(new Mesto("Holic",4,4," jasno55", "stvrtok"));
        mestoList.add(new Mesto("Holic",5,5," jasno5", "piatok"));
        mestoList.add(new Mesto("Holic",6,6," jasno5", "sobota"));*/

        TableColumn<Mesto, String> col1 = new TableColumn<>("Datum");
        col1.setCellValueFactory(new PropertyValueFactory<>("datum"));

        TableColumn<Mesto, String> col2 = new TableColumn<>("Oblano");
        col2.setCellValueFactory(new PropertyValueFactory<>("oblacno"));

        TableColumn<Mesto, String> col3 = new TableColumn<>("Naj teploa");
        col3.setCellValueFactory(new PropertyValueFactory<>("najTeplota"));

        TableColumn<Mesto, String> col4 = new TableColumn<>("Min teplota");
        col4.setCellValueFactory(new PropertyValueFactory<>("minTeplota"));

        TableColumn<Mesto, String> col5 = new TableColumn<>("Pitny rezim");
        col5.setCellValueFactory(new PropertyValueFactory<>("pitnyRezim"));

        tableView.getColumns().add(col1);
        tableView.getColumns().add(col2);
        tableView.getColumns().add(col3);
        tableView.getColumns().add(col4);
        tableView.getColumns().add(col5);

        uzivatel.setMeno("Lenka");
        uzivatel.setPriezvisko("Dzurová");
        uzivatel.setHmotnost(70.0);

        tableView.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {

            Mesto mesto =  (Mesto) tableView.getSelectionModel().getSelectedItem();
            menoUzivatela.setText(new Double(mesto.getPitnyRezim()).toString());
        });


       // tableView.getItems().addAll(FXCollections.observableArrayList(mestoList));
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
        tableView.getItems().clear();
        mesta.clear();

        try {
            connection = databaseCon.getConnection();
            statement = connection.createStatement();
            String sql1 = "SELECT * From POCASIE WHERE mesto ='"+ vyhladavatMesto.getText().trim()+"'";
            vystupZDatabazy = statement.executeQuery(sql1);
            double x = uzivatel.getHmotnost();
            while (vystupZDatabazy.next()) {
                Mesto mesto = new Mesto();
                mesto.setMesto(vystupZDatabazy.getString("mesto"));
                mesto.setOblacno(vystupZDatabazy.getString("oblacnost"));
                mesto.setNajTeplota(vystupZDatabazy.getDouble("najvysiaTeplotaCezDen"));
                mesto.setMinTeplota(vystupZDatabazy.getDouble("najnizsiaTeplotaVNoci"));
                mesto.setPitnyRezim(vystupZDatabazy.getString("pitnyRezim"));

                mesto.setVypocetPitnehoRezimu(PitnyRezim.vypocet(uzivatel.getHmotnost(), vystupZDatabazy.getDouble("najvysiaTeplotaCezDen")));

                mesta.add(mesto);
            }
            tableView.getItems().addAll(FXCollections.observableArrayList(mesta));
            mojeMesto.setText(vyhladavatMesto.getText());


        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    @FXML
    public void akoSpravnePit(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/pitnyRezim/AkoSpravnePit.fxml").toURI().toURL();
        Parent root;
        try {
            //root = FXMLLoader.load(getClass().getResource("/obvodyObsahy/ObvodyObsahy.fxml"));
            root = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.setTitle("Ako správne piť");
            stage.setScene(new Scene(root, 1200, 400));
            stage.show();
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void vyhodnotit(){


    }

    List<Double> hodnotyPT = new ArrayList<>();
    public void hodnotyPitnehoRezimu(){
        Connection connection = null;
        Statement statement = null;
        ResultSet vystupZDatabazy = null;
        try {
            connection = databaseCon.getConnection();
            statement = connection.createStatement();
            String sql1 = "SELECT * From POCASIE";
            vystupZDatabazy = statement.executeQuery(sql1);
            while (vystupZDatabazy.next()) {
                String ciastkovyPR = vystupZDatabazy.getString("PITNYREZIM");
                List<String> strings = Arrays.asList(ciastkovyPR.split(","));
                    for (String s : strings){
                        Double d = Double.parseDouble(s);
                        hodnotyPT.add(d);
                    }
            }
            System.out.println(hodnotyPT.toString());
            nalacno.setText(String.valueOf(hodnotyPT.get(0)));
            osmej.setText(String.valueOf(hodnotyPT.get(1)));
            desiatej.setText(String.valueOf(hodnotyPT.get(2)));
            dvanastej.setText(String.valueOf(hodnotyPT.get(3)));
            strnastej.setText(String.valueOf(hodnotyPT.get(4)));
            sestnastej.setText(String.valueOf(hodnotyPT.get(5)));
            osemnastej.setText(String.valueOf(hodnotyPT.get(6)));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void ulozit() {
        String dataPT = nalacno.getText() + " " + osmej.getText() + " " + desiatej.getText() + " " + dvanastej.getText()
                        + " " + strnastej.getText() + " " + sestnastej.getText() + " " + osemnastej.getText();
        System.out.println(dataPT);
    }


    public void pouziNastavenie(){
        menoUzivatela.setText(Uzivatel.getInstance().getMeno() + " " + Uzivatel.getInstance().getPriezvisko());

        System.out.println(Uzivatel.getInstance().getMeno() + Uzivatel.getInstance().getPriezvisko());
    }



}


