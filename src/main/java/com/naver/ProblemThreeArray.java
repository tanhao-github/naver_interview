package com.naver;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author tanchaowen
 */
public class ProblemThreeArray<E> {

    private E[] data;
    private int data_length;
    private int size;

    public ProblemThreeArray(int initSize) {
        this.data = (E[]) new Object[initSize];
        this.size = initSize;
        this.data_length = 0;
    }

    public ProblemThreeArray() {
        this(20);
    }

    /**
     * 把元素放入队列尾部
     *
     * @param element 元素
     */
    public void push(E element) {
        if (size() >= size) {
            grow();
        }
        data[data_length++] = element;
    }

    /**
     * 获取队列头元素并删除
     *
     * @return 头元素
     */
    public E pop() {
        if (empty()) {
            throw new NoSuchElementException("queue is empty");
        }
        E first = data[0];
        data = Arrays.copyOfRange(data, 1, size);
        data_length--;
        return first;
    }

    /**
     * 获取队列头元素，不删除
     *
     * @return 头元素
     */
    public E peek() {
        if (empty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return data[0];
    }

    /**
     * 判断队列是否为空
     *
     * @return true代表空队列
     */
    public boolean empty() {
        return data_length == 0;
    }

    /**
     * 队列的大小
     *
     * @return 大小值
     */
    public int size() {
        return data_length;
    }

    /**
     * 扩容到原来数组的2倍
     */
    private void grow() {
        size = size * 2;
        data = Arrays.copyOf(data, size);
    }

    public static void main(String[] args) {
        ProblemThreeArray<Integer> queue = new ProblemThreeArray<>();
        queue.push(0);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println("queue is empty: " + queue.empty());
        System.out.println("queue pop: " + queue.pop());
        System.out.println("queue pop: " + queue.pop());
        System.out.println("queue peek: " + queue.peek());
        System.out.println("queue peek: " + queue.peek());
    }

}
