package org.ernesto.controllers;

import org.ernesto.builders.PersonBuilder;
import org.ernesto.db.DataBase;
import org.ernesto.models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PersonController {
    private static PersonController personController = null;
    private DataBase db;
    private Connection connection;

    private PersonController() {
        this.db = DataBase.getInstance();
        this.connection = db.connection;
    }

    /***
     *
     * @return List<Person>
     */
    public List<Person> getAllPersons() {
        List<Person> personList = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        ResultSet resultset = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM users");
            resultset = statement.executeQuery();
            while(resultset.next()){
                personList.add(createPerson(resultset.getInt("id"), resultset.getString("name"), resultset.getString("nickname"), resultset.getInt("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personList;
    }

    /***
     *
     * @param id int
     * @return
     */
    public Person getPerson(int id){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM users WHERE users.id=?");
            statement.setInt(1, id);
            statement.setMaxRows(1);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return createPerson(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("nickname"), resultSet.getInt("age"), resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     *
     * @param person the person to store in the database
     */
    public void savePerson(Person person){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?);");
            statement.setInt(1, person.getId());
            statement.setString(2, person.getName());
            statement.setString(3, person.getNickname());
            statement.setInt(4, person.getAge());
            statement.setString(5, person.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * @param person  the person object that stores the information to update
     */
    public void updatePerson(Person person){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE users SET name=?, nickname=?, age=?, password=? WHERE users.id=?;");
            statement.setString(1, person.getName());
            statement.setString(2, person.getNickname());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getPassword());
            statement.setInt(5, person.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * @param id int
     */
    public void deletePerson(int id){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM users WHERE users.id=?;");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /***
     * returns an instance of PersonController
     * @return
     */
    public static PersonController getInstance(){
        if(personController == null){
            personController = new PersonController();
        }
        return personController;
    }


    private Person createPerson(int id, String name, String nickname, int age){
        return createPerson(id, name, nickname, age, null);
    }

    /***
     * creates a new instance of Person
     * @param id
     * @param name
     * @param nickname
     * @param age
     * @param password
     * @return
     */
    private Person createPerson(int id, String name, String nickname, int age, String password){
        return new PersonBuilder()
                .setId(id)
                .setName(name)
                .setNickname(nickname)
                .setAge(age)
                .setPassword(password)
                .build();
    }

}
