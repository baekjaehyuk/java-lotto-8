package lotto.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.controller.validation.WinningNumberValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumberValidatorTest {

    private final WinningNumberValidator winningNumberValidator = new WinningNumberValidator();

    @DisplayName("당첨 번호의 공백일 경우 예외가 발생한다.")
    @Test
    void validateInvalidSize() {
        assertThatThrownBy(() -> winningNumberValidator.validate(" "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("쉼표가 아닌 다른 기호로 구분되어 있을 경우 예외가 발생한다.")
    @Test
    void validateDelimiterNumbers() {
        assertThatThrownBy(() -> winningNumberValidator.validate("1, 2| 3, 4, 5, 5"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
