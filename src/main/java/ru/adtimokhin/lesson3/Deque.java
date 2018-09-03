package ru.adtimokhin.lesson3;

import ru.adtimokhin.lesson2.Array;

/**
 * Class Deque. Created by adtimokhin. 03.09.2018 (09:32)
 **/
public class Deque {
    /**
     * Class Deque is a simpler version of java.util.Deque
     * @see java.util.Deque
     * Class ru.adtimokhin.lesson2.Deque is a usual deque, to which you can add/remove elements from both ends.
     **/

    /**
     * Two arrays, which realising functions of a queue. In the work with them user won't see huge difference as if those were two queues
     *
     * @see Array
     * @see java.util.Queue
     **/
    private Array topArray;
    private Array bottomArray;

    public Deque() {
        this.topArray = new Array(8);
        this.bottomArray = new Array(8);
    }

    /**
     * Method addTop(int value) adds new element to the end of topArray
     */
    public void addTop(int value) {
        topArray.append(value);
    }

    /**
     * First value from values[] would go first into the end of the deque.
     */
    public void addTop(int... values) {
        for (int i = 0; i < values.length; i++) {
            topArray.append(values[i]);
        }
    }

    /**
     * Method addBottom(int value) moves whole bottomArray by 1 unit to the right, making possible to insert into bottomArray[0] int value
     **/
    public void addBottom(int value) {
        bottomArray.moveFowardBy(1);
        bottomArray.set(0, value);
    }

    /**
     * Method addBottom(int ... values) moves whole bottomArray by values.length units to the right, creating new spaces for all integers from values[]. First value would be insert
     * to the first created spot, which is the closest to the end of bottomArray
     **/
    public void addBottom(int... values) {
        bottomArray.moveFowardBy(values.length);
        for (int i = values.length; i > 0; i--) {
            bottomArray.set(i - 1, values[values.length - i]);
        }
    }

    /**
     * Method peekTop() takes topArray[0] and returns it. If topArray is empty, bottomArray[0] would be returned ( which is at the beginning of bottomArray)
     * Selected element would not be deleted
     * If both topArray and bottomArray are empty,
     *
     * @throws RuntimeException
     */
    public int peekTop() {
        if (topArray.length() != 0) return topArray.get(0);
        else if (bottomArray.length() != 0) return bottomArray.get(0);
        else throw new RuntimeException("Deque is empty");
    }

    /**
     * Method peekBottom() takes bottomArray[bottomArray.length - 1] and returns it. If bottomArray is empty, topArray[topArray.length -1] would be returned ( which is at the beginning of bottomArray)
     * Selected element would not be deleted
     * If both topArray and bottomArray are empty,
     *
     * @throws RuntimeException
     */
    public int peekBottom() {
        if (bottomArray.length() != 0) return bottomArray.getLast();
        else if (topArray.length() != 0) return topArray.getLast();
        else throw new RuntimeException("Deque is empty");
    }

    /**
     * Method popTop() takes topArray[0] and returns it. If topArray is empty, bottomArray[0] would be returned ( which is at the beginning of bottomArray)
     * Selected element would  be deleted
     * If both topArray and bottomArray are empty,
     *
     * @throws RuntimeException
     */
    public int popTop() {
        if (topArray.length() != 0) {
            int temp = topArray.get(0);
            topArray.delete(0);
            return temp;
        } else if (bottomArray.length() != 0) {
            int temp = bottomArray.get(0);
            bottomArray.delete(0);
            return temp;
        } else throw new RuntimeException("Deque is empty");
    }

    /**
     * Method popBottom() takes bottomArray[bottomArray.length - 1] and returns it. If bottomArray is empty, topArray[topArray.length -1] would be returned ( which is at the beginning of bottomArray)
     * Selected element would  be deleted
     * If both topArray and bottomArray are empty,
     *
     * @throws RuntimeException
     */
    public int popBottom() {
        if (bottomArray.length() != 0) {
            int temp = bottomArray.getLast();
            bottomArray.remove();
            return temp;
        } else if (topArray.length() != 0) {
            int temp = topArray.getLast();
            topArray.remove();
            return temp;
        } else throw new RuntimeException("Deque is empty");
    }

    /**
     * Method removeTop() would delete topArray[0]. If topArray is empty, the method would delete bottomArray[0]
     * If both topArray and bottomArray are empty,
     *
     * @throws RuntimeException
     **/
    public void removeTop() {
        if (topArray.length() != 0) topArray.delete(0);
        else if (bottomArray.length() != 0) bottomArray.delete(0);
        else throw new RuntimeException("Deque is empty");
    }

    /**
     * Method removeBottom() would delete bottomArray[bottom.length -1]. If bottomArray is empty, the method would delete  topArray[topArray.length -1]
     * If both topArray and bottomArray are empty,
     *
     * @throws RuntimeException
     **/
    public void removeBottom() {
        if (bottomArray.length() != 0) bottomArray.remove();
        else if (topArray.length() != 0) topArray.remove();
        else throw new RuntimeException("Deque is empty");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < topArray.length(); i++) {
            stringBuilder.append(topArray.get(i));
            if (bottomArray.length() != 0 || bottomArray.length() == 0 && i != topArray.length() - 1)
                stringBuilder.append(", ");
        }
        for (int i = 0; i < bottomArray.length(); i++) {
            stringBuilder.append(bottomArray.get(i));
            if (i != bottomArray.length() - 1)
                stringBuilder.append(" ,");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
