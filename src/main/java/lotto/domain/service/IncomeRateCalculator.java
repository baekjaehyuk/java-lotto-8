package lotto.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lotto.domain.LottoResult;

public class IncomeRateCalculator {

    private static final BigDecimal PERCENTAGE_MULTIPLIER = BigDecimal.valueOf(100);
    private static final int DIVIDE_SCALE = 3;
    private static final int MULTIPLY_SCALE = 1;

    public BigDecimal calculate(LottoResult lottoResult, BigDecimal amount) {
        BigDecimal income = lottoResult.getResult().entrySet().stream()
                .map(entry -> {
                    BigDecimal prize = entry.getKey().getPrize();
                    BigDecimal count = BigDecimal.valueOf(entry.getValue());
                    return prize.multiply(count);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal ratio = income.divide(amount, DIVIDE_SCALE, RoundingMode.HALF_UP);
        return ratio.multiply(PERCENTAGE_MULTIPLIER).setScale(MULTIPLY_SCALE, RoundingMode.HALF_UP);
    }
}
