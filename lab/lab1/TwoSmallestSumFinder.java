package com.testapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 Дан массив целых чисел. Минимальное количество элементов – 5. Вернуть
 число, которое является суммой двух наименьших положительных чисел.
 */
public class TwoSmallestSumFinder {

    private static final int MINIMUM_ARRAY_SIZE = 5;

    /**
     * Основной метод класса
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            int[] array = readArrayFromUser();
            printArray("Введенный массив:", array);

            int result = findSumOfTwoSmallestPositiveNumbers(array);
            printResult(result);

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка формата числа: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Метод для чтения массива с консоли
     * @return заполненный массив
     * @throws IOException если произошла ошибка ввода
     */
    private static int[] readArrayFromUser() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите количество элементов массива (минимум " + MINIMUM_ARRAY_SIZE + "): ");
        int size = Integer.parseInt(reader.readLine());

        if (size < MINIMUM_ARRAY_SIZE) {
            throw new IllegalArgumentException("Минимальное количество элементов - " + MINIMUM_ARRAY_SIZE);
        }

        int[] array = new int[size];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            array[i] = Integer.parseInt(reader.readLine());
        }

        return array;
    }

    /**
     * Метод для поиска суммы двух наименьших положительных чисел
     * @param array исходный массив
     * @return сумма двух наименьших положительных чисел или -1 если не найдено
     */
    private static int findSumOfTwoSmallestPositiveNumbers(int[] array) {
        int firstSmallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int number : array) {
            if (number > 0) {
                if (number < firstSmallest) {
                    secondSmallest = firstSmallest;
                    firstSmallest = number;
                } else if (number < secondSmallest) {
                    secondSmallest = number;
                }
            }
        }

        if (secondSmallest == Integer.MAX_VALUE) {
            return -1;
        }

        return firstSmallest + secondSmallest;
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
     * Метод для вывода результата
     * @param result результат поиска
     */
    private static void printResult(int result) {
        if (result == -1) {
            System.out.println("В массиве меньше двух положительных чисел");
        } else {
            System.out.println("Сумма двух наименьших положительных чисел: " + result);
        }
    }
}