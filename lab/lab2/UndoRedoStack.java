import java.util.ArrayList;
import java.util.Stack;

/**
 * 3. Реализовать свой «Стек» и «Двустороннюю очередь» с поддержкой
 * операций undo/redo. Прокомментировать код.
 * Примечание. Undo отменяет эффект последней команды. Redo, в свою
 * очередь, повторно выполняет команду, отменённую при откате.
 **/

public class UndoRedoStack {

    private enum OperationType {
        PUSH,
        POP
    }

    private final Stack<Integer> stack;
    private final Stack<Integer> lastActionsValues; // Стек для хранения значений в последних операциях.
    private final Stack<OperationType> lastActionsTypes; // Стек для хранения типов операций в последних операциях.
    private final Stack<Integer> canceledActionsValues; // Стек для хранения значений в отменённых операциях.
    private final Stack<OperationType> canceledActionsTypes; // Стек для хранения типов операций в отменённых операциях.

    public UndoRedoStack() {
        stack = new Stack<>();
        lastActionsValues = new Stack<>();
        lastActionsTypes = new Stack<>();
        canceledActionsValues = new Stack<>();
        canceledActionsTypes = new Stack<>();
    }

    public int push(int value) {
        stack.push(value);
        lastActionsValues.push(value);
        lastActionsTypes.push(OperationType.PUSH);
        canceledActionsValues.clear(); // После действия история отменённых операций сбрасывается.
        canceledActionsTypes.clear();
        return value;
    }

    public int pop() {
        lastActionsValues.push(stack.peek());
        lastActionsTypes.push(OperationType.POP);
        canceledActionsValues.clear(); // После действия история отменённых операций сбрасывается.
        canceledActionsTypes.clear();
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public void undo() {
        if (lastActionsTypes.isEmpty()) {
            System.out.println("Нечего отменять");
            return;
        }

        int value = lastActionsValues.pop(); // Запоминаем значение, используемое в последней операции.

        switch (lastActionsTypes.pop()) { // Смотрим тип последней операции.
            case POP -> {
                stack.push(value); // Если последним действием элемент был убран, нужно его вернуть.
                // В историю отменённых операций добавляем нашу отменённую операцию.
                canceledActionsTypes.push(OperationType.POP);
                canceledActionsValues.push(value);
            }
            case PUSH -> {
                stack.pop(); // Если последним действием элемент был добавлен, его нужно убрать.
                // В историю отменённых операций добавляем нашу отменённую операцию.
                canceledActionsTypes.push(OperationType.PUSH);
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
            case POP -> {
                stack.pop(); // Если отменили pop, нужно его опять сделать.
                // В историю операций добавляем нашу вновь выполненную операцию.
                lastActionsTypes.push(OperationType.POP);
                lastActionsValues.push(value);
            }
            case PUSH -> {
                stack.push(value); // Если отменили push, нужно его опять сделать.
                // В историю операций добавляем нашу вновь выполненную операцию.
                lastActionsTypes.push(OperationType.PUSH);
                lastActionsValues.push(value);
            }
        }
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