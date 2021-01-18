package nastavenia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUzivatela {
    public static void main(String[] args) {

        String url = "jdbc:h2:tcp://localhost/C:/Users/admin/Desktop/škola/PRO/4.ročník/java/maturitnyProjekt.db";
        String meno = "lenka";
        String heslo = "heslo";

        try{
            Connection connection = DriverManager.getConnection(url,meno,heslo);

            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE UZIVATELIA " +
                    "(id INTEGER not NULL, " +
                    " meno VARCHAR(255), " +
                    " priezvisko VARCHAR(255), " +
                    " pohlavie VARCHAR(255), " +
                    " vek INTEGER, " +
                    " hmotnost DOUBLE, " +
                    " PRIMARY KEY ( id )}";

            statement.execute(sql);
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
