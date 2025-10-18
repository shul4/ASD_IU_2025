import java.util.*;

/*
 Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.
 Логика Min Stack:
Создаем два списка, один хранит все элементы(mainList), в другом элемент - минимальное
 значение на момент его добавления.
В mainList всегда добавляем элемент, а в minList добавляем только если новый элемент меньше
или равен текущему минимуму. Из mainList всегда удаляем элемент,из minList удаляем только
 если удаляемый элемент равен текущему минимуму.
*/

public class TaskA1 {
    private List<Integer> mainList; //Хранение элементоа
    private List<Integer> minList; //Хранение минимумов

    public TaskA1() {
        mainList = new ArrayList<>();
        minList = new ArrayList<>();
    }

    //Добавление элемента в конец
    public void push(int value) {
        mainList.add(value);
        if (minList.isEmpty() || value <= getLast(minList)) {
            minList.add(value);
        }
    }

    //Добавление элемента в начало
    public void pushToStart(int value) {
        mainList.add(0, value);
        newMinList();
    }

    //Добавление элемента в середину
    public void pushToMiddle(int value) {
        int middleIndex = mainList.size() / 2;
        mainList.add(middleIndex, value);
        newMinList();
    }

    //Пересчитываем минимум
    private void newMinList() {
        minList.clear();
        if (mainList.isEmpty()) return;
        int currentMin = mainList.get(0);
        minList.add(currentMin);
        for (int i = 1; i < mainList.size(); i++) {
            currentMin = Math.min(currentMin, mainList.get(i));
            minList.add(currentMin);
        }
    }

    //Удаление элемента из конца
    public int pop() {
        int value = removeLast(mainList);
        if (!minList.isEmpty() && value == getLast(minList)) {
            removeLast(minList);
        }
        return value;
    }

    //Удаление элемента из начала
    public int popFromStart() {
        int value = mainList.remove(0);
        newMinList();
        return value;
    }

    //Удаление элемента из середины
    public int popFromMiddle() {
        int middleIndex = mainList.size() / 2;
        int value = mainList.remove(middleIndex);
        newMinList();
        return value;
    }

    //Получение минимального элемента
    public int getMin() {
        if (minList.isEmpty()) {
            throw new EmptyStackException();
        }
        return getLast(minList);
    }

    //Кол-во элементов
    public int size() {
        return mainList.size();
    }


    //Печать прямом порядке (сверху вниз)
    public void printForward() {
        System.out.print("Стек (сверху вниз): ");
        for (int i = mainList.size() - 1; i >= 0; i--) {
            System.out.print(mainList.get(i) + " ");
        }
        System.out.println();
    }

    //Печать в обратном порядке (снизу вверх)
    public void printBackward() {
        if (mainList.isEmpty()) {
            System.out.println("Стек пуст");
            return;
        }
        System.out.print("Стек (снизу вверх): ");
        for (int i = 0; i < mainList.size(); i++) {
            System.out.print(mainList.get(i) + " ");
        }
        System.out.println();
    }


    //Получение последнего элемента
    private int getLast(List<Integer> list) {
        return list.get(list.size() - 1);
    }

    //Удаление последнего элемента списка
    private int removeLast(List<Integer> list) {
        return list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println("Демонстрация стека с минимумом");
        TaskA1 stack = new TaskA1();

        System.out.println("\n1.Добавление в конец");
        stack.push(5);
        stack.push(3);
        stack.push(7);
        System.out.println("Добавлены 5, 3, 7");
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n2.Добавление в начало:");
        stack.pushToStart(1);
        System.out.println("Добавление 1");
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n3.Добавление в середину:");
        stack.pushToMiddle(4);
        System.out.println("Добавление 4");
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n4. Удаление с конца: " + stack.pop());
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n5.Удаление с начала: " + stack.popFromStart());
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n6.Удаление из середины: " + stack.popFromMiddle());
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n7.Печать в обратном порядке:");
        stack.printBackward();
        System.out.println("Размер: " + stack.size());
    }
}
/*Демонстрация стека с минимумом

1.Добавление в конец
Добавлены 5, 3, 7
Стек (сверху вниз): 7 3 5
Минимум: 3
Размер: 3

2.Добавление в начало:
Добавление 1
Стек (сверху вниз): 7 3 5 1
Минимум: 1
Размер: 4

3.Добавление в середину:
Добавление 4
Стек (сверху вниз): 7 3 4 5 1
Минимум: 1
Размер: 5

4. Удаление с конца: 7
Стек (сверху вниз): 3 4 5 1
Минимум: 1
Размер: 4

5.Удаление с начала: 1
Стек (сверху вниз): 3 4 5
Минимум: 3
Размер: 3

6.Удаление из середины: 4
Стек (сверху вниз): 3 5
Минимум: 3
Размер: 2

7.Печать в обратном порядке:
Стек (снизу вверх): 5 3
Размер: 2 */