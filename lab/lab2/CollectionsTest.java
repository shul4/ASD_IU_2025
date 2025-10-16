import groupA.MinStack;
import groupA.CircularQueue;

import java.util.EmptyStackException;
import java.util.List;

public class CollectionsTest {
    public static void main(String[] args) {
        System.out.println("MinStack<String>");
        MinStack<String> stringStack = new MinStack<>();
        testMinStack(stringStack, List.of("zebra", "apple", "banana", "ant", "cat"));
        /*MinStack<String>
        Start:
        *isEmpty(): true
        pushing elements:
        **push(e1): [zebra]
        *getMin(): zebra
        **push(e2): [zebra, apple]
        *getMin(): apple
        **push(e3): [zebra, apple, banana]
        *getMin(): apple
        **push(e4): [zebra, apple, banana, ant]
        *getMin(): ant
        **push(e5): [zebra, apple, banana, ant, cat]
        *getMin(): ant
        **size(): 5
        **print()
        [zebra, apple, banana, ant, cat]
        **printReversed()
        [cat, ant, banana, zebra]
        popping elements:
        **pop(): [zebra, apple, banana, ant]
        *getMin: ant
        **pop(): [zebra, apple, banana]
        *getMin: apple
        **pop(): [zebra, apple]
        *getMin: apple
        **pop(): [zebra]
        *getMin: zebra
        **pop(): []
        *getMin: EmptyStackException occurred
        End:
        *isEmpty(): true

        Process finished with exit code 0*/

        System.out.println("\n\n\nCircularQueue<String>");
        CircularQueue<String> stringQueue = new CircularQueue<>(5);
        testCircularQueue(stringQueue, List.of("zebra", "apple", "banana", "ant", "cat"));

        /*CircularQueue<String>
        Start:
        *isEmpty(): true
        enqueue elements:
        **enqueue(e1): [zebra]
        *peek(): zebra
        **enqueue(e2): [zebra, apple]
        *peek(): zebra
        **enqueue(e3): [zebra, apple, banana]
        *peek(): zebra
        **enqueue(e4): [zebra, apple, banana, ant]
        *peek(): zebra
        **enqueue(e5): [zebra, apple, banana, ant, cat]
        *peek(): zebra
        **size(): 5
        **isFull(): true
        **print()
        [zebra, apple, banana, ant, cat]
        **printReversed()
        [
        cat, ant, banana, apple, zebra]
        dequeue elements:
        **dequeue(): [apple, banana, ant, cat]
        **dequeue(): [banana, ant, cat]
        **dequeue(): [ant, cat]
        **dequeue(): [cat]
        **dequeue(): []
        Testing Exceptions
        dequeue from empty queue: IllegalStateException occurred - Queue is empty
        Filling queue to capacity:
        isFull(): true
        enqueue to full queue: IllegalStateException occurred - CircularQueue is overloaded
        End:
        *isEmpty(): false

        Process finished with exit code 0*/
    }

    public static <T extends Comparable<T>> void testMinStack(MinStack<T> stack, List<T> elements) {
        System.out.println("Start: ");
        System.out.println("*isEmpty(): " + stack.isEmpty());

        // Добавление элементов в цикле.
        System.out.println("pushing elements:");
        for (int i = 0; i < elements.size(); i++) {
            stack.push(elements.get(i));
            System.out.print("**push(e" + (i + 1) + "): ");
            stack.print();
            System.out.println("*getMin(): " + stack.getMin());
        }

        System.out.println("**size(): " + stack.size());

        System.out.println("**print()");
        stack.print();
        System.out.println("**printReversed()");
        stack.printReversed();

        // Удаление элементов в цикле.
        System.out.println("popping elements:");
        for (int i = 0; i < elements.size(); i++) {
            stack.pop();
            System.out.print("**pop(): ");
            stack.print();
            try {
                System.out.println("*getMin: " + stack.getMin());
            } catch (EmptyStackException exception) {
                System.out.println("*getMin: EmptyStackException occurred");
            }
        }

        System.out.println("End: ");
        System.out.println("*isEmpty(): " + stack.isEmpty());
    }
    public static <T> void testCircularQueue(CircularQueue<T> queue, List<T> elements) {
        System.out.println("Start: ");
        System.out.println("*isEmpty(): " + queue.isEmpty());

        //Добавление элементов в цикле
        System.out.println("enqueue elements:");
        for (int i = 0; i < elements.size(); i++) {
            queue.enqueue(elements.get(i));
            System.out.print("**enqueue(e" + (i + 1) + "): ");
            queue.print();
            System.out.println("*peek(): " + queue.peek());
        }

        System.out.println("**size(): " + queue.size());
        System.out.println("**isFull(): " + queue.isFull());

        System.out.println("**print()");
        queue.print();
        System.out.println("**printReversed()");
        queue.printReversed();

        //Удаление элементов в цикле
        System.out.println("dequeue elements:");
        int initialSize = queue.size();
        for (int i = 0; i < initialSize; i++) {
            System.out.print("**dequeue(): ");
            queue.dequeue();
            queue.print();
        }

        //Тестирование исключений
        System.out.println("Testing Exceptions");
        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("dequeue from empty queue: IllegalStateException occurred - " + e.getMessage());
        }

        //Заполнение очередь до предела
        System.out.println("Filling queue to capacity:");
        for (int i = 0; true; i++) {
            try {
                queue.enqueue(elements.get(i % elements.size()));
            } catch (IllegalStateException e) {
                System.out.println("isFull(): " + queue.isFull());
                System.out.println("enqueue to full queue: IllegalStateException occurred - " + e.getMessage());
                break;
            }
        }

        System.out.println("End: ");
        System.out.println("*isEmpty(): " + queue.isEmpty());
    }
}