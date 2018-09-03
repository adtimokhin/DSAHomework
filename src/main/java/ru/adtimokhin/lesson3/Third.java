package ru.adtimokhin.lesson3;

import ru.adtimokhin.lesson2.Array;

import java.io.File;
import java.io.IOException;

public class Third {
    public static void main(String[] args) throws IOException {
        //1.
        new PairMatcher(new File("hw3.txt"));
        new PairMatcher("(,<><><>)");
        /*//2.
        ReverseReader rr = new ReverseReader(new File("Write.txt"), true, new File("part3.txt"));
        rr.addText("der text oil");
        rr.readFromCurrentFile();
        //3.
        Deque deque = new Deque();
        deque.addTop(1, 2, 3, 4, 5, 6, 7);
        deque.addBottom(1, 2, 3, 4, 5, 6, 7);
        System.out.println(deque);
        deque.popBottom();
        deque.addBottom(1);
        System.out.println(deque);
        //4.
        PriorityQueue pq = new PriorityQueue(8);
        pq.add(1,2,3,4,5,4,3,2,0);
        pq.pop();
        pq.pop();
        pq.add(-1,-1);
        pq.pop();
        System.out.println(pq);*/
    }
}