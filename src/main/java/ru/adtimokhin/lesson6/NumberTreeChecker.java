package ru.adtimokhin.lesson6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class NumberTreeChecker. Created by adtimokhin. 12.09.2018 (10:05)
 **/
public class NumberTreeChecker {
    Random random = new Random();
    List<NumberTree> trees = new ArrayList<>();
    int elementNumber=0;

    public void generateTrees(int treeNumber, int elementNumber){
        if(elementNumber < 0)throw new RuntimeException("How to put minus no. of elements !?");
        for (int i = 0; i < treeNumber; i++) {
            trees.add(new NumberTree());
            for (int j = 0; j < elementNumber; j++) {
                trees.get(i).insert(random.nextInt(200)-100);
            }
        }
        this.elementNumber = elementNumber;
    }
    public float checkBalance(){
        float percentage = 0;
        if(elementNumber ==0){
            return 100f;
        }
        for (int i = 0; i < trees.size(); i++) {
            percentage += trees.get(i).isBalanced(trees.get(i).root, 0)*100;
        }
        return percentage/=trees.size();

    }


}
