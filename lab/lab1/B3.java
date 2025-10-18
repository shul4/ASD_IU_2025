package lab1;
import java.util.Scanner;
import java.util.Arrays;

public class B3 {
    public static void main(String[] args) {
        int[] array = Binarysearch.readArray();

        if (array.length < 5) {
            System.out.println("Массив должен содержать минимум 5 элементов");
            return;
        }

        int result = calculateSumOfTwoSmallestPositiveNumbers(array);

        if (result == -1) {
            System.out.println("Given array contains less than 2 positive numbers");
        } else {
            System.out.printf("Minimum sum is %d", result);
        }
    }

    private static int calculateSumOfTwoSmallestPositiveNumbers(int[] array) {
        int smallestPositive = Integer.MAX_VALUE;
        int secondSmallestPositive = Integer.MAX_VALUE;

        for (int number : array) {
            if (isPositiveNumber(number)) {
                if (number < smallestPositive) {
                    secondSmallestPositive = smallestPositive;
                    smallestPositive = number;
                } else if (number < secondSmallestPositive) {
                    secondSmallestPositive = number;
                }
            }
        }

        return getSumIfBothFound(smallestPositive, secondSmallestPositive);
    }

    private static boolean isPositiveNumber(int number) {
        return number > 0;
    }

    private static int getSumIfBothFound(int first, int second) {
        if (first == Integer.MAX_VALUE || second == Integer.MAX_VALUE) {
            return -1;
        }
        return first + second;
    }
}