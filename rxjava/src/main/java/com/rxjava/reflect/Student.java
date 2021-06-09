package com.rxjava.reflect;

/**
 * @author caixiaoxiao
 * @desc 学生
 * @date 5/8/21
 */
public class Student {
    public String name;
    public String age;

    public Student(){
        this.name = "default";
        this.age = "11";
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
