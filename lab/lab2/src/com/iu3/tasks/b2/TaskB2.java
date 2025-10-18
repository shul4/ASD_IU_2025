// 2. Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
// списка и хеш-таблицы. Прокомментировать код.

package com.iu3.tasks.b2;


public class TaskB2 {
    public static void programB2(String[] args)  {
        System.out.println("=== LRU Cache ===");
        LRUCache<Integer, String> lru = new LRUCache<>(3);
        lru.put(1, "One");
        lru.put(2, "Two");
        lru.put(3, "Three");
        System.out.println(lru.get(1)); // One
        lru.put(4, "Four"); // Удаляет 2
        System.out.println(lru.get(2)); // null
        System.out.println(lru.get(3)); // Three
        System.out.println(lru.get(4)); // Four
    }
}

// === LRU Cache ===
// One
// null
// Three
// Four
