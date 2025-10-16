import java.util.*;

/*Реализовать «Многослойная очередь» (очередь очередей), которая
поддерживает приоритетный режим обработки элементов.
Прокомментировать код. */

public class TaskB1 {

    private static class LineLayer {
        private List<Integer> elements;
        private int priority;
        public LineLayer(int priority) {
            this.priority = priority;
            this.elements = new ArrayList<>();
        }

        //Добавление элемента в конец очереди
        public void addToEnd(int value) {
            elements.add(value);
        }

        //Добавление элемента в начало очереди
        public void addToStart(int value) {
            elements.add(0, value);
        }

        //Добавление элемента в середину очереди
        public void addToMiddle(int value) {
            int middle = elements.size() / 2;
            elements.add(middle, value);
        }

        //Удаление элемента из начала очереди
        public int removeFromStart() {
            return elements.remove(0);
        }

        //Удаление элемента из конца очереди
        public int removeFromEnd() {
            return elements.remove(elements.size() - 1);
        }

        //Удаление элемента из середины очереди
        public int removeFromMiddle() {
            int middle = elements.size() / 2;
            return elements.remove(middle);
        }

        public int size() {
            return elements.size();
        }

         //Печать в прямом порядке (от начала к концу)
        public void printForward() {
            System.out.print("Очередь " + priority+ ": ");
            for (int element : elements) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        //Печать в обратном порядке (от конца к началу)
        public void printBackward() {
            System.out.print("Очередь " + priority + ": ");
            for (int i = elements.size() - 1; i >= 0; i--) {
                System.out.print(elements.get(i) + " ");
            }
            System.out.println();
        }
    }

    private List<LineLayer> layers;    // Список очередей
    private int layerCount;             // Кол-во слоев

    public TaskB1(int layerCount) {
        this.layerCount = layerCount;
        this.layers = new ArrayList<>();
        for (int i = 0; i < layerCount; i++) {
            layers.add(new LineLayer(i));
        }
    }
    //Добавление элемента в указанный слой и позицию
    public void addElement(int layer, int value, String position) {
        LineLayer queueLayer = layers.get(layer);

        switch (position.toLowerCase()) {
            case "start":
                queueLayer.addToStart(value);
                System.out.println("Добавлен " + value + " в начало слоя " + layer);
                break;
            case "middle":
                queueLayer.addToMiddle(value);
                System.out.println("Добавлен " + value + " в середину слоя " + layer);
                break;
            case "end":
                queueLayer.addToEnd(value);
                System.out.println("Добавлен " + value + " в конец слоя " + layer);
                break;
            default:
                throw new IllegalArgumentException("Неверная позиция: " + position);
        }
    }

    //Удаление элемента из указанного слоя и позиции
    public int removeElement(int layer, String position) {
        LineLayer queueLayer = layers.get(layer);

        int value;
        switch (position.toLowerCase()) {
            case "start":
                value = queueLayer.removeFromStart();
                System.out.println("Удален " + value + " из начала слоя " + layer);
                break;
            case "middle":
                value = queueLayer.removeFromMiddle();
                System.out.println("Удален " + value + " из середины слоя " + layer);
                break;
            case "end":
                value = queueLayer.removeFromEnd();
                System.out.println("Удален " + value + " из конца слоя " + layer);
                break;
            default:
                throw new IllegalArgumentException("Неверная позиция: " + position);
        }

        return value;
    }


   //Кол-во элеметов
    public int totalSize() {
        int total = 0;
        for (LineLayer layer : layers) {
            total += layer.size();
        }
        return total;
    }

    //Кол-во элементов в слое
    public int layerSize(int layer) {
        return layers.get(layer).size();
    }

    //Печать всех слоев в прямом порядке
    public void printAllForward() {
        System.out.println("\nПрямой порядок");
        for (int i = 0; i < layerCount; i++) {
            layers.get(i).printForward();
        }
    }

    //Печать всех слоев в обратном порядке
    public void printAllBackward() {
        System.out.println("\nОбратный порядок");
        for (int i = 0; i < layerCount; i++) {
            layers.get(i).printBackward();
        }
    }

    public static void main(String[] args) {
        System.out.println("Демонстрация многослойной очереди");

        TaskB1 multiQueue = new TaskB1(3);

        System.out.println("1. Добавление элементов:");

        multiQueue.addElement(0, 100, "end");
        multiQueue.addElement(0, 50, "start");
        multiQueue.addElement(0, 75, "middle");

        multiQueue.addElement(1, 200, "end");
        multiQueue.addElement(1, 150, "start");
        multiQueue.addElement(1, 175, "middle");

        multiQueue.addElement(2, 300, "end");
        multiQueue.addElement(2, 250, "start");
        multiQueue.addElement(2, 275, "middle");

        multiQueue.printAllForward();

        System.out.println("\n2.Печать в разных порядках:");
        multiQueue.printAllForward();
        multiQueue.printAllBackward();

        System.out.println("\n3.Кол-во элементов:");
        System.out.println("Всего элементов: " + multiQueue.totalSize());
        for (int i = 0; i < 3; i++) {
            System.out.println("Слой " + i + ": " + multiQueue.layerSize(i) + " элементов");
        }

        System.out.println("\n4. Удаление элементов:");

        multiQueue.removeElement(0, "start");
        multiQueue.removeElement(1, "middle");
        multiQueue.removeElement(2, "end");
        multiQueue.printAllForward();
    }
}
/*Демонстрация многослойной очереди
1. Добавление элементов:
Добавлен 100 в конец слоя 0
Добавлен 50 в начало слоя 0
Добавлен 75 в середину слоя 0
Добавлен 200 в конец слоя 1
Добавлен 150 в начало слоя 1
Добавлен 175 в середину слоя 1
Добавлен 300 в конец слоя 2
Добавлен 250 в начало слоя 2
Добавлен 275 в середину слоя 2

Прямой порядок
Очередь 0: 50 75 100
Очередь 1: 150 175 200
Очередь 2: 250 275 300

2.Печать в разных порядках:

Прямой порядок
Очередь 0: 50 75 100
Очередь 1: 150 175 200
Очередь 2: 250 275 300

Обратный порядок
Очередь 0: 100 75 50
Очередь 1: 200 175 150
Очередь 2: 300 275 250

3.Кол-во элементов:
Всего элементов: 9
Слой 0: 3 элементов
Слой 1: 3 элементов
Слой 2: 3 элементов

4. Удаление элементов:
Удален 50 из начала слоя 0
Удален 175 из середины слоя 1
Удален 300 из конца слоя 2

Прямой порядок
Очередь 0: 75 100
Очередь 1: 150 200
Очередь 2: 250 275 */