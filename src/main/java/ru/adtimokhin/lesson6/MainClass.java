package ru.adtimokhin.lesson6;

/**
 * Class MainClass. Created by adtimokhin. 12.09.2018 (09:59)
 **/
public class MainClass {
    public static void main(String[] args) {
        NumberTreeChecker ntc = new NumberTreeChecker();
        ntc.generateTrees(100,100);
        System.out.println(ntc.checkBalance()+"%");
    }
}
