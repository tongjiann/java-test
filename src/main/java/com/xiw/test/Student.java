package com.xiw.test;

public class Student {
    private int no;
    private int sex;
    private String name;

    public Student() {
    }

    public Student(int no, int sex) {
        new Student(no,sex,"123");
    }

    public Student(int no, int sex, String name) {
        this.no = no;
        this.sex = sex;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no=" + no +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }
}
