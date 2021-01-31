package nastavenia;

import connection.DatabaseCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUzivatela {
    public static void main(String[] args) {

        try{

            Connection connection = DatabaseCon.getInstance().getConnection();

            Statement statement = connection.createStatement();

            /*String sql = "CREATE TABLE UZIVATELIA " +
                    "(id INTEGER not NULL, " +
                    " meno VARCHAR(255), " +
                    " priezvisko VARCHAR(255), " +
                    " pohlavie VARCHAR(255), " +
                    " vek INTEGER, " +
                    " hmotnost DOUBLE, " +
                    " PRIMARY KEY ( id ));";
            statement.execute(sql);*/

            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
