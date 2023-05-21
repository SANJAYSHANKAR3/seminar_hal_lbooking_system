package com.example.minp;



public class person
{
    // Variable to store data corresponding
    // to firstname keyword in database
    String name;


    person(){

    }
    // Mandatory empty constructor
    // for use of FirebaseUI
    public person(String name) {
        this.name=name;
    }

    // Getter and setter method
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

}