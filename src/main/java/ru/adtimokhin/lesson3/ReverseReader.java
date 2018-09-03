package ru.adtimokhin.lesson3;

import com.sun.istack.internal.NotNull;

import java.io.*;

/**
 * Class ReverseReader. Created by adtimokhin. 02.09.2018 (20:50)
 **/


public class ReverseReader {
    private Stack stack = new Stack(20);
    private DataInputStream dis;
    private BufferedOutputStream bos;
    private boolean wordsSeparate;
    private boolean writeToConsole = false;
    private int letters = 0;
    private File file;


    public ReverseReader(File readingFile, boolean wordsSeparate, File destinationFile) throws IOException {
        if (destinationFile == null) {
            writeToConsole = true;
        } else
            bos = new BufferedOutputStream(new ObjectOutputStream(new FileOutputStream(destinationFile)));
        this.file = destinationFile;
        this.wordsSeparate = wordsSeparate;
        reverseFile(readingFile);
    }

    public ReverseReader(String text, boolean wordsSeparate, File destinationFile) throws IOException {
        this.wordsSeparate = wordsSeparate;
        if (destinationFile == null)
            writeToConsole = true;
        else bos = new BufferedOutputStream(new ObjectOutputStream(new FileOutputStream(destinationFile)));
        this.file = destinationFile;
        reverseText(text);
    }


    private void reverseText(@NotNull String text) {
        char arr[] = text.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (wordsSeparate && arr[i] == 32) {
                print();
                letters = 1;
                stack.push(arr[i]);
                print();
            } else {
                stack.push(arr[i]);
                letters++;
            }
        }

        if (letters != 0)
            print();
    }

    private void reverseFile(File readingFile) throws IOException {
        dis = new DataInputStream(new FileInputStream(readingFile));
        System.out.println(readingFile.getName() + ":");
        assert dis != null;
        int val = dis.read();
        while (val != -1) {
            if (32 == val && wordsSeparate) {
                print();
                letters = 1;
                stack.push(val);
                print();
            } else {
                stack.push(val);
                letters++;
            }
            val = dis.read();
        }
        if (letters != 0)
            print();
        System.out.println("\n"+readingFile.getName() + " - запись завершена");
    }

    private void print() {
        for (int i = 0; i < letters; i++) {
            if (writeToConsole) {
                char sym = (char) stack.pop();
                System.out.print(sym);
            } else {
                try {
                    bos.write((char)stack.pop());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        letters = 0;


    }

    public void addText(String text) {
        reverseText(text);
    }

    public void addFile(@NotNull File newReadingFile) {
        try {
            reverseFile(newReadingFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWritingFile(File dest) throws IOException {
        if (dest == null)
            writeToConsole = true;
        else {
            writeToConsole = false;
            bos = new BufferedOutputStream(new ObjectOutputStream(new FileOutputStream(dest)));
            file = dest;
        }
    }

    public void close() throws IOException {
        dis.close();
        bos.close();
    }
    public void readFromCurrentFile() throws IOException {
        if(file==null)throw new RuntimeException("This ReverseReader doesn't write into a File");
        else {
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            int val = dis.read();
            while (val!=-1) {
                System.out.print((char) val);
                val = dis.read();
            }
        }
    }
}
