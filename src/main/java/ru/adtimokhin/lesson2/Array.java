package ru.adtimokhin.lesson2;

public class Array {
    private int[] arr;
    private int size;
    private boolean isSorted;

    private Array() {
        isSorted = false;
    }

    public Array(int size) {
        this();
        this.size = 0;
        arr = new int[size];
    }

    public Array(int... args) { // int[] args = new int[args.length];
        this();
        size = args.length;
        arr = args;
    }

    public boolean isSorted() {
        return isSorted;
    }

    public int length() {
        return size;
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException("WTF!");
        return arr[index];
    }
    public int getLast(){
        return arr[size-1];
    }
    public void moveFowardBy(int units){
        for (int i = 0; i < units; i++) {
            int temp[] = new int[size+1];
            for (int j = 0; j < temp.length; j++) {
                if(j==0)temp[j]=j;
                else temp[j] = arr[j-1];
            }
           arr = new int[temp.length];
            System.arraycopy(temp,0,arr,0,temp.length);
            size = arr.length;
        }
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException("WTF!");
        arr[index] = value;
        isSorted =false;
    }

    public void append(int value) {
        if (size >= arr.length - 1) {
            int[] temp = arr;
            arr = new int[size * 2];
            System.arraycopy(temp, 0, arr, 0, size);
        }
        arr[size++] = value;
        isSorted=false;
    }

    public boolean remove() {
        if (size == 0)
            return false;
        size--;
        return true;
    }

    // homework
    public boolean delete(int index) { // сдвигаем эллементы влево
        if(index<0 || index >= size)
            return false;
        for (int i = index; i < size-1; i++) {
            arr[i] = arr[i+1];
        }
        size--;
        isSorted=false;
        return true;
   }
    public boolean deleteAll(int value) {
        if(size ==0)return false;
        for (int i = 0; i < size; i++) {
            if(arr[i]==value) {
                delete(i);
                deleteAll(value);
            }
        }
        return true;
   }
    public boolean deleteAll() {
        size-=size;
        return true;
    }

    public boolean isInArray(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                return true;
            }
        }
        return false;
    }
    // k << n == k * 2 ^ n
// k >> n == k / 2 ^ n
    public int find(int value) {
        if (!isSorted)
            throw new RuntimeException("Trying to search in unsorted array");
        int l = 0;
        int r = size;
        int m;
        while (l < r) {
            m = (l + r) >> 1; // 8 = 00001000 >> 2 = 00000010 = 2
            if (value == arr[m]) {
                return m;
            } else {
                if (value < arr[m]) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
        }
        return -1;
    }

    private void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        isSorted=false;
    }
    //sorting
    public void sortBubble() {
        int count =0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (arr[j] > arr[j + 1])
                    swap(j, j + 1);
                count++;
            }
        }
        isSorted = true;
        System.out.println("SortBubble: count = " + count);
        // сложность : O(n^2)
    }

    public void sortSelect() {
        int f;
        int count =0;
        for (int i = 0; i < size; i++) {
            f = i;
            for (int j = i + 1; j < size; j++) {
                if (arr[j] < arr[f])
                    f = j;
                count++;
            }
            swap(i, f);
            count++;
        }
        System.out.println("SortSelect: count = " + count);
        isSorted = true;
        // сложность : O(n^2)
    }

    public void sortInsert() {
        int count =0;
        for (int i = 1; i < size; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
                count++;
            }
            arr[j] = temp;
            count++;

        }
        System.out.println("SortInsert: count = " + count);
        isSorted = true;
        // сложность : O(n^2).
    }
    public void sortPigeonhole(){
        int count =0;
        int min = arr[0], max = arr[0];
        for (int i = 0; i < size; i++) {
            if(min>arr[i])
                min = arr[i];
            if(max<arr[i])
                max = arr[i];
        }
        int temp[] = new int[max-min+1];
        int k =0;
        for (int i = min; i <= max; i++) {
            for (int j = 0; j < size; j++) {
                if(i == arr[j])temp[k]++;
            }
            k++;
        }

       k =0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i]; j++) {
                arr[k] = i+min;
                k++;
                count++;
            }
        }
        isSorted=true;
        System.out.println("SortPigeonhole : count = " + count);
        // сложность : O(n).
    }

    @Override
    public String toString() {
        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(arr[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}