package org.ernesto.models;

public class Person {

    private int id;
    private String name;
    private int age;

    private Person(PersonBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static class PersonBuilder {
        private int id;
        private String name;
        private int age;

        public PersonBuilder id(int id){
            this.id = id;
            return this;
        }

        public PersonBuilder name(String name){
            this.name = name;
            return this;
        }

        public PersonBuilder age(int age){
            this.age = age;
            return this;
        }

        public Person build(){
            return new Person(this);
        }

    }

}
