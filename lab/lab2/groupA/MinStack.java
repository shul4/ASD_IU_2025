package groupA;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//1. Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.

public class MinStack<T extends Comparable<T>> {
    public Stack<T> generalStack, minimumStack;


    public MinStack() {
        generalStack = new Stack<T>(); //Общий стек всех элементов.
        minimumStack = new Stack<T>(); //Стек минимальных элементов.
    }


    public T push(T element) {
        generalStack.push(element);
        //При необходимости нужно положить элемент в стек минимумов.
        if (minimumStack.empty()) minimumStack.push(element);
        else  if (element.compareTo(minimumStack.peek()) <= 0) minimumStack.push(element);
        return element;
    }


    public T pop() {
        T popped = generalStack.pop();
        //Если удаляемое из стека значение является при этом минимальным, то его необходимо удалить из стека минимумов.
        if (popped.compareTo(minimumStack.peek()) == 0) minimumStack.pop();
        return popped;
    }

    public T peek() {
        return generalStack.peek();
    }


    public T getMin() {
        return minimumStack.peek();
    }

    public int size() {
        return generalStack.size();
    }

    public boolean isEmpty() {
        return generalStack.isEmpty();
    }

    public void print() {
        System.out.println(generalStack);
    }

    public void printReversed() {
        List<T> listOfStack = new ArrayList<>(generalStack);
        System.out.print("[");
        for (int i = generalStack.size() - 1; i > 1; i--) {
            System.out.print(listOfStack.get(i) + ", ");
        }
        if (!generalStack.isEmpty()) System.out.println(listOfStack.get(0) + "]");
        else System.out.println("]");
    }
}