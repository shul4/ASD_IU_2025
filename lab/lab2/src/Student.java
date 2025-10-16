import java.util.*;

public class Student {
    private Long id;
    private String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static void main(String[] args) {
        final int SIZE = 10000000;
        System.out.println("Тестирование для " + SIZE + " объектов");
        testArrayList(SIZE);
        testLinkedList(SIZE);
        testHashSet(SIZE);
        testHashMap(SIZE);
    }

    private static void testArrayList(int size) {
        System.out.println("\nArrayList test");
        ArrayList<Student> arrayList = new ArrayList<>();
        for (long i = 0; i < size; i++) {
            arrayList.add(new Student(i, "Student_" + i));
        }
        long time;

        //  Добавление 1 несуществующего элемента в конец (id = 10 000 001)
        time = System.nanoTime();
        arrayList.add(new Student((long) size, "Student_" + size));
        System.out.println("1. Добавление в конец: " + (System.nanoTime() - time) + " нс");
        arrayList.remove(arrayList.size() - 1);

        //  Добавление 1 несуществующего элемента в начало
        time = System.nanoTime();
        arrayList.add(0, new Student((long) size + 1, "Student_" + (size + 1)));
        System.out.println("2. Добавление в начало: " + (System.nanoTime() - time) + " нс");
        arrayList.remove(0);

        //  Удаление последнего элемента
        time = System.nanoTime();
        Student last = arrayList.remove(arrayList.size() - 1);
        System.out.println("3. Удаление последнего: " + (System.nanoTime() - time) + " нс");
        arrayList.add(last);

        //  Удаление первого элемента
        time = System.nanoTime();
        Student first = arrayList.remove(0);
        System.out.println("4. Удаление первого: " + (System.nanoTime() - time) + " нс");
        arrayList.add(0, first);

        //  Взятие (Get) центрального элемента (id = 5 000 000)
        time = System.nanoTime();
        Student middle = arrayList.get(size / 2);
        System.out.println("5. Получение центрального: " + (System.nanoTime() - time) + " нс");

        //  Взятие (Get) последнего элемента (id = 9 999 999)
        time = System.nanoTime();
        Student lastElement = arrayList.get(size - 1);
        System.out.println("6. Получение последнего: " + (System.nanoTime() - time) + " нс");
    }

    private static void testLinkedList(int size) {
        System.out.println("\nLinkedList test");
        LinkedList<Student> linkedList = new LinkedList<>();
        for (long i = 0; i < size; i++) {
            linkedList.add(new Student(i, "Student_" + i));
        }
        long time;

        //  Добавление 1 несуществующего элемента в конец (id = 10 000 001)
        time = System.nanoTime();
        linkedList.addLast(new Student((long) size, "Student_" + size));
        System.out.println("1. Добавление в конец: " + (System.nanoTime() - time) + " нс");
        linkedList.removeLast();

        //  Добавление 1 несуществующего элемента в начало
        time = System.nanoTime();
        linkedList.addFirst(new Student((long) size + 1, "Student_" + (size + 1)));
        System.out.println("2. Добавление в начало: " + (System.nanoTime() - time) + " нс");
        linkedList.removeFirst();

        //  Удаление последнего элемента
        time = System.nanoTime();
        Student last = linkedList.removeLast();
        System.out.println("3. Удаление последнего: " + (System.nanoTime() - time) + " нс");
        linkedList.addLast(last);

        //  Удаление первого элемента
        time = System.nanoTime();
        Student first = linkedList.removeFirst();
        System.out.println("4. Удаление первого: " + (System.nanoTime() - time) + " нс");
        linkedList.addFirst(first);

        //  Взятие (Get) центрального элемента (id = 5 000 000)
        time = System.nanoTime();
        Student middle = linkedList.get(size / 2);
        System.out.println("5. Получение центрального: " + (System.nanoTime() - time) + " нс");

        //  Взятие (Get) последнего элемента (id = 9 999 999)
        time = System.nanoTime();
        Student lastElement = linkedList.getLast();
        System.out.println("6. Получение последнего: " + (System.nanoTime() - time) + " нс");
    }

    private static void testHashSet(int size) {
        System.out.println("\nHashSet test");
        HashSet<Student> hashSet = new HashSet<>();
        for (long i = 0; i < size; i++) {
            hashSet.add(new Student(i, "Student_" + i));
        }
        long time;

        //  Добавление 1 несуществующего элемента в конец (id = 10 000 001)
        time = System.nanoTime();
        hashSet.add(new Student((long) size, "Student_" + size));
        System.out.println("1. Добавление несуществующего элемента: " + (System.nanoTime() - time) + " нс");
        hashSet.remove(new Student((long) size, "Student_" + size));

        //  Добавление 1 несуществующего элемента в начало
        time = System.nanoTime();
        hashSet.add(new Student((long) size + 1, "Student_" + (size + 1)));
        System.out.println("2. Добавление элемента: " + (System.nanoTime() - time) + " нс");
        hashSet.remove(new Student((long) size + 1, "Student_" + (size + 1)));

        //  Удаление последнего элемента
        time = System.nanoTime();
        boolean removedLast = hashSet.remove(new Student((long) size - 1, "Student_" + (size - 1)));
        System.out.println("3. Удаление последнего добавленного элемента: " + (System.nanoTime() - time) + " нс");
        if (removedLast) {
            hashSet.add(new Student((long) size - 1, "Student_" + (size - 1)));
        }

        //  Удаление первого элемента
        time = System.nanoTime();
        boolean removedFirst = hashSet.remove(new Student(0L, "Student_0"));
        System.out.println("4. Удаление первого добавленного элемента: " + (System.nanoTime() - time) + " нс");
        if (removedFirst) {
            hashSet.add(new Student(0L, "Student_0"));
        }

        //  Взятие (Get) центрального элемента (id = 5 000 000)
        time = System.nanoTime();
        boolean containsMiddle = hashSet.contains(new Student((long) size / 2, "Student_" + (size / 2)));
        System.out.println("5. Поиск центрального: " + (System.nanoTime() - time) + " нс");

        //  Взятие (Get) последнего элемента (id = 9 999 999)
        time = System.nanoTime();
        boolean containsLast = hashSet.contains(new Student((long) size - 1, "Student_" + (size - 1)));
        System.out.println("6. Поиск последнего: " + (System.nanoTime() - time) + " нс");
    }

    private static void testHashMap(int size) {
        System.out.println("\nHashMap test");
        HashMap<Long, Student> hashMap = new HashMap<>();
        for (long i = 0; i < size; i++) {
            hashMap.put(i, new Student(i, "Student_" + i));
        }
        long time;

        //  Добавление 1 несуществующего элемента в конец (id = 10 000 001)
        time = System.nanoTime();
        hashMap.put((long) size, new Student((long) size, "Student_" + size));
        System.out.println("1. Добавление несуществующего элемента: " + (System.nanoTime() - time) + " нс");
        hashMap.remove((long) size);

        //  Добавление 1 несуществующего элемента в начало
        time = System.nanoTime();
        hashMap.put((long) size + 1, new Student((long) size + 1, "Student_" + (size + 1)));
        System.out.println("2. Добавление элемента: " + (System.nanoTime() - time) + " нс");
        hashMap.remove((long) size + 1);

        //  Удаление последнего элемента
        time = System.nanoTime();
        Student removedLast = hashMap.remove((long) size - 1);
        System.out.println("3. Удаление последнего добавленного элемента: " + (System.nanoTime() - time) + " нс");
        hashMap.put((long) size - 1, removedLast);

        //  Удаление первого элемента
        time = System.nanoTime();
        Student removedFirst = hashMap.remove(0L);
        System.out.println("4. Удаление первого добавленного элемента: " + (System.nanoTime() - time) + " нс");
        hashMap.put(0L, removedFirst);

        //  Взятие (Get) центрального элемента (id = 5 000 000)
        time = System.nanoTime();
        Student middle = hashMap.get((long) size / 2);
        System.out.println("5. Получение центрального: " + (System.nanoTime() - time) + " нс");

        //  Взятие (Get) последнего элемента (id = 9 999 999)
        time = System.nanoTime();
        Student last = hashMap.get((long) size - 1);
        System.out.println("6. Получение последнего: " + (System.nanoTime() - time) + " нс");
    }
}

/*Тестирование для 10000000 объектов

ArrayList test
1. Добавление в конец: 1419400 нс
2. Добавление в начало: 3436500 нс
3. Удаление последнего: 2800 нс
4. Удаление первого: 3073400 нс
5. Получение центрального: 11500 нс
6. Получение последнего: 900 нс

LinkedList test
1. Добавление в конец: 314400 нс
2. Добавление в начало: 47000 нс
3. Удаление последнего: 1100 нс
4. Удаление первого: 700 нс
5. Получение центрального: 211340800 нс
6. Получение последнего: 11100 нс

HashSet test
1. Добавление несуществующего элемента: 303700 нс
2. Добавление элемента: 30500 нс
3. Удаление последнего добавленного элемента: 64000 нс
4. Удаление первого добавленного элемента: 2200 нс
5. Поиск центрального: 47400 нс
6. Поиск последнего: 33600 нс

HashMap test
1. Добавление несуществующего элемента: 305200 нс
2. Добавление элемента: 43200 нс
3. Удаление последнего добавленного элемента: 500 нс
4. Удаление первого добавленного элемента: 1400 нс
5. Получение центрального: 5000 нс
6. Получение последнего: 500 нс
*/