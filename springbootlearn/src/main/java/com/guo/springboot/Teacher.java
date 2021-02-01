package com.guo.springboot;

/**
 * @Date: 2021/1/28 10:47
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class Teacher extends Person{

    private String name="Tom";

    public Teacher() {
        System.out.println("this is teacher");
    }


    public static void main(String[] args) {
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Person {
    public Person() {
        System.out.println("this is person");
    }
}