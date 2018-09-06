package ru.adtimokhin.lesson4;

import java.util.Objects;

/**
 * Class DoubleRelatedList. Created by adtimokhin. 05.09.2018 (11:32)
 **/
public class DoubleRelatedList<T> extends RelatedList<T> {
    private int number =0;
    private class Node<T>{
        T c;
        Node<T> next;
        Node<T> previous;
        public Node(T c) {
            this.c = c;
        }
        @Override
        public String toString() {
            return c.toString();
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node<T> node = (Node) o;
            return Objects.equals(c, node.c);
        }
    }
    private Node<T> head;
    private Node<T> tail;
    public DoubleRelatedList() {
    head =null;
    tail =null;
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public void insert(T c) {
        Node<T> n = new Node<>(c);
        if(head==null){
            tail = n;
            head = n;
            tail.previous = head;
            head.next = head;
            setHeadTail();
        }else {
            n.next =head;
            head.previous =n;
            head = n;
            setHeadTail();
        }
        number++;
    }

    @Override
    public T remove() {
        T c = head.c;
        head = head.next;
        setHeadTail();
        number--;
        return c;
    }

    @Override
    public boolean contains(T c) {
        Node<T> current = head;
       while (!current.c.equals(c)){
           if(current==tail)return false;
           current = current.next;
       }
       return true;
    }

    @Override
    public T delete(String name) {
        Node<T> previous = tail;
        Node<T> current = head;
        while (!(((Cat)current.c).getName()).equals(name)) {
            if (current == tail) return null;
             previous = current;
             current = current.next;
        }
        T temp = current.c;
        previous.next = current.next;
        previous.next.previous = current.previous;
        current = null;
        number--;
        return temp;
    }
    private void setHeadTail(){
        head.previous = tail;
        tail.next = head;
    }

    @Override
    public String toString() {
        System.out.println(number);
        int number = 0;
        if (head == null) return "[]";
        Node<T> current = head;
        StringBuilder sb = new StringBuilder("[");
        while (number != this.number) {
            sb.append(current);
            current = current.next;
            number++;
            sb.append((number== this.number) ? "]" : ", ");

        }

        return sb.toString();
    }
}
