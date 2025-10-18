package groupA;

//2. Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать логику.

public class CircularQueue<T> {
    private T[] queue;
    private int front; //Указатель на начало очереди
    private int rear;  //Указатель на конец очереди
    private int size;
    private final int CAPACITY;

    public CircularQueue(int CAPACITY) {
        this.CAPACITY = CAPACITY;
        this.queue = (T[]) new Object[CAPACITY];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(T element) {
    if (isFull()) {
        dequeue(); // Удаляем старый элемент чтобы освободить место
    }
    rear = (rear + 1) % CAPACITY;
    queue[rear] = element;
    size++;
    }

    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        T removedElement = queue[front];
        queue[front] = null;
        front = (front + 1) % CAPACITY;
        size--;
        return removedElement;
    }

    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == CAPACITY;
    }

    public int size() {
        return size;
    }

    public void print() {
        System.out.print("[");
        int i = front;
        int count = 0;
        while (count++ < size - 1) {
            System.out.print(queue[i] + ", ");
            i = (i + 1) % CAPACITY;
        }
        if (!isEmpty()) System.out.println(queue[i] + "]");
        else System.out.println("]");
    }

    public void printReversed() {
        System.out.print("[");
        int i = rear;
        int count = 0;
        while (count++ < size - 1) {
            System.out.print(queue[i] + ", ");
            i = (i - 1 + CAPACITY) % CAPACITY;
        }
        if (!isEmpty()) System.out.println(queue[i] + "]");
        else System.out.println("]");
    }
}
