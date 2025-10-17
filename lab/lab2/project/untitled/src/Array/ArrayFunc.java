package Array;
import java.util.Scanner;


public class ArrayFunc {
    public static int[] arrayInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите длину массива");
        int arrayLength = input.nextInt();
        int[] array = new int[arrayLength];
        System.out.println("Введите " + arrayLength + " чисел");
        for (int i = 0; i < arrayLength; i++) {
            array[i] = input.nextInt();
        }
        return array;
    }
    public static void arrayOutput(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
    public static void arrayOutput(boolean[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
    public static boolean isPositive(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < 0) {
                return false;
            }
        }
        return true;
    }
    public static boolean isDigit(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > 9 || array[i] < 0) {
                return false;
            }
        }
        return true;
    }
}
