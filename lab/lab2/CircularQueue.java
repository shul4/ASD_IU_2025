/**
 * 2. Реализовать «Циклическую очередь» (Circular Queue).
 * Прокомментировать логику.
 **/

public class CircularQueue {
    private int front; // Указатель на начало очереди.
    private int rear; // Указатель на конец очереди (на место для следующей вставки).
    private final int[] data;
    private int size; // Число элементов в очереди.

    public CircularQueue(int length) {
        size = 0;
        front = 0;
        rear = 0;
        data = new int[length];
    }

    public int dequeue() {
        if (size != 0) {
            int value = data[front];

            // Передвигаем указатель начала вперёд.
            // Eсли указатель ушёл за пределы очереди, он должен оказатья в её начале.
            front = (front + 1) % data.length;
            size--;
            return value;
        } else return Integer.MIN_VALUE; // если в очереди нет элементов
    }

    public boolean enqueue(int value) { // Добавление в очередь, если добавили успешно - возвращаем true.
        data[rear] = value; // Присваиваем значение по указателю.
        // Передвигаем указатель конца вперёд.
        // Eсли указатель ок
        // ажется в конце очереди, он должен оказаться в её начале.
        rear = (rear + 1) % data.length;
        if (size == data.length) {
            front = (front + 1) % data.length;
        } else size++;
        return true;
    }

    public int size() {
        return size;
    }

    public void print() {
        if (size == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < size - 1; i++) {
                // Двигаемся начиная с начала очереди.
                int index = (front + i) % data.length;
                System.out.print(data[index] + ", ");
            }
            System.out.print(data[(front + size - 1) % data.length]);
            System.out.println("]");
        }
    }

    public void printReversed() {
        if (size == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < size; i++) {
                // Двигаемся начиная с конца очереди, для этого вычисляем индекс:
                int index = (rear - 1 - i + data.length) % data.length; // К rear - i добавлен data.length чтобы не уйти в отрицательынй числа, так как мы идем справа налево.
                System.out.print(data[index]);
                if (i < size - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }
}