import java.util.Scanner;

public class Main {

    public static String calc(String input) {
        Converter converter = new Converter();
        String[] act = {"+", "-", "/", "*"};
        int indexAct = -1;
        for (int i = 0; i < act.length; i++) {
            if (input.contains(act[i])) {
                indexAct = i;
                break;
            }
        }
        if (indexAct == -1) {
            throw new RuntimeException("Несуществующая арифметическая операция!");
        }
        String[] numbers = input.split(" ");
        if (numbers.length == 3) {

            if (converter.isRoman(numbers[0]) == converter.isRoman(numbers[2])) {

                boolean isRoman = converter.isRoman(numbers[0]);
                int firstNum;
                int secondNum;
                if (isRoman) {
                    firstNum = converter.romanToInt(numbers[0]);
                    secondNum = converter.romanToInt(numbers[2]);
                } else {
                    firstNum = Integer.parseInt(numbers[0]);
                    secondNum = Integer.parseInt(numbers[2]);
                }
                if ((firstNum > 0) && (firstNum <= 10) && (secondNum > 0) && (secondNum <= 10)) {


                    int res = 0;
                    switch (act[indexAct]) {
                        case "+" -> res = firstNum + secondNum;
                        case "-" -> res = firstNum - secondNum;
                        case "/" -> res = firstNum / secondNum;
                        case "*" -> res = firstNum * secondNum;
                    }
                    if (isRoman) {
                        try {
                            return (converter.intToRoman(res));
                        } catch (NullPointerException e) {
                            throw new RuntimeException("Результатом работы с римскими числами могут быть только положительные числа!");
                        }
                    } else {
                        return String.valueOf(res);
                    }
                } else {
                    throw new RuntimeException("Числа должны быть в диапазоне от 1 до 10!");
                }
            } else {
                throw new RuntimeException("Числа должны быть в одном формате!");
            }
        } else {
            throw new RuntimeException("Должно быть всего два операнда!");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        System.out.println(calc(input.nextLine()));
    }
}