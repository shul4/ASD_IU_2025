import java.util.*;

/*Задание
В рамках данной лабораторной работы предлагается решить одну
основную задачу, а также несколько дополнительных задач. Они распределены
по группам. Каждая группа оценивается в определенное количество баллов.
Чтобы сдать лабораторную работу, необходимо набрать 6 баллов на
дополнительных задачах.
Выбор, какие задачи решать остается за каждым студентом. Основное
задание должен выполнить каждый студент.
Основное задание. Задание оценивается в 0 баллов.
Создать класс Student с полями:
1. Long id
2. String name
В методе main:
1. Создать ArrayList, который хранит объекты класса Student
(ArrayList<Student>).
2. Создать LinkedList, который хранит объекты класса Student
(LinkedList <Student>).
3. Создать Set, который хранит объекты класса Student (HashSet
<Student>).
4. Создать HashMap, который хранит объекты класса Student (HashMap
<Long, Student>).
В каждую структуру данных добавить 10 000 000  объектов.
После этого для каждой структуры данных измерить время в нс:
1. Добавление 1 несуществующего элемента в конец (id = 10 000 001).
2. Добавление 1 несуществующего элемента в начало.
3. Удаление последнего элемента
4. Удаление первого элемента
5. Взятие (Get) центрального элемента (id = 5 000 000)
4
6. Взятие (Get) последнего элемента (id = 9 999 999).
Помимо кода решение должно содержать цифры, полученные при
тестах. При невозможности работать с 10 000 000 записей позволительно
несколько сократить количество объектов.
В дополнительных заданиях необходимо создать структуры данных. Для
каждого вида списка реализовать следующие функции:
1. добавление элемента (начало, середина, конец);
2. удаление элемента;
3. подсчет числа элементов в списке;
4. печать списка в прямом и обратном порядке (по возможности).
Подготовить демонстрацию работы каждой из структур данных. */

public class Student {
    Long id;
    String name;

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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Student student = (Student) object;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public static void main(String[] args) {
        final int SIZE = 10_000_000;

        List<Student> studentsArrayList = new ArrayList<Student>();
        List<Student> studentsLinkedList = new LinkedList<>();
        Set<Student> studentsSet = new HashSet<>();
        Map<Long, Student> studentsMap = new HashMap<>();

        for (long i = 0; i < SIZE; i++) {
            Student s = new Student(i, "Bob" + i);
            studentsArrayList.add(s);
            studentsLinkedList.add(s);
            studentsSet.add(s);
            studentsMap.put(i, s);
        }

        System.out.println("|Collections performance|");
        measureOperationsTime((ArrayList<Student>) studentsArrayList);
        /*Можно просто написать ArrayList сразу вверху,
        т.к. метод measureOperationsTime принимает в качестве аргумента именно ArrayList,
        но объявление коллекции как List более универсально на практике. Далее аналогично.*/
        measureOperationsTime((LinkedList<Student>) studentsLinkedList);
        measureOperationsTime((HashSet<Student>) studentsSet);
        measureOperationsTime((HashMap<Long, Student>) studentsMap);
        /*Output:
        |Collections performance|
        ArrayList measurements:
        * Add to end: 4700 ns;
        * Add to beginning: 3833800 ns;
        * Remove last: 10200 ns;
        * Remove first: 3712100 ns;
        * Get middle: 7800 ns;
        * Get last: 500 ns;
        LinkedList measurements:
        * Add to end: 7800 ns;
        * Add to beginning: 6500 ns;
        * Remove last: 5000 ns;
        * Remove first: 2500 ns;
        * Get middle: 17386300 ns;
        * Get last: 7900 ns;
        HashSet measurements:
        * Add element: 5600 ns;
        * Remove element: 3800 ns;
        * Contains element: 2396200 ns;
        HashMap measurements:
        * Add element: 4300 ns;
        * Remove element by key: 2400 ns;
        * Get value by key: 6200 ns;*/
    }

    public static void measureOperationsTime(ArrayList<Student> students) {
        long start, end;

        System.out.println("ArrayList measurements:");

        //1. Добавление 1 несуществующего элемента в конец (id = 10 000 001).
        start = System.nanoTime();
        students.add(new Student((long) (students.size() + 1), "Johny"));
        end = System.nanoTime();
        System.out.println("* Add to end: " + (end - start) + " ns;");

        //2. Добавление 1 несуществующего элемента в начало.
        start = System.nanoTime();
        students.add(0, new Student((long) (students.size() + 2), "Johny"));
        end = System.nanoTime();
        System.out.println("* Add to beginning: " + (end - start) + " ns;");

        //3. Удаление последнего элемента
        start = System.nanoTime();
        students.remove(students.size() - 1);
        end = System.nanoTime();
        System.out.println("* Remove last: " + (end - start) + " ns;");

        //4. Удаление первого элемента
        start = System.nanoTime();
        students.remove(0);
        end = System.nanoTime();
        System.out.println("* Remove first: " + (end - start) + " ns;");

        //5. Взятие (Get) центрального элемента (id = 5 000 000)
        start = System.nanoTime();
        students.get(students.size() / 2);
        end = System.nanoTime();
        System.out.println("* Get middle: " + (end - start) + " ns;");

        //6. Взятие (Get) последнего элемента (id = 9 999 999).
        start = System.nanoTime();
        students.get(students.size() - 1);
        end = System.nanoTime();
        System.out.println("* Get last: " + (end - start) + " ns;");
    }

    public static void measureOperationsTime(LinkedList<Student> students) {
        long start, end;

        System.out.println("LinkedList measurements:");

        //1. Добавление 1 несуществующего элемента в конец (id = 10 000 001).
        start = System.nanoTime();
        students.addLast(new Student((long) (students.size() + 1), "Johny"));
        end = System.nanoTime();
        System.out.println("* Add to end: " + (end - start) + " ns;");

        //2. Добавление 1 несуществующего элемента в начало.
        start = System.nanoTime();
        students.addFirst(new Student((long) (students.size() + 2), "Johny"));
        end = System.nanoTime();
        System.out.println("* Add to beginning: " + (end - start) + " ns;");

        //3. Удаление последнего элемента
        start = System.nanoTime();
        students.removeLast();
        end = System.nanoTime();
        System.out.println("* Remove last: " + (end - start) + " ns;");

        //4. Удаление первого элемента
        start = System.nanoTime();
        students.removeFirst();
        end = System.nanoTime();
        System.out.println("* Remove first: " + (end - start) + " ns;");

        //5. Взятие (Get) центрального элемента (id = 5 000 000)
        start = System.nanoTime();
        students.get(students.size() / 2);
        end = System.nanoTime();
        System.out.println("* Get middle: " + (end - start) + " ns;");

        //6. Взятие (Get) последнего элемента (id = 9 999 999).
        start = System.nanoTime();
        students.getLast();
        end = System.nanoTime();
        System.out.println("* Get last: " + (end - start) + " ns;");
    }

    public static void measureOperationsTime(HashSet<Student> students) {
        long start, end;

        System.out.println("HashSet measurements:");

        //1. Добавление 1 несуществующего элемента в конец (id = 10 000 001).
        //2. Добавление 1 несуществующего элемента в начало.
        //HashSet - неупорядоченная коллекция. Понятия начало и конец не применяются.
        start = System.nanoTime();
        students.add(new Student((long) (students.size() + 1), "Johny"));
        end = System.nanoTime();
        System.out.println("* Add element: " + (end - start) + " ns;");

        //3. Удаление последнего элемента
        //4. Удаление первого элемента
        //HashSet - неупорядоченная коллекция. Понятия начало и конец не применяются.
        start = System.nanoTime();
        students.remove(new Student((long) (students.size() + 1), "Johny"));
        end = System.nanoTime();
        System.out.println("* Remove element: " + (end - start) + " ns;");

        //5. Взятие (Get) центрального элемента (id = 5 000 000)
        //6. Взятие (Get) последнего элемента (id = 9 999 999).
            /*HashSet - неупорядоченная коллекция.
            Понятия индекс элемента нет, поэтому нет операциии get().*/
        start = System.nanoTime();
        students.contains(new Student((long) (students.size() / 2), "Bob" + students.size() / 2));
        end = System.nanoTime();
        System.out.println("* Contains element: " + (end - start) + " ns;");
    }

    public static void measureOperationsTime(HashMap<Long, Student> students) {
        long start, end;

        System.out.println("HashMap measurements:");

        //1. Добавление 1 несуществующего элемента в конец (id = 10 000 001).
        //2. Добавление 1 несуществующего элемента в начало.
            /*HashMap хранит таблицу отсортированную относительно значений хэш-кодов ключей.
            Понятия начало и конец не применяются в привычном смысле*/
        start = System.nanoTime();
        students.put((long) (students.size() + 1), new Student((long) (students.size() + 1), "Johny"));
        end = System.nanoTime();
        System.out.println("* Add element: " + (end - start) + " ns;");

        //3. Удаление последнего элемента
        //4. Удаление первого элемента
        start = System.nanoTime();
        students.remove((long) (students.size() + 1));
        end = System.nanoTime();
        System.out.println("* Remove element by key: " + (end - start) + " ns;");


        //5. Взятие (Get) центрального элемента (id = 5 000 000)
        //6. Взятие (Get) последнего элемента (id = 9 999 999).
            /*HashMap хранит таблицу отсортированную относительно значений хэш-кодов ключей.
            Поэтому получение элемента "по индексу" не применяется*/
        start = System.nanoTime();
        students.get((long) (students.size() / 2));
        end = System.nanoTime();
        System.out.println("* Get value by key: " + (end - start) + " ns;");
    }
}
