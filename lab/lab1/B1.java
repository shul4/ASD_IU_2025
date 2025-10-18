package lab1;

import java.util.HashMap;
import java.util.Map;

public class B1 {

    /*
     * Дан целочисленный массив. Верните число, частота встречи которого в
     * массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
     * таких чисел несколько, вернуть наибольшее.
     */

    public static void main(String[] args) {
        int[] array = binarysearch.readArray();
        System.out.println("Решение с HashMap: " + getNumberMatchingCount(array));
        System.out.println("Решение без HashMap: " + getNumberMatchingCountAlt(array));
    }

    private static int getNumberMatchingCount(int[] array) {
        if (array.length == 0) return -1;

        HashMap<Integer, Integer> numbers = new HashMap<>();
        for (int n : array) {
            numbers.put(n, numbers.getOrDefault(n, 0) + 1);
        }

        int answer = -1;
        for (Map.Entry<Integer, Integer> n : numbers.entrySet()) {
            int key = n.getKey();
            int value = n.getValue();
            if (key == value && key > answer) answer = key;
        }

        return answer;
    }

    // Менее эффективная функция без использования HashMap
    private static int getNumberMatchingCountAlt(int[] array) {
        if (array.length == 0) return -1;

        // null - непосчитанные значения
        // false - посчитанные значения
        // true - первое вхождение значения, удовлетворяющего условию
        Boolean[] status = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            if (status[i] != null) continue;

            int number = array[i];
            status[i] = false;
            int counter = 1;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] == number) {
                    status[j] = false;
                    counter++;
                }
            }

            if (counter == number) status[i] = true;
        }

        int answer = -1;
        for (int i = 0; i < array.length; i++) {
            if (status[i] == true && array[i] > answer) answer = array[i];
        }

        return answer;
    }
}