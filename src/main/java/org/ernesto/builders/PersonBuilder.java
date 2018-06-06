package org.ernesto.builders;

import org.ernesto.models.Person;

public class PersonBuilder {

    private int id;
    private String name;
    private String nickname;
    private int age;
    private String password;

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

    public PersonBuilder setNickname(String nickname){
        this.nickname = nickname;
        return this;
    }

    public PersonBuilder setPassword(String password){
        this.password = password;
        return this;
    }

    public Person build(){
        Person person = new Person();
        person.setId(this.id);
        person.setName(this.name);
        person.setNickname(this.nickname);
        person.setAge(this.age);
        person.setPassword(this.password);
        return person;
    }


}
