import java.util.Scanner;

public class Task1GroupA {
    public static void main(String[] args) {
        System.out.println("Введите десятичное число: ");
        int inputDecimalNumber = inputNumber();
        System.out.println("Введите новую систему счисления(от 2 до 9): ");
        int inputNewBase = inputNumber();
        int convertedNumber = convertToBase(inputDecimalNumber, inputNewBase);
        if (convertedNumber != -1) {
            System.out.println("Число в указанной системе счисления: " + convertedNumber);
        }
    }

    public static int inputNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int convertToBase(int decimalNumber, int newBase) {
        if (newBase < 2 || newBase > 9) {
            System.out.println("Ошибка: основание системы счисления должно быть от 2 до 9");
            return -1; // Возвращаем -1 как индикатор ошибки
        }
        if (decimalNumber == 0) {
            return 0;
        }
        int result = 0;
        int multiplier = 1;
        while (decimalNumber > 0) {
            int remainder = decimalNumber % newBase;
            result = result + remainder * multiplier;
            decimalNumber = decimalNumber / newBase;
            multiplier *= 10;
        }
        return result;
    }
}

/*
1 Реализуйте метод, входными данными которого являются два числа N и M,
где N – число в десятичной системе исчисления, а M – число в диапазоне от
2 до 9, основание системы исчисления, в которое надо перевести исходное число.
Метод должен возвращать строку с преобразованным значением.
 */
