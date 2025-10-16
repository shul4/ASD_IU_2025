import java.util.*;

/*Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
списка и хеш-таблицы. Прокомментировать код.
 */

public class TaskB2 {
    private final LinkedHashMap<Integer, Integer> cache;
    private final int capacity;

    public TaskB2(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    //Добавление элемента в кэш
    public void put(int key, int value) {
        cache.put(key, value);
    }

    //Добавление элемента в начало (самый старый)
    public void addToStart(int key, int value) {
        LinkedHashMap<Integer, Integer> temp = new LinkedHashMap<>(capacity, 0.75f, true);
        temp.put(key, value);
        temp.putAll(cache);
        cache.clear();
        cache.putAll(temp);
    }

    //Добавление элемента в середину
    public void addToMiddle(int key, int value) {
        if (cache.containsKey(key)) {
            put(key, value);
            return;
        }
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(cache.entrySet());
        LinkedHashMap<Integer, Integer> temp = new LinkedHashMap<>(capacity, 0.75f, true);
        int middle = entries.size() / 2;
        for (int i = 0; i < middle; i++) {
            Map.Entry<Integer, Integer> entry = entries.get(i);
            temp.put(entry.getKey(), entry.getValue());
        }
        temp.put(key, value);
        for (int i = middle; i < entries.size(); i++) {
            Map.Entry<Integer, Integer> entry = entries.get(i);
            temp.put(entry.getKey(), entry.getValue());
        }
        cache.clear();
        cache.putAll(temp);
    }

    //Удаление элемента из начала (самого старого)
    public Integer removeFromStart() {
        if (cache.isEmpty()) {
            return null;
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = cache.entrySet().iterator();
        Map.Entry<Integer, Integer> firstEntry = iterator.next();
        int key = firstEntry.getKey();
        iterator.remove();
        return key;
    }

    //Удаление элемента из конца (самого нового)
    public Integer removeFromEnd() {
        if (cache.isEmpty()) {
            return null;
        }
        Integer lastKey = null;
        for (Integer key : cache.keySet()) {
            lastKey = key;
        }
        if (lastKey != null) {
            cache.remove(lastKey);
            return lastKey;
        }
        return null;
    }

    //Удаление элемента из середины
    public Integer removeFromMiddle() {
        if (cache.isEmpty()) {
            return null;
        }
        List<Integer> keys = new ArrayList<>(cache.keySet());
        int middle = keys.size() / 2;
        int middleKey = keys.get(middle);
        cache.remove(middleKey);
        return middleKey;
    }

    //Получение количества элементов в кэше
    public int size() {
        return cache.size();
    }

    //Печать кэша в прямом порядке (от самого старого к самому новому)
    public void printForward() {
        System.out.print("LRU Cache: ");
        for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
            System.out.print("[" + entry.getKey() + "=" + entry.getValue() + "] ");
        }
        System.out.println();
    }

    //Печать кэша в обратном порядке (от самого нового к самому старому)
    public void printBackward() {
        System.out.print("LRU Cache: ");
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(cache.entrySet());
        Collections.reverse(entries);
        for (Map.Entry<Integer, Integer> entry : entries) {
            System.out.print("[" + entry.getKey() + "=" + entry.getValue() + "] ");
        }
        System.out.println();
    }

    //Печать внутреннего состояния для отладки
    public void printInternalState() {
        System.out.println("Внутреннее состояние:");
        System.out.println("Capacity: " + capacity + ", Size: " + size());
        System.out.println("Ключи в порядке от LRU к MRU: " + new ArrayList<>(cache.keySet()));
    }

    public static void main(String[] args) {
        System.out.println("Демонстрация LRU CACHE");

        TaskB2 cache = new TaskB2(5);

        System.out.println("1.Добавление элементов:");

        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);
        System.out.println("Добавлены элементы 1, 2, 3");
        cache.printForward();
        cache.printInternalState();

        cache.addToStart(0, 50);
        System.out.println("\nДобавлен элемент 0 в начало");
        cache.printForward();

        cache.addToMiddle(25, 250);
        System.out.println("\nДобавлен элемент 25 в середину");
        cache.printForward();
        cache.printInternalState();

        System.out.println("\n2. Печать в разных порядках:");
        cache.printForward();
        cache.printBackward();

        System.out.println("\n3.Кол-во элементов:");
        System.out.println("Текущий размер: " + cache.size());

        System.out.println("\n4. Удаление элементов:");
        Integer removedFromStart = cache.removeFromStart();
        System.out.println("Удаление из начала: " + removedFromStart);
        cache.printForward();

        Integer removedFromEnd = cache.removeFromEnd();
        System.out.println("Удаление из конца: " + removedFromEnd);
        cache.printForward();

        Integer removedFromMiddle = cache.removeFromMiddle();
        System.out.println("Удаление из середины: " + removedFromMiddle);
        cache.printForward();
    }
}
/*Демонстрация LRU CACHE
1.Добавление элементов:
Добавлены элементы 1, 2, 3
LRU Cache: [1=100] [2=200] [3=300]
Внутреннее состояние:
Capacity: 5, Size: 3
Ключи в порядке от LRU к MRU: [1, 2, 3]

Добавлен элемент 0 в начало
LRU Cache: [0=50] [1=100] [2=200] [3=300]

Добавлен элемент 25 в середину
LRU Cache: [0=50] [1=100] [25=250] [2=200] [3=300]
Внутреннее состояние:
Capacity: 5, Size: 5
Ключи в порядке от LRU к MRU: [0, 1, 25, 2, 3]

2. Печать в разных порядках:
LRU Cache: [0=50] [1=100] [25=250] [2=200] [3=300]
LRU Cache: [3=300] [2=200] [25=250] [1=100] [0=50]

3.Кол-во элементов:
Текущий размер: 5

4. Удаление элементов:
Удаление из начала: 0
LRU Cache: [1=100] [25=250] [2=200] [3=300]
Удаление из конца: 3
LRU Cache: [1=100] [25=250] [2=200]
Удаление из середины: 25
LRU Cache: [1=100] [2=200] */