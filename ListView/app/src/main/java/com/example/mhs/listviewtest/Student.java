package com.example.mhs.listviewtest;

public class Student {
    private String name;
    private String cla;
    private String num;
    private String sex;
    private String phone;

    public Student(String name, String cla, String num, String sex, String phone) {
        this.name=name;
        this.cla=cla;
        this.num=num;
        this.sex=sex;
        this.phone=phone;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public String getCla() {
        return cla;
    }

    public String getNum() {
        return num;
    }

    public String getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }
}
