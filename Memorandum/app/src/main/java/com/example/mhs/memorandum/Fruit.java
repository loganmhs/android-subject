package com.example.mhs.memorandum;

import org.litepal.crud.DataSupport;

public class Fruit extends DataSupport{
    private String name;
    private String CNname;

    public Fruit(String name,String CNname){
        this.name=name;
        this.CNname=CNname;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setCNname(String CNname) {
        this.CNname = CNname;
    }

    public String getCNname() {
        return CNname;
    }
}
