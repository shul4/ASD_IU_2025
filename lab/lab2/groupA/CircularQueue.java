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
        /*CircularQueue - СД, основанная на массиве. Её цикличность позволяет записывать элементы в конец очереди
        фактически в начало массива, если там освободилось место, то есть некоторые элементы были удалены из очереди.
        При этом цикличность - это только про реализацию очереди, а не про функционал. Он совпадает с простым Queue.*/
        if (isFull()) throw new IllegalStateException("CircularQueue is overloaded");
        rear = (rear + 1) % CAPACITY;   /*Круговое (циклическое) перемещение указателя на конец очереди
                                        возвращаемся к началу (0). Гарантирует, что индекс всегда остается в границах [0, CAPACITY-1].
                                        %CAPACITY при перемещении указателя rear обеспечивает то, что после достижения индекса CAPACITY-1,
                                        в тот момент, когда rear должен стать равным CAPACITY, остаток от деления перемещает указатель на индекс 0,
                                        ведь CAPACITY % CAPACITY даёт 0. Всем индексам, меньшим CAPACITY, а именно такими они и являются,
                                        %CAPACITY не мешает, ведь он даёт именно эти же индексы.
                                        */
        queue[rear] = element;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        T removedElement = queue[front];
        queue[front] = null;
        front = (front + 1) % CAPACITY; /*Циклическое перемещение указателя на начало очереди
                                         При достижении конца массива указатель переходит к 0.
                                         Все пояснения аналогичны пояснению выше для сдвига указателя rear*/
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
            i = (i + 1) % CAPACITY; /*Циклическое (круговое) перемещение индекса
                                     Циклический обход от front к rear.
                                     Обеспечивает корректный переход через границу массива
                                     Правило циклического обхода остаётся тем же.
                                     Учитывается случай rear < front как раз при круговом обходе.*/
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
            i = (i - 1 + CAPACITY) % CAPACITY;  /*Циклический обход в обратном направлении.
                                                +CAPACITY предотвращает отрицательные индексы,
                                                затем % CAPACITY обеспечивает зацикливание.
                                                То есть всё абсолютно аналогично пояснению выше. Также учтён случай rear < front*/
        }
        if (!isEmpty()) System.out.println(queue[i] + "]");
        else System.out.println("]");
    }
}
