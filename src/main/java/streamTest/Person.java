package streamTest;

import lombok.Data;

@Data
public class Person {
    private String name; // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area; // 地区

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    // 构造方法
    public Person(String name, int salary, int age, String sex, String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

        // 构造方法
    public Person(String name, int age, String sex, String area) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }
    // 省略了get和set，请自行添加


}