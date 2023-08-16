package academy.kata.calculator;

public class Main {

    public static String calc(String input) {
        String[] args = input.split(" ");
        int value1, value2;
        boolean is_romes = false;


        if (args.length != 3) {
            throw new IllegalArgumentException("Requires two operands and one operator (+, -, /, *). Example: 1 + 2");
        }

        try {
            value1 = Integer.parseInt(args[0]);
            value2 = Integer.parseInt(args[2]);
        } catch (NumberFormatException error) {
            value1 = romes_to_arabic(args[0]);
            value2 = romes_to_arabic(args[2]);
            is_romes = true;
        }

        if (value1 < 1 || value2 < 1 || value1 > 10 || value2 > 10) {
            throw new IllegalArgumentException("Arguments must be: 1..10");
        }


        int math_result = switch (args[1]) {
            case "+" -> value1 + value2;
            case "-" -> value1 - value2;
            case "*" -> value1 * value2;
            case "/" -> value1 / value2;
            default -> throw new IllegalArgumentException();
        };

        if (is_romes) {
            if (math_result > 0) {
                return arabic_to_romes(math_result);
            } else {
                throw new IllegalStateException("Roman numerals cannot be less than one: " + math_result);
            }
        }
        return Integer.toString(math_result);
    }

    private static Integer romes_to_arabic(String ar_number) {
        return switch (ar_number) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new IllegalStateException("Unexpected value: " + ar_number);
        };

    }
    private static String arabic_to_romes(Integer rom_number) {

        String[] romes = {"I", "II", "III", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        Integer[] arabic = {1, 2, 3, 4, 5, 9, 10, 40, 50, 90, 100};

        int number = rom_number;
        StringBuilder result = new StringBuilder();

        while (number > 0) {
            for (int i = 10; i >= 0; i--) {
                if (arabic[i] <= number) {
                    number -= arabic[i];
                    result.append(romes[i]);
                    ++i;
                }
            }
        }
        return result.toString();
    }
}
