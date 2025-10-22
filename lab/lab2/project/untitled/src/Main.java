import BinarySearch.BinarySearch;
import Tasks.BinaryArray;
import Tasks.Multiplication;
import Tasks.SumTwoSmallest;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 0. Бинарный поиск. Введите ОТСОРТИРОВАННЫЙ массив");
        BinarySearch.main(args);

        System.out.println("--------------------------------------------------------------------");

        System.out.println("Задание B2. Умножение чисел в массиве. Вводите ПОЛОЖИТЕЛЬНЫЕ ЦИФРЫ ");
        Multiplication.main(args);

        System.out.println("--------------------------------------------------------------------");

        System.out.println("Задание B3. Сумма наименьших положительных. В массиве положительных чисел >=2. Длина >=5");
        SumTwoSmallest.main(args);

        System.out.println("--------------------------------------------------------------------");

        System.out.println("Задание B4. Деление массива 2->10 на N. Введите двоичный массив ");
        BinaryArray.main(args);


    }
}

