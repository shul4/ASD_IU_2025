//  4. Дан массив целых чисел, представляющий двоичное число.
//  Пример, дан массив bi_arr = [1, 1, 0]. Этот массив в десятичной системе
//  выглядит так: arr = [1, 3, 6]. То есть:
//  • arr[0] = bi_arr[0] = 1! = 1"#,
//  • arr[1] = bi_arr[0] bi_arr[1] =11! = 3"#,
//  • arr[2] = bi_arr[0] bi_arr[1] bi_arr[2] =110! = 6"#
//  10
//  Так же дано целое положительное число – n. Вернуть массив Boolean, где
//  true – число делится на N, false – нет.
//  Пусть n = 6, тогда для предыдущего примера результат должен выглядеть
//  так: [false, false, true].

package com.iu3.tasks.b4;

import java.util.Arrays;
import java.util.Scanner;

import static com.iu3.utils.ArrayTools.fillArray;

public class TaskB4 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void taskB4Example() {
        int[] arr = fillArray();
        System.out.print("Введите делитель N: ");
        int n = scanner.nextInt();
        boolean[] result = checker(arr, n);
        System.out.println(Arrays.toString(result));
    }

    static boolean[] checker(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return new boolean[0];
        }

        boolean[] results = new boolean[arr.length];
        int c = 0;

        for (int i = 0; i < arr.length; i++) {
            c = c * 2 + arr[i];
            results[i] = (c % n == 0);
        }

        return results;
    }
}
