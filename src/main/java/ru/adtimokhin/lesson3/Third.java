package ru.adtimokhin.lesson3;

import java.io.File;
import java.io.IOException;

public class Third {
    public static void main(String[] args) throws IOException {
        //1.
        new PairMatcher(new File("hw3.txt"));
        new PairMatcher("(,<><><>)");
    }
}