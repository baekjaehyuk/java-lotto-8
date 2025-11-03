package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import lotto.util.ErrorMessages;
import lotto.util.LottoConstants;

public class WinningLotto {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateWinningNumbers(winningNumbers);
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateWinningNumbers(List<Integer> winningNumbers) {
        if (winningNumbers.size() != LottoConstants.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }
        if (new HashSet<>(winningNumbers).size() != LottoConstants.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
        if (winningNumbers.stream().anyMatch(n -> n < LottoConstants.MIN_LOTTO_NUMBER || n > LottoConstants.MAX_LOTTO_NUMBER)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    private void validateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (bonusNumber < LottoConstants.MIN_LOTTO_NUMBER || bonusNumber > LottoConstants.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }
        if (Objects.requireNonNull(winningNumbers).contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }
}
