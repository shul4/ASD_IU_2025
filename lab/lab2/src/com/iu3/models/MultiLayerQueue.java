package com.iu3.models;

import java.util.*;

public class MultiLayerQueue<T> {
    private final Map<Integer, Queue<T>> priorityQueues = new LinkedHashMap<>();
    private final int maxPriority;

    public MultiLayerQueue(int maxPriority) {
        this.maxPriority = maxPriority;
        for (int i = 0; i <= maxPriority; i++) {
            priorityQueues.put(i, new LinkedList<>());
        }
    }

    // Добавить элемент в очередь с заданным приоритетом
    public void add(int priority, T item) {
        if (priority >= 0 && priority <= maxPriority) {
            priorityQueues.get(priority).add(item);
        }
    }

    // Извлечь элемент с наивысшим приоритетом (0 — самый высокий)
    public T poll() {
        for (int i = 0; i <= maxPriority; i++) {
            Queue<T> queue = priorityQueues.get(i);
            if (!queue.isEmpty()) {
                return queue.poll();
            }
        }
        return null;
    }

    // Подсчет общего числа элементов
    public int size() {
        int total = 0;
        for (Queue<T> queue : priorityQueues.values()) {
            total += queue.size();
        }
        return total;
    }

    // Печать очередей в порядке приоритета
    public void print() {
        for (int i = 0; i <= maxPriority; i++) {
            Queue<T> queue = priorityQueues.get(i);
            if (!queue.isEmpty()) {
                System.out.println("Приоритет " + i + ": " + queue);
            }
        }
    }

}