package lotto.controller.validation;

import java.math.BigDecimal;
import lotto.util.ErrorMessages;
import org.junit.platform.commons.util.StringUtils;

public class AmountValidator {

    private static final BigDecimal MINIMUM_AMOUNT = BigDecimal.valueOf(1000);
    private static final BigDecimal AMOUNT_UNIT = BigDecimal.valueOf(1000);

    public BigDecimal validate(String input) {
        validateNotBlank(input);
        return validateAndToPositiveBigDecimal(input);
    }

    private void validateNotBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NUMBER_INPUT.getMessage());
        }
    }

    private BigDecimal validateAndToPositiveBigDecimal(String input) {
        try {
            BigDecimal amount = BigDecimal.valueOf(Integer.parseInt(input));
            if (amount.compareTo(MINIMUM_AMOUNT) < 0 || amount.remainder(AMOUNT_UNIT).compareTo(BigDecimal.ZERO) != 0) {
                throw new IllegalArgumentException(ErrorMessages.INVALID_PURCHASE_LOTTO.getMessage());
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NUMBER_INPUT.getMessage());
        }
    }
}
