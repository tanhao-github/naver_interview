package com.naver;

import java.util.Stack;

/**
 * 基于栈实现的队列
 *
 * 使用一个缓冲栈进行实现
 *
 * @author tanchaowen
 */
public class ProblemThreeStack<E> {

    /**
     * 首栈
     */
    private Stack<E> stackFrom;

    /**
     * 缓冲栈
     */
    private Stack<E> stackTo;

    /**
     * 初始化队列栈结构
     */
    public ProblemThreeStack() {
        stackFrom = new Stack();
        stackTo = new Stack();
    }

    /**
     * 元素加入到队列中
     */
    public void push(E element) {
        //首栈
        stackFrom.push(element);
    }

    /**
     * 获取队列头元素并删除
     */
    public E pop() {
        //从首栈中把元素加入到缓冲栈，缓冲栈的第一个元素就是最后加入到队列中的元素
        while (!stackFrom.isEmpty()) {
            stackTo.push(stackFrom.pop());
        }
        E result = stackTo.pop();
        //数据还原到首栈中
        while (!stackTo.isEmpty()) {
            stackFrom.push(stackTo.pop());
        }
        return result;
    }

    /**
     * 获取队列头元素不删除
     */
    public E peek() {
        while (!stackFrom.isEmpty()) {
            stackTo.push(stackFrom.pop());
        }
        E result = stackTo.peek();
        while (!stackTo.isEmpty()) {
            stackFrom.push(stackTo.pop());
        }
        return result;
    }

    /**
     * 是否为空队列
     */
    public boolean empty() {
        return stackFrom.isEmpty();
    }

    public static void main(String[] args) {
        ProblemThreeStack<Integer> queue = new ProblemThreeStack<>();
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
