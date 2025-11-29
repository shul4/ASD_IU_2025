import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ввод первого числа (как массива цифр)
        System.out.print("Введите количество цифр первого числа: ");
        int n1 = sc.nextInt();
        int[] num1 = new int[n1];
        System.out.println("Введите цифры первого числа:");
        for (int i = 0; i < n1; i++) {
            num1[i] = sc.nextInt();
        }

        // ввод второго числа (как массива цифр)
        System.out.print("Введите количество цифр второго числа: ");
        int n2 = sc.nextInt();
        int[] num2 = new int[n2];
        System.out.println("Введите цифры второго числа:");
        for (int i = 0; i < n2; i++) {
            num2[i] = sc.nextInt();
        }

        // преобразуем массивы в числа
        int a = 0;
        for (int i = 0; i < n1; i++) {
            a = a * 10 + num1[i];
        }

        int b = 0;
        for (int i = 0; i < n2; i++) {
            b = b * 10 + num2[i];
        }

        int product = a * b;

        // переводим результат обратно в массив цифр
        int temp = product;
        int len = (temp == 0) ? 1 : 0;
        while (temp > 0) {
            len++;
            temp /= 10;
        }

        int[] result = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = product % 10;
            product /= 10;
        }

        System.out.print("Результат: ");
        for (int digit : result) {
            System.out.print(digit + " ");
        }
    }
}
