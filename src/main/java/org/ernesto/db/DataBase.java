package org.ernesto.db;

import org.ernesto.models.Person;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static DataBase db = null;
    public List<Person> personList;

    private DataBase(){
        this.personList = new ArrayList<>();
    }


    public static DataBase getInstance(){
        if(db == null){
            db = new DataBase();
        }
        return db;
    }
}
