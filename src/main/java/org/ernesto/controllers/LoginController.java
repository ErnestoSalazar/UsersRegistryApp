package org.ernesto.controllers;

import org.ernesto.db.DataBase;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private static LoginController loginController = null;
    private DataBase db;
    private Connection connection;


    private LoginController(){
        this.db = DataBase.getInstance();
        this.connection = this.db.connection;
    }

    /***
     * used to validate if a user exists in a database
     * @param nickname
     * @param password
     * @return
     */
    public boolean loginValidator(String nickname, String password){
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM users WHERE users.nickname=? AND users.password=?;");
            statement.setString(1, nickname);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.first()){
                return true;
            }
            else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static LoginController getInstance(){
        if(loginController == null) {
            loginController = new LoginController();
        }
        return loginController;
    }


}
