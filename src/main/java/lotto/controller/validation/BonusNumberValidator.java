package lotto.controller.validation;

import java.util.List;
import java.util.Objects;
import lotto.util.ErrorMessages;
import lotto.util.LottoConstants;
import org.junit.platform.commons.util.StringUtils;

public class BonusNumberValidator {

    public int validate(String input, List<Integer> winningNumbers) {
        validateNotBlank(input);

        int bonusNumber = parseToInt(input);
        validateRange(bonusNumber);
        validateNotDuplicate(bonusNumber, winningNumbers);

        return bonusNumber;
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NUMBER_INPUT.getMessage());
        }
    }

    private void validateNotBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NUMBER_INPUT.getMessage());
        }
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < LottoConstants.MIN_LOTTO_NUMBER ||
                bonusNumber > LottoConstants.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    private void validateNotDuplicate(int bonusNumber, List<Integer> winningNumbers) {
        if (Objects.requireNonNull(winningNumbers).contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }
}

