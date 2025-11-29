import java.util.Scanner;

public class Task0 {
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите количество элементов массива: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Введите отсортированный массив:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Введите число для поиска: ");
        int target = sc.nextInt();

        int indexIter = binarySearchIterative(arr, target);
        int indexRec = binarySearchRecursive(arr, target, 0, arr.length - 1);

        System.out.println("Итеративный поиск: " + indexIter);
        System.out.println("Рекурсивный поиск: " + indexRec);
    }
}
