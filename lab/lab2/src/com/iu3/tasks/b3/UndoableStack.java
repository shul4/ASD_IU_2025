package com.iu3.tasks.b3;

import java.util.*;

public class UndoableStack<T> {
    private final Stack<T> stack = new Stack<>();
    private final Stack<Operation<T>> undoStack = new Stack<>();
    private final Stack<Operation<T>> redoStack = new Stack<>();

    // Внутренний класс для представления операции
    private static class Operation<T> {
        final String type; // "push" или "pop"
        final T value;

        Operation(String type, T value) {
            this.type = type;
            this.value = value;
        }
    }

    // Добавить элемент в стек
    public void push(T item) {
        stack.push(item);
        undoStack.push(new Operation<>("push", item));
        redoStack.clear(); // После новой операции redo становится недоступен
    }

    // Удалить элемент из стека
    public void pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T item = stack.pop();
        undoStack.push(new Operation<>("pop", item));
        redoStack.clear();
    }

    // Отменить последнюю операцию
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("undoStack isEmpty");
            return;
        }

        Operation<T> op = undoStack.pop();
        redoStack.push(op);

        if (op.type.equals("push")) {
            stack.pop();
            System.out.println("Undo: popped " + op.value);
        } else if (op.type.equals("pop")) {
            stack.push(op.value);
            System.out.println("Undo: pushed back " + op.value);
        }
    }

    // Повторить отменённую операцию
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("redoStack is Empty");
            return;
        }

        Operation<T> op = redoStack.pop();
        undoStack.push(op);

        if (op.type.equals("push")) {
            stack.push(op.value);
            System.out.println("Redo: pushed " + op.value);
        } else if (op.type.equals("pop")) {
            stack.pop();
            System.out.println("Redo: popped " + op.value);
        }
    }

    // Получить текущее состояние стека
    public List<T> getStack() {
        return new ArrayList<>(stack);
    }
}