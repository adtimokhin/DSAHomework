package ru.adtimokhin.lesson3;

import ru.adtimokhin.lesson2.Array;

import java.io.File;
import java.io.IOException;

public class Third {
    public static void main(String[] args) throws IOException {
        //1.
        new PairMatcher(new File("hw3.txt"));
        new PairMatcher("(,<><><>)");
        //2.
        ReverseReader rr = new ReverseReader(new File("Write.txt"), true, null);
        rr.addText("der text oil");
        //3.
        Deque deque = new Deque();
        deque.addTop(1, 2, 3, 4, 5, 6, 7);
        deque.addBottom(1, 2, 3, 4, 5, 6, 7);
        System.out.println(deque);
        deque.popBottom();
        deque.addBottom(1);
        System.out.println(deque);
    }
}