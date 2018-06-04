package org.ernesto.controllers;

import org.ernesto.db.DataBase;
import org.ernesto.models.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonController {
    private static PersonController personController = null;
    private DataBase db;

    private PersonController(){
        this.db = DataBase.getInstance();
    }

    public List<Person> getAllPersons(){
        return db.personList;
    }

    public Person getPerson(int id){
        try{
            Person person = db.personList.stream().filter(p -> p.getId() == id).collect(Collectors.toList()).get(0);
            return person;
        }catch(IndexOutOfBoundsException ex){
            return null;
        }
    }

    public void savePerson(Person person){
        db.personList.add(person);
    }

    public void updatePerson(Person person){
        try{
            Person personToReplace = db.personList.stream().filter(p -> p.getId() == person.getId()).collect(Collectors.toList()).get(0);
            int personToReplacePosition = db.personList.indexOf(personToReplace);
            db.personList.set(personToReplacePosition, person);
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public void deletePerson(int id){
        db.personList.removeIf(p -> p.getId() == id);
    }


    public static PersonController getInstance(){
        if(personController == null){
            personController = new PersonController();
        }
        return personController;
    }

}
