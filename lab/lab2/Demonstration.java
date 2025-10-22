/**
 * Демонстрация работы структур данных
 **/

public class Demonstration {
    public static void main(String[] args) {
        System.out.println("--------------\nДемонстрация MinStack\n--------------");
        MinStack minStack = new MinStack();
        minStack.push(8);
        minStack.push(11);
        minStack.push(7);
        minStack.push(9);
        minStack.push(10);
        minStack.print();
        System.out.println("Минимальное значение: " + minStack.minimum());
        minStack.pop();
        minStack.print();
        System.out.println("Минимальное значение: " + minStack.minimum());
        minStack.pop();
        minStack.print();
        System.out.println("Минимальное значение: " + minStack.minimum());
        minStack.pop();
        minStack.print();
        System.out.println("Минимальное значение: " + minStack.minimum());
        minStack.pop();

        System.out.println("--------------\nДемонстрация CircularQueue\n--------------");
        CircularQueue circularQueue = new CircularQueue(4);
        circularQueue.enqueue(4);
        circularQueue.enqueue(3);
        circularQueue.enqueue(2);
        circularQueue.enqueue(6);
        circularQueue.print();
        circularQueue.dequeue();
        circularQueue.dequeue();
        circularQueue.dequeue();
        circularQueue.enqueue(100);
        circularQueue.print();
        circularQueue.enqueue(101);
        circularQueue.enqueue(102);
        circularQueue.enqueue(103); // Не добавилось, т.к. в очереди нет места.
        circularQueue.print();

        System.out.println("--------------\nДемонстрация LRUCache\n--------------");
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.add(1, "A");
        lruCache.add(2, "B");
        lruCache.add(3, "C");
        lruCache.print();
        lruCache.get(2);
        lruCache.get(1);
        lruCache.add(4, "D"); // Пара (3, "C") должна удалиться, так как менее используемая.
        lruCache.print();

        System.out.println("--------------\nДемонстрация UndoRedoStack\n--------------");
        UndoRedoStack undoRedoStack = new UndoRedoStack();
        undoRedoStack.push(1);
        undoRedoStack.push(2);
        undoRedoStack.push(3);
        undoRedoStack.print();
        System.out.println("Отменяем два последних действия");
        undoRedoStack.undo();
        undoRedoStack.undo();
        undoRedoStack.print();
        System.out.println("Возвращаем одно действие и делаем pop(), после чего пытаемся сделать три раза redo()");
        undoRedoStack.redo();
        undoRedoStack.pop();
        undoRedoStack.redo();
        undoRedoStack.redo();
        undoRedoStack.redo();
        undoRedoStack.print(); // Элемент вернулся, после pop() удалился и история redo очистилась.
        undoRedoStack.undo();
        System.out.println("Отменяем pop()");
        undoRedoStack.print();

        System.out.println("--------------\nДемонстрация UndoRedoDeque\n--------------");
        UndoRedoDeque undoRedoDeque = new UndoRedoDeque(4);
        undoRedoDeque.addToEnd(3);
        undoRedoDeque.addToStart(2);
        undoRedoDeque.addToEnd(4);
        undoRedoDeque.addToStart(1);
        undoRedoDeque.print();
        undoRedoDeque.popFromEnd();
        undoRedoDeque.undo();
        undoRedoDeque.undo();
        undoRedoDeque.print();
        undoRedoDeque.redo();
        undoRedoDeque.print();
        undoRedoDeque.redo();
        undoRedoDeque.print();
        undoRedoDeque.redo();
        undoRedoDeque.redo();
        undoRedoDeque.print();
    }
}
