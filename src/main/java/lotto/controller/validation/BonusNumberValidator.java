package lotto.controller.validation;

import lotto.util.ErrorMessages;
import org.junit.platform.commons.util.StringUtils;

public class BonusNumberValidator {

    public int validate(String input) {
        if(StringUtils.isBlank(input)) {
            throw new IllegalArgumentException();
        }
        return parseNumber(input);
    }

    private int parseNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NUMBER_INPUT.getMessage());
        }
    }
}
