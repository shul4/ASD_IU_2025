import java.util.Stack;

/**
 * 3. Реализовать свой «Стек» и «Двустороннюю очередь» с поддержкой
 * операций undo/redo. Прокомментировать код.
 * Примечание. Undo отменяет эффект последней команды. Redo, в свою
 * очередь, повторно выполняет команду, отменённую при откате.
 **/

public class UndoRedoDeque {

    private enum OperationType {
        ADD_TO_START,
        ADD_TO_END,
        POP_FROM_START,
        POP_FROM_END
    }

    // Максимальная вместимость очереди.
    private int front; // Указатель на начало очереди.
    private int rear; // Указатель на конец очереди.
    private final int[] data;
    private int size; // Число элементов в очереди.

    private final Stack<Integer> lastActionsValues; // Стек для хранения значений в последних операциях.
    private final Stack<UndoRedoDeque.OperationType> lastActionsTypes; // Стек для хранения типов операций в последних операциях.
    private final Stack<Integer> canceledActionsValues; // Стек для хранения значений в отменённых операциях.
    private final Stack<UndoRedoDeque.OperationType> canceledActionsTypes; // Стек для хранения типов операций в отменённых операциях.

    public UndoRedoDeque(int length) {
        front = -1;
        rear = -1;
        data = new int[length];
        lastActionsValues = new Stack<>();
        lastActionsTypes = new Stack<>();
        canceledActionsValues = new Stack<>();
        canceledActionsTypes = new Stack<>();
    }

    public boolean isFull() {
        return ((front == 0 && rear == data.length - 1) || front == rear + 1);
    }

    public boolean isEmpty() {
        return (front == -1);
    }

    public void addToStart(int value) {
        if (isFull()) {
            System.out.println("В очереди нет места");
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = data.length - 1;
        } else {
            front--;
        }

        data[front] = value;
        size++;

        updateActionsHistory(value, OperationType.ADD_TO_START);
    }

    private void updateActionsHistory(int value, OperationType type) {
        lastActionsValues.push(value);
        lastActionsTypes.push(type);
        canceledActionsValues.clear(); // // После действия история отменённых операций сбрасывается.
        canceledActionsTypes.clear();
    }

    public void addToEnd(int value) {
        if (isFull()) {
            System.out.println("В очереди нет места");
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (rear == data.length - 1) {
            rear = 0;
        } else {
            rear++;
        }

        data[rear] = value;
        size++;

        updateActionsHistory(value, OperationType.ADD_TO_END);
    }

    public int popFromStart() {
        if (isEmpty()) return Integer.MIN_VALUE; // Нет элементов.

        int value = data[front];
        if (front == rear) { // Если единственный элемент.
            front = rear = -1;
            size = 0;
        } else if (front == data.length - 1) {
            front = 0;
            size--;
        } else {
            front++;
            size--;
        }

        updateActionsHistory(value, OperationType.POP_FROM_START);
        return value;
    }

    public int popFromEnd() {
        if (isEmpty()) return Integer.MIN_VALUE;

        int value = data[rear];
        if (front == rear) {
            front = rear = -1;
            size = 0;
        } else if (rear == 0) {
            rear = data.length - 1;
            size--;
        } else {
            rear--;
            size--;
        }

        updateActionsHistory(value, OperationType.POP_FROM_END);
        return value;
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
                int index = (rear - i + data.length) % data.length; // К rear - i добавлен data.length чтобы не уйти в отрицательынй числа, так как мы идем справа налево.
                System.out.print(data[index]);
                if (i < size - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    public void undo() {
        if (lastActionsTypes.isEmpty()) {
            System.out.println("Нечего отменять");
            return;
        }

        int value = lastActionsValues.pop(); // Запоминаем значение, используемое в последней операции.

        switch (lastActionsTypes.pop()) { // Смотрим тип последней операции.
            case POP_FROM_END -> {
                internalAddToEnd(value); // Если последним действием последний элемент был убран, нужно его вернуть в конец.
                // В историю отменённых операций добавляем нашу отменённую операцию.
                canceledActionsTypes.push(OperationType.POP_FROM_END);
                canceledActionsValues.push(value);
            }
            case ADD_TO_END -> {
                internalPopFromEnd(); // Если последним действием элемент был добавлен в конец, его нужно убрать из конца.
                // В историю отменённых операций добавляем нашу отменённую операцию.
                canceledActionsTypes.push(OperationType.ADD_TO_END);
                canceledActionsValues.push(value);
            }
            case POP_FROM_START -> {
                internalAddToStart(value); // Если последним действием первый элемент был убран, нужно его вернуть в начало.
                // В историю отменённых операций добавляем нашу отменённую операцию.
                canceledActionsTypes.push(OperationType.POP_FROM_START);
                canceledActionsValues.push(value);
            }
            case ADD_TO_START -> {
                internalPopFromStart(); // Если последним действием элемент был добавлен в начало, его нужно убрать из начала.
                // В историю отменённых операций добавляем нашу отменённую операцию.
                canceledActionsTypes.push(OperationType.ADD_TO_START);
                canceledActionsValues.push(value);
            }
        }
    }

    public void redo() {
        if (canceledActionsTypes.isEmpty()) {
            System.out.println("Нечего повторять");
            return;
        }

        int value = canceledActionsValues.pop(); // Запоминаем значение, используемое в последней операции.

        switch (canceledActionsTypes.pop()) { // Смотрим тип последней операции.
            case POP_FROM_END -> {
                internalPopFromEnd(); // Если отменили popFromEnd, нужно его опять сделать.
                // В историю операций добавляем нашу вновь выполненную операцию.
                lastActionsTypes.push(OperationType.POP_FROM_END);
                lastActionsValues.push(value);
            }
            case ADD_TO_END -> {
                internalAddToEnd(value); // Если отменили addToEnd, нужно его опять сделать.
                // В историю операций добавляем нашу вновь выполненную операцию.
                lastActionsTypes.push(OperationType.ADD_TO_END);
                lastActionsValues.push(value);
            }
            case POP_FROM_START -> {
                internalPopFromStart(); // Если отменили popFromStart, нужно его опять сделать.
                // В историю операций добавляем нашу вновь выполненную операцию.
                lastActionsTypes.push(OperationType.POP_FROM_START);
                lastActionsValues.push(value);
            }
            case ADD_TO_START -> {
                internalAddToStart(value); // Если отменили addToStart, нужно его опять сделать.
                // В историю операций добавляем нашу вновь выполненную операцию.
                lastActionsTypes.push(OperationType.ADD_TO_START);
                lastActionsValues.push(value);
            }
        }
    }

    // Ниже приведены дублированные методы описанные выше, за тем отличием,
    // что они не вызывают updateActionsHistory, что позволяет реализовать корректную работу undo/redo.

    private void internalAddToStart(int value) {
        if (isFull()) {
            System.out.println("В очереди нет места");
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = data.length - 1;
        } else {
            front--;
        }

        data[front] = value;
        size++;
    }

    private void internalAddToEnd(int value) {
        if (isFull()) {
            System.out.println("В очереди нет места");
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (rear == data.length - 1) {
            rear = 0;
        } else {
            rear++;
        }

        data[rear] = value;
        size++;
    }

    private void internalPopFromStart() {
        if (isEmpty()) return; // Нет элементов.

        if (front == rear) {
            front = rear = -1;
            size = 0;
        } else if (front == data.length - 1) {
            front = 0;
            size--;
        } else {
            front++;
            size--;
        }
    }

    private void internalPopFromEnd() {
        if (isEmpty()) return;

        if (front == rear) {
            front = rear = -1;
            size = 0;
        } else if (rear == 0) {
            rear = data.length - 1;
            size--;
        } else {
            rear--;
            size--;
        }
    }
}