package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseCon {
    private String DB_URL = "jdbc:h2:./maturitnyProjekt.db";
    private String USER = "lenka";
    private String PASS = "heslo";


    private DatabaseCon(){

    }

    public static DatabaseCon databaseCon = new DatabaseCon();

    public static DatabaseCon getInstance() {

        if (databaseCon == null) {
            return new DatabaseCon();

        } else {
            return databaseCon;
        }
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
