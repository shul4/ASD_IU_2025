import groupA.MinStack;
import groupA.CircularQueue;
import groupB.LRUCache;
import groupC.RedBlackTree;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

public class CollectionsTest {

    // Вспомогательный класс для пар ключ-значение
    static class TestData<K, V> {
        final K key;
        final V value;

        TestData(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println("MinStack<String>");
        MinStack<String> stringStack = new MinStack<>();
        testMinStack(stringStack, List.of("zebra", "apple", "banana", "ant", "cat"));
        /*MinStack<String>
        Start:
        *isEmpty(): true
        pushing elements:
        **push(e1): [zebra]
        *getMin(): zebra
        **push(e2): [zebra, apple]
        *getMin(): apple
        **push(e3): [zebra, apple, banana]
        *getMin(): apple
        **push(e4): [zebra, apple, banana, ant]
        *getMin(): ant
        **push(e5): [zebra, apple, banana, ant, cat]
        *getMin(): ant
        **size(): 5
        **print()
        [zebra, apple, banana, ant, cat]
        **printReversed()
        [cat, ant, banana, zebra]
        popping elements:
        **pop(): [zebra, apple, banana, ant]
        *getMin: ant
        **pop(): [zebra, apple, banana]
        *getMin: apple
        **pop(): [zebra, apple]
        *getMin: apple
        **pop(): [zebra]
        *getMin: zebra
        **pop(): []
        *getMin: EmptyStackException occurred
        End:
        *isEmpty(): true

        Process finished with exit code 0*/

        System.out.println("\n\n\nCircularQueue<String>");
        CircularQueue<String> stringQueue = new CircularQueue<>(5);
        testCircularQueue(stringQueue, List.of("zebra", "apple", "banana", "ant", "cat"));

        /*CircularQueue<String>
        Start:
        *isEmpty(): true
        enqueue elements:
        **enqueue(e1): [zebra]
        *peek(): zebra
        **enqueue(e2): [zebra, apple]
        *peek(): zebra
        **enqueue(e3): [zebra, apple, banana]
        *peek(): zebra
        **enqueue(e4): [zebra, apple, banana, ant]
        *peek(): zebra
        **enqueue(e5): [zebra, apple, banana, ant, cat]
        *peek(): zebra
        **size(): 5
        **isFull(): true
        **print()
        [zebra, apple, banana, ant, cat]
        **printReversed()
        [
        cat, ant, banana, apple, zebra]
        dequeue elements:
        **dequeue(): [apple, banana, ant, cat]
        **dequeue(): [banana, ant, cat]
        **dequeue(): [ant, cat]
        **dequeue(): [cat]
        **dequeue(): []
        Testing Exceptions
        dequeue from empty queue: IllegalStateException occurred - Queue is empty
        Filling queue to capacity:
        isFull(): true
        enqueue to full queue: IllegalStateException occurred - CircularQueue is overloaded
        End:
        *isEmpty(): false

        Process finished with exit code 0*/

        System.out.println("\nRedBlackTree<String>");
        RedBlackTree<String> stringTree = new RedBlackTree<>();
        testRedBlackTree(stringTree, List.of("mango", "apple", "pear", "banana", "orange", "grape", "kiwi"));

        /*RedBlackTree<String>
        Start:
        Inserting elements:
        **insert(e1): mango
        In-order traversal: mango
        Tree structure:
        └── mango(B)

        **insert(e2): apple
        In-order traversal: apple mango
        Tree structure:
        └── mango(B)
            ├── apple(R)

        **insert(e3): pear
        In-order traversal: apple mango pear
        Tree structure:
        └── mango(B)
            ├── apple(R)
            └── pear(R)

        **insert(e4): banana
        In-order traversal: apple banana mango pear
        Tree structure:
        └── mango(B)
            ├── apple(B)
            │   └── banana(R)
            └── pear(B)

        **insert(e5): orange
        In-order traversal: apple banana mango orange pear
        Tree structure:
        └── mango(B)
            ├── apple(B)
            │   └── banana(R)
            └── pear(B)
                ├── orange(R)

        **insert(e6): grape
        In-order traversal: apple banana grape mango orange pear
        Tree structure:
        └── mango(B)
            ├── banana(B)
            │   ├── apple(R)
            │   └── grape(R)
            └── pear(B)
                ├── orange(R)

        **insert(e7): kiwi
        In-order traversal: apple banana grape kiwi mango orange pear
        Tree structure:
        └── mango(B)
            ├── banana(R)
            │   ├── apple(B)
            │   └── grape(B)
            │       └── kiwi(R)
            └── pear(B)
                ├── orange(R)

        Checking contains:
        **contains(mango): true
        **contains(apple): true
        **contains(pear): true
        **contains(banana): true
        **contains(orange): true
        **contains(grape): true
        **contains(kiwi): true
        Checking non-existent element:
        **contains(nonExistent): false
        Deleting elements:
        **delete(mango)
        In-order traversal after deletion: apple banana grape kiwi orange pear
        Tree structure after deletion:
        └── orange(B)
            ├── banana(R)
            │   ├── apple(B)
            │   └── grape(B)
            │       └── kiwi(R)
            └── pear(B)

        **delete(apple)
        In-order traversal after deletion: banana grape kiwi orange pear
        Tree structure after deletion:
        └── orange(B)
            ├── grape(R)
            │   ├── banana(B)
            │   └── kiwi(B)
            └── pear(B)

        **delete(pear)
        In-order traversal after deletion: banana grape kiwi orange
        Tree structure after deletion:
        └── grape(B)
            ├── banana(B)
            └── orange(B)
                ├── kiwi(R)

        **delete(banana)
        In-order traversal after deletion: grape kiwi orange
        Tree structure after deletion:
        └── kiwi(B)
            ├── grape(B)
            └── orange(B)

        **delete(orange)
        In-order traversal after deletion: grape kiwi
        Tree structure after deletion:
        └── kiwi(B)
            ├── grape(R)

        **delete(grape)
        In-order traversal after deletion: kiwi
        Tree structure after deletion:
        └── kiwi(B)

        **delete(kiwi)
        In-order traversal after deletion:
        Tree structure after deletion:

        **delete(apple)
        IllegalStateException occurred - Impossible to delete incorrect data
        Testing Exceptions: IllegalStateException already occurred in line above
        End:
        Final exception test - delete from empty tree:
        delete from empty tree: IllegalStateException occurred - Impossible to delete incorrect data

        Process finished with exit code 0
        */

        System.out.println("\nLRUCache<Integer, String>");
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        List<TestData<Integer, String>> testData = Arrays.asList(
                new TestData<>(1, "Apple"),
                new TestData<>(2, "Banana"),
                new TestData<>(3, "Cherry"),
                new TestData<>(4, "Date"),
                new TestData<>(5, "Elderberry")
        );
        testLRUCache(cache, testData);
        /*LRUCache<Integer, String>
        Start:
        *size(): 0
        *isEmpty(): true

        Adding elements from test data
        **put(1, 'Apple')
        LRU Cache (newest -> oldest): [1=Apple]
        *size(): 1
        **put(2, 'Banana')
        LRU Cache (newest -> oldest): [2=Banana, 1=Apple]
        *size(): 2
        **put(3, 'Cherry')
        LRU Cache (newest -> oldest): [3=Cherry, 2=Banana, 1=Apple]
        *size(): 3
        **put(4, 'Date')
        LRU Cache (newest -> oldest): [4=Date, 3=Cherry, 2=Banana]
        *size(): 3
        **put(5, 'Elderberry')
        LRU Cache (newest -> oldest): [5=Elderberry, 4=Date, 3=Cherry]
        *size(): 3

        Getting elements
        **get(1): null
        **get(2): null
        **get(3): Cherry
        LRU Cache (newest -> oldest): [3=Cherry, 5=Elderberry, 4=Date]
        **get(4): Date
        LRU Cache (newest -> oldest): [4=Date, 3=Cherry, 5=Elderberry]
        **get(5): Elderberry
        LRU Cache (newest -> oldest): [5=Elderberry, 4=Date, 3=Cherry]

        Checking contains
        **containsKey(1): false
        **containsKey(2): false
        **containsKey(3): true
        **containsKey(4): true
        **containsKey(5): true

        Testing updates
        **put(1, 'UPDATED') - update existing
        LRU Cache (newest -> oldest): [1=UPDATED, 5=Elderberry, 4=Date]

        Testing removal
        **remove(1)
        LRU Cache (newest -> oldest): [5=Elderberry, 4=Date]
        *size(): 2
        IllegalStateException occurred - Impossible to delete incorrect data

        --- Testing operation sequence ---
        **clear() - starting fresh
        Initial state:
        LRU Cache (newest -> oldest): [3=Cherry, 2=Banana, 1=Apple]
        After get(1):
        LRU Cache (newest -> oldest): [1=Apple, 3=Cherry, 2=Banana]
        After put(4, 'Date'):
        LRU Cache (newest -> oldest): [4=Date, 1=Apple, 3=Cherry]

        End:
        **clear()
        LRU Cache (newest -> oldest): []
        *size(): 0
        *isEmpty(): true

        Process finished with exit code 0
         */
    }

    public static <T extends Comparable<T>> void testMinStack(MinStack<T> stack, List<T> elements) {
        System.out.println("Start: ");
        System.out.println("*isEmpty(): " + stack.isEmpty());

        // Добавление элементов в цикле.
        System.out.println("pushing elements:");
        for (int i = 0; i < elements.size(); i++) {
            stack.push(elements.get(i));
            System.out.print("**push(e" + (i + 1) + "): ");
            stack.print();
            System.out.println("*getMin(): " + stack.getMin());
        }

        System.out.println("**size(): " + stack.size());

        System.out.println("**print()");
        stack.print();
        System.out.println("**printReversed()");
        stack.printReversed();

        // Удаление элементов в цикле.
        System.out.println("popping elements:");
        for (int i = 0; i < elements.size(); i++) {
            stack.pop();
            System.out.print("**pop(): ");
            stack.print();
            try {
                System.out.println("*getMin: " + stack.getMin());
            } catch (EmptyStackException exception) {
                System.out.println("*getMin: EmptyStackException occurred");
            }
        }

        System.out.println("End: ");
        System.out.println("*isEmpty(): " + stack.isEmpty());
    }

    public static <T> void testCircularQueue(CircularQueue<T> queue, List<T> elements) {
        System.out.println("Start: ");
        System.out.println("*isEmpty(): " + queue.isEmpty());

        //Добавление элементов в цикле
        System.out.println("enqueue elements:");
        for (int i = 0; i < elements.size(); i++) {
            queue.enqueue(elements.get(i));
            System.out.print("**enqueue(e" + (i + 1) + "): ");
            queue.print();
            System.out.println("*peek(): " + queue.peek());
        }

        System.out.println("**size(): " + queue.size());
        System.out.println("**isFull(): " + queue.isFull());

        System.out.println("**print()");
        queue.print();
        System.out.println("**printReversed()");
        queue.printReversed();

        //Удаление элементов в цикле
        System.out.println("dequeue elements:");
        int initialSize = queue.size();
        for (int i = 0; i < initialSize; i++) {
            System.out.print("**dequeue(): ");
            queue.dequeue();
            queue.print();
        }

        //Тестирование исключений
        System.out.println("Testing Exceptions");
        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("dequeue from empty queue: IllegalStateException occurred - " + e.getMessage());
        }

        //Заполнение очередь до предела
        System.out.println("Filling queue to capacity:");
        for (int i = 0; true; i++) {
            try {
                queue.enqueue(elements.get(i % elements.size()));
            } catch (IllegalStateException e) {
                System.out.println("isFull(): " + queue.isFull());
                System.out.println("enqueue to full queue: IllegalStateException occurred - " + e.getMessage());
                break;
            }
        }

        System.out.println("End: ");
        System.out.println("*isEmpty(): " + queue.isEmpty());
    }

    public static <T extends Comparable<T>> void testRedBlackTree(RedBlackTree<T> tree, List<T> elements) {
        System.out.println("Start: ");

        // Добавление элементов в цикле
        System.out.println("Inserting elements:");
        for (int i = 0; i < elements.size(); i++) {
            tree.insert(elements.get(i));
            System.out.println("**insert(e" + (i + 1) + "): " + elements.get(i));
            System.out.print("In-order traversal: ");
            tree.inorderTraversal();
            System.out.println("Tree structure:");
            tree.printTree();
            System.out.println();
        }

        // Проверка наличия элементов
        System.out.println("Checking contains:");
        for (int i = 0; i < elements.size(); i++) {
            boolean contains = tree.contains(elements.get(i));
            System.out.println("**contains(" + elements.get(i) + "): " + contains);
        }

        // Проверка наличия несуществующего элемента
        System.out.println("Checking non-existent element:");
        // Создаем элемент, который заведомо не существует в дереве
        T nonExistent = (T) "nonExistent";
        boolean contains = tree.contains(nonExistent);
        System.out.println("**contains(" + nonExistent + "): " + contains);

        // Удаление элементов в цикле (удаляем первые 3 элемента)
        System.out.println("Deleting elements:");
        for (int i = 0; true; i++) {
            try {
                T elementToDelete = elements.get(i);
                System.out.println("**delete(" + elementToDelete + ")");
                tree.delete(elementToDelete);
                System.out.print("In-order traversal after deletion: ");
                tree.inorderTraversal();
                System.out.println("Tree structure after deletion:");
                tree.printTree();
                System.out.println();
            } catch (ArrayIndexOutOfBoundsException e) {
                i = 0;
            } catch (IllegalStateException e) {
                System.out.println("IllegalStateException occurred - " + e.getMessage());
                break;
            }
        }

        // Тестирование исключений при удалении
        System.out.println("Testing Exceptions: IllegalStateException already occurred in line above");


        System.out.println("End: ");

        // Финальная проверка - попытка удалить из пустого дерева
        System.out.println("Final exception test - delete from empty tree:");
        try {
            tree.delete(elements.get(0));
        } catch (IllegalStateException e) {
            System.out.println("delete from empty tree: IllegalStateException occurred - " + e.getMessage());
        }
    }

    public static <K, V> void testLRUCache(LRUCache<K, V> cache, List<TestData<K, V>> testData) {
        System.out.println("Start: ");
        System.out.println("*size(): " + cache.size());
        System.out.println("*isEmpty(): " + (cache.size() == 0));

        // Добавление элементов из переданного списка
        System.out.println("\nAdding elements from test data");
        for (TestData<K, V> data : testData) {
            cache.put(data.key, data.value);
            System.out.println("**put(" + data.key + ", '" + data.value + "')");
            cache.printCache();
            System.out.println("*size(): " + cache.size());
        }

        // Тестирование получения элементов
        System.out.println("\nGetting elements");
        for (TestData<K, V> data : testData) {
            V value = cache.get(data.key);
            System.out.println("**get(" + data.key + "): " + value);
            if (value != null) {
                cache.printCache();
            }
        }

        // Тестирование containsKey
        System.out.println("\nChecking contains");
        for (TestData<K, V> data : testData) {
            boolean contains = cache.containsKey(data.key);
            System.out.println("**containsKey(" + data.key + "): " + contains);
        }

        // Тестирование обновления существующих элементов
        System.out.println("\nTesting updates");
        if (!testData.isEmpty()) {
            TestData<K, V> first = testData.get(0);
            cache.put(first.key, (V)"UPDATED");
            System.out.println("**put(" + first.key + ", 'UPDATED') - update existing");
            cache.printCache();
        }

        // Тестирование удаления
        System.out.println("\nTesting removal");
        for (int i = 0; true; i++) {
            try {
                TestData<K, V> data = testData.get(i);
                cache.remove(data.key);
                System.out.println("**remove(" + data.key + ")");
                cache.printCache();
                System.out.println("*size(): " + cache.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                i = 0;
            } catch (IllegalStateException e) {
                System.out.println("IllegalStateException occurred - " + e.getMessage());
                break;
            }
        }

        // Тестирование последовательности операций
        System.out.println("\n--- Testing operation sequence ---");
        cache.clear();
        System.out.println("**clear() - starting fresh");

        // Добавляем элементы для демонстрации LRU логики
        for (int i = 0; i < Math.min(3, testData.size()); i++) {
            TestData<K, V> data = testData.get(i);
            cache.put(data.key, data.value);
        }
        System.out.println("Initial state:");
        cache.printCache();

        // Симуляция использования для демонстрации вытеснения
        if (testData.size() >= 3) {
            cache.get(testData.get(0).key); // Делаем первый элемент самым новым
            System.out.println("After get(" + testData.get(0).key + "):");
            cache.printCache();

            // Добавляем новый элемент - должен вытеснить самый старый
            if (testData.size() >= 4) {
                TestData<K, V> newData = testData.get(3);
                cache.put(newData.key, newData.value);
                System.out.println("After put(" + newData.key + ", '" + newData.value + "'):");
                cache.printCache();
            }
        }

        // Финальный clear() как последний этап
        System.out.println("\nEnd:");
        cache.clear();
        System.out.println("**clear()");
        cache.printCache();
        System.out.println("*size(): " + cache.size());
        System.out.println("*isEmpty(): " + (cache.size() == 0));
    }
}