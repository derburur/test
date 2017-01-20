package org.androidtown.mylist;

/**
 * Created by PB on 2016-10-08.
 */
public class SingerItem {
    String name;
    String age;


    public SingerItem() {

    }

    public SingerItem(String age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
