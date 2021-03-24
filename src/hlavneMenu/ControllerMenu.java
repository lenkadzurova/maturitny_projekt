package hlavneMenu;

import connection.DatabaseCon;
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

    @FXML
    Label vyhodnotenie;

    Uzivatel uzivatel = Uzivatel.getInstance();
    TEST pokus = TEST.getInstance();


    List<Mesto> mestoList  = new ArrayList<>();
    List<Mesto>mesta = new ArrayList<>();
    List<Double> hodnotyPT = new ArrayList<>();
    double pitnyRezim = 0;
    int id = 0;

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
        col5.setCellValueFactory(new PropertyValueFactory<>("vypocetPitnehoRezimu"));

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
            if(mesto != null) {
                pitnyRezim = mesto.getVypocetPitnehoRezimu();
                id = mesto.getId();
                hodnotyPitnehoRezimu(mesto.getPitnyRezim());
            }//menoUzivatela.setText(new Double(mesto.getPitnyRezim()).toString());
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
        tableView.getItems().clear();
        mesta.clear();
        Connection connection = null;
        Statement statement = null;
        ResultSet vystupZDatabazy = null;

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
            stage.setScene(new Scene(root, 1000, 400));
            stage.show();
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void hodnotyPitnehoRezimu(String pitnyRezim){


        List<String> strings = Arrays.asList(pitnyRezim.split(","));
        for (String s : strings){
            Double d = Double.parseDouble(s);
            hodnotyPT.add(d);
        }


        double nal = hodnotyPT.get(0);
        double osm = hodnotyPT.get(1);
        double des = hodnotyPT.get(2);
        double dva = hodnotyPT.get(3);
        double str = hodnotyPT.get(4);
        double ses = hodnotyPT.get(5);
        double ose = hodnotyPT.get(6);

        nalacno.setText(String.valueOf(nal));
        osmej.setText(String.valueOf(osm));
        desiatej.setText(String.valueOf(des));
        dvanastej.setText(String.valueOf(dva));
        strnastej.setText(String.valueOf(str));
        sestnastej.setText(String.valueOf(ses));
        osemnastej.setText(String.valueOf(ose));
    }

    public void vyhodnotit(){

        //hodnotyPitnehoRezimu(hodnotyPT.toString());


        double nal = Double.parseDouble(nalacno.getText());
        double osm = Double.parseDouble(osmej.getText());
        double des = Double.parseDouble(desiatej.getText());
        double dva = Double.parseDouble(dvanastej.getText());
        double str = Double.parseDouble(strnastej.getText());
        double ses = Double.parseDouble(sestnastej.getText());
        double ose = Double.parseDouble(osemnastej.getText());

        double celkovyPR = nal + osm + des + dva + str + ses + ose;
        if(celkovyPR >= pitnyRezim ){
            vyhodnotenie.setText("výborne, tvoj pitný režim je správny");
        }else{
            vyhodnotenie.setText("tvoj pitný režim je slabý, mal by si ho zlepšiť");
        }
        System.out.println(celkovyPR);
    }




    public void ulozit() {
        String dataPT = nalacno.getText() +  "," + osmej.getText() + "," + desiatej.getText() + "," + dvanastej.getText()
                        + "," + strnastej.getText() + "," + sestnastej.getText() + "," + osemnastej.getText();
        System.out.println(dataPT);
        Connection connection = null;
        Statement statement = null;
        ResultSet vystupZDatabazy = null;
        try {
            connection = databaseCon.getConnection();
            statement = connection.createStatement();

            String sql = "UPDATE POCASIE SET PITNYREZIM = '"+ dataPT +  "' WHERE ID= 67";
            System.out.println(sql);
            statement.execute(sql);

            updatePitnyrezim(dataPT);

        } catch (SQLException e) {
            e.getStackTrace();

        }

    }


    public void pouziNastavenie(){
        menoUzivatela.setText(Uzivatel.getInstance().getMeno() + " " + Uzivatel.getInstance().getPriezvisko());

        System.out.println(Uzivatel.getInstance().getMeno() + Uzivatel.getInstance().getPriezvisko());
    }


    private void updatePitnyrezim(String pitnyRezim){

        for (Mesto m : mesta){
            if(m.getId() == id){
                m.setPitnyRezim(pitnyRezim);
                break;
            }
        }

    }

}


