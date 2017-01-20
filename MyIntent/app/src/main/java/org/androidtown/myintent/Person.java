package org.androidtown.myintent;

import java.io.Serializable;

/**
 * Created by PB on 2016-09-11.
 */
public class Person implements Serializable{

    String name;
    int age;

    public Person(){

    }
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
