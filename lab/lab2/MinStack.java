import java.util.ArrayList;
import java.util.Stack;

/**
 * 1. Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.
 **/

public class MinStack {
    private final Stack<Integer> stack; // Общий стек.
    private final Stack<Integer> minStack; // Стек минмальных значений.

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public int push(int value) {
        stack.push(value); // При push всегда добавляем элемент в общий стек.

        // В стек минимумов добавляем элемент,
        // только если он меньше или равен значению на вершине стека минимумов.
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }

        return value;
    }

    public int pop() {
        int removed = stack.pop();

        // Если удаляемое значение является минимумом с вершины,
        // то удаляем его так же и из стека минимумов.
        if (removed == minStack.peek()) {
            return minStack.pop();
        } else {
            return removed;
        }
    }

    public int peek() {
        return stack.peek();
    }

    public int minimum() {
        return minStack.peek(); // Возвращает текущий минимум.
    }

    public int size() {
        return stack.size();
    }

    public void print() {
        System.out.println(stack);
    }

    public void printReversed() {
        ArrayList<Integer> list = new ArrayList<>(stack);
        System.out.print("[");
        if (!list.isEmpty()) {
            for (int i = list.size() - 1; i > 0; i--) {
                System.out.print(list.get(i) + ", ");
            }
            System.out.print(list.getFirst() + "]");
        } else {
            System.out.println("]");
        }
    }
}