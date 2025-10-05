//  2. Пусть любое число – это массив его цифр слева направо. Пример, число
//  1234 – это массив [1,2,3,4].
//  Дан массив целых чисел. Реализовать умножение двух чисел.
//  Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//  Результат – число, представленное массивом.

package com.iu3.tasks.b2;

import java.util.Arrays;

import static com.iu3.utils.ArrayTools.fillArray;

public class TaskB2 {
    public static void taskB2Example() {
        int[] arr1 = fillArray();
        int[] arr2 = fillArray();
        int[] arr3 = longToArray((long) arrayToInt(arr1) * arrayToInt(arr2));
        System.out.println(Arrays.toString(arr3));
    }

    public static int[] longToArray(long n) {
        char[] chars = Long.toString(n).toCharArray();
        int[] result = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            result[i] = chars[i] - '0';
        }

        return result;
    }


    public static int arrayToInt(int[] arr) {
        int result = 0;

        for (int digit : arr) {
            result = result * 10 + digit;
        }

        return result;
    }
}
