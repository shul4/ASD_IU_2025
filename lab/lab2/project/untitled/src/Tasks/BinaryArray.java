package Tasks;

import java.util.Scanner;
import static Array.ArrayFunc.arrayOutput;
import static Array.ArrayFunc.arrayInput;

public class BinaryArray {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите число N");
        int number = input.nextInt();
        int[] array = arrayInput();
        for (int i = 0; i < array.length; i++) {
            if(array[i] > 1){
                throw new IllegalArgumentException("Массив должен быть двоичным");
            }
        }

        arrayOutput(dividingN(array, number));

    }
    private static boolean[] dividingN (int[] array, int number) {
        boolean[] decimals = new boolean[array.length];
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum * 2 + array[i];
            if(sum % 2 == 0){
                decimals[i] = true;
            }
            else {
                decimals[i] = false;
            }

        }
        return decimals;
    }
}
