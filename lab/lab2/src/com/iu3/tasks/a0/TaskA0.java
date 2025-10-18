//  Создать класс Student с полями:
//  1. Long id
//  2. String name
//  В методе main:
//  1. Создать ArrayList, который хранит объекты класса Student
//  (ArrayList<Student>).
//  2. Создать LinkedList, который хранит объекты класса Student
//  (LinkedList <Student>).
//  3. Создать Set, который хранит объекты класса Student (HashSet
//  <Student>).
//  4. Создать HashMap, который хранит объекты класса Student (HashMap
//  <Long, Student>).
//  В каждую структуру данных добавить 10 000 000 объектов.
//  После этого для каждой структуры данных измерить время в нс:
//  1. Добавление 1 несуществующего элемента в конец (id = 10 000 001).
//  2. Добавление 1 несуществующего элемента в начало.
//  3. Удаление последнего элемента
//  4. Удаление первого элемента
//  5. Взятие (Get) центрального элемента (id = 5 000 000)
//  6. Взятие (Get) последнего элемента (id = 9 999 999).
//  Помимо кода решение должно содержать цифры, полученные при
//  тестах. При невозможности работать с 10 000 000 записей позволительно
//  несколько сократить количество объектов.


package com.iu3.tasks.a0;

import com.iu3.models.Student;

import java.util.*;

public class TaskA0 {

    // Размер коллекций для теста
    private static final int N = 10_000_000;
    private static final long ID_CENTER = N / 2; // 5_000_000L
    private static final long ID_LAST = N - 1; // 9_999_999L

    public static void programA0(String[] args) {
        List<Student> baseStudents = new ArrayList<>(N);
        for (long i = 0; i < N; i++) {
            baseStudents.add(new Student(i, "Student " + i));
        }

        Student studentToAddEnd = new Student((long) N + 1, "Student " + N + 1);
        Student studentToAddBegin = new Student((long) -1, "Student -1");
        Student studentToRemoveLast = baseStudents.getLast();
        Student studentCenter = new Student(ID_CENTER, "Student " + ID_CENTER);
        Student studentLast = new Student(ID_LAST, "Student " + ID_LAST);

        // --- Тест ArrayList ---
        System.out.println("--- Тестирование ArrayList ---");
        ArrayList<Student> arrayList = new ArrayList<>(baseStudents);

        long start = System.nanoTime();
        arrayList.addLast(studentToAddEnd);
        long timeAddEnd = System.nanoTime() - start;
        System.out.println("1. Добавление в конец: " + timeAddEnd + " нс");

        arrayList = new ArrayList<>(baseStudents);
        start = System.nanoTime();
        arrayList.addFirst(studentToAddBegin);
        long timeAddBegin = System.nanoTime() - start;
        System.out.println("2. Добавление в начало: " + timeAddBegin + " нс");

        arrayList = new ArrayList<>(baseStudents);
        start = System.nanoTime();
        arrayList.removeLast();
        long timeDelEnd = System.nanoTime() - start;
        System.out.println("3. Удаление последнего: " + timeDelEnd + " нс");

        arrayList = new ArrayList<>(baseStudents);
        start = System.nanoTime();
        arrayList.removeFirst();
        long timeDelBegin = System.nanoTime() - start;
        System.out.println("4. Удаление первого: " + timeDelBegin + " нс");

        arrayList = new ArrayList<>(baseStudents);
        start = System.nanoTime();
        Student center = arrayList.get((int) ID_CENTER);
        long timeGetCenter = System.nanoTime() - start;
        System.out.println("Id центрального: " + center.getId()); // Id центрального: 5000000
        System.out.println("5. Get центрального: " + timeGetCenter + " нс");

        arrayList = new ArrayList<>(baseStudents);
        start = System.nanoTime();
        Student last = arrayList.getLast();
        long timeGetLast = System.nanoTime() - start;
        System.out.println("Id: " + last.getId());
        System.out.println("6. Get последнего: " + timeGetLast + " нс");

        // --- Тест LinkedList ---
        System.out.println("\n--- Тестирование LinkedList ---");
        LinkedList<Student> linkedList = new LinkedList<>(baseStudents);

        start = System.nanoTime();
        linkedList.addLast(studentToAddEnd);
        timeAddEnd = System.nanoTime() - start;
        System.out.println("1. Добавление в конец: " + timeAddEnd + " нс");

        linkedList = new LinkedList<>(baseStudents);
        start = System.nanoTime();
        linkedList.addFirst(studentToAddBegin);
        timeAddBegin = System.nanoTime() - start;
        System.out.println("2. Добавление в начало: " + timeAddBegin + " нс");

        linkedList = new LinkedList<>(baseStudents);
        start = System.nanoTime();
        linkedList.removeLast();
        timeDelEnd = System.nanoTime() - start;
        System.out.println("3. Удаление последнего: " + timeDelEnd + " нс");

        linkedList = new LinkedList<>(baseStudents);
        start = System.nanoTime();
        linkedList.removeFirst();
        timeDelBegin = System.nanoTime() - start;
        System.out.println("4. Удаление первого: " + timeDelBegin + " нс");

        linkedList = new LinkedList<>(baseStudents);
        start = System.nanoTime();
        center = linkedList.get((int) ID_CENTER);
        timeGetCenter = System.nanoTime() - start;
        System.out.println("Id: " + center.getId());
        System.out.println("5. Get центрального: " + timeGetCenter + " нс");

        linkedList = new LinkedList<>(baseStudents);
        start = System.nanoTime();
        last = linkedList.getLast(); // Используем getLast() для O(1)
        timeGetLast = System.nanoTime() - start;
        System.out.println("Id: " + last.getId());
        System.out.println("6. Get последнего: " + timeGetLast + " нс");

        // --- Тест HashSet ---

        System.out.println("\n--- Тестирование HashSet ---");
        HashSet<Student> hashSet = new HashSet<>(baseStudents);

        start = System.nanoTime();
        hashSet.add(studentToAddEnd);
        long timeAdd = System.nanoTime() - start;
        System.out.println("1 и 2. Добавление нового элемента: " + timeAdd + " нс");

        hashSet = new HashSet<>(baseStudents);
        start = System.nanoTime();
        hashSet.remove(studentToRemoveLast);
        long timeDel = System.nanoTime() - start;
        System.out.println("3 и 4. Удаление элемента: " + timeDel + " нс");

        hashSet = new HashSet<>(baseStudents);
        start = System.nanoTime();
        boolean containsCenter = hashSet.contains(studentCenter);
        timeGetCenter = System.nanoTime() - start;
        System.out.println("Id: " + (containsCenter ? studentCenter.getId() : "Нету"));
        System.out.println("5. Проверка наличия центрального: " + timeGetCenter + " нс");

        start = System.nanoTime();
        boolean containsLast = hashSet.contains(studentLast);
        timeGetLast = System.nanoTime() - start;
        System.out.println("Id: " + (containsLast ? studentLast.getId() : "Нету"));
        System.out.println("6. Проверка наличия последнего: " + timeGetLast + " нс");

        // --- Тест HashMap ---
        System.out.println("\n--- Тестирование HashMap ---");
        HashMap<Long, Student> hashMap = new HashMap<>();
        for (Student s : baseStudents) {
            hashMap.put(s.getId(), s);
        }

        start = System.nanoTime();
        hashMap.put(studentToAddEnd.getId(), studentToAddEnd);
        timeAdd = System.nanoTime() - start;
        System.out.println("1 и 2. Добавление нового элемента: " + timeAdd + " нс");

        start = System.nanoTime();
        Student centerFromMap = hashMap.get(ID_CENTER);
        timeGetCenter = System.nanoTime() - start;
        System.out.println("Id: " + centerFromMap.getId());
        System.out.println("5. Get по ключу (центральный): " + timeGetCenter + " нс");

        start = System.nanoTime();
        Student lastFromMap = hashMap.get((long) (N - 1));
        timeGetLast = System.nanoTime() - start;
        System.out.println("Id: " + lastFromMap.getId());
        System.out.println("6. Get по ключу (последний): " + timeGetLast + " нс");

        start = System.nanoTime();
        hashMap.remove(studentToRemoveLast.getId());
        timeDel = System.nanoTime() - start;
        System.out.println("3 и 4. Удаление элемента по ключу: " + timeDel + " нс");
    }
}


//  --- Тестирование ArrayList ---
// 1. Добавление в конец: 9341100 нс
// 2. Добавление в начало: 45701200 нс
// 3. Удаление последнего: 9500 нс
// 4. Удаление первого: 2839100 нс
// Id центрального: 5000000
// 5. Get центрального: 11200 нс
// Id: 9999999
// 6. Get последнего: 7400 нс
//
// --- Тестирование LinkedList ---
// 1. Добавление в конец: 52200 нс
// 2. Добавление в начало: 23900 нс
// 3. Удаление последнего: 24500 нс
// 4. Удаление первого: 21200 нс
// Id: 5000000
// 5. Get центрального: 8795300 нс
// Id: 9999999
// 6. Get последнего: 24600 нс
//
// --- Тестирование HashSet ---
// 1 и 2. Добавление нового элемента: 24000 нс
// 3 и 4. Удаление элемента: 28800 нс
// Id: 5000000
// 5. Проверка наличия центрального: 57100 нс
// Id: 9999999
// 6. Проверка наличия последнего: 3200 нс
//
// --- Тестирование HashMap ---
// 1 и 2. Добавление нового элемента: 2500 нс
// Id: 5000000
// 5. Get по ключу (центральный): 10000 нс
// Id: 9999999
// 6. Get по ключу (последний): 900 нс
// 3 и 4. Удаление элемента по ключу: 4100 нс
