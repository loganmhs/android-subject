package com.example.mhs.memorandum;

public class Fruit {
    private String name;
    private String CNname;
    public Fruit(String name,String CNname){
        this.name=name;
        this.CNname=CNname;
    }

    public String getCNname() {
        return CNname;
    }

    public String getName() {
        return name;
    }
}
