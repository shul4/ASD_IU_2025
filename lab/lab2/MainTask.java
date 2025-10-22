import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Создать класс Student с полями:
 * 1. Long id
 * 2. String name
 * В методе main:
 * 1. Создать ArrayList, который хранит объекты класса Student
 * (ArrayList<Student>).
 * 2. Создать LinkedList, который хранит объекты класса Student
 * (LinkedList <Student>).
 * 3. Создать Set, который хранит объекты класса Student (HashSet
 * <Student>).
 * 4. Создать HashMap, который хранит объекты класса Student (HashMap
 * <Long, Student>).
 * В каждую структуру данных добавить 10 000 000 объектов.
 * После этого для каждой структуры данных измерить время в нс:
 * 1. Добавление 1 несуществующего элемента в конец (id = 10 000 001).
 * 2. Добавление 1 несуществующего элемента в начало.
 * 3. Удаление последнего элемента
 * 4. Удаление первого элемента
 * 5. Взятие (Get) центрального элемента (id = 5 000 000)
 * 6. Взятие (Get) последнего элемента (id = 9 999 999).
 * Помимо кода решение должно содержать цифры, полученные при
 * тестах. При невозможности работать с 10 000 000 записей позволительно
 * несколько сократить количество объектов.
 **/

public class MainTask {
    public static void main(String[] args) {

        ArrayList<Student> arrayList = new ArrayList<>();
        addStudentsToArrayList(arrayList);
        measureArrayListOperations(arrayList);

        LinkedList<Student> linkedList = new LinkedList<>();
        addStudentsToLinkedList(linkedList);
        measureLinkedListOperations(linkedList);

        HashSet<Student> hashSet = new HashSet<>();
        addStudentsToHashSet(hashSet);
        measureHashSetOperations(hashSet);

        HashMap<Long, Student> hashMap = new HashMap<>();
        addStudentsToHashMap(hashMap);
        measureHashMapOperations(hashMap);
    }

    static void addStudentsToArrayList(ArrayList<Student> arrayList) {
        for (int i = 0; i < 10_000_000; i++) {
            arrayList.add(new Student((long) i + 1, ""));
        }
    }

    static void measureArrayListOperations(ArrayList<Student> arrayList) {
        long startTime, endTime;

        System.out.println("---------------------------\nArrayList\n---------------------------");


        startTime = System.nanoTime();
        arrayList.addLast(new Student(10_000_001L, "Алексей")); // 10000 нс
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в конец: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        arrayList.addFirst(new Student(0L, "Алексей")); // 76206200 нс
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в начало: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        arrayList.removeLast(); // 9000 нс
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        arrayList.removeFirst(); // 82902500 нс
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        arrayList.get(5_000_000); // 7400 нс
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        arrayList.get(9_999_999); // 800 нс
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс");
    }

    static void addStudentsToLinkedList(LinkedList<Student> linkedList) {
        for (int i = 0; i < 10_000_000; i++) {
            linkedList.add(new Student((long) i + 1, ""));
        }
    }

    static void measureLinkedListOperations(LinkedList<Student> linkedList) {
        long startTime, endTime;

        System.out.println("---------------------------\nLinkedList\n---------------------------");

        startTime = System.nanoTime();
        linkedList.addLast(new Student(10_000_001L, "Алексей")); // 10000 нс
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в конец: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        linkedList.addFirst(new Student(0L, "Алексей")); // 8200 нс
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в начало: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        linkedList.removeLast(); // 4900 нс
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        linkedList.removeFirst(); // 2800 нс
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        linkedList.get(5_000_000); // 65581400 нс
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        linkedList.get(9_999_999); // 4900 нс
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс");
    }

    static void addStudentsToHashSet(HashSet<Student> hashSet) {
        for (int i = 0; i < 10_000_000; i++) {
            hashSet.add(new Student((long) i + 1, ""));
        }
    }

    static void measureHashSetOperations(HashSet<Student> hashSet) {
        long startTime, endTime;

        System.out.println("---------------------------\nHashSet\n---------------------------");

        startTime = System.nanoTime();
        hashSet.add(new Student(10_000_001L, "Алексей")); // 63200 нс
        endTime = System.nanoTime();
        System.out.println("Добавление элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        hashSet.remove(new Student(10_000_001L, "Алексей")); // 20000 нс
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        hashSet.remove(new Student(1L, "")); // 3300 нс
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        hashSet.contains(new Student(5_000_000L, "")); // 19600 нс
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        hashSet.contains(new Student(10_000_000L, "")); // 2100 нс
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс");
    }

    static void addStudentsToHashMap(HashMap<Long, Student> hashMap) {
        for (int i = 0; i < 10_000_000; i++) {
            hashMap.put((long) i + 1, new Student((long) i + 1, ""));
        }
    }

    static void measureHashMapOperations(HashMap<Long, Student> hashMap) {
        long startTime, endTime;

        System.out.println("---------------------------\nHashMap\n---------------------------");


        startTime = System.nanoTime();
        hashMap.put(10_000_001L, new Student(10_000_001L, "Алексей")); // 2500 нс
        endTime = System.nanoTime();
        System.out.println("Добавление элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        hashMap.remove(1L); // 13500 нс
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        hashMap.remove(10_000_001L); // 3000 нс
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        hashMap.get(5_000_000L); // 7900 нс
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        hashMap.get(9_999_999L); // 1000 нс
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс");
    }
}