package Tasks;

import java.util.Scanner;

import static Array.ArrayFunc.*;

public class Multiplication {
    public static void main(String[] args) {
        int[] array1 = arrayInput();
        int[] array2 = arrayInput();
        if(!isPositive(array1) || !isPositive(array2)){
            throw new IllegalArgumentException("Числа должны быть положительные");
        }
        if(!isDigit(array1) || !isDigit(array2)){
            throw new IllegalArgumentException("Должны быть цифры");
        }
        int resoultNumber = arrayToNumber(array1) * arrayToNumber(array2);
        System.out.println("Результат умножения равен");
        arrayOutput(numberToArray(resoultNumber));
        System.out.println();


    }
    private static int arrayToNumber(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum*10 + array[i];
        }
        return sum;
    }
    private static int[] numberToArray(int number) {
        String strNumber = Integer.toString(number);
        int[] array = new int[strNumber.length()];
        for (int i = 0; i < array.length; i++) {
            array[i] = strNumber.charAt(i) - '0';
        }
        return array;
    }
}
