import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество элементов массива (>=5): ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                if (arr[i] < min1) {
                    min2 = min1;
                    min1 = arr[i];
                } else if (arr[i] < min2) {
                    min2 = arr[i];
                }
            }
        }

        if (min1 == Integer.MAX_VALUE || min2 == Integer.MAX_VALUE) {
            System.out.println("Недостаточно положительных чисел.");
        } else {
            System.out.println("Сумма двух наименьших положительных чисел: " + (min1 + min2));
        }
    }
}
