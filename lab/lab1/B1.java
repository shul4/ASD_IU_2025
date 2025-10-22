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
        int[] array = Binarysearch.readArray();
        System.out.println("Решение с HashMap: " + findNumberWithMatchingFrequency(array));
        System.out.println("Решение без HashMap: " + findNumberWithMatchingFrequencyAlternative(array));
    }

    // Основное решение с использованием HashMap
    private static int findNumberWithMatchingFrequency(int[] array) {
        if (isEmptyArray(array)) {
            return -1;
        }

        Map<Integer, Integer> frequencyMap = buildFrequencyMap(array);
        return findMaxMatchingNumber(frequencyMap);
    }

    // Альтернативное решение без использования HashMap
    private static int findNumberWithMatchingFrequencyAlternative(int[] array) {
        if (isEmptyArray(array)) {
            return -1;
        }

        Boolean[] processedFlags = createProcessedFlagsArray(array.length);
        processArrayElements(array, processedFlags);
        return findMaxValidNumber(array, processedFlags);
    }

    // Вспомогательные методы для основного решения
    private static boolean isEmptyArray(int[] array) {
        return array.length == 0;
    }

    private static Map<Integer, Integer> buildFrequencyMap(int[] array) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int number : array) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }
        return frequencyMap;
    }

    private static int findMaxMatchingNumber(Map<Integer, Integer> frequencyMap) {
        int maxMatchingNumber = -1;

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int number = entry.getKey();
            int frequency = entry.getValue();

            if (isNumberMatchingFrequency(number, frequency)) {
                maxMatchingNumber = updateMaxNumber(maxMatchingNumber, number);
            }
        }

        return maxMatchingNumber;
    }

    private static boolean isNumberMatchingFrequency(int number, int frequency) {
        return number == frequency;
    }

    private static int updateMaxNumber(int currentMax, int candidate) {
        return Math.max(currentMax, candidate);
    }

    // Вспомогательные методы для альтернативного решения
    private static Boolean[] createProcessedFlagsArray(int length) {
        return new Boolean[length];
    }

    private static void processArrayElements(int[] array, Boolean[] processedFlags) {
        for (int i = 0; i < array.length; i++) {
            if (isAlreadyProcessed(processedFlags[i])) {
                continue;
            }
            processElement(array, processedFlags, i);
        }
    }

    private static boolean isAlreadyProcessed(Boolean status) {
        return status != null;
    }

    private static void processElement(int[] array, Boolean[] processedFlags, int currentIndex) {
        int targetNumber = array[currentIndex];
        markAsProcessed(processedFlags, currentIndex);

        int frequency = countFrequency(array, processedFlags, currentIndex, targetNumber);
        markIfValid(processedFlags, currentIndex, frequency, targetNumber);
    }

    private static void markAsProcessed(Boolean[] processedFlags, int index) {
        processedFlags[index] = false;
    }

    private static int countFrequency(int[] array, Boolean[] processedFlags, int startIndex, int targetNumber) {
        int frequency = 1;

        for (int j = startIndex + 1; j < array.length; j++) {
            if (array[j] == targetNumber) {
                markAsProcessed(processedFlags, j);
                frequency++;
            }
        }

        return frequency;
    }

    private static void markIfValid(Boolean[] processedFlags, int index, int frequency, int number) {
        if (frequency == number) {
            processedFlags[index] = true;
        }
    }

    private static int findMaxValidNumber(int[] array, Boolean[] processedFlags) {
        int maxValidNumber = -1;

        for (int i = 0; i < array.length; i++) {
            if (isValidNumber(processedFlags[i])) {
                maxValidNumber = updateMaxNumber(maxValidNumber, array[i]);
            }
        }

        return maxValidNumber;
    }

    private static boolean isValidNumber(Boolean status) {
        return Boolean.TRUE.equals(status);
    }
}