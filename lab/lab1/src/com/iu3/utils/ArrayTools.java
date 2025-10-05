package com.iu3.utils;

import java.util.Scanner;

public class ArrayTools {
    private static final Scanner scanner = new Scanner(System.in);


    public static int[] fillArray() {
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Размер должен быть положительным числом.");
            return fillArray(); // рекурсивный вызов для корректного ввода
        }

        int[] array = new int[size];
        System.out.println("Введите " + size + " целых чисел (массив должен быть отсортирован по возрастанию):");

        for (int i = 0; i < size; i++) {
            System.out.print("Элемент [" + i + "]: ");
            array[i] = scanner.nextInt();
        }

        return array;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.println();
    }

    public static void shellSort(int[] array) {
        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;

                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }
}
