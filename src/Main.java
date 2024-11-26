import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String input = scanner.nextLine();
        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("throws Exception // " + e.getMessage());
        }
    }


    public static String calc(String input) throws Exception {
        String[] dannye = input.split(" ");

        if (dannye.length != 3) {
            throw new Exception("Строка не является математической операцией");
        }



        if (dannye[0].length() > 4)
            throw new Exception("Неверно введено 1е римское число");
        if (dannye[2].length() > 4)
            throw new Exception("Неверно введено 2е римское число");

        if (dannye[0].contains("IIV")){
            throw new Exception("1е число не 3");
        }
        if (dannye[0].contains("IIIV")){
            throw new Exception("1е число не 2");
        }
        if (dannye[0].contains("IIX")){
            throw new Exception("1е число не 8");
        }
        if (dannye[0].contains("IIIX")){
            throw new Exception("1е число не 7");
        }
        if (dannye[2].contains("IIV")){
            throw new Exception("2е число не 3");
        }
        if (dannye[2].contains("IIIV")){
            throw new Exception("2е число не 2");
        }
        if (dannye[2].contains("IIX")){
            throw new Exception("2е число не 8");
        }
        if (dannye[2].contains("IIIX")){
            throw new Exception("2е число не 7");
        }
        if (dannye[0].contains("IIII")){
            throw new Exception("Введите правильно число 4");
        }
        if (dannye[2].contains("IIII")){
            throw new Exception("Введите правильно число 4");
        }

        boolean isRoman = isRoman(dannye[0]) && isRoman(dannye[2]);
        boolean isArabic = isArabic(dannye[0]) && isArabic(dannye[2]);

        if (!isRoman && !isArabic) {
            throw new Exception("Используются одновременно разные системы счисления");
        }

        int a;
        int b;
        if (isRoman) {
            a = RomanToArabic(dannye[0]);
            b = RomanToArabic(dannye[2]);
        } else {
            a = Integer.parseInt(dannye[0]);
            b = Integer.parseInt(dannye[2]);
        }

        if ((a < 1 || a > 10) || (b < 1 || b > 10)) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
        }

        int result;
        switch (dannye[1]) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Неверная операция");
        }

        if (isRoman) {
            if (result < 1) {
                throw new IllegalArgumentException("В римской системе нет отрицательных чисел и нуля");
            }
            return ArabicToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    private static boolean isRoman(String value) {
        return value.matches("[IVXLC]+");
    }

    private static boolean isArabic(String value) {
        return value.matches("[0-9]+");
    }

    private static int RomanToArabic(String roman) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);

        int result = 0;
        int prev = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int current = map.get(roman.charAt(i));
            if (current < prev) {
                result -= current;
            } else {
                result += current;
            }
            prev = current;
        }

        return result;
    }

    private static String ArabicToRoman(int num) {
        if (num < 1) {
            throw new IllegalArgumentException("Результат не может быть меньше 1");
        }

        String[] romanNumerals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder roman = new StringBuilder();

        int i = 0;
        while (num > 0) {
            if (num >= values[i]) {
                roman.append(romanNumerals[i]);
                num -= values[i];
            } else {
                i++;
            }
        }

        return roman.toString();
    }
}