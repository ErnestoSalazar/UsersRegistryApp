package org.ernesto.builders;

import org.ernesto.models.Person;

public class PersonBuilder {

    private int id;
    private String name;
    private int age;


    public PersonBuilder setId(int id){
        this.id = id;
        return this;
    }

    public PersonBuilder setName(String name){
        this.name = name;
        return this;
    }

    public PersonBuilder setAge(int age){
        this.age = age;
        return this;
    }

    public Person build(){
        Person person = new Person();
        person.setId(this.id);
        person.setName(this.name);
        person.setAge(this.age);
        return person;
    }


}
