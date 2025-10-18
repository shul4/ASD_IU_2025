package com.iu3.tasks.b3;

import java.util.*;

class UndoableDeque<T> {
    private final Deque<T> deque = new LinkedList<>();
    private final Stack<Operation<T>> undoStack = new Stack<>();
    private final Stack<Operation<T>> redoStack = new Stack<>();

    public static class Operation<T> {
        final String type; // "addFirst", "addLast", "removeFirst", "removeLast"
        final T value;
        final boolean wasAtFront; // используется для remove, чтобы знать, где был элемент

        Operation(String type, T value, boolean wasAtFront) {
            this.type = type;
            this.value = value;
            this.wasAtFront = wasAtFront;
        }
    }

    // Добавить элемент в начало
    public void addFirst(T item) {
        deque.addFirst(item);
        undoStack.push(new Operation<>("addFirst", item, true));
        redoStack.clear();
    }

    // Добавить элемент в конец
    public void addLast(T item) {
        deque.addLast(item);
        undoStack.push(new Operation<>("addLast", item, false));
        redoStack.clear();
    }


    // Удалить элемент из конца
    public void removeLast() {
        if (deque.isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        T item = deque.removeLast();
        undoStack.push(new Operation<>("removeLast", item, false));
        redoStack.clear();
    }

    // Отменить последнюю операцию
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }

        Operation<T> op = undoStack.pop();
        redoStack.push(op);

        switch (op.type) {
            case "addFirst":
                deque.removeFirst();
                System.out.println("Undo: removed from front " + op.value);
                break;
            case "addLast":
                deque.removeLast();
                System.out.println("Undo: removed from rear " + op.value);
                break;
            case "removeFirst":
                deque.addFirst(op.value);
                System.out.println("Undo: added to front " + op.value);
                break;
            case "removeLast":
                deque.addLast(op.value);
                System.out.println("Undo: added to rear " + op.value);
                break;
        }
    }

    // Повторить отменённую операцию
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo.");
            return;
        }

        Operation<T> op = redoStack.pop();
        undoStack.push(op);

        switch (op.type) {
            case "addFirst":
                deque.addFirst(op.value);
                System.out.println("Redo: added to front " + op.value);
                break;
            case "addLast":
                deque.addLast(op.value);
                System.out.println("Redo: added to rear " + op.value);
                break;
            case "removeFirst":
                deque.removeFirst();
                System.out.println("Redo: removed from front " + op.value);
                break;
            case "removeLast":
                deque.removeLast();
                System.out.println("Redo: removed from rear " + op.value);
                break;
        }
    }

    // Получить текущее состояние deque
    public List<T> getDeque() {
        return new ArrayList<>(deque);
    }
}
