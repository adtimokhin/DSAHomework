package ru.adtimokhin.lesson3;

import com.sun.istack.internal.NotNull;

import java.io.*;

/**
 * Class ReverseReader. Created by adtimokhin. 02.09.2018 (20:50)
 **/


public class ReverseReader {
    /**
     * Class ReverseReader's purpose to mirror whole entered text or each word separately,
     * but keeping them in same positions as they were in the text
     **/

    /**
     * All the symbols are going to placed in stack
     *
     * @see Stack
     * It is the easiest way to mirror words/whole text
     **/
    private Stack stack = new Stack(20);
    /**
     * @see DataInputStream dis is used to read from the file, which was given in the constructor{@linkplain #ReverseReader(File, boolean, File)}
     * @see BufferedOutputStream bos is used to write symbols from the stack into a destinationFile
     **/
    private DataInputStream dis;
    private BufferedOutputStream bos;
    /**
     * Those boolean-flags are used for setting 2 parameters:
     * 1. Should each word be mirrored
     * 2. Should the class write into the console
     **/
    private boolean wordsSeparate;
    private boolean writeToConsole = false;

    private int letters = 0;
    /**
     * @see File file is used to read text after it was flushed into a text file. Should notice : this file may change({@linkplain #changeWritingFile(File)})
     * as a programm runs, meaning this class can read other, not initialy setted file
     **/
    private File file;

    /**
     * Constructor.
     *
     * @param readingFile     used to get symbols. Also used to set{@linkplain #dis} in {@linkplain #reverseFile(File)}
     * @param wordsSeparate   1 of 2 boolean-flags, which sets {@linkplain #wordsSeparate}
     * @param destinationFile If it is equals to null, {@linkplain #writeToConsole} = true and the text would be printed into
     *                        the console immediately. This file also used to set {@linkplain #bos} and {@linkplain #file}
     **/
    public ReverseReader(File readingFile, boolean wordsSeparate, File destinationFile) throws IOException {
        if (destinationFile == null) {
            writeToConsole = true;
        } else
            bos = new BufferedOutputStream(new ObjectOutputStream(new FileOutputStream(destinationFile)));
        this.file = destinationFile;
        this.wordsSeparate = wordsSeparate;
        reverseFile(readingFile);
    }

    /**
     * Constructor.
     *
     * @param text            source of symbols. Used in {@linkplain #reverseText(String)}
     * @param wordsSeparate   1 of 2 boolean-flags, which sets {@linkplain #wordsSeparate}
     * @param destinationFile If it is equals to null, {@linkplain #writeToConsole} = true and the text would be printed into
     *                        the console immediately. This file also used to set {@linkplain #bos} and {@linkplain #file}
     **/
    public ReverseReader(String text, boolean wordsSeparate, File destinationFile) throws IOException {
        this.wordsSeparate = wordsSeparate;
        if (destinationFile == null)
            writeToConsole = true;
        else bos = new BufferedOutputStream(new ObjectOutputStream(new FileOutputStream(destinationFile)));
        this.file = destinationFile;
        reverseText(text);
    }

    /**
     * Method used to put symbols into a {@linkplain #stack}. Also calls {@linkplain #print()}, depending on a boolean-flag {@linkplain #wordsSeparate}
     *
     * @param text converts into a char arr[]. This char is used instead of {@linkplain #dis}, sending symbols one by one into {@linkplain #stack}.
     *             If char arr[i] == 32 (symbol of SPACE) then depending on{@linkplain #wordsSeparate}, method {@linkplain #print()}
     *             would be called to mirror the word, which is in the {@linkplain #stack}, or  cycle continues.
     *             {@linkplain #letters} is a variable, which is used to indicate how many elements in {@linkplain #stack}
     *             are left. This is used, so at the end of the method mirror whole
     *             text if {@linkplain #wordsSeparate == {@code false}}.
     *             If {@linkplain #wordsSeparate == {@code true}} after each mirrored word space would be printed on its own, so words won't get stick together.
     **/
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

    /**
     * Method used to put symbols into a {@linkplain #stack}. Also calls {@linkplain #print()}, depending on a boolean-flag {@linkplain #wordsSeparate}
     *
     * @param readingFile is used to set {@linkplain #dis}. In a while - cycle until red symbol is not going to be -1, each symbols is going to be send into {@linkplain #stack}.
     *                    If dis.read == 32 (symbol of SPACE) then depending on {@linkplain #wordsSeparate}, method {@linkplain #print()} would be called to mirror the word,
     *                    which is in the {@linkplain #stack}, or cycle continues.
     *                    {@linkplain #letters} is a variable, which is used to indicate how many elements in {@linkplain #stack}
     *                    are left. This is used, so at the end of the method mirror whole
     *                    text if {@linkplain #wordsSeparate == {@code false}}.
     *                    If {@linkplain #wordsSeparate == {@code true}} after each mirrored word space would be printed on its own, so words won't get stick together.
     **/
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
        System.out.println("\n" + readingFile.getName() + " - запись завершена");
    }

    /**
     * Method print() is used to print all the elements from {@linkplain #stack}
     * Depending on{@linkplain #writeToConsole}, symbols will print in the console or in {@linkplain #file}, using {@linkplain #bos}
     * For - cycle would go on, until {@linkplain #stack} is would become empty (this is equivalent to until i < {@linkplain #letters})
     * At the end of the method, {@linkplain #letters} reset to 0, because it represents number of elements in {@linkplain #stack}, and at the end it should be empty
     *
     * @see Stack
     * @see BufferedOutputStream
     **/
    private void print() {
        for (int i = 0; i < letters; i++) {
            if (writeToConsole) {
                char sym = (char) stack.pop();
                System.out.print(sym);
            } else {
                try {
                    bos.write(stack.pop());
                    bos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        letters = 0;


    }

    /**
     * @param text would go as a parameter to the {@linkplain #reverseText(String)} to restart whole operation
     */
    public void addText(String text) {
        reverseText(text);
    }

    /**
     * @param newReadingFile will be used as a parameter into a {@linkplain #reverseFile(File)}
     **/
    public void addFile(@NotNull File newReadingFile) {
        try {
            reverseFile(newReadingFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param dest used to change {@linkplain #file} and {@link #bos}, sets {@link #writeToConsole}, but doesn't start
     *             write into file. {@linkplain #addFile(File)} just provides user with new file to read from
     **/
    public void changeWritingFile(File dest) throws IOException {
        if (dest == null)
            writeToConsole = true;
        else {
            writeToConsole = false;
            bos = new BufferedOutputStream(new ObjectOutputStream(new FileOutputStream(dest)));
            file = dest;
        }
    }

    /**
     * Method close() should be called at the end of using current ReverseReader.
     * It closes {@linkplain #dis} and {@linkplain #bos} to release them
     * If methods using those streams would be called
     *
     * @throws IOException
     **/
    public void close() throws IOException {
        dis.close();
        bos.close();
    }

    /**
     * Method readFromCurrentFile() is used to print insides of {@linkplain #file}, using {@linkplain #dis}, into the console
     * If {@linkplain #file} == {@code null}, then RunTimeException would be thrown
     * In other case, symbols will be printed in while-cycle, until dis.read!= -1
     **/
    public void readFromCurrentFile() throws IOException {
        if (file == null) throw new RuntimeException("This ReverseReader doesn't write into a File");
        else {
            dis = new DataInputStream(new FileInputStream(file));
            int val = dis.read();
            while (val != -1) {
                System.out.print((char) val);
                val = dis.read();
            }
        }
    }
}
