package org.ernesto.controllers;

import org.ernesto.db.DataBase;
import org.ernesto.models.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonController {
    private static DataBase db = DataBase.getInstance();

    public static List<Person> getAllPersons(){
        return db.personList;
    }

    public static Person getPerson(int id){
        Person person = db.personList.stream().filter(p -> p.getId() == id).collect(Collectors.toList()).get(0);
        return person;
    }

    public static void savePerson(Person person){
        db.personList.add(person);
    }

    public static void updatePerson(Person person){
        Person personToReplace = db.personList.stream().filter(p -> p.getId() == person.getId()).collect(Collectors.toList()).get(0);
        int personToReplacePosition = db.personList.indexOf(personToReplace);
        db.personList.set(personToReplacePosition, person);
    }

    public static void deletePerson(int id){
        db.personList.removeIf(p -> p.getId() == id);
    }



}
