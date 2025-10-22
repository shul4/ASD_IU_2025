public class Task1GroupB {

    public static void main(String[] args) {
            int[] array = Utils.inputArray();
            System.out.println("Число,равное количеству вхождений в массив: ");
            System.out.println(findFrequencyNumber(array));
    }

    public static int findFrequencyNumber(int[] array) {
        if (array.length == 0) {
            return -1;
        }
        int maxNumber = -1;    
        for (int i = 0; i < array.length; i++) {
            int currentNumber = array[i];
            int frequency = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == currentNumber) {
                    frequency++;
                }
            }
            if (currentNumber == frequency) {
                if (currentNumber > maxNumber) {
                    maxNumber = currentNumber;
                }
            }
        }
        return maxNumber;
    }
}
    /*
    1 Дан целочисленный массив. Верните число, частота встречи которого в
    массиве равна его значению. Если таких чисел нет, вернуть «-1».
    Если таких чисел несколько, вернуть наибольшее.
     */
