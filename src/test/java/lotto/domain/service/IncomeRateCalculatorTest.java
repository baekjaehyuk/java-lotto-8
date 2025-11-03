package lotto.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IncomeRateCalculatorTest {

    private final IncomeRateCalculator incomeRateCalculator = new IncomeRateCalculator();

    @DisplayName("당첨 통계와 구입 금액을 바탕으로 수익률을 계산한다.")
    @Test
    void calculateIncomeRate() {
        // given
        Map<LottoRank, Integer> resultMap = new EnumMap<>(LottoRank.class);
        resultMap.put(LottoRank.FOURTH, 1);
        LottoResult lottoResult = new LottoResult(resultMap);
        BigDecimal amount = BigDecimal.valueOf(10000);

        // when
        BigDecimal incomeRate = incomeRateCalculator.calculate(lottoResult, amount);

        // then
        assertThat(incomeRate).isEqualTo(BigDecimal.valueOf(500.0));
    }
}
