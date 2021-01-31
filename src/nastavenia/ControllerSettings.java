package nastavenia;

import connection.DatabaseCon;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;

public class ControllerSettings {

    @FXML
    TextField meno;
    @FXML
    TextField priezvisko;
    @FXML
    TextField pohlavie;
    @FXML
    TextField vek;
    @FXML
    TextField hmotnost;
    @FXML
    TextField vyhladavatMena;
    @FXML
    TextField vyhladavatPriezviska;
    @FXML
    Label chyba;


    @FXML
    public void ulozit(){
        System.out.println("save");

        String localMeno = meno.getText().trim();

        String localPriezvisko = priezvisko.getText().trim();

        String  localPohlavie = pohlavie.getText().trim();

        String  localVek = vek.getText().trim();

        String  localHmotnost = hmotnost.getText().trim();



        try {
            Connection connection = DatabaseCon.getInstance().getConnection();
            Statement statement = connection.createStatement();

            String sql = " INSERT INTO UZIVATELIA VALUES ("+ generator() + ",'" + localMeno + "','" + localPriezvisko + "','" + localPohlavie + "','" + localVek + "'," + localHmotnost +");";
            System.out.println(sql);
            statement.execute(sql);



        } catch (SQLException e) {
            e.getStackTrace();

        }
    }
    private int  generator() {
        Random random = new Random();
        return (random.nextInt(500));
    }

    public void hladat() {
        if( vyhladavatMena.getText().trim().isEmpty() && !vyhladavatPriezviska.getText().trim().isEmpty()){
            System.out.println("zapísané iba priezvisko");

        }else if(!vyhladavatMena.getText().trim().isEmpty() && vyhladavatPriezviska.getText().trim().isEmpty()){
            System.out.println("zapísané iba meno");

        }else if(!vyhladavatMena.getText().trim().isEmpty() && !vyhladavatPriezviska.getText().trim().isEmpty()){
            System.out.println("obidve zapísané");

        }else{
            System.out.println("prázdne");

        }

    }
}
