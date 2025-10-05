//  1. Дан целочисленный массив. Верните число, частота встречи которого в
//  массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//  таких чисел несколько, вернуть наибольшее.

package com.iu3.tasks.b1;

import static com.iu3.utils.ArrayTools.*;

public class TaskB1 {
    public static void taskB1Example() {
        int[] array = fillArray();

        System.out.print(finder(array));
    }

    public static int finder(int[] arr) {
        int max = Integer.MIN_VALUE;

        for (int element : arr) {

            // считаем сколько раз встречается число в массиве
            int counter = 0;
            for (int value : arr) {
                if (value == element) counter++;
            }

            if (counter == element && element > max) {
                max = element;
            }
        }
        if (max == Integer.MIN_VALUE) return -1;
        return max;
    }
}
