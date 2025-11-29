import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество элементов массива: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int result = -1;

        for (int i = 0; i < n; i++) {
            int value = arr[i];
            int count = 0;

            // считаем сколько раз встречается arr[i]
            for (int j = 0; j < n; j++) {
                if (arr[j] == value) {
                    count++;
                }
            }

            // проверка условия
            if (count == value && value > result) {
                result = value;
            }
        }

        System.out.println("Результат: " + result);
    }
}

