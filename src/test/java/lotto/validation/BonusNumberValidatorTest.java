package lotto.validation;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.controller.validation.BonusNumberValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberValidatorTest {

    private final BonusNumberValidator bonusNumberValidator = new BonusNumberValidator();

    @DisplayName("유효한 보너스 번호는 예외가 발생하지 않는다.")
    @Test
    void validateBonusNumber() {
        // given
        String bonusNumber = "5";

        // when & then
        assertThatCode(() -> bonusNumberValidator.validate(bonusNumber))
                .doesNotThrowAnyException();
    }
}
