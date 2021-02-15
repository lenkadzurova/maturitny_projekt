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

    DatabaseCon databaseCon = DatabaseCon.getInstance();

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

    public void hladat() throws SQLException {
        Connection  connection = null;
        Statement statement = null;
        ResultSet vystupZDatabazy = null;
        String meno = null;
        String priezvisko = null;
        String pohlavie = null;
        int vek = 0;
        int hmotnost = 0;
        int controlneCislo = 0;

        try {
           connection = databaseCon.getConnection();
           statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if( vyhladavatMena.getText().trim().isEmpty() && !vyhladavatPriezviska.getText().trim().isEmpty()){
            String sql1 = "SELECT * From UZIVATELIA WHERE priezvisko ='"+ vyhladavatPriezviska.getText().trim()+"'";
            vystupZDatabazy = statement.executeQuery(sql1);
            while (vystupZDatabazy.next()){
                controlneCislo++;
                if (controlneCislo > 1 ){
                    chyba.setText("upresni hladanie");
                    meno = "";
                    priezvisko = "";
                    pohlavie = "";
                    vek = 0;
                    hmotnost = 0;
                    break;
                }

                meno = vystupZDatabazy.getString("meno");
                priezvisko = vystupZDatabazy.getString("priezvisko");
                pohlavie = vystupZDatabazy.getString("pohlavie");
                vek = vystupZDatabazy.getInt("vek");
                hmotnost = vystupZDatabazy.getInt("hmotnost");



                System.out.println(meno + " " + priezvisko + " " + pohlavie + " " + vek + " " + hmotnost);
            }
            this.meno.setText(meno);
            this.priezvisko.setText(priezvisko);
            this.pohlavie.setText(pohlavie);
            this.vek.setText(String.valueOf(vek));
            this.hmotnost.setText(String.valueOf(hmotnost));

        }else if(!vyhladavatMena.getText().trim().isEmpty() && vyhladavatPriezviska.getText().trim().isEmpty()){
            String sql1 = "SELECT * From UZIVATELIA WHERE meno = '"+ vyhladavatMena.getText().trim()+"'";
            vystupZDatabazy = statement.executeQuery(sql1);
            while (vystupZDatabazy.next()){
                controlneCislo++;
                if (controlneCislo > 1 ){
                    chyba.setText("upresni hladanie");
                    meno = "";
                    priezvisko = "";
                    pohlavie = "";
                    vek = 0;
                    hmotnost = 0;
                    break;
                }

                meno = vystupZDatabazy.getString("meno");
                priezvisko = vystupZDatabazy.getString("priezvisko");
                pohlavie = vystupZDatabazy.getString("pohlavie");
                vek = vystupZDatabazy.getInt("vek");
                hmotnost = vystupZDatabazy.getInt("hmotnost");



                System.out.println(meno + " " + priezvisko + " " + pohlavie + " " + vek + " " + hmotnost);
            }
            if (controlneCislo == 0){
                chyba.setText("uživateľ neexistuje");
            }
            this.meno.setText(meno);
            this.priezvisko.setText(priezvisko);
            this.pohlavie.setText(pohlavie);
            this.vek.setText(String.valueOf(vek));
            this.hmotnost.setText(String.valueOf(hmotnost));

        }else if(!vyhladavatMena.getText().trim().isEmpty() && !vyhladavatPriezviska.getText().trim().isEmpty()){
            String sql1 = "SELECT * From UZIVATELIA WHERE meno = '"+ vyhladavatMena.getText().trim()+"' AND priezvisko ='"+ vyhladavatPriezviska.getText().trim()+"'";
            vystupZDatabazy = statement.executeQuery(sql1);

            while (vystupZDatabazy.next()){
                meno = vystupZDatabazy.getString("meno");
                priezvisko = vystupZDatabazy.getString("priezvisko");
                pohlavie = vystupZDatabazy.getString("pohlavie");
                vek = vystupZDatabazy.getInt("vek");
                hmotnost = vystupZDatabazy.getInt("hmotnost");



                System.out.println(meno + " " + priezvisko + " " + pohlavie + " " + vek + " " + hmotnost);
            }
            this.meno.setText(meno);
            this.priezvisko.setText(priezvisko);
            this.pohlavie.setText(pohlavie);
            this.vek.setText(String.valueOf(vek));
            this.hmotnost.setText(String.valueOf(hmotnost));

        }



    }
    public void pouzit(){
        Uzivatel uzivatel = Uzivatel.getInstance();
        uzivatel.setMeno(meno.getText());
        uzivatel.setPriezvisko(priezvisko.getText());
        uzivatel.setPohlavie(pohlavie.getText());
        uzivatel.setHmotnost(Double.parseDouble(hmotnost.getText()) );
        uzivatel.setVek(Integer.parseInt(vek.getText()) );
    }
}
