package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String numberString = Console.readLine();

        boolean isSimplePattern = validInput(numberString);
        String delim = ",|:";
        if (!isSimplePattern) {
            delim = delim + "|" + Pattern.quote(String.valueOf(numberString.charAt(2)));
            validDelim(numberString, delim);
            numberString = numberString.split("\\\\n")[1];
            // 입력 문자열에서 앞부분의 커스텀구분자 부분을 제거하고 숫자 문자열만 남김.
        }

        int sum = 0;
        if (!numberString.isEmpty()) {
            String[] numbers = numberString.split(delim);
            sum = addNumberString(numbers);
        }

        System.out.printf("결과 : %d", sum);
    }

    private static boolean validInput(String input) {
        String simplePattern = "([0-9]+([:,][0-9]+)*)?";
        String customPattern = "//[^0-9]\\\\n[0-9]+([^0-9][0-9]+)*";
        if (input.matches(simplePattern)) {
            return true;
        }
        if (input.matches(customPattern)) {
            return false;
        }
        throw new IllegalArgumentException();
    }

    private static void validDelim(String input, String delim) {
        String patter = "//[^0-9]\\\\n[0-9]+((" + delim + ")[0-9]+)*";
        if (!input.matches(patter)) {
            throw new IllegalArgumentException();
        }
    }

    private static int addNumberString(String[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += Integer.parseInt(numbers[i]);
        }
        return sum;
    }
}
