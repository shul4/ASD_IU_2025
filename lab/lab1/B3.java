// Группа Б. Номер 3
// Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
// число, которое является суммой двух наименьших положительных чисел.

package lab1;
import java.util.Scanner;

public class B3 {
    public static void main() {
        Scanner scanner = new Scanner(System.in);

        int[] array = binarysearch.readArray();

        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                if ((i + 1) <= array.length - 1) {
                    result = array[i] + array[i+1];
                }
            }
        }
        if (result == -1) {
            System.out.println("Given array contains less than 2 positive numbers");
        } else {
            System.out.printf("Minimum sum is %d", result);
        }
    }
}