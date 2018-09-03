package ru.adtimokhin.lesson3;

import ru.adtimokhin.lesson2.Array;

/**
 * Class PriorityQueue. Created by adtimokhin. 03.09.2018 (17:56)
 **/
public class PriorityQueue {
    private Array queue;

    public PriorityQueue(int size) {
        queue = new Array(size);
    }
    public void add(int val){
        queue.append(val);
    }
    public void add(int... values){
        for (int i = 0; i < values.length; i++) {
            queue.append(values[i]);
        }
    }
    public int pop(){
        if(queue.isEmpty())
            throw new RuntimeException("Queue is empty");
        int temp = queue.get(0);
        queue.delete(0);
        return temp;
    }
    public void sort(){
        queue.sortBubble();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < queue.length(); i++) {
            stringBuilder.append(queue.get(i));
            if(i!=queue.length()-1)
                stringBuilder.append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
