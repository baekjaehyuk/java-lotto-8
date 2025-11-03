package lotto.controller.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lotto.util.ErrorMessages;
import lotto.util.LottoConstants;
import org.junit.platform.commons.util.StringUtils;

public class WinningNumberValidator {

    private static final String NUMBER_DELIMITER = ",";

    public List<Integer> validate(String input) {
        validateNotBlank(input);
        List<Integer> numbers = parseNumbers(input);
        validateNumbers(numbers);
        return numbers;
    }

    private void validateNotBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NUMBER_INPUT.getMessage());
        }
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

    public void validateNumbers(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }
        if (new HashSet<>(numbers).size() != LottoConstants.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
        if (numbers.stream().anyMatch(n -> n < LottoConstants.MIN_LOTTO_NUMBER || n > LottoConstants.MAX_LOTTO_NUMBER)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }
    }
}
