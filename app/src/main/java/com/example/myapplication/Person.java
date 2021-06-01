package com.example.myapplication;

import androidx.recyclerview.widget.RecyclerView;

public class Person  {
    private int id;
    private String name;
    private int age;
    private String lop;

    public Person() {
    }

    public Person( int id,String name, int age, String lop) {
        this.id=id;
        this.name = name;
        this.age = age;
        this.lop = lop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
