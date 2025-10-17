package com.testapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 Дано целое число. Реализуйте метод, который находит N первых простых
 чисел. Используйте алгоритм «Решето Эратосфена».
 */
public class PrimeNumbersFinder {

    private static final int UPPER_BOUND_MULTIPLIER = 20;

    /**
     * Основной метод класса
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            int numberCount = readNumberCountFromUser();
            int[] primeNumbers = findFirstNPrimeNumbers(numberCount);
            printPrimeNumbers("Первые " + numberCount + " простых чисел:", primeNumbers);

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
     * Метод для оценки верхней границы поиска
     * @param numberCount количество простых чисел
     * @return верхняя граница поиска
     */
    private static int estimateUpperBound(int numberCount) {
        return numberCount * UPPER_BOUND_MULTIPLIER;
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

        return primeNumbers;
    }

    /**
     * Метод для вывода простых чисел в консоль
     * @param message сообщение перед выводом
     * @param primeNumbers массив простых чисел
     */
    private static void printPrimeNumbers(String message, int[] primeNumbers) {
        System.out.println(message);
        for (int i = 0; i < primeNumbers.length; i++) {
            System.out.print(primeNumbers[i] + (i < primeNumbers.length - 1 ? " " : "\n"));
        }
    }
}