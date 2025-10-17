package groupB;


import java.util.HashMap;

public class LRUCache<K, V> {
    //Внутренний класс для узлов двусвязного списка
    private class Node {
        K key;
        V value;
        Node prev, next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final HashMap<K, Node> map;
    private final Node head, tail; //Узлы для упрощения вставки/удаления

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }

    //Получение значения по ключу
    public V get(K key) {
        Node node = map.get(key);
        if (node == null) return null;
        moveToFront(node); //Если нашли — делаем самым «свежим»
        return node.value;
    }

    //Добавление или обновление значения
    public void put(K key, V value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            moveToFront(node);
        } else {
            /*Если кэш переполнен, то необходимо удалить lru наименее используемый элемент.
            Так как в голову списка сдвигаются элементы при их использовании для актуализации информации,
            то lru - это элемент хвоста списка, за исключением самого узла tail который используется в качестве вспомогательного.*/
            if (map.size() == capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }
            //Добавление нового элемента в начало списка, за исключением вспомогательного узла head
            Node newNode = new Node(key, value);
            insertAtFront(newNode);
            map.put(key, newNode);
        }
    }

    //Перемещение узла в начало списка
    private void moveToFront(Node node) {
        removeNode(node);
        insertAtFront(node);
    }

    //Вставка узла сразу после head
    private void insertAtFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    /*Удалить узел из списка.
    Удаляем все ссылки на наш элемент. Позже он стирается мусоросборщиком.
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    //Удаление элемента из кэша - удаляем узел из хеш-таблицы и из списка
    public void remove(K key) {
        Node node = map.get(key);
        if (node != null) {
            removeNode(node);
            map.remove(key);
        } else throw new IllegalStateException("Impossible to delete incorrect data");
    }

    //Проверка наличия ключа в кэше
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    //Текущий размер кэша
    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.size() == 0;
    }

    //Очистка кэша - map очищается внутренним методом, ссылки на все элементы удалены и список будет удалён мусоросборщиком.
    public void clear() {
        map.clear();
        head.next = tail;
        tail.prev = head;
    }

    public void printCache() {
        System.out.print("LRU Cache (newest -> oldest): [");
        Node current = head.next;  //Пропускаем фиктивную голову
        while (current != tail) {  //Останавливаемся перед фиктивным хвостом
            System.out.print(current.key + "=" + current.value);
            if (current.next != tail) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    public void printCacheReversed() {
        System.out.print("LRU Cache (oldest -> newest): [");
        Node current = tail.prev;  //Пропускаем фиктивный хвост
        while (current != head) {  //Останавливаемся перед фиктивной головой
            System.out.print(current.key + "=" + current.value);
            if (current.prev != head) {
                System.out.print(", ");
            }
            current = current.prev;
        }
        System.out.println("]");
    }
}