package lotto.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.controller.validation.BonusNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberValidatorTest {

    private BonusNumberValidator bonusNumberValidator;
    private List<Integer> winningNumbers;

    @BeforeEach
    void setUp() {
        bonusNumberValidator = new BonusNumberValidator();
        winningNumbers = List.of(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("유효한 보너스 번호를 입력하면 숫자를 반환한다.")
    @Test
    void validateAndReturnBonusNumber() {
        // given
        String bonusNumberInput = "7";

        // when
        int bonusNumber = bonusNumberValidator.validate(bonusNumberInput, winningNumbers);

        // then
        assertThat(bonusNumber).isEqualTo(7);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void throwExceptionForDuplicateBonusNumber() {
        // given
        String bonusNumberInput = "6";

        // when & then
        assertThatThrownBy(() -> bonusNumberValidator.validate(bonusNumberInput, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 범위를 벗어나면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "46"})
    void throwExceptionForOutOfRangeBonusNumber(String bonusNumberInput) {
        // when & then
        assertThatThrownBy(() -> bonusNumberValidator.validate(bonusNumberInput, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 숫자가 아니거나 비어있으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", " ", ""})
    void throwExceptionForInvalidFormatBonusNumber(String bonusNumberInput) {
        // when & then
        assertThatThrownBy(() -> bonusNumberValidator.validate(bonusNumberInput, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
