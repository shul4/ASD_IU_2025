// 3. Реализовать свой «Стек» и «Двустороннюю очередь» с поддержкой
// операций undo/redo. Прокомментировать код.
// Примечание. Undo отменяет эффект последней команды. Redo, в свою
// очередь, повторно выполняет команду, отменённую при откате.

package com.iu3.tasks.b3;

public class TaskB3 {
    public static void programB3(String[] args) {
        // === UndoableStack ===
        System.out.println("=== UndoableStack ===");
        UndoableStack<String> stack = new UndoableStack<>();
        stack.push("First");
        stack.push("Second");
        stack.push("Third");

        System.out.println("Stack: " + stack.getStack());

        stack.pop();
        System.out.println("After pop: " + stack.getStack());

        stack.undo();
        System.out.println("After undo: " + stack.getStack());

        stack.redo();
        System.out.println("After redo: " + stack.getStack());

        // === UndoableDeque ===
        System.out.println("\n=== UndoableDeque ===");
        UndoableDeque<String> deque = new UndoableDeque<>();
        deque.addFirst("Front");
        deque.addLast("Rear");
        deque.addFirst("NewFront");

        System.out.println("Deque: " + deque.getDeque());

        deque.removeLast();
        System.out.println("After removeLast: " + deque.getDeque());

        deque.undo();
        System.out.println("After undo: " + deque.getDeque());

        deque.redo();
        System.out.println("After redo: " + deque.getDeque());
    }
}


// === UndoableStack Demo ===
// Stack: [First, Second, Third]
// After pop: [First, Second]
// Undo: pushed back Third
// After undo: [First, Second, Third]
// Redo: popped Third
// After redo: [First, Second]
//
// === UndoableDeque Demo ===
// Deque: [NewFront, Front, Rear]
// After removeLast: [NewFront, Front]
// Undo: added to rear Rear
// After undo: [NewFront, Front, Rear]
// Redo: removed from rear Rear
// After redo: [NewFront, Front]