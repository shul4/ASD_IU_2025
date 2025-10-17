package BinarySearch;

import java.util.Scanner;

import static Array.ArrayFunc.arrayInput;
import static Array.ArrayFunc.arrayOutput;
import static Array.ArrayFunc.isSorted;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = arrayInput();
        if (!isSorted(array)) {
            throw new RuntimeException("Массив не отсортирован");
        }
        Scanner input = new Scanner(System.in);
        System.out.println("ВВедите искомое число");
        int key = input.nextInt();
        System.out.println("Индекс искомого числа итерационным методом равен " + binarySearchIterative(array, key));
        System.out.println("Индекс искомого числа рекурсивным методом равен " + binarySearchRecursive(array, key));
    }

    public static int binarySearchIterative(int[] array, int key){
        int left = 0;
        int right = array.length-1;
        while(left<=right){
            int mid = left+(right-left)/2;
            if(key == array[mid]){
                return mid;
            }
            else if(key < array[mid]){
                right = mid-1;
            }
            else if(key > array[mid]){
                left = mid+1;
            }
        }
        return -1;
    }
    private static int binarySearchRecursive(int[] array, int key, int left, int right){
        if(left>right){
            return -1;
        }
        int mid = left+(right-left)/2;
        if(key == array[mid]){
            return mid;
        }
        else if(key < array[mid]){
            return binarySearchRecursive(array, key, left, mid-1);
        }
        return binarySearchRecursive(array, key, mid+1, right);
    }
    public static int binarySearchRecursive(int[] array, int key){
        return  binarySearchRecursive(array, key, 0, array.length-1);
    }
}

