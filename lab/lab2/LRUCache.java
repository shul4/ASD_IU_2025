import java.util.HashMap;

/**
 * 2. Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
 * списка и хеш-таблицы. Прокомментировать код.
 **/

public class LRUCache<K, V> {

    private class Node { // Элемент кэша.
        V value;
        K key;
        Node next;
        Node prev;

        Node(K key, V value) {
            this.value = value;
            this.key = key;
        }
    }

    private final int capacity; // Размер кэша.
    private Node tail; // Конец.
    private Node head; // Начало.
    private final HashMap<K, Node> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = null;
        tail = null;
    }

    // Суть работы - перемещение использованных элементов в начало (голову).
    // Тем самым неиспользуемые элементы будут оставаться в конце.
    // При нехватке места в кэше "пожертвуем" элементом находящимся в конце (хвосте).

    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node); // Элемент считаем использованным и перемещаем в начало (голову).
        return node.value;
    }

    private void moveToHead(Node node) {
        if (node == head) {
            return; // Элемент уже в начале, так что ничего не делаем.
        }
        removeNode(node); // Удаление элемента из старой позиции.
        addToHead(node); // Добавление элемента в начало.
    }

    private void removeNode(Node node) {
        if (node.prev != null) { // Проверяем есть ли перед нашим элементом другой элемент.
            node.prev.next = node.next; // Если есть, то указатель на следующий элемент у предыдущего элемента премещается с нашего на один вперёд.
        } else {
            head = node.next; // Если нет, то наш элемент в начале, соответственно следующий за ним элемент должен стать новой головой.
        }

        if (node.next != null) { // Проверяем есть ли после нашего элемента другой элемент.
            node.next.prev = node.prev; // Если есть, то указатель на предыдущий элемент у следующего элемента перемещается с нашего на один назад.
        } else {
            tail = node.prev; // Если нет, то наш элемент в конце, соответственно предыдущий элемент должен стать новым хвостом (концом).
        }
        // Удаляем ссылки на предыдущий и следующий элементы.
        node.prev = null;
        node.next = null;
    }

    private void addToHead(Node node) {
        node.prev = null; // Так как элемент теперь в начале, предыдущих у него быть не должно.

        // Первый элемент должен "уступить" своё место нашему.
        node.next = head;
        if (head != null) {
            head.prev = node;
        }
        head = node;

        if (tail == null) { // Если список был пустой, то наш элемент станет и хвостом, и головой одноврменно.
            tail = node;
        }
    }

    public void add(K key, V value) {
        Node node = cache.get(key);
        if (node != null) {
            node.value = value; // Если элемент с таким ключом существует, то перезаписываем его value.
            moveToHead(node); // Элемент был использован, перемещаем его в начало.
        } else {
            node = new Node(key, value);
            cache.put(key, node); // Элемента с таким ключом не существует, значит создаём новый.
            addToHead(node); // Добавляем элемент в начало.

            if (cache.size() > capacity) { // Если нет места, то жертвуем последним элементом (самым неиспользуемым).
                removeTail();
            }
        }
    }

    private void removeTail() {
        cache.remove(tail.key); // Удаляем хвост из кэша.
        removeNode(tail); // Удаляем хвост из списка.
    }

    public void remove(K key) { // Удаление элемента по ключу.
        Node node = cache.get(key);
        if (node == null) {
            return; // Если такого ключа нет, ничего не делаем.
        }
        cache.remove(key); // Удаляем элемент из кэша.
        removeNode(node); // Удаляем элемент из списка.
    }

    public int size() {
        return cache.size();
    }

    public void print() { // От более используемых к менее использованным.
        Node temp = head;
        System.out.print("[");
        while (temp.next != null) {
            System.out.print("[" + temp.key + "->" + temp.value + "], ");
            temp = temp.next;
        }
        System.out.print("[" + temp.key + "->" + temp.value + "]");
        System.out.println("]");
    }

    public void printReversed() { // От менее используемых к более использованным.
        Node temp = tail;
        System.out.print("[");
        while (temp.prev != null) {
            System.out.print("[" + temp.key + "->" + temp.value + "], ");
            temp = temp.prev;
        }
        System.out.print("[" + temp.key + "->" + temp.value + "]");
        System.out.println("]");
    }
}