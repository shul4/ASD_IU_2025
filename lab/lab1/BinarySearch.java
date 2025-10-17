package com.testapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 Реализовать алгоритм бинарного поиска двумя способами.
 */
public class BinarySearch {

    /**
     * Основной метод класса
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            int[] array = readArrayFromUser();
            sortArray(array);
            printArray("Отсортированный массив:", array);

            int target = readTargetValueFromUser();

            int iterativeResult = binarySearchIterative(array, target);
            int recursiveResult = binarySearchRecursive(array, target);

            printSearchResult("Итеративный поиск", target, iterativeResult);
            printSearchResult("Рекурсивный поиск", target, recursiveResult);

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка формата числа: " + e.getMessage());
        }
    }

    /**
     * Метод для чтения массива с консоли
     * @return заполненный массив
     * @throws IOException если произошла ошибка ввода
     */
    private static int[] readArrayFromUser() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите количество элементов массива: ");
        int size = Integer.parseInt(reader.readLine());

        int[] array = new int[size];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            array[i] = Integer.parseInt(reader.readLine());
        }

        return array;
    }

    /**
     * Метод для чтения искомого значения с консоли
     * @return искомое значение
     * @throws IOException если произошла ошибка ввода
     */
    private static int readTargetValueFromUser() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите элемент для поиска: ");
        return Integer.parseInt(reader.readLine());
    }

    /**
     * Итеративная реализация бинарного поиска
     * @param array отсортированный массив
     * @param target искомый элемент
     * @return индекс элемента или -1 если не найден
     */
    private static int binarySearchIterative(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Рекурсивная реализация бинарного поиска
     * @param array отсортированный массив
     * @param target искомый элемент
     * @return индекс элемента или -1 если не найден
     */
    private static int binarySearchRecursive(int[] array, int target) {
        return binarySearchRecursiveHelper(array, target, 0, array.length - 1);
    }

    /**
     * Вспомогательный метод для рекурсивного бинарного поиска
     * @param array отсортированный массив
     * @param target искомый элемент
     * @param left левая граница поиска
     * @param right правая граница поиска
     * @return индекс элемента или -1 если не найден
     */
    private static int binarySearchRecursiveHelper(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return binarySearchRecursiveHelper(array, target, mid + 1, right);
        } else {
            return binarySearchRecursiveHelper(array, target, left, mid - 1);
        }
    }

    /**
     * Метод для сортировки массива пузырьком
     * @param array массив для сортировки
     */
    private static void sortArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Метод для вывода массива в консоль
     * @param message сообщение перед выводом
     * @param array массив для вывода
     */
    private static void printArray(String message, int[] array) {
        System.out.println(message);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + (i < array.length - 1 ? " " : "\n"));
        }
    }

    /**
     * Метод для вывода результата поиска
     * @param methodName название метода поиска
     * @param target искомый элемент
     * @param result результат поиска
     */
    private static void printSearchResult(String methodName, int target, int result) {
        System.out.println(methodName + ":");
        if (result != -1) {
            System.out.println("Элемент " + target + " найден на позиции " + result);
        } else {
            System.out.println("Элемент " + target + " не найден в массиве");
        }
    }
}