package com.testapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 Реализуйте перевод из римских чисел в арабские.
 */
public class RomanNumberConverter {

    /**
     * Основной метод класса
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            String romanNumber = readRomanNumberFromUser();
            int arabicNumber = convertRomanToArabic(romanNumber);
            printConversionResult(romanNumber, arabicNumber);

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }

    /**
     * Метод для чтения римского числа с консоли
     * @return введенное римское число
     * @throws IOException если произошла ошибка ввода
     */
    private static String readRomanNumberFromUser() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите римское число: ");
        return reader.readLine().trim().toUpperCase();
    }

    /**
     * Метод для конвертации римского числа в арабское
     * @param romanNumber римское число
     * @return арабское число
     */
    private static int convertRomanToArabic(String romanNumber) {
        int result = 0;
        int previousValue = 0;

        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            int currentValue = getNumericValueOfRomanChar(romanNumber.charAt(i));

            if (currentValue < previousValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            previousValue = currentValue;
        }

        return result;
    }

    /**
     * Метод для получения числового значения римского символа
     * @param romanChar римский символ
     * @return числовое значение
     */
    private static int getNumericValueOfRomanChar(char romanChar) {
        switch (romanChar) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    /**
     * Метод для вывода результата конвертации
     * @param romanNumber римское число
     * @param arabicNumber арабское число
     */
    private static void printConversionResult(String romanNumber, int arabicNumber) {
        System.out.println("Римское число: " + romanNumber);
        System.out.println("Арабское число: " + arabicNumber);
    }
}