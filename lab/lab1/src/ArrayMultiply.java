package src;

/*
Пусть любое число – это массив его цифр слева направо.
Пример, число 1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
Результат – число, представленное массивом.
*/
import java.util.Scanner;
import java.util.Arrays;

public class ArrayMultiply {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        String firstString = scanner.next();
        System.out.print("Введите второе число: ");
        String secondString = scanner.next();

        int[] firstNumber = stringIntoArray(firstString);
        int[] secondNumber = stringIntoArray(secondString);

        int[] resultArray = multiplyArrays(firstNumber, secondNumber);

        printArray(firstNumber);
        System.out.print(" * ");
        printArray(secondNumber);
        System.out.print(" = ");
        printArray(resultArray);
    }

    private static int[] multiplyArrays(int[] num1, int[] num2) {
        int len1 = num1.length;
        int len2 = num2.length;
        int[] result = new int[len1 + len2];

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int product = num1[i] * num2[j];
                int sum = product + result[i + j + 1];

                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }

        int startIndex = 0;
        while (startIndex < result.length - 1 && result[startIndex] == 0) {
            startIndex++;
        }

        return Arrays.copyOfRange(result, startIndex, result.length);
    }

    private static int[] stringIntoArray(String number) {
        int n = number.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = number.charAt(i) - '0';
        }
        return arr;
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
}