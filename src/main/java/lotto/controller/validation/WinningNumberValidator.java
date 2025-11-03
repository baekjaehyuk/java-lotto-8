package lotto.controller.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.util.ErrorMessages;
import org.junit.platform.commons.util.StringUtils;

public class WinningNumberValidator {

    private static final String NUMBER_DELIMITER = ",";

    public List<Integer> validate(String input) {
        if(StringUtils.isBlank(input)) {
            throw new IllegalArgumentException();
        }
        return parseNumbers(input);
    }

    private List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(NUMBER_DELIMITER))
                    .map(String::strip)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NUMBER_INPUT.getMessage());
        }
    }
}
