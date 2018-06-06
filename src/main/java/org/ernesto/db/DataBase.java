package org.ernesto.db;

import org.ernesto.models.Person;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private static DataBase db = null;
    public Connection connection;

    public DataBase(){
        this.connection = DataBaseConnection();
    }


    /***
     * Create a database connection
     * @return Connection
     */
    private Connection DataBaseConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            return DriverManager.getConnection("jdbc:mysql://localhost/user_registry_db?" +
                                                            "user=root&password=WhiteFatalis252514");

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * returns an instance of DataBase
     * @return DataBase
     */
    public static DataBase getInstance(){
        if(db == null){
            db = new DataBase();
        }
        return db;
    }
}
