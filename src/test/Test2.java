package test;

import java.util.LinkedList;

public class Test2 {
    LinkedList<Integer> queue1 = new LinkedList<>();
    LinkedList<Integer> queue2 = new LinkedList<>();
    public void add(int x) {
        queue1.addLast(x);
    }
    public int pop() {
        if(size() != 0) {
            if(!queue1.isEmpty()) {
                System.out.println("check1");
                q1ToQ2();
                return queue1.removeFirst();
            } else {
                q1ToQ2();
                return queue2.removeFirst();
            }
        } else {
            System.out.println("stack is empty");
            return -1;
        }
    }
    private void q1ToQ2() {
        if(!queue1.isEmpty()) {
            while(queue1.size() > 1) {
                queue2.addLast(queue1.removeFirst());
                System.out.println("check");
            }
        } else if(!queue2.isEmpty()) {
            while ((queue2.size() > 1)) {
                queue1.addLast(queue2.removeFirst());
            }
        }
    }
    public int size() {
        return queue1.size() + queue2.size();
    }
    public static void main(String[] args) {
        Test2 test = new Test2();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        System.out.println(test.pop());
        test.add(5);
        test.add(6);
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
    }
}
