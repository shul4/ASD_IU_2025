package lab1;

public class B4 {

    /*
     * Дан массив целых чисел, представляющий двоичное число.
     * <p>
     * Пример, дан массив bi_arr = [1, 1, 0]. Этот массив в десятичной системе
     * выглядит так: arr = [1, 3, 6]. То есть:
     * • arr[0] = bi_arr[0] = 1 = 1,
     * • arr[1] = bi_arr[0] bi_arr[1] = 11 = 3,
     * • arr[2] = bi_arr[0] bi_arr[1] bi_arr[2] = 110 = 6
     * <p>
     * Так же дано целое положительное число – n. Вернуть массив Boolean, где
     * true – число делится на N, false – нет.
     * <p>
     * Пусть n = 6, тогда для предыдущего примера результат должен выглядеть
     * так: [false, false, true].
     * <p>
     * Примечание. Делитель тоже необходимо ввести с клавиатуры.
     */

    public static void main(String[] args) {
        int[] binaryArray = binarysearch.readArray();
        System.out.println();
        int divider = binarysearch.readIntSafe("Введите делитель: ", false);
        System.out.println(writeBooleanArray(divides(toDecimal(binaryArray), divider)));
    }

    private static int[] toDecimal(int[] binaryArray) {
        int[] array = new int[binaryArray.length];
        if (array.length == 0) return array;

        array[0] = binaryArray[0];
        for (int i = 1; i < array.length; i++) {
            array[i] = binaryArray[i] + array[i - 1] * 2;
        }

        return array;
    }

    private static boolean[] divides(int[] array, int divider) {
        boolean[] answer = new boolean[array.length];

        for (int i = 0; i < array.length; i++) {
            answer[i] = array[i] % divider == 0;
        }

        return answer;
    }

    private static String writeBooleanArray(boolean[] array) {
        if (array.length == 0) return "[]";

        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < array.length - 1; i++) {
            s.append(array[i]).append(", ");
        }
        s.append(array[array.length - 1]).append(']');

        return s.toString();
    }
}