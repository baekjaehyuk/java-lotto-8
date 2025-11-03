package lotto.validation;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.controller.validation.AmountValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountValidatorTest {

    private final AmountValidator amountValidator = new AmountValidator();

    @DisplayName("구입 금액이 공백일 경우 예외가 발생한다.")
    @Test
    void validateBlankAmount() {
        assertThatThrownBy(() -> amountValidator.validate(" "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 null일 경우 예외가 발생한다.")
    @Test
    void validateNullAmount() {
        assertThatCode(() -> amountValidator.validate(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
