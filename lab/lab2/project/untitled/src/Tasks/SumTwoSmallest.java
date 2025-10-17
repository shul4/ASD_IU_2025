package Tasks;
import static Array.ArrayFunc.arrayInput;

public class SumTwoSmallest {
    public static void main(String[] args) {
        int[] array = arrayInput();
        if(array.length < 5){
            throw new IllegalArgumentException("Длина массива не менее 5");
        }
        int[] resoult = twoSmallest(array);
        System.out.println("Сумма двух наименьших положительных равна " + (resoult[0] + resoult[1]));

    }
    private static int[] twoSmallest(int[] array) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if(array[i] > 0){
                if (array[i] < first) {
                    second = first;
                    first = array[i];
                }
                else if (array[i] < second) {
                    second = array[i];
                }
            }

        }
        if (first == Integer.MAX_VALUE || second == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("В массиве меньше 2 положительных чисел");
        }
        int[] result = new int[2];
        result[0] = first;
        result[1] = second;
        return result;
    }
}
