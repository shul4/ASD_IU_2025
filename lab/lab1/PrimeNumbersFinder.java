package com.testapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 Дано целое число. Реализуйте метод, который находит N первых простых
 чисел. Используйте алгоритм «Решето Эратосфена».
 */
public class PrimeNumbersFinder {

    /**
     * Основной метод класса
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            int numberCount = readNumberCountFromUser();
            int[] primeNumbers = findFirstNPrimeNumbers(numberCount);
            ArrayPrinter.printArray("Первые " + numberCount + " простых чисел:", primeNumbers);

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка формата числа: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Метод для чтения количества чисел с консоли
     * @return количество простых чисел для поиска
     * @throws IOException если произошла ошибка ввода
     */
    private static int readNumberCountFromUser() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите количество простых чисел для поиска: ");
        int numberCount = Integer.parseInt(reader.readLine());

        if (numberCount <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным числом");
        }

        return numberCount;
    }

    /**
     * Метод для поиска первых N простых чисел
     * @param numberCount количество простых чисел
     * @return массив простых чисел
     */
    private static int[] findFirstNPrimeNumbers(int numberCount) {
        if (numberCount == 1) {
            return new int[]{2};
        }

        int upperBound = estimateUpperBound(numberCount);
        boolean[] isPrime = applySieveOfEratosthenes(upperBound);

        return extractFirstNPrimeNumbers(isPrime, numberCount);
    }

    /**
     * Метод для оценки верхней границы поиска с использованием формулы
     * Формула: upperBound ≈ n * (ln(n) + ln(ln(n))) для n > 6
     * @param numberCount количество простых чисел
     * @return верхняя граница поиска
     */
    private static int estimateUpperBound(int numberCount) {
        if (numberCount <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным числом");
        }

        // Для малых значений используем фиксированные границы
        if (numberCount <= 6) {
            switch (numberCount) {
                case 1: return 2;
                case 2: return 3;
                case 3: return 5;
                case 4: return 7;
                case 5: return 11;
                case 6: return 13;
            }
        }

        // Для больших значений используем формулу n * (ln(n) + ln(ln(n)))
        double n = numberCount;
        double upperBoundEstimate = n * (Math.log(n) + Math.log(Math.log(n)));

        // Добавляем запас 20% для гарантии
        int upperBound = (int) Math.ceil(upperBoundEstimate * 1.2);

        // Гарантируем, что верхняя граница не меньше минимального значения
        return Math.max(upperBound, 20);
    }

    /**
     * Метод для применения решета Эратосфена
     * @param upperBound верхняя граница поиска
     * @return массив отметок простых чисел
     */
    private static boolean[] applySieveOfEratosthenes(int upperBound) {
        boolean[] isPrime = new boolean[upperBound + 1];

        for (int i = 2; i <= upperBound; i++) {
            isPrime[i] = true;
        }

        for (int p = 2; p * p <= upperBound; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= upperBound; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        return isPrime;
    }
    /**
     * Метод для извлечения первых N простых чисел
     * @param isPrime массив отметок простых чисел
     * @param numberCount количество простых чисел
     * @return массив простых чисел
     */
    private static int[] extractFirstNPrimeNumbers(boolean[] isPrime, int numberCount) {
        int[] primeNumbers = new int[numberCount];
        int count = 0;
        int currentNumber = 2;

        while (count < numberCount && currentNumber < isPrime.length) {
            if (isPrime[currentNumber]) {
                primeNumbers[count] = currentNumber;
                count++;
            }
            currentNumber++;
        }

        // Если не нашли достаточно простых чисел, повторяем с большей верхней границей
        if (count < numberCount) {
            int newUpperBound = isPrime.length * 2;
            boolean[] newIsPrime = applySieveOfEratosthenes(newUpperBound);
            return extractFirstNPrimeNumbers(newIsPrime, numberCount);
        }

        return primeNumbers;
    }
}

/**
 * Класс для вывода массивов в консоль
 */
class ArrayPrinter {

    /**
     * Метод для вывода массива в консоль
     * @param message сообщение перед выводом
     * @param array массив для вывода
     */
    public static void printArray(String message, int[] array) {
        System.out.println(message);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + (i < array.length - 1 ? " " : "\n"));
        }
    }

    /**
     * Перегруженный метод для вывода массива с форматированием
     * @param message сообщение перед выводом
     * @param array массив для вывода
     * @param elementsPerLine количество элементов в строке
     */
    public static void printArray(String message, int[] array, int elementsPerLine) {
        System.out.println(message);
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%6d", array[i]);
            if ((i + 1) % elementsPerLine == 0 || i == array.length - 1) {
                System.out.println();
            }
        }
    }

    /**
     * Метод для вывода массива boolean в консоль
     * @param message сообщение перед выводом
     * @param array массив для вывода
     * @param elementsPerLine количество элементов в строке
     */
    public static void printArray(String message, boolean[] array, int elementsPerLine) {
        System.out.println(message);
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%6s", array[i] ? "T" : "F");
            if ((i + 1) % elementsPerLine == 0 || i == array.length - 1) {
                System.out.println();
            }
        }
    }
}