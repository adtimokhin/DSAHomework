package ru.adtimokhin.lesson2;

public class Second {
    public static void main(String args[]) {
//        int[] arr; // int arr[];
//        arr = new int[5];
//        int[] arr2 = {1, 2, 3, 4, 5}; // -X-> 4
//        System.out.println(arr.length);
//        System.out.println(arr[4]);
//        System.out.println(Arrays.toString(arr2));

        Array arr = new Array(5);
        arr.append(1);
        arr.append(2);
        arr.append(3);
        System.out.println(arr);
        arr.append(4);
        arr.append(5);
        arr.append(6);
        arr.append(7);
        arr.append(8);
        System.out.println(arr);
        arr.remove();
        arr.remove();
        System.out.println(arr);
        arr.delete(1);
        System.out.println(arr);
        arr.append(5);
        arr.append(-1);
        arr.append(5);
        arr.append(1);
        arr.append(5);
        arr.append(11);
        System.out.println(arr);
//        arr.sortPigeonhole(); // при количестве эллементов в 11, сложность: O(11)
//        arr.sortBubble(); // при количестве эллементов в 11, сложность: O(11*11)
//        arr.sortInsert();// 11 эллементов, сложность : O(32)
        arr.sortSelect();// 11 эллементов, сложность : O(66)
        System.out.println(arr);


    }
}