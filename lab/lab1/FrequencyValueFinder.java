package com.testapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 Дан целочисленный массив. Верните число, частота встречи которого в
 массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
 таких чисел несколько, вернуть наибольшее.5
 */
public class FrequencyValueFinder {

    /**
     * Основной метод класса
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            int[] array = readArrayFromUser();
            printArray("Введенный массив:", array);

            int result = findNumberWithEqualFrequencyAndValue(array);
            printResult(result);

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
     * Метод для поиска числа с равной частотой и значением
     * @param array исходный массив
     * @return найденное число или -1 если не найдено
     */
    private static int findNumberWithEqualFrequencyAndValue(int[] array) {
        int maxValue = findMaxValueInArray(array);
        int[] frequencyArray = createFrequencyArray(array, maxValue);

        return findMaxValidNumber(frequencyArray);
    }

    /**
     * Метод для поиска максимального значения в массиве
     * @param array массив чисел
     * @return максимальное значение
     */
    private static int findMaxValueInArray(int[] array) {
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return maxValue;
    }

    /**
     * Метод для создания массива частот
     * @param array исходный массив
     * @param maxValue максимальное значение
     * @return массив частот
     */
    private static int[] createFrequencyArray(int[] array, int maxValue) {
        int[] frequencyArray = new int[maxValue + 1];

        for (int number : array) {
            if (number >= 0 && number < frequencyArray.length) {
                frequencyArray[number]++;
            }
        }

        return frequencyArray;
    }

    /**
     * Метод для поиска максимального валидного числа
     * @param frequencyArray массив частот
     * @return найденное число или -1
     */
    private static int findMaxValidNumber(int[] frequencyArray) {
        int maxValidNumber = -1;

        for (int i = 0; i < frequencyArray.length; i++) {
            if (frequencyArray[i] == i && i > 0) {
                if (i > maxValidNumber) {
                    maxValidNumber = i;
                }
            }
        }

        return maxValidNumber;
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
            System.out.println("Чисел, у которых значение равно частоте, не найдено");
        } else {
            System.out.println("Найдено число: " + result);
        }
    }
}