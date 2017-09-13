package test;

import java.util.Map;
import java.util.Stack;
import java.lang.Object;

public class Test {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    public void add(int x) {
        stack1.push(x);

    }
    public int pop() {
        if(size() != 0) {
            if(stack2.isEmpty()) {
                stack1ToStack2();
            }
            return stack2.pop();
        } else {
            System.out.println("queue is empty");
            return -1;
        }
    }
    private void stack1ToStack2() {
        while(!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
    public int size() {
        return stack1.size() + stack2.size();
    }
    public static void main(String[] arges) {
        Test test = new Test();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        System.out.println(test.pop());
        test.add(5);
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());

    }
}
