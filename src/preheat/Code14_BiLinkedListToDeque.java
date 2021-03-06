package preheat;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: duccio
 * @Date: 21, 03, 2022
 * @Description: Implement Deque that can offer/poll from both ends, using bi-directional linked list
 * @Note:   Uni-directional linked list is not sufficient for polling from tail
 */
public class Code14_BiLinkedListToDeque {
    public static void main(String[] args) {
        validateMyDeque();
    }

    // bi-directional node
    public static class BiNode<V> {
        V value;
        BiNode<V> next;
        BiNode<V> pre;

        public BiNode(V val) {
            value = val;
        }
    }

    public static class MyDeque<V> {
        int size;
        BiNode<V> head;
        BiNode<V> tail;

        public MyDeque() {
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offerHead(V val) {
            BiNode<V> cur = new BiNode<>(val);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.pre = cur;
                head = cur;
            }
            size++;
        }

        public void offerTail(V val) {
            BiNode<V> cur = new BiNode<>(val);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.pre = tail;
                tail = cur;
            }
            size++;
        }

        public V pollHead() {
            if (head == null) {
                return null;
            }
            V res = head.value;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.pre = null;
            }
            size--;
            return res;
        }

        public V pollTail() {
            if (tail == null) {
                return null;
            }
            V res = tail.value;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.pre;
                tail.next = null;
            }
            size--;
            return res;
        }

        public V peekHead() {
            return head == null ? null : head.value;
        }

        public V peekTail() {
            return tail == null ? null : tail.value;
        }
    }

    public static void validateMyDeque() {
        MyDeque<Integer> myDeque = new MyDeque<>();
        Deque<Integer> testDeque = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        for (int i = 0; i < testTime; i++) {
            if (myDeque.isEmpty() != testDeque.isEmpty()) {
                System.out.println("Failed on isEmpty()");
            }
            if (myDeque.size() != testDeque.size()) {
                System.out.println("Failed on size()");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                if (Math.random() < 0.5) {
                    myDeque.offerHead(num);
                    testDeque.addFirst(num);
                } else {
                    myDeque.offerTail(num);
                    testDeque.addLast(num);
                }
            } else if (decide < 0.66) {
                if (!myDeque.isEmpty()) {
                    int num1 = 0;
                    int num2 = 0;
                    if (Math.random() < 0.5) {
                        num1 = myDeque.pollHead();
                        num2 = testDeque.pollFirst();
                    } else {
                        num1 = myDeque.pollTail();
                        num2 = testDeque.pollLast();
                    }
                    if (num1 != num2) {
                        System.out.println("Failed on poll");
                    }
                }
            } else {
                if (!myDeque.isEmpty()) {
                    int num1 = 0;
                    int num2 = 0;
                    if (Math.random() < 0.5) {
                        num1 = myDeque.peekHead();
                        num2 = testDeque.peekFirst();
                    } else {
                        num1 = myDeque.peekTail();
                        num2 = testDeque.peekLast();
                    }
                    if (num1 != num2) {
                        System.out.println("Failed on peek()");
                    }
                }
            }
        }
        if (myDeque.size() != testDeque.size()) {
            System.out.println("Failed on size()..");
        }
        while (!myDeque.isEmpty()) {
            int num1 = myDeque.pollHead();
            int num2 = testDeque.pollFirst();
            if (num1 != num2) {
                System.out.println("Failed on poll..");
            }
        }
        System.out.println("Test passed!");
    }

}
