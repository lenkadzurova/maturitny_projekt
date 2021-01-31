package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseCon {
    private String DB_URL = "jdbc:h2:tcp://localhost/C:/Users/admin/Desktop/škola/PRO/4.ročník/java/maturitnyProjekt.db";
    private String USER = "lenka";
    private String PASS = "heslo";


    private DatabaseCon(){

    }

    private static DatabaseCon databaseCon = null;

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
