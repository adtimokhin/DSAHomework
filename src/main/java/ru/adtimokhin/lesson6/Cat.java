package ru.adtimokhin.lesson6;

/**
 * Class Cat. Created by adtimokhin. 12.09.2018 (09:47)
 **/
public class Cat {
    private static int uid = 0;
    String name;
    int age;
    int id;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        id = uid++;
    }
}