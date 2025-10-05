//  Основное задание. Задание оценивается в 0 баллов, но является обязательным.
//  Реализовать алгоритм бинарного поиска двумя способами.

package com.iu3.tasks.a0;

import java.util.Scanner;

import static com.iu3.utils.ArrayTools.*;

public class BinarySearch {

    private static final Scanner scanner = new Scanner(System.in);

    public static void binarySearchExample() {

        int[] array = fillArray();

        System.out.println("Введённый массив:");
        printArray(array);

        shellSort(array);

        System.out.print("Введите число для поиска: ");
        int target = scanner.nextInt();

        // 1 способ через циклы
        int resultIterative = binarySearchIterative(array, target);
        if (resultIterative != -1) {
            System.out.println("Итеративный поиск: элемент найден на позиции " + resultIterative);
        } else {
            System.out.println("Итеративный поиск: элемент не найден");
        }

        // 1 способ через рекурсию
        int resultRecursive = binarySearchRecursive(array, target, 0, array.length - 1);
        if (resultRecursive != -1) {
            System.out.println("Рекурсивный поиск: элемент найден на позиции " + resultRecursive);
        } else {
            System.out.println("Рекурсивный поиск: элемент не найден");
        }

    }


    public static int binarySearchIterative(int[] array, int target) {
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


    public static int binarySearchRecursive(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1; // базовый случай: элемент не найден
        }

        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            return mid; // элемент найден
        } else if (array[mid] < target) {
            return binarySearchRecursive(array, target, mid + 1, right); // правая половина
        } else {
            return binarySearchRecursive(array, target, left, mid - 1); // левая половина
        }
    }
}