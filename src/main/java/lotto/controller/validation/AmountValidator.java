package lotto.controller.validation;

import java.math.BigDecimal;
import lotto.util.ErrorMessages;
import org.junit.platform.commons.util.StringUtils;

public class AmountValidator {

    public BigDecimal validate(String input) {
        if(StringUtils.isBlank(input)) {
            throw new IllegalArgumentException();
        }
        return toPositiveBigDecimal(input);
    }

    private BigDecimal toPositiveBigDecimal(String input) {
        try {
            return BigDecimal.valueOf(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NUMBER_INPUT.getMessage());
        }
    }
}
