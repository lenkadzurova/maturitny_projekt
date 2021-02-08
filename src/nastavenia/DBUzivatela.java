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

            String sql = "CREATE TABLE POCASIE " +
                    "(id INTEGER not NULL, " +
                    " mesto VARCHAR(255), " +
                    " den VARCHAR(255), " +
                    " oblacno VARCHAR(255), " +
                    " najvTeplCezDen DOUBLE, " +
                    " najnTeplVNoci DOUBLE, " +
                    " PRIMARY KEY ( id ));";
            statement.execute(sql);

            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
